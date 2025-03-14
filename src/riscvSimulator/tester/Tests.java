package riscvSimulator.tester;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import riscvSimulator.InstructionRiscV;
import riscvSimulator.RegisterFileRiscV;
import riscvSimulator.RiscVFunc;
import riscvSimulator.RiscVMemory;
import riscvSimulator.RiscVOpCode;
import riscvSimulator.steps.DecodeRiscV;
import riscvSimulator.steps.ExecuteRiscV;
import riscvSimulator.steps.FetchRiscV;
import riscvSimulator.steps.MemoryRiscV;
import riscvSimulator.steps.WriteBackRiscV;
import riscvSimulator.values.RiscVValue12;
import riscvSimulator.values.RiscVValue32;

class Tests {
	InstructionRiscV i;
	TestInstruction currentBinary;
	
	RegisterFileRiscV registers;
	RiscVMemory memory;
	
	
	
	
	@BeforeEach
	void setup() {
		registers = new RegisterFileRiscV();
		memory = new RiscVMemory();
	}
	@Test
	void test() {
		long b = 0b100000000100;
		System.out.println("b :" + b);
		
		RiscVValue12 value = new RiscVValue12(b);
		System.out.println("b20s :" + value.getSignedValue());
		System.out.println("b20u :" + value.getUnsignedValue());
		
		RiscVValue32 value32 = value.toValue32();
		System.out.println("b32s :" + value32.getSignedValue());
		System.out.println("b32u :" + value32.getUnsignedValue());
	}
	
	@Test
	void testValue32() {
		/*long test = -32;
		RiscVValue32 value = new RiscVValue32(test);
		System.out.println("Template : " + "LLLLLLLL" + "DDDDDDDD");
		System.out.println("Signed   : " + String.format("%016x", value.getSignedValue()) + "::" + value.getSignedValue());
		System.out.println("Unsigned : " + String.format("%016x", value.getUnsignedValue()) + "::" + value.getUnsignedValue());*/
		
	}
	
	@Test
	void testValue32bis() {
	/*	int test = -32;
		RiscVValue32 value = new RiscVValue32(test);
		System.out.println("Signed   : " + "LLLLLLLL" + "DDDDDDDD");
		System.out.println("Signed   : " + String.format("%016x", value.getSignedValue()) + "::" + value.getSignedValue());
		System.out.println("Unsigned : " + String.format("%016x", value.getUnsignedValue()) + "::" + value.getUnsignedValue());
		System.out.println("*-----*");
		RiscVValue32 value2 = new RiscVValue32(value.getSignedValue());
		System.out.println("Signed   : " + "LLLLLLLL" + "DDDDDDDD");
		System.out.println("Signed   : " + String.format("%016x", value2.getSignedValue()) + "::" + value2.getSignedValue());
		System.out.println("Unsigned : " + String.format("%016x", value2.getUnsignedValue()) + "::" + value2.getUnsignedValue());
		System.out.println("*-----*");
		RiscVValue32 value3 = new RiscVValue32(value.getUnsignedValue());
		System.out.println("Signed   : " + "LLLLLLLL" + "DDDDDDDD");
		System.out.println("Signed   : " + String.format("%016x", value3.getSignedValue()) + "::" + value3.getSignedValue());
		System.out.println("Unsigned : " + String.format("%016x", value3.getUnsignedValue()) + "::" + value3.getUnsignedValue());
		*/
		
		
	}
	
	/*@Test
	void testGetBits() {
		currentBinary = TestBinaryInstructions.add1;
		assertEquals(currentBinary.getBinary(),currentBinary.getBits(32, 0));
		assertEquals(0b0110011,currentBinary.getBits(7, 0));
	}
	
	@Test
	void testAdd() {
		currentBinary = TestBinaryInstructions.add1;
		i = new InstructionRiscV(currentBinary.getBinary(), 20, 1);
		
		assertEquals(i.getImm12(), 0);
		assertEquals(i.getImm20(), 0);
		assertEquals(i.getRd(),0b00001);
		assertEquals(i.getRs1(),0b00010);
		assertEquals(i.getRs2(),0b00010);
		assertEquals(i.getOp(),RiscVOpCode.op);
		assertEquals(i.getFunc(),RiscVFunc.add);
		
		currentBinary = TestBinaryInstructions.add2;
		i = new InstructionRiscV(currentBinary.getBinary(), 20, 1);
		
		assertEquals(i.getImm12(), 0);
		assertEquals(i.getImm20(), 0);
		assertEquals(i.getRd(),0b00001);
		assertEquals(i.getRs1(),0b00010);
		assertEquals(i.getRs2(),0b00011);
		assertEquals(i.getOp(),RiscVOpCode.op);
		assertEquals(i.getFunc(),RiscVFunc.add);
		
		currentBinary = TestBinaryInstructions.add3;
		i = new InstructionRiscV(currentBinary.getBinary(), 20, 1);
		
		assertEquals(i.getImm12(), 0);
		assertEquals(i.getImm20(), 0);
		assertEquals(i.getRd(),0b00001);
		assertEquals(i.getRs1(),0b11111);
		assertEquals(i.getRs2(),0b11111);
		assertEquals(i.getOp(),RiscVOpCode.op);
		assertEquals(i.getFunc(),RiscVFunc.add);
		
		currentBinary = TestBinaryInstructions.add4;
		i = new InstructionRiscV(currentBinary.getBinary(), 20, 1);
		
		assertEquals(i.getImm12(), 0);
		assertEquals(i.getImm20(), 0);
		assertEquals(i.getRd(),0b00101);
		assertEquals(i.getRs1(),0b00101);
		assertEquals(i.getRs2(),0b11010);
		assertEquals(i.getOp(),RiscVOpCode.op);
		assertEquals(i.getFunc(),RiscVFunc.add);
	}*/

