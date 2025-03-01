package riscvSimulator;

import java.util.ArrayList;

import riscvSimulator.values.RiscVValue;
import riscvSimulator.values.RiscVValue12;
import riscvSimulator.values.RiscVValue20;
import riscvSimulator.values.RiscVValue32;

public class InstructionRiscV {

	private long pc;
	private RiscVOpCode op;
	private RiscVType type; 
	private RiscVFunc func;
	

	
	private int imm12; //for I-TYPE S-TYPE
	private int imm20; //for J-TYPE
	private int rd;
	private int r1;
	private int r2;
	
	private RiscVValue32 valueA;
	private RiscVValue32 valueB;
	private RiscVValue32 aluResult;
	private RiscVValue valueToStore;
	private RiscVValue32 valueLoaded;
	
	public boolean isDecodeDone;
	public boolean isExecuteDone;
	public boolean isMemoryDone; 
	public boolean isWritebackDone;
	
	private long cycleNumber;
	

	
	public InstructionRiscV(long binaryInstruction, long pc, long startCycle){
		this.pc = pc;
		int opBinary = (int) (binaryInstruction & 0x7F);						// get the first 7 bits of the instruction by doing a bitwise and operation
		this.setCycleNumber(startCycle);

		for (RiscVOpCode opcode : RiscVOpCode.values()){						// find the corresponding OpCode
			if (opcode.getOpCode() == opBinary){
				this.op = opcode;
				break;
			}
			
			this.op = RiscVOpCode.unknown;
		}

		
		this.isDecodeDone = false;
		this.isExecuteDone = false; 
		this.isMemoryDone = false;
		this.isWritebackDone = false;
		
		
		
		
		switch (op) {
		
		case branch: {// CASE OF B-TYPE
			this.type = RiscVType.BTYPE;
			int func3Binary = (int) ((binaryInstruction >> 12) & 0x7); //get the func3 binary value
			for (RiscVFunc func3 : RiscVFunc.values()) {
				if (func3.getFunc3Code() == func3Binary  && func3.getOpCode() == op) {
					this.func = func3;
					break;
				}
			}
			
			int firstPart = (int) ((binaryInstruction >> 8) & 0xf);
			int secondPart = (int) ((binaryInstruction >> 25) & 0x3f);
			int thirdPart = (int) ((binaryInstruction >> 7) & 0x1);
			int fourthPart =(int) ((binaryInstruction >> 31) & 0x1);
			
			/*int tmp1 = ( (firstPart & 0xf) << 6) | secondPart & 0x3f;
			int tmp2 = ( (thirdPart & 0x1) << 1) | fourthPart & 0x1;
			this.imm12 = (((tmp1 & 0x3ff)<< 2) | tmp2 & 0x3);*/
			int tmp1 = ( (secondPart & 0x3f) << 4) | firstPart & 0xf;
			int tmp2 = ( (fourthPart & 0x1) << 1) | thirdPart & 0x1;
			this.imm12 = (((tmp2 & 0x3) << 10) | tmp1 & 0x3ff);
			
			this.r1 = (int) ((binaryInstruction >> 15) & 0x1f);
			this.r2 = (int) ((binaryInstruction >> 20) & 0x1f);
			break;
		}
		case miscmem:
		case system:
		{
			this.type = RiscVType.ITYPE;
			int func3Binary = (int) ((binaryInstruction >> 12) & 0x7); //get the func3 binary value
			this.imm12 = (int) ((binaryInstruction >> 20) & 0xfff);
			this.rd = (int) ((binaryInstruction >> 7) & 0x1f);
			this.r1 = (int) ((binaryInstruction >> 15) & 0x1f);
			
			for (RiscVFunc func : RiscVFunc.values()) {
				
				if (func.getFunc3Code() == func3Binary && func.getOpCode() == op) {
					this.func = func;
				}
				if (0b000 == func3Binary && func.getOpCode() == op) {
					if (imm12 == 0) {
						this.func = RiscVFunc.ecall;
					}
					if (imm12 == 1) {
						this.func = RiscVFunc.ebreak;
					}
				} 
			}
			break;
		}
		case load:
		case jalr: 
		case opimm:{//I-TYPE
			this.type = RiscVType.ITYPE;
			int func3Binary = (int) ((binaryInstruction >> 12) & 0x7); //get the func3 binary value
			for (RiscVFunc func : RiscVFunc.values()) {
				if (func.getFunc3Code() == func3Binary && func.getOpCode() == op) {
					this.func = func;
					
					if (func == RiscVFunc.srli ) {
						int func7Binary = (int) ((binaryInstruction >> 25) & 0x7f); //get the func7 binary value
						if (func7Binary == RiscVFunc.srai.getFunc7Code()) {
							this.func = RiscVFunc.srai;
							
						}
					}	
					break;
				}
			}
			switch (this.func) {
			case slli:
			case srli :
			case srai:
				this.imm12 = (int) ((binaryInstruction >> 20) & 0x1f);
				break;
			default:
				this.imm12 = (int) ((binaryInstruction >> 20) & 0xfff);
				break;
			
			}
			this.rd = (int) ((binaryInstruction >> 7) & 0x1f);
			this.r1 = (int) ((binaryInstruction >> 15) & 0x1f);
			//System.out.println("bin " + Long.toBinaryString(binaryInstruction));

			break;
		}
		case jal:{//J-TYPE		
			this.type = RiscVType.JTYPE;
			
			//reconstruction of the mixed up imm20 value
			int firstPart = (int) ((binaryInstruction >> 21) & 0x3ff);
			int secondPart = (int) ((binaryInstruction >> 20) & 0x1);
			int thirdPart = (int) ((binaryInstruction >> 12) & 0xff);
			int fourthPart =(int) ((binaryInstruction >> 31) & 0x1);		
			
			int tmp1 = ( (secondPart & 0x1 )<< 10) | (firstPart & 0x3ff);
			int tmp2 = ( (fourthPart & 0x1) << 8) | (thirdPart & 0xff);
			
	
			this.imm20 = (int) ((tmp2 & 0x1ff) << 11) | (tmp1 & 0x7ff);
			
			this.rd = (int) ((binaryInstruction >> 7) & 0x1f);
			break;
		}
		case op:{//R-TYPE		
			this.type = RiscVType.RTYPE;
			int func3Binary = (int) ((binaryInstruction >> 12) & 0x7);  //get the func3 binary value
			int func7Binary = (int) ((binaryInstruction >> 25) & 0x7f); //get the func7 binary value
			for (RiscVFunc func : RiscVFunc.values()) {
				if (func.getFunc3Code() == func3Binary && func.getFunc7Code() == func7Binary && func.getOpCode() == op) {
					this.func = func;
					break;
				}
			}
			
			this.rd = (int) ((binaryInstruction >> 7) & 0x1f);
			this.r1 = (int) ((binaryInstruction >> 15) & 0x1f);
			this.r2 = (int) ((binaryInstruction >> 20) & 0x1f);
			break;
		}
		case store:{//S-TYPE
			this.type = RiscVType.STYPE;
			int func3Binary = (int) ((binaryInstruction >> 12) & 0x7); //get the func3 binary value
			for (RiscVFunc func : RiscVFunc.values()) {
				//System.out.println(Integer.toBinaryString(func3Binary) + " - " + Integer.toBinaryString(func.getFunc3Code()));
				if (func.getFunc3Code() == func3Binary && func.getOpCode() == op) {
					this.func = func;
					break;
				}
				
				//this.func = RiscVFunc.sw;
			}
			
			int firstPart = (int)((binaryInstruction >> 25) & 0x7f);
			int secondPart = (int) ((binaryInstruction >> 7) & 0x1f);
			this.imm12 = ( firstPart << 5) | secondPart & 0x1f;
			
			this.r1 = (int) ((binaryInstruction >> 15) & 0x1f);
			this.r2 = (int) ((binaryInstruction >> 20) & 0x1f);
			break;
		}
		case auipc:
		case lui:{
			this.type = RiscVType.UTYPE;
				
			this.imm20 = (int) ((binaryInstruction >> 12) & 0xfffff);
			
			this.rd = (int) ((binaryInstruction >> 7) & 0x1f);
			break;
		}
		
		
		case unknown:		
		case amo:
		case loadfp:
		case madd:
		case msub:
		case nmadd:
		case nmsub:
		case op32:
		case opfp:
		case opimm32:
		case storefp:
		default:
			type = RiscVType.UNKNOWN;	
		}
		if (func != null) {
		/*System.out.println("New Instruction :");
		System.out.println("Pc : " + this.getPC());
		System.out.println("type : " + this.getOp());
		System.out.println("func : " + this.getFunc());
		System.out.println("imm12 : " + this.getImm12());
		System.out.println("imm20 : " + this.getImm20());
		System.out.println("r1 : " + this.r1);
		System.out.println("r2 : " + this.r2);
		System.out.println("rd : " + this.rd);
		System.out.println("-----------------");*/
		}
	}
	
	
	public int getWrittenRegister(){	
		
		switch (this.getType()) {
		case JTYPE:
			return rd;
		case RTYPE:
			return rd;
		case UTYPE:
			return rd;
		case ITYPE:
			return rd;
		case BTYPE:
		case STYPE:
		case UNKNOWN:
			break;
		}
		return -1;
	}
	
