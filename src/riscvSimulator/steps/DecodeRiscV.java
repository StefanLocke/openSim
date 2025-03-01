package riscvSimulator.steps;

import riscvSimulator.InstructionRiscV;
import riscvSimulator.RegisterFileRiscV;
import riscvSimulator.values.RiscVValue12;
import riscvSimulator.values.RiscVValue20;
import riscvSimulator.values.RiscVValue32;

public class DecodeRiscV {

	private RegisterFileRiscV registers;
	private boolean isLast;
	
	private InstructionRiscV currentInstruction;
	private boolean isStalled;
	private boolean jumped;

	public DecodeRiscV(RegisterFileRiscV registers){
		this.registers = registers;
		this.isLast = isLast;
	}
	
	public void doStep(){
		//Main job of this part is to access registers and extend/select immediate values
		switch(currentInstruction.getType()){
		case BTYPE:
			currentInstruction.setValueA(registers.get(currentInstruction.getRs1()));
			currentInstruction.setValueB(registers.get(currentInstruction.getRs2()));
			currentInstruction.setValueToStore(new RiscVValue12(currentInstruction.getImm12()).toValue32());
			break;
		case ITYPE:
			currentInstruction.setValueA(registers.get(currentInstruction.getRs1()));
			currentInstruction.setValueB(new RiscVValue12(currentInstruction.getImm12()).toValue32());
			break;
		case JTYPE: 
			currentInstruction.setValueA(new RiscVValue20(currentInstruction.getImm20()).toValue32());
			break;
		case RTYPE:
			currentInstruction.setValueA(registers.get(currentInstruction.getRs1()));
			currentInstruction.setValueB(registers.get(currentInstruction.getRs2()));
			break;
		case STYPE:
			currentInstruction.setValueA(new RiscVValue12(currentInstruction.getImm12()).toValue32());
			currentInstruction.setValueB(registers.get(currentInstruction.getRs1()));
			currentInstruction.setValueToStore(registers.get(currentInstruction.getRs2()));
			break;
		case UTYPE:
			currentInstruction.setValueA(new RiscVValue20(currentInstruction.getImm20()).toValue32());
			break;
		default:
			break;
		}
		
		
		//For unconditional jumps, we perform the jump now
		jumped = false;
		switch (currentInstruction.getOp()) {
		case jalr : {
			long address = currentInstruction.getValueA().getSignedValue() + currentInstruction.getValueB().getSignedValue();
			address = address & 0xFFFFFFFE;
			currentInstruction.setAluResult(new RiscVValue32(currentInstruction.getPC() + 4));
			registers.setPC(new RiscVValue32(address));
			jumped = true;
			break;
		}
		case jal : { //TODO clean this
			long offset = currentInstruction.getValueA().getSignedValue();
			
			offset = ((offset) << 1 | 0b0);
			
			
			
			currentInstruction.setAluResult(new RiscVValue32(currentInstruction.getPC() + 4));
			registers.setPC(new RiscVValue32(currentInstruction.getPC() + offset));
			jumped = true;
			break;
		}
		default:
			break;
		}
		
		
	}
	
	
		
	
	public InstructionRiscV getCurrentInstruction(){
		return this.currentInstruction;
	}
	
	public void setCurrentInstruction(InstructionRiscV instr){
		this.currentInstruction = instr;
	}
	
	
	public void setStalled(boolean stall){
		this.isStalled = stall;
	}

	public boolean isStalled(){
		return this.isStalled;
	}
	
	public boolean hasJumped(){
		return this.jumped;
	}
}