	/*
	@Test
	void testAddbis() {
		currentBinary = TestBinaryInstructions.add1;
		i = new InstructionRiscV(currentBinary.getBinary(), 20, 1);
		
		assertEquals(i.getImm12(), 0);
		assertEquals(i.getImm20(), 0);
		assertEquals(i.getRd(),currentBinary.getBits(5,7));
		assertEquals(i.getRs1(),currentBinary.getBits(5,15));
		assertEquals(i.getRs2(),currentBinary.getBits(5,20));
		assertEquals(i.getOp(),RiscVOpCode.op);
		assertEquals(i.getFunc(),RiscVFunc.add);
		
		currentBinary = TestBinaryInstructions.add2;
		i = new InstructionRiscV(currentBinary.getBinary(), 20, 1);
		
		assertEquals(i.getImm12(), 0);
		assertEquals(i.getImm20(), 0);
		assertEquals(i.getRd(),currentBinary.getBits(5,7));
		assertEquals(i.getRs1(),currentBinary.getBits(5,15));
		assertEquals(i.getRs2(),currentBinary.getBits(5,20));
		assertEquals(i.getOp(),RiscVOpCode.op);
		assertEquals(i.getFunc(),RiscVFunc.add);
		
		currentBinary = TestBinaryInstructions.add3;
		i = new InstructionRiscV(currentBinary.getBinary(), 20, 1);
		
		assertEquals(i.getImm12(), 0);
		assertEquals(i.getImm20(), 0);
		assertEquals(i.getRd(),currentBinary.getBits(5,7));
		assertEquals(i.getRs1(),currentBinary.getBits(5,15));
		assertEquals(i.getRs2(),currentBinary.getBits(5,20));
		assertEquals(i.getOp(),RiscVOpCode.op);
		assertEquals(i.getFunc(),RiscVFunc.add);
		
		currentBinary = TestBinaryInstructions.add4;
		i = new InstructionRiscV(currentBinary.getBinary(), 20, 1);
		
		assertEquals(i.getImm12(), 0);
		assertEquals(i.getImm20(), 0);
		assertEquals(i.getRd(),currentBinary.getBits(5,7));
		assertEquals(i.getRs1(),currentBinary.getBits(5,15));
		assertEquals(i.getRs2(),currentBinary.getBits(5,20));
		assertEquals(i.getOp(),RiscVOpCode.op);
		assertEquals(i.getFunc(),RiscVFunc.add);
	}*/
	 
	
	
	
	@Test
	public void autoTestAdd() {
		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.add));
		i = new InstructionRiscV(currentBinary.getBinary(), 20, 1);
		
		assertEquals(i.getImm12(), 0);
		assertEquals(i.getImm20(), 0);
		assertEquals(i.getRd(),currentBinary.getBits(5,7));
		assertEquals(i.getRs1(),currentBinary.getBits(5,15));
		assertEquals(i.getRs2(),currentBinary.getBits(5,20));
		assertEquals(i.getOp(),RiscVOpCode.op);
		assertEquals(i.getFunc(),RiscVFunc.add);
	}
	
	
	@RepeatedTest(value = 100)
	public void autoTestAddSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());


		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.add));
		long rs1 = currentBinary.getBits(5,15);
		long rs2 = currentBinary.getBits(5,20);
		long rs1V = generateRandomBinary(20);
		long rs2V = generateRandomBinary(20);
		if (rs2 == rs1) {
			rs1V = rs2V;
		}
		RiscVValue32 rs1V32 = new RiscVValue32(rs1V);
		RiscVValue32 rs2V32 = new RiscVValue32(rs2V);
		
		registers.set((int)rs1, rs1V32);
		registers.set((int)rs2, rs2V32);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary);
		System.out.println("Rs1 = " + rs1 +" : "+ registers.get((int)rs1).getSignedValue() + " Rs2 = " + rs2 +" : " + registers.get((int)rs2).getSignedValue());
		System.out.println("Rd = " + rd + " : "+ registers.get((int)rd).getSignedValue());
		assertEquals(rs1V32.getSignedValue() + rs2V32.getSignedValue()  , registers.get((int)rd).getSignedValue());
	}
	
	@RepeatedTest(value = 100)
	public void autoTestSubSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());


		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.sub));
		long rs1 = currentBinary.getBits(5,15);
		long rs2 = currentBinary.getBits(5,20);
		long rs1V = generateRandomBinary(20);
		long rs2V = generateRandomBinary(20);
		if (rs2 == rs1) {
			rs1V = rs2V;
		}
		RiscVValue32 rs1V32 = new RiscVValue32(rs1V);
		RiscVValue32 rs2V32 = new RiscVValue32(rs2V);
		
		registers.set((int)rs1, rs1V32);
		registers.set((int)rs2, rs2V32);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary);
		System.out.println("Rs1 = " + rs1 +" : "+ registers.get((int)rs1).getSignedValue() + " Rs2 = " + rs2 +" : " + registers.get((int)rs2).getSignedValue());
		System.out.println("Rd = " + rd + " : "+ registers.get((int)rd).getSignedValue());
		assertEquals(rs1V32.getSignedValue() - rs2V32.getSignedValue() , registers.get((int)rd).getSignedValue());
	}
	
	
	
	@RepeatedTest(value = 100)
	public void autoTestSltSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());


		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.slt));
		long rs1 = currentBinary.getBits(5,15);
		long rs2 = currentBinary.getBits(5,20);
		long rs1V = generateRandomBinary(20);
		long rs2V = generateRandomBinary(20);
		if (rs2 == rs1) {
			rs1V = rs2V;
		}
		RiscVValue32 rs1V32 = new RiscVValue32(rs1V);
		RiscVValue32 rs2V32 = new RiscVValue32(rs2V);
		
		registers.set((int)rs1, rs1V32);
		registers.set((int)rs2, rs2V32);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary);
		System.out.println("Rs1 = " + rs1 +" : "+ registers.get((int)rs1).getSignedValue() + " Rs2 = " + rs2 +" : " + registers.get((int)rs2).getSignedValue());
		System.out.println("Rd = " + rd + " : "+ registers.get((int)rd).getSignedValue());
		
		assertEquals(rs1V32.getSignedValue()<rs2V32.getSignedValue()?1:0, registers.get((int)rd).getSignedValue());
	}
	
	@RepeatedTest(value = 100)
	public void autoTestSltuSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());


		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.slt));
		long rs1 = currentBinary.getBits(5,15);
		long rs2 = currentBinary.getBits(5,20);
		long rs1V = generateRandomBinary(20);
		long rs2V = generateRandomBinary(20);
		if (rs2 == rs1) {
			rs1V = rs2V;
		}
		RiscVValue32 rs1V32 = new RiscVValue32(rs1V);
		RiscVValue32 rs2V32 = new RiscVValue32(rs2V);
		
		registers.set((int)rs1, rs1V32);
		registers.set((int)rs2, rs2V32);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary);
		System.out.println("Rs1 = " + rs1 +" : "+ registers.get((int)rs1).getSignedValue() + " Rs2 = " + rs2 +" : " + registers.get((int)rs2).getSignedValue());
		System.out.println("Rd = " + rd + " : "+ registers.get((int)rd).getSignedValue());
		
		assertEquals(rs1V32.getUnsignedValue()<rs2V32.getUnsignedValue()?1:0, registers.get((int)rd).getSignedValue());
	}
	
	@RepeatedTest(value = 100)
	public void autoTestXorSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());


		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.xor));
		long rs1 = currentBinary.getBits(5,15);
		long rs2 = currentBinary.getBits(5,20);
		long rs1V = generateRandomBinary(20);
		long rs2V = generateRandomBinary(20);
		if (rs2 == rs1) {
			rs1V = rs2V;
		}
		RiscVValue32 rs1V32 = new RiscVValue32(rs1V);
		RiscVValue32 rs2V32 = new RiscVValue32(rs2V);
		
		registers.set((int)rs1, rs1V32);
		registers.set((int)rs2, rs2V32);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary);
		System.out.println("Rs1 = " + rs1 +" : "+ registers.get((int)rs1).getSignedValue() + " Rs2 = " + rs2 +" : " + registers.get((int)rs2).getSignedValue());
		System.out.println("Rd = " + rd + " : "+ registers.get((int)rd).getSignedValue());
		
		assertEquals(rs1V32.getUnsignedValue() ^ rs2V32.getUnsignedValue(), registers.get((int)rd).getSignedValue());
	}
	
	@RepeatedTest(value = 100)
	public void autoTestandSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());


		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.and));
		long rs1 = currentBinary.getBits(5,15);
		long rs2 = currentBinary.getBits(5,20);
		long rs1V = generateRandomBinary(20);
		long rs2V = generateRandomBinary(20);
		if (rs2 == rs1) {
			rs1V = rs2V;
		}
		RiscVValue32 rs1V32 = new RiscVValue32(rs1V);
		RiscVValue32 rs2V32 = new RiscVValue32(rs2V);
		
		registers.set((int)rs1, rs1V32);
		registers.set((int)rs2, rs2V32);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary);
		System.out.println("Rs1 = " + rs1 +" : "+ registers.get((int)rs1).getSignedValue() + " Rs2 = " + rs2 +" : " + registers.get((int)rs2).getSignedValue());
		System.out.println("Rd = " + rd + " : "+ registers.get((int)rd).getSignedValue());
		
		assertEquals(rs1V32.getUnsignedValue() & rs2V32.getUnsignedValue(), registers.get((int)rd).getSignedValue());
	}
	
	@RepeatedTest(value = 100)
	public void autoTestOrSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());


		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.or));
		long rs1 = currentBinary.getBits(5,15);
		long rs2 = currentBinary.getBits(5,20);
		long rs1V = generateRandomBinary(20);
		long rs2V = generateRandomBinary(20);
		if (rs2 == rs1) {
			rs1V = rs2V;
		}
		RiscVValue32 rs1V32 = new RiscVValue32(rs1V);
		RiscVValue32 rs2V32 = new RiscVValue32(rs2V);
		
		registers.set((int)rs1, rs1V32);
		registers.set((int)rs2, rs2V32);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary);
		System.out.println("Rs1 = " + rs1 +" : "+ registers.get((int)rs1).getSignedValue() + " Rs2 = " + rs2 +" : " + registers.get((int)rs2).getSignedValue());
		System.out.println("Rd = " + rd + " : "+ registers.get((int)rd).getSignedValue());
		
		assertEquals(rs1V32.getUnsignedValue() | rs2V32.getUnsignedValue(), registers.get((int)rd).getSignedValue());
	}
	
	@RepeatedTest(value = 100)
	public void autoTestSrlSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());


		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.srl));
		long rs1 = currentBinary.getBits(5,15);
		long rs2 = currentBinary.getBits(5,20);
		long rs1V = generateRandomBinary(20);
		long rs2V = generateRandomBinary(20);
		if (rs2 == rs1) {
			rs1V = rs2V;
		}
		RiscVValue32 rs1V32 = new RiscVValue32(rs1V);
		RiscVValue32 rs2V32 = new RiscVValue32(rs2V);
		
		registers.set((int)rs1, rs1V32);
		registers.set((int)rs2, rs2V32);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary);
		System.out.println("Rs1 = " + rs1 +" : "+ registers.get((int)rs1).getSignedValue() + " Rs2 = " + rs2 +" : " + registers.get((int)rs2).getSignedValue());
		System.out.println("Rd = " + rd + " : "+ registers.get((int)rd).getSignedValue());
		
		assertEquals(rs1V32.getUnsignedValue()  >> rs2V32.getUnsignedValue(), registers.get((int)rd).getSignedValue());
	}
	@RepeatedTest(value = 100)
	public void autoTestSllSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());


		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.sll));
		long rs1 = currentBinary.getBits(5,15);
		long rs2 = currentBinary.getBits(5,20);
		long rs1V = generateRandomBinary(20);
		long rs2V = generateRandomBinary(20);
		if (rs2 == rs1) {
			rs1V = rs2V;
		}
		RiscVValue32 rs1V32 = new RiscVValue32(rs1V);
		RiscVValue32 rs2V32 = new RiscVValue32(rs2V);
		
		registers.set((int)rs1, rs1V32);
		registers.set((int)rs2, rs2V32);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary);
		System.out.println("Rs1 = " + rs1 +" : "+ registers.get((int)rs1).getSignedValue() + " Rs2 = " + rs2 +" : " + registers.get((int)rs2).getSignedValue());
		System.out.println("Rd = " + rd + " : "+ registers.get((int)rd).getSignedValue());
		assertEquals(new RiscVValue32(rs1V32.getUnsignedValue() << rs2V32.getUnsignedValue()).getUnsignedValue()  , registers.get((int)rd).getUnsignedValue());
	}
	
	@RepeatedTest(value = 100)
	public void autoTestSraSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());


		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.sra));
		long rs1 = currentBinary.getBits(5,15);
		long rs2 = currentBinary.getBits(5,20);
		long rs1V = generateRandomBinary(20);
		long rs2V = generateRandomBinary(20);
		if (rs2 == rs1) {
			rs1V = rs2V;
		}
		RiscVValue32 rs1V32 = new RiscVValue32(rs1V);
		RiscVValue32 rs2V32 = new RiscVValue32(rs2V);
		
		registers.set((int)rs1, rs1V32);
		registers.set((int)rs2, rs2V32);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary);
		System.out.println("Rs1 = " + rs1 +" : "+ registers.get((int)rs1).getSignedValue() + " Rs2 = " + rs2 +" : " + registers.get((int)rs2).getSignedValue());
		System.out.println("Rd = " + rd + " : "+ registers.get((int)rd).getSignedValue());
		
		assertEquals(rs1V32.getUnsignedValue() >>> rs2V32.getUnsignedValue(), registers.get((int)rd).getSignedValue());
	}
	
	@RepeatedTest(value = 100)
	public void autoTestAddiSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());


		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.addi));
		long rs1 = currentBinary.getBits(5,15);
		RiscVValue32 rs1V = new RiscVValue32(generateRandomBinary(16));
		RiscVValue32  immValue = new RiscVValue12(currentBinary.getBits(12, 20)).toValue32();
		registers.set((int)rs1, rs1V);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary);
		System.out.println("Rs1 = " + rs1 +" : "+ registers.get((int)rs1).getSignedValue() + " imm = " + immValue );
		System.out.println("Rd = " + rd + " : "+ registers.get((int)rd).getSignedValue());
		
		assertEquals(rs1V.getSignedValue() + immValue.getSignedValue(), registers.get((int)rd).getSignedValue());
	}
	
	@RepeatedTest(value = 100)
	public void autoTestSlliSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());


		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.slli));
		long rs1 = currentBinary.getBits(5,15);
		RiscVValue32 rs1V = new RiscVValue32(generateRandomBinary(16));
		RiscVValue32  immValue = new RiscVValue12(currentBinary.getBits(12, 20)).toValue32();
		registers.set((int)rs1, rs1V);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary);
		System.out.println("Rs1 = " + rs1 +" : "+ registers.get((int)rs1).getSignedValue() + " imm = " + immValue );
		System.out.println("Rd = " + rd + " : "+ registers.get((int)rd).getSignedValue());
		
		assertEquals(new RiscVValue32((rs1V.getUnsignedValue() << immValue.getUnsignedValue())).getUnsignedValue(), registers.get((int)rd).getUnsignedValue());
	}
	
	@RepeatedTest(value = 100)
	public void autoTestSrliSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());


		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.srli));
		long rs1 = currentBinary.getBits(5,15);
		RiscVValue32 rs1V = new RiscVValue32(generateRandomBinary(16));
		RiscVValue32  immValue = new RiscVValue12(currentBinary.getBits(12, 20)).toValue32();
		registers.set((int)rs1, rs1V);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary);
		System.out.println("Rs1 = " + rs1 +" : "+ registers.get((int)rs1).getSignedValue() + " imm = " + immValue );
		System.out.println("Rd = " + rd + " : "+ registers.get((int)rd).getSignedValue());
		
		assertEquals(rs1V.getSignedValue() >> immValue.getSignedValue(), registers.get((int)rd).getSignedValue());
	}
	
	@RepeatedTest(value = 100)
	public void autoTestSraiSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());


		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.srai));
		long rs1 = currentBinary.getBits(5,15);
		RiscVValue32 rs1V = new RiscVValue32(generateRandomBinary(16));
		RiscVValue32  immValue = new RiscVValue12(currentBinary.getBits(12, 20)).toValue32();
		registers.set((int)rs1, rs1V);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary);
		System.out.println("Rs1 = " + rs1 +" : "+ registers.get((int)rs1).getSignedValue() + " imm = " + immValue );
		System.out.println("Rd = " + rd + " : "+ registers.get((int)rd).getSignedValue());
		
		assertEquals(rs1V.getSignedValue() >>> immValue.getSignedValue(), registers.get((int)rd).getSignedValue());
	}
	
	@RepeatedTest(value = 100)
	public void autoTestSltiSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());


		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.slti));
		long rs1 = currentBinary.getBits(5,15);
		RiscVValue32 rs1V = new RiscVValue32(generateRandomBinary(16));
		RiscVValue32  immValue = new RiscVValue12(currentBinary.getBits(12, 20)).toValue32();
		registers.set((int)rs1, rs1V);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary);
		System.out.println("Rs1 = " + rs1 +" : "+ registers.get((int)rs1).getSignedValue() + " imm = " + immValue );
		System.out.println("Rd = " + rd + " : "+ registers.get((int)rd).getSignedValue());
		
		assertEquals(rs1V.getSignedValue()<immValue.getSignedValue()?1:0, registers.get((int)rd).getSignedValue());
	}
	
	@RepeatedTest(value = 100)
	public void autoTestSltiuSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());


		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.sltiu));
		long rs1 = currentBinary.getBits(5,15);
		RiscVValue32 rs1V = new RiscVValue32(generateRandomBinary(16));
		RiscVValue32  immValue = new RiscVValue12(currentBinary.getBits(12, 20)).toValue32();
		registers.set((int)rs1, rs1V);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary);
		System.out.println("Rs1 = " + rs1 +" : "+ registers.get((int)rs1).getSignedValue() + " imm = " + immValue );
		System.out.println("Rd = " + rd + " : "+ registers.get((int)rd).getSignedValue());
		
		assertEquals(rs1V.getUnsignedValue()<immValue.getUnsignedValue()?1:0, registers.get((int)rd).getSignedValue());
	}
	
	@RepeatedTest(value = 100)
	public void autoTestXoriSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());


		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.xori));
		long rs1 = currentBinary.getBits(5,15);
		RiscVValue32 rs1V = new RiscVValue32(generateRandomBinary(16));
		RiscVValue32  immValue = new RiscVValue12(currentBinary.getBits(12, 20)).toValue32();
		registers.set((int)rs1, rs1V);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary);
		System.out.println("Rs1 = " + rs1 +" : "+ registers.get((int)rs1).getSignedValue() + " imm = " + immValue );
		System.out.println("Rd = " + rd + " : "+ registers.get((int)rd).getSignedValue());
		
		assertEquals(rs1V.getUnsignedValue()^immValue.getUnsignedValue(), registers.get((int)rd).getUnsignedValue());
	}
	
	@RepeatedTest(value = 100)
	public void autoTestAndiSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());


		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.andi));
		long rs1 = currentBinary.getBits(5,15);
		RiscVValue32 rs1V = new RiscVValue32(generateRandomBinary(16));
		RiscVValue32  immValue = new RiscVValue12(currentBinary.getBits(12, 20)).toValue32();
		registers.set((int)rs1, rs1V);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary);
		System.out.println("Rs1 = " + rs1 +" : "+ registers.get((int)rs1).getSignedValue() + " imm = " + immValue );
		System.out.println("Rd = " + rd + " : "+ registers.get((int)rd).getSignedValue());
		
		assertEquals(rs1V.getUnsignedValue()&immValue.getUnsignedValue(), registers.get((int)rd).getUnsignedValue());
	}
	@RepeatedTest(value = 100)
	public void autoTestOriSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());


		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.ori));
		long rs1 = currentBinary.getBits(5,15);
		RiscVValue32 rs1V = new RiscVValue32(generateRandomBinary(16));
		RiscVValue32  immValue = new RiscVValue12(currentBinary.getBits(12, 20)).toValue32();
		registers.set((int)rs1, rs1V);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary);
		System.out.println("Rs1 = " + rs1 +" : "+ registers.get((int)rs1).getSignedValue() + " imm = " + immValue );
		System.out.println("Rd = " + rd + " : "+ registers.get((int)rd).getSignedValue());
		
		assertEquals(rs1V.getUnsignedValue()|immValue.getUnsignedValue(), registers.get((int)rd).getUnsignedValue());
	}
	
	@RepeatedTest(value = 100)
	public void autoTestBeqSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());

		
		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.beq));
		long rs1 = currentBinary.getBits(5,15);
		long rs2 = currentBinary.getBits(5,20);
		RiscVValue32 rs1V; 
		RiscVValue32 rs2V;
		if (rs2 == rs1) {
			long tmp = generateRandomBinary(16);
			rs1V = new RiscVValue32(tmp);
			rs2V = new RiscVValue32(tmp);
		}
		else {
			rs1V = new RiscVValue32(generateRandomBinary(16));
			rs2V = new RiscVValue32(generateRandomBinary(16));
		}
		
		
		int firstPart =(int) currentBinary.getBits(4,8);//(int) ((binaryInstruction >> 8) & 0xf);
		int secondPart = (int) currentBinary.getBits(6,25);//(int) ((binaryInstruction >> 25) & 0x3f);
		int thirdPart = (int) currentBinary.getBits(1,7);//(int) ((binaryInstruction >> 7) & 0x1);
		int fourthPart =(int) currentBinary.getBits(1,31);//(int) ((binaryInstruction >> 31) & 0x1);
		
		int tmp1 = ( (secondPart & 0x3f) << 4) | firstPart & 0xf;
		int tmp2 = ( (fourthPart & 0x1) << 1) | thirdPart & 0x1;
		int imm12 = (((tmp2 & 0x3) << 10) | tmp1 & 0x3ff);
		
		RiscVValue32 generatedPc = new RiscVValue32(generateRandomBinary(14)<<2);
		RiscVValue32 offset = new RiscVValue12(imm12).toValue32();
		registers.set((int)rs1, rs1V);
		registers.set((int)rs2, rs2V);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary,generatedPc.getUnsignedValue());
		
		
		long newaddress = (offset.getSignedValue() << 1) + generatedPc.getUnsignedValue();
		if ((generatedPc.getUnsignedValue() > Math.abs(offset.getSignedValue()<<1))) {
			assertEquals(rs1V.getUnsignedValue()==rs2V.getUnsignedValue()?newaddress:generatedPc.getUnsignedValue()+4, registers.getPC().getUnsignedValue());
		}
	}
	
	@RepeatedTest(value = 100)
	public void autoTestBneSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());
		
		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.bne));
		long rs1 = currentBinary.getBits(5,15);
		long rs2 = currentBinary.getBits(5,20);
		RiscVValue32 rs1V; 
		RiscVValue32 rs2V;
		if (rs2 == rs1) {
			long tmp = generateRandomBinary(16);
			rs1V = new RiscVValue32(tmp);
			rs2V = new RiscVValue32(tmp);
		}
		else {
			rs1V = new RiscVValue32(generateRandomBinary(16));
			rs2V = new RiscVValue32(generateRandomBinary(16));
		}
		
		
		int firstPart =(int) currentBinary.getBits(4,8);//(int) ((binaryInstruction >> 8) & 0xf);
		int secondPart = (int) currentBinary.getBits(6,25);//(int) ((binaryInstruction >> 25) & 0x3f);
		int thirdPart = (int) currentBinary.getBits(1,7);//(int) ((binaryInstruction >> 7) & 0x1);
		int fourthPart =(int) currentBinary.getBits(1,31);//(int) ((binaryInstruction >> 31) & 0x1);
		
		int tmp1 = ( (secondPart & 0x3f) << 4) | firstPart & 0xf;
		int tmp2 = ( (fourthPart & 0x1) << 1) | thirdPart & 0x1;
		int imm12 = (((tmp2 & 0x3) << 10) | tmp1 & 0x3ff);
		
		RiscVValue32 generatedPc = new RiscVValue32(generateRandomBinary(14)<<2);
		RiscVValue32 offset = new RiscVValue12(imm12).toValue32();
		registers.set((int)rs1, rs1V);
		registers.set((int)rs2, rs2V);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary,generatedPc.getUnsignedValue());
		
		
		long newaddress = (offset.getSignedValue() << 1) + generatedPc.getUnsignedValue();
		if ((generatedPc.getUnsignedValue() > Math.abs(offset.getSignedValue()<<1))) {
			assertEquals(rs1V.getUnsignedValue()!=rs2V.getUnsignedValue()?newaddress:generatedPc.getUnsignedValue()+4, registers.getPC().getUnsignedValue());
		}
		
	}
	
	@RepeatedTest(value = 100)
	public void autoTestBltSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());
		
		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.blt));
		long rs1 = currentBinary.getBits(5,15);
		long rs2 = currentBinary.getBits(5,20);
		RiscVValue32 rs1V; 
		RiscVValue32 rs2V;
		if (rs2 == rs1) {
			long tmp = generateRandomBinary(16);
			rs1V = new RiscVValue32(tmp);
			rs2V = new RiscVValue32(tmp);
		}
		else {
			rs1V = new RiscVValue32(generateRandomBinary(16));
			rs2V = new RiscVValue32(generateRandomBinary(16));
		}
		
		
		int firstPart =(int) currentBinary.getBits(4,8);//(int) ((binaryInstruction >> 8) & 0xf);
		int secondPart = (int) currentBinary.getBits(6,25);//(int) ((binaryInstruction >> 25) & 0x3f);
		int thirdPart = (int) currentBinary.getBits(1,7);//(int) ((binaryInstruction >> 7) & 0x1);
		int fourthPart =(int) currentBinary.getBits(1,31);//(int) ((binaryInstruction >> 31) & 0x1);
		
		int tmp1 = ( (secondPart & 0x3f) << 4) | firstPart & 0xf;
		int tmp2 = ( (fourthPart & 0x1) << 1) | thirdPart & 0x1;
		int imm12 = (((tmp2 & 0x3) << 10) | tmp1 & 0x3ff);
		
		RiscVValue32 generatedPc = new RiscVValue32(generateRandomBinary(14)<<2);
		RiscVValue32 offset = new RiscVValue12(imm12).toValue32();
		registers.set((int)rs1, rs1V);
		registers.set((int)rs2, rs2V);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary,generatedPc.getUnsignedValue());
		
		
		long newaddress = (offset.getSignedValue() << 1) + generatedPc.getUnsignedValue();
		if ((generatedPc.getUnsignedValue() > Math.abs(offset.getSignedValue()<<1))) {
			assertEquals(rs1V.getSignedValue() < rs2V.getSignedValue()?newaddress:generatedPc.getUnsignedValue()+4, registers.getPC().getUnsignedValue());
		}
		
	}
	
	@RepeatedTest(value = 100)
	public void autoTestBltuSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());
		
		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.bltu));
		long rs1 = currentBinary.getBits(5,15);
		long rs2 = currentBinary.getBits(5,20);
		RiscVValue32 rs1V; 
		RiscVValue32 rs2V;
		if (rs2 == rs1) {
			long tmp = generateRandomBinary(16);
			rs1V = new RiscVValue32(tmp);
			rs2V = new RiscVValue32(tmp);
		}
		else {
			rs1V = new RiscVValue32(generateRandomBinary(16));
			rs2V = new RiscVValue32(generateRandomBinary(16));
		}
		
		
		int firstPart =(int) currentBinary.getBits(4,8);//(int) ((binaryInstruction >> 8) & 0xf);
		int secondPart = (int) currentBinary.getBits(6,25);//(int) ((binaryInstruction >> 25) & 0x3f);
		int thirdPart = (int) currentBinary.getBits(1,7);//(int) ((binaryInstruction >> 7) & 0x1);
		int fourthPart =(int) currentBinary.getBits(1,31);//(int) ((binaryInstruction >> 31) & 0x1);
		
		int tmp1 = ( (secondPart & 0x3f) << 4) | firstPart & 0xf;
		int tmp2 = ( (fourthPart & 0x1) << 1) | thirdPart & 0x1;
		int imm12 = (((tmp2 & 0x3) << 10) | tmp1 & 0x3ff);
		
		RiscVValue32 generatedPc = new RiscVValue32(generateRandomBinary(14)<<2);
		RiscVValue32 offset = new RiscVValue12(imm12).toValue32();
		registers.set((int)rs1, rs1V);
		registers.set((int)rs2, rs2V);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary,generatedPc.getUnsignedValue());
		
		
		long newaddress = (offset.getSignedValue() << 1) + generatedPc.getUnsignedValue();
		if ((generatedPc.getUnsignedValue() > Math.abs(offset.getSignedValue()<<1))) {
			assertEquals(rs1V.getUnsignedValue() < rs2V.getUnsignedValue()?newaddress:generatedPc.getUnsignedValue()+4, registers.getPC().getUnsignedValue());
		}
		
	}
	
	@RepeatedTest(value = 100)
	public void autoTestBgeSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());
		
		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.bge));
		long rs1 = currentBinary.getBits(5,15);
		long rs2 = currentBinary.getBits(5,20);
		RiscVValue32 rs1V; 
		RiscVValue32 rs2V;
		if (rs2 == rs1) {
			long tmp = generateRandomBinary(16);
			rs1V = new RiscVValue32(tmp);
			rs2V = new RiscVValue32(tmp);
		}
		else {
			rs1V = new RiscVValue32(generateRandomBinary(16));
			rs2V = new RiscVValue32(generateRandomBinary(16));
		}
		
		
		int firstPart =(int) currentBinary.getBits(4,8);//(int) ((binaryInstruction >> 8) & 0xf);
		int secondPart = (int) currentBinary.getBits(6,25);//(int) ((binaryInstruction >> 25) & 0x3f);
		int thirdPart = (int) currentBinary.getBits(1,7);//(int) ((binaryInstruction >> 7) & 0x1);
		int fourthPart =(int) currentBinary.getBits(1,31);//(int) ((binaryInstruction >> 31) & 0x1);
		
		int tmp1 = ( (secondPart & 0x3f) << 4) | firstPart & 0xf;
		int tmp2 = ( (fourthPart & 0x1) << 1) | thirdPart & 0x1;
		int imm12 = (((tmp2 & 0x3) << 10) | tmp1 & 0x3ff);
		
		RiscVValue32 generatedPc = new RiscVValue32(generateRandomBinary(14)<<2);
		RiscVValue32 offset = new RiscVValue12(imm12).toValue32();
		registers.set((int)rs1, rs1V);
		registers.set((int)rs2, rs2V);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary,generatedPc.getUnsignedValue());
		
		
		long newaddress = (offset.getSignedValue() << 1) + generatedPc.getUnsignedValue();
		if ((generatedPc.getUnsignedValue() > Math.abs(offset.getSignedValue()<<1))) {
			assertEquals(rs1V.getSignedValue() >= rs2V.getSignedValue()?newaddress:generatedPc.getUnsignedValue()+4, registers.getPC().getUnsignedValue());
		}
		
	}
	@RepeatedTest(value = 100)
	public void autoTestBgeuSteps(RepetitionInfo rep) {
		System.out.println("Repetition number : " + rep.getCurrentRepetition());
		
		currentBinary = new TestInstruction(generateInstruction(RiscVFunc.bgeu));
		long rs1 = currentBinary.getBits(5,15);
		long rs2 = currentBinary.getBits(5,20);
		RiscVValue32 rs1V; 
		RiscVValue32 rs2V;
		if (rs2 == rs1) {
			long tmp = generateRandomBinary(16);
			rs1V = new RiscVValue32(tmp);
			rs2V = new RiscVValue32(tmp);
		}
		else {
			rs1V = new RiscVValue32(generateRandomBinary(16));
			rs2V = new RiscVValue32(generateRandomBinary(16));
		}
		
		
		int firstPart =(int) currentBinary.getBits(4,8);//(int) ((binaryInstruction >> 8) & 0xf);
		int secondPart = (int) currentBinary.getBits(6,25);//(int) ((binaryInstruction >> 25) & 0x3f);
		int thirdPart = (int) currentBinary.getBits(1,7);//(int) ((binaryInstruction >> 7) & 0x1);
		int fourthPart =(int) currentBinary.getBits(1,31);//(int) ((binaryInstruction >> 31) & 0x1);
		
		int tmp1 = ( (secondPart & 0x3f) << 4) | firstPart & 0xf;
		int tmp2 = ( (fourthPart & 0x1) << 1) | thirdPart & 0x1;
		int imm12 = (((tmp2 & 0x3) << 10) | tmp1 & 0x3ff);
		
		RiscVValue32 generatedPc = new RiscVValue32(generateRandomBinary(14)<<2);
		RiscVValue32 offset = new RiscVValue12(imm12).toValue32();
		registers.set((int)rs1, rs1V);
		registers.set((int)rs2, rs2V);
		long rd = currentBinary.getBits(5,7);
		doSteps(currentBinary,generatedPc.getUnsignedValue());
		
		
		long newaddress = (offset.getSignedValue() << 1) + generatedPc.getUnsignedValue();
		if ((generatedPc.getUnsignedValue() > Math.abs(offset.getSignedValue()<<1))) {
			assertEquals(rs1V.getUnsignedValue() >= rs2V.getUnsignedValue()?newaddress:generatedPc.getUnsignedValue()+4, registers.getPC().getUnsignedValue());
		}
		
	}
	

	
	
	
	
	@Test
	public void generatorTest() {
		/*int o = generateInstruction(RiscVFunc.add);
		System.out.println("      |-----||---||---||-||---||-----|");
		System.out.println("add : " +binString(32, o));
		int b = generateInstruction(RiscVFunc.beq);
		System.out.println("      ||----||---||---||-||--|||-----|");
		System.out.println("beq : " +binString(32, b));
		int u = generateInstruction(RiscVFunc.lui);
		System.out.println("      |------------------||---||-----|");
		System.out.println("lui : " +binString(32, u));
		int j = generateInstruction(RiscVFunc.jal);
		System.out.println("      ||--------|||------||---||-----|");
		System.out.println("jal : " +binString(32, j));*/
	}
	

	
	public int generateInstruction(RiscVFunc instr) {
		int binaryInstruction = 0;
		switch (instr.getOpCode()) {
		case op:
			//System.out.println("Func7 : " + binString(7,  instr.getFunc7Code()));
			//System.out.println("Func3 : " + binString(3,  instr.getFunc3Code()));
			//System.out.println("OpCod : " + binString(7,  instr.getOpCode().getOpCode()));
			binaryInstruction = (int) (instr.getFunc7Code() << 25 | (TestRegisters.getRandomRegister() << 20 | (TestRegisters.getRandomRegister() << 15 | (instr.getFunc3Code() << 12 | (TestRegisters.getRandomRegister() << 7 | (instr.getOpCode().getOpCode()))))));
			break;
		case opimm:
			switch (instr) {
				case addi :
				case slti :
				case sltiu :
				case andi :
				case ori :
				case xori :
					binaryInstruction = (int) (generateRandomBinary(12) << 20 | (TestRegisters.getRandomRegister() << 15 | (instr.getFunc3Code() << 12 | (TestRegisters.getRandomRegister() << 7 | (instr.getOpCode().getOpCode())))));
				break;
				case slli :
				case srli :
				case srai :
					binaryInstruction = (int) (instr.getFunc7Code() << 25 | (generateRandomBinary(5) << 20 | (TestRegisters.getRandomRegister() << 15 | (instr.getFunc3Code() << 12 | (TestRegisters.getRandomRegister() << 7 | (instr.getOpCode().getOpCode()))))));
			default:
				break;
			}
			break;
		case branch:
				binaryInstruction = (int) (generateRandomBinary(1) << 31 | (generateRandomBinary(6) << 25 | (TestRegisters.getRandomRegister() << 20 | (TestRegisters.getRandomRegister() << 15 | (instr.getFunc3Code() << 12 | (generateRandomBinary(4) << 8) | (generateRandomBinary(1) << 7 | (instr.getOpCode().getOpCode()))) ))));
			break;
		
		case jal:
			binaryInstruction = (int) (generateRandomBinary(1) << 31 | (generateRandomBinary(10) << 21 | (generateRandomBinary(1) << 20 | (generateRandomBinary(8) << 12 | (TestRegisters.getRandomRegister() << 7 | (instr.getOpCode().getOpCode()))))) );
			break;
		case jalr:
			binaryInstruction = (int) (generateRandomBinary(12) << 20 | (TestRegisters.getRandomRegister() << 15 | (instr.getFunc3Code() << 12 | (TestRegisters.getRandomRegister() << 7 | (instr.getOpCode().getOpCode())))));
			break;
		case auipc:
			binaryInstruction = (int) (generateRandomBinary(20) << 12 | (TestRegisters.getRandomRegister() << 7 | (instr.getOpCode().getOpCode())));
			break;
		case lui:
			binaryInstruction = (int) (generateRandomBinary(20) << 12 | (TestRegisters.getRandomRegister() << 7 | (instr.getOpCode().getOpCode())));
			break;
		case store:
			binaryInstruction = (int) (generateRandomBinary(7) << 25 | (TestRegisters.getRandomRegister() << 20 | (TestRegisters.getRandomRegister() << 15 | (instr.getFunc3Code() << 12 | (generateRandomBinary(5) << 7 | (instr.getOpCode().getOpCode()))))));
			break;
		case load:
			binaryInstruction = (int) (generateRandomBinary(12) << 20 | (TestRegisters.getRandomRegister() << 15 | (instr.getFunc3Code() << 12 | (TestRegisters.getRandomRegister() << 7 | (instr.getOpCode().getOpCode())))));
			break;
		
		
		case loadfp:
			break;
		case amo:
			break;
		case madd:
			break;
		case miscmem:
			break;
		case msub:
			break;
		case nmadd:
			break;
		case nmsub:
			break;
		case op32:
			break;
		case opfp:
			break;	
		case opimm32:
			break;
		case storefp:
			break;
		case system:
			break;
		case unknown:
			break;
		default:
			break;
		
		}
		
		return binaryInstruction;
	}
	
	public long generateRandomBinary(int length) {
		int binary = 0;
		Random r = new Random();
		if (r.nextBoolean()) {
			binary = 1;
		}
		
		for (int i = 1; i < length ; i++) {
			int tmp = 0;
			if (r.nextBoolean()) {
				tmp = 1;
			}
			binary = binary << 1 | tmp;
			
		}
		//System.out.println("Bin generated (" + length +"): " + binString(length,binary));
		return binary;
	}
	
	public String binString(int length, int binary) {
		return String.format("%"+ length +"s", Integer.toBinaryString(binary)).replace(' ', '0');
	}
		
	private void doSteps(TestInstruction instruction) {
		memory.storeWord(0, new RiscVValue32(instruction.getBinary()),false);
		registers.setPC(new RiscVValue32(0));
		step(instruction);
	}
	
	private void doSteps(TestInstruction instruction, long pcStart) {
		memory.storeWord(pcStart, new RiscVValue32(instruction.getBinary()),false);
		registers.setPC(new RiscVValue32(pcStart));
		step(instruction);
	}
	
	
	private void step(TestInstruction instruction) {
		System.out.println("\n\n\n");
		System.out.println("Test  :");
	
		FetchRiscV fetch = new FetchRiscV(registers, memory, true);
		fetch.doFetch(0);
		InstructionRiscV currentI = fetch.getCurrentInstruction();
		System.out.println("---FETCH-------");
		System.out.println("Test -Pc : " + fetch.getCurrentInstruction().getPC());
		System.out.println("Test -Op : " + fetch.getCurrentInstruction().getOp());
		System.out.println("Test -Func : " + fetch.getCurrentInstruction().getFunc());
		System.out.println("Test -Rs1 : " + fetch.getCurrentInstruction().getRs1());
		System.out.println("Test -Rs2 : " + fetch.getCurrentInstruction().getRs2());
		System.out.println("Test -Rd : " + fetch.getCurrentInstruction().getRd());
		System.out.println("Test -Imm12 : " + fetch.getCurrentInstruction().getImm12());
		System.out.println("Test -Imm20 : " + fetch.getCurrentInstruction().getImm20());
		System.out.println("---------------");
		System.out.println("---DECODE------");
		DecodeRiscV decode = new DecodeRiscV(registers);
		decode.setCurrentInstruction(currentI);
		decode.doStep();
		System.out.println("Test -Value A: s:" +currentI.getValueA().getSignedValue() + " u:" + currentI.getValueA().getUnsignedValue() );
		System.out.println("Test -Value B: s:" +currentI.getValueB().getSignedValue() + " u:" + currentI.getValueB().getUnsignedValue() );
		System.out.println("---------------");
		System.out.println("---EXECUTE-----");
		ExecuteRiscV execute = new ExecuteRiscV(registers, true);
		execute.setCurrentInstruction(currentI);
		execute.doStep();
		System.out.println("Test -ALU : " + currentI.getAluResult());
		
		System.out.println("---------------");
		System.out.println("---MEMORY------");
		MemoryRiscV memoryStep = new MemoryRiscV(registers, memory, true);
		memoryStep.setCurrentInstruction(currentI);
		memoryStep.doStep();
		
		System.out.println("---------------");
		System.out.println("---WRITE BACK--");
		WriteBackRiscV writeBack = new WriteBackRiscV(registers);
		writeBack.setCurrentInstruction(currentI);
		writeBack.doStep();
		System.out.println("Test -Register 1: " + registers.get((int) currentI.getRs1()));
		System.out.println("Test -Register 2: " + registers.get((int) currentI.getRs2()));
		System.out.println("Test -Register Dest: " + registers.get((int) currentI.getRd()));
	}
}