	public ArrayList<Integer> getUsedRegisters(){
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		switch (this.getType()){
		case BTYPE:
			result.add(r1);
			result.add(r2);
			break;
		case ITYPE:
			result.add(r1);
			break;	
		case RTYPE:
			result.add(r1);
			result.add(r2);
			break;
		case STYPE:
			result.add(r1);
			result.add(r2);
			break;
		case UNKNOWN:
		case UTYPE:
		case JTYPE:	
		default:
			break;
			
		}
		
		return result;		
	}
	
	//******************************************************************************************
	//*              Getters and setters
	//******************************************************************************************

	public RiscVValue32 getValueA() {
		if (valueA == null) {
			System.out.println("Tried to get a null value in " + this);
			return new RiscVValue32(0);
		}
		return valueA;
	}


	public void setValueA(RiscVValue32 valueA) {
		this.valueA = valueA;
	}


	public RiscVValue32 getValueB() {
		if (valueB == null) {
			System.out.println("Tried to get a null value in " + this);
			return new RiscVValue32(0);
		}
		return valueB;
	}


	public void setValueB(RiscVValue32 valueB) {
		this.valueB = valueB;
	}


	public RiscVValue32 getAluResult() {
		if (aluResult == null) {
			System.out.println("Tried to get a null value in " + this);
			return new RiscVValue32(0);
		}
		return aluResult;
	}


