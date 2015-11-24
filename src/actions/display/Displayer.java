package actions.display;

import java.util.TreeMap;

import actions.toolsets.Toolbox;
import classes.main.Circuit;
import classes.main.CycleCircuit;

public abstract class Displayer {
	public static TreeMap<String, String> display(Circuit it) {
		TreeMap<String, String> result = new TreeMap<String, String>();
		String key = "";
		String value = "";
		// Instuction memory --->> HashMap key will be instuction memory , value will all data separated by /n
		// [0:1010101010]
		key = "Instuction Memory";
		value += "\n";
		for (Long x : it.instructionmemory.keySet()) {
			value += x.longValue();
			value += ": " + Toolbox.getRegisterBinary(it.instructionmemory.get(x));
			value += "\n";
		}
		result.put(key, value);
		key = "";
		value = "";
		// data memory same as instruction memory
		key = "Data Memory";
		value += "\n";
		for (Long x : it.datamemory.keySet()) {
			value += x.longValue();
			value += ": " + Toolbox.getRegisterBinary(it.datamemory.get(x));
			value += "\n";
		}
		result.put(key, value);
		key = "";
		value = "";
		// Register file will be like key = at ----> value 00100010010 and the same for all registers
		result.put("at", Toolbox.getRegisterBinary(it.registerfile_i.at));
		result.put("v0", Toolbox.getRegisterBinary(it.registerfile_i.v0));
		result.put("v1", Toolbox.getRegisterBinary(it.registerfile_i.v1));
		result.put("a0", Toolbox.getRegisterBinary(it.registerfile_i.a0));
		result.put("a1", Toolbox.getRegisterBinary(it.registerfile_i.a1));
		result.put("a2", Toolbox.getRegisterBinary(it.registerfile_i.a2));
		result.put("a3", Toolbox.getRegisterBinary(it.registerfile_i.a3));
		result.put("t0", Toolbox.getRegisterBinary(it.registerfile_i.t0));
		result.put("t1", Toolbox.getRegisterBinary(it.registerfile_i.t1));
		result.put("t2", Toolbox.getRegisterBinary(it.registerfile_i.t2));
		result.put("t3", Toolbox.getRegisterBinary(it.registerfile_i.t3));
		result.put("t4", Toolbox.getRegisterBinary(it.registerfile_i.t4));
		result.put("t5", Toolbox.getRegisterBinary(it.registerfile_i.t5));
		result.put("t6", Toolbox.getRegisterBinary(it.registerfile_i.t6));
		result.put("t7", Toolbox.getRegisterBinary(it.registerfile_i.t7));
		result.put("t8", Toolbox.getRegisterBinary(it.registerfile_i.t8));
		result.put("t9", Toolbox.getRegisterBinary(it.registerfile_i.t9));
		result.put("s0", Toolbox.getRegisterBinary(it.registerfile_i.s0));
		result.put("s1", Toolbox.getRegisterBinary(it.registerfile_i.s1));
		result.put("s2", Toolbox.getRegisterBinary(it.registerfile_i.s2));
		result.put("s3", Toolbox.getRegisterBinary(it.registerfile_i.s3));
		result.put("s4", Toolbox.getRegisterBinary(it.registerfile_i.s4));
		result.put("s5", Toolbox.getRegisterBinary(it.registerfile_i.s5));
		result.put("s6", Toolbox.getRegisterBinary(it.registerfile_i.s6));
		result.put("s7", Toolbox.getRegisterBinary(it.registerfile_i.s7));
		result.put("k0", Toolbox.getRegisterBinary(it.registerfile_i.k0));
		result.put("k1", Toolbox.getRegisterBinary(it.registerfile_i.k1));
		result.put("gp", Toolbox.getRegisterBinary(it.registerfile_i.gp));
		result.put("sp", Toolbox.getRegisterBinary(it.registerfile_i.sp));
		result.put("fp", Toolbox.getRegisterBinary(it.registerfile_i.fp));
		result.put("ra", Toolbox.getRegisterBinary(it.registerfile_i.ra));
		// pc_i and pc_o will be as the registers
		result.put("pc_i", Toolbox.getRegisterBinary(it.pc_i));
		result.put("pc_o", Toolbox.getRegisterBinary(it.pc_o));
		// ifid_i pcplus4 and instruction will be as the registers but will be key = 'ifid_i-PcPlus4' and the second key
		// = 'ifid_i-Instuction'
		result.put("ifid_i-PcPlus4", Toolbox.getRegisterBinary(it.ifid_i.pcplus4));
		result.put("ifid_i-Instruction", Toolbox.getRegisterBinary(it.ifid_i.instruction));
		// idex_i will be the same schema as ifid_i
		result.put("idex_i-AluOp", Toolbox.getRegisterBinary(it.idex_i.aluOP));
		result.put("idex_i-Extendedconstant", Toolbox.getRegisterBinary(it.idex_i.extendedConstant));
		result.put("idex_i-PcPlus4", Toolbox.getRegisterBinary(it.idex_i.pcPlus4));
		result.put("idex_i-Rd", Toolbox.getRegisterBinary(it.idex_i.rd));
		result.put("idex_i-Read1", Toolbox.getRegisterBinary(it.idex_i.read1));
		result.put("idex_i-Read2", Toolbox.getRegisterBinary(it.idex_i.read2));
		result.put("idex_i-Rt", Toolbox.getRegisterBinary(it.idex_i.rt));
		result.put("idex_i-AluSrc", (it.idex_i.aluSrc) ? "1" : "0");
		result.put("idex_i-Branch", (it.idex_i.branch) ? "1" : "0");
		result.put("idex_i-Jump", (it.idex_i.jump) ? "1" : "0");
		result.put("idex_i-Link", (it.idex_i.link) ? "1" : "0");
		result.put("idex_i-MemRead", (it.idex_i.memRead) ? "1" : "0");
		result.put("idex_i-MemToReg", (it.idex_i.memToReg) ? "1" : "0");
		result.put("idex_i-MemWrite", (it.idex_i.memWrite) ? "1" : "0");
		result.put("idex_i-RegisterWrite", (it.idex_i.regWrite) ? "1" : "0");
		result.put("idex_i-RegDest", (it.idex_i.regDest) ? "1" : "0");
		// exmem_i same as ifid_i
		result.put("exmem_i-AluResult", Toolbox.getRegisterBinary(it.exmem_i.aluResult));
		result.put("exmem_i-BranchAddress", Toolbox.getRegisterBinary(it.exmem_i.branchAddress));
		result.put("exmem_i-MemWriteData", Toolbox.getRegisterBinary(it.exmem_i.memWriteData));
		result.put("exmem_i-Rd", Toolbox.getRegisterBinary(it.exmem_i.rd));
		result.put("exmem_i-Branch", (it.exmem_i.branch) ? "1" : "0");
		result.put("exmem_i-MemRead", (it.exmem_i.memRead) ? "1" : "0");
		result.put("exmem_i-MemWrite", (it.exmem_i.memWrite) ? "1" : "0");
		result.put("exmem_i-RegisterWrite", (it.exmem_i.regWrite) ? "1" : "0");
		result.put("exmem_i-Zero", (it.exmem_i.zero) ? "1" : "0");
		result.put("exmem_i-MemToReg", (it.exmem_i.memToReg) ? "1" : "0");
		// memwb_i as ifid_1
		result.put("exmem_i-Rd", Toolbox.getRegisterBinary(it.memwb_i.aluResult));
		result.put("exmem_i-Rd", Toolbox.getRegisterBinary(it.memwb_i.memReadData));
		result.put("exmem_i-Rd", Toolbox.getRegisterBinary(it.memwb_i.rd));
		result.put("exmem_i-Rd", (it.exmem_i.branch) ? "1" : "0");
		result.put("exmem_i-Rd", Toolbox.getRegisterBinary(it.exmem_i.branchAddress));
		return result;
	}
	public static TreeMap<String, String> display(CycleCircuit it) {
		TreeMap<String, String> result = new TreeMap<String, String>();
		String key = "";
		String value = "";
		// Instuction memory --->> HashMap key will be instuction memory , value will all data separated by /n
		// [0:1010101010]
		key = "Instuction Memory";
		value += "\n";
		for (Long x : it.instructionmemory.keySet()) {
			value += x.longValue();
			value += ": " + Toolbox.getRegisterBinary(it.instructionmemory.get(x));
			value += "\n";
		}
		result.put(key, value);
		key = "";
		value = "";
		// data memory same as instruction memory
		key = "Data Memory";
		value += "\n";
		for (Long x : it.datamemory.keySet()) {
			value += x.longValue();
			value += ": " + Toolbox.getRegisterBinary(it.datamemory.get(x));
			value += "\n";
		}
		result.put(key, value);
		key = "";
		value = "";
		// Register file will be like key = at ----> value 00100010010 and the same for all registers
		result.put("at", Toolbox.getRegisterBinary(it.registerfile_i.at));
		result.put("v0", Toolbox.getRegisterBinary(it.registerfile_i.v0));
		result.put("v1", Toolbox.getRegisterBinary(it.registerfile_i.v1));
		result.put("a0", Toolbox.getRegisterBinary(it.registerfile_i.a0));
		result.put("a1", Toolbox.getRegisterBinary(it.registerfile_i.a1));
		result.put("a2", Toolbox.getRegisterBinary(it.registerfile_i.a2));
		result.put("a3", Toolbox.getRegisterBinary(it.registerfile_i.a3));
		result.put("t0", Toolbox.getRegisterBinary(it.registerfile_i.t0));
		result.put("t1", Toolbox.getRegisterBinary(it.registerfile_i.t1));
		result.put("t2", Toolbox.getRegisterBinary(it.registerfile_i.t2));
		result.put("t3", Toolbox.getRegisterBinary(it.registerfile_i.t3));
		result.put("t4", Toolbox.getRegisterBinary(it.registerfile_i.t4));
		result.put("t5", Toolbox.getRegisterBinary(it.registerfile_i.t5));
		result.put("t6", Toolbox.getRegisterBinary(it.registerfile_i.t6));
		result.put("t7", Toolbox.getRegisterBinary(it.registerfile_i.t7));
		result.put("t8", Toolbox.getRegisterBinary(it.registerfile_i.t8));
		result.put("t9", Toolbox.getRegisterBinary(it.registerfile_i.t9));
		result.put("s0", Toolbox.getRegisterBinary(it.registerfile_i.s0));
		result.put("s1", Toolbox.getRegisterBinary(it.registerfile_i.s1));
		result.put("s2", Toolbox.getRegisterBinary(it.registerfile_i.s2));
		result.put("s3", Toolbox.getRegisterBinary(it.registerfile_i.s3));
		result.put("s4", Toolbox.getRegisterBinary(it.registerfile_i.s4));
		result.put("s5", Toolbox.getRegisterBinary(it.registerfile_i.s5));
		result.put("s6", Toolbox.getRegisterBinary(it.registerfile_i.s6));
		result.put("s7", Toolbox.getRegisterBinary(it.registerfile_i.s7));
		result.put("k0", Toolbox.getRegisterBinary(it.registerfile_i.k0));
		result.put("k1", Toolbox.getRegisterBinary(it.registerfile_i.k1));
		result.put("gp", Toolbox.getRegisterBinary(it.registerfile_i.gp));
		result.put("sp", Toolbox.getRegisterBinary(it.registerfile_i.sp));
		result.put("fp", Toolbox.getRegisterBinary(it.registerfile_i.fp));
		result.put("ra", Toolbox.getRegisterBinary(it.registerfile_i.ra));
		// pc_i and pc_o will be as the registers
		result.put("pc_i", Toolbox.getRegisterBinary(it.pc_i));
		// ifid_i pcplus4 and instruction will be as the registers but will be key = 'ifid_i-PcPlus4' and the second key
		// = 'ifid_i-Instuction'
		result.put("ifid_i-PcPlus4", Toolbox.getRegisterBinary(it.ifid_i.pcplus4));
		result.put("ifid_i-Instruction", Toolbox.getRegisterBinary(it.ifid_i.instruction));
		// idex_i will be the same schema as ifid_i
		result.put("idex_i-AluOp", Toolbox.getRegisterBinary(it.idex_i.aluOP));
		result.put("idex_i-Extendedconstant", Toolbox.getRegisterBinary(it.idex_i.extendedConstant));
		result.put("idex_i-PcPlus4", Toolbox.getRegisterBinary(it.idex_i.pcPlus4));
		result.put("idex_i-Rd", Toolbox.getRegisterBinary(it.idex_i.rd));
		result.put("idex_i-Read1", Toolbox.getRegisterBinary(it.idex_i.read1));
		result.put("idex_i-Read2", Toolbox.getRegisterBinary(it.idex_i.read2));
		result.put("idex_i-Rt", Toolbox.getRegisterBinary(it.idex_i.rt));
		result.put("idex_i-AluSrc", (it.idex_i.aluSrc) ? "1" : "0");
		result.put("idex_i-Branch", (it.idex_i.branch) ? "1" : "0");
		result.put("idex_i-Jump", (it.idex_i.jump) ? "1" : "0");
		result.put("idex_i-Link", (it.idex_i.link) ? "1" : "0");
		result.put("idex_i-MemRead", (it.idex_i.memRead) ? "1" : "0");
		result.put("idex_i-MemToReg", (it.idex_i.memToReg) ? "1" : "0");
		result.put("idex_i-MemWrite", (it.idex_i.memWrite) ? "1" : "0");
		result.put("idex_i-RegisterWrite", (it.idex_i.regWrite) ? "1" : "0");
		result.put("idex_i-RegDest", (it.idex_i.regDest) ? "1" : "0");
		// exmem_i same as ifid_i
		result.put("exmem_i-AluResult", Toolbox.getRegisterBinary(it.exmem_i.aluResult));
		result.put("exmem_i-BranchAddress", Toolbox.getRegisterBinary(it.exmem_i.branchAddress));
		result.put("exmem_i-MemWriteData", Toolbox.getRegisterBinary(it.exmem_i.memWriteData));
		result.put("exmem_i-Rd", Toolbox.getRegisterBinary(it.exmem_i.rd));
		result.put("exmem_i-Branch", (it.exmem_i.branch) ? "1" : "0");
		result.put("exmem_i-MemRead", (it.exmem_i.memRead) ? "1" : "0");
		result.put("exmem_i-MemWrite", (it.exmem_i.memWrite) ? "1" : "0");
		result.put("exmem_i-RegisterWrite", (it.exmem_i.regWrite) ? "1" : "0");
		result.put("exmem_i-Zero", (it.exmem_i.zero) ? "1" : "0");
		result.put("exmem_i-MemToReg", (it.exmem_i.memToReg) ? "1" : "0");
		// memwb_i as ifid_1
		result.put("exmem_i-Rd", Toolbox.getRegisterBinary(it.memwb_i.aluResult));
		result.put("exmem_i-Rd", Toolbox.getRegisterBinary(it.memwb_i.memReadData));
		result.put("exmem_i-Rd", Toolbox.getRegisterBinary(it.memwb_i.rd));
		result.put("exmem_i-Rd", (it.exmem_i.branch) ? "1" : "0");
		result.put("exmem_i-Rd", Toolbox.getRegisterBinary(it.exmem_i.branchAddress));
		return result;
	}



}