	public void setAluResult(RiscVValue32 aluResult) {
		this.aluResult = aluResult;
	}


	public RiscVValue getValueToStore() {
		return valueToStore;
	}


	public void setValueToStore(RiscVValue valueToStore) {
		this.valueToStore = valueToStore;
	}


	public RiscVValue32 getValueLoaded() {
		if (valueLoaded == null) {
			System.out.println("Tried to get a null value in " + this);
			return new RiscVValue32(0);
		}
		return valueLoaded;
	}


	public void setValueLoaded(RiscVValue32 valueLoaded) {
		this.valueLoaded = valueLoaded;
	}


	public RiscVOpCode getOp() {
		return op;
	}


	public RiscVType getType() {
		return type;
	}


	public RiscVFunc getFunc() {
		return func;
	}


	public int getImm12() {
		return imm12;
	}


	public int getImm20() {
		return imm20;
	}


	public int getRd() {
		return rd;
	}


	public int getRs1() {
		return r1;
	}


	public int getRs2() {
		return r2;
	}
	
	public long getPC(){
		return this.pc;
	}

	public String toString(){
		switch (type) {
		case BTYPE:
			return this.getFunc().name() + " x" + r1 + ", x" + r2 + ", 0x" + Long.toHexString(new RiscVValue12(imm12).getSignedValue()) ;
		case ITYPE:
			return this.getFunc().name() + " x" + rd + ", x" + r1 + ", " + new RiscVValue12(imm12).getSignedValue() ;
		case JTYPE:
			return this.getOp().name() + " x" + rd + ", 0x" + Long.toHexString(new RiscVValue20(imm20).toValue32().getSignedValue()) ;
		case RTYPE:
			return this.getFunc().name() + " x" + rd + ", x" + r1 + ", x" + r2 ;
		case STYPE:
			return this.getFunc().name() + " x" + r2 + ", " + new RiscVValue12(imm12).getSignedValue() + "(x" + r1 + ")";
		case UTYPE:
			return this.getOp().name() + " x" + rd + ", " + new RiscVValue12(imm12).getSignedValue() ;
		case UNKNOWN:
			break;	
		default:
			break;
		}
		return "";
		
	}

	public long getCycleNumber() {
		return cycleNumber;
	}

	public void setCycleNumber(long cycleNumber) {
		this.cycleNumber = cycleNumber;
	}
}
	
