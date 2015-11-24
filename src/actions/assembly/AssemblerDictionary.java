package actions.assembly;

import java.util.HashMap;

class AssemblerDictionary {
	static HashMap<String, String> opMap = new HashMap<String, String>();
	static HashMap<String, String> fMap = new HashMap<String, String>();
	static HashMap<String, String> rMap = new HashMap<String, String>();
	static {
		opMap.put("add", "000000");
		opMap.put("sub", "000000");
		opMap.put("sll", "000000");
		opMap.put("srl", "000000");
		opMap.put("and", "000000");
		opMap.put("nor", "000000");
		opMap.put("slt", "000000");
		opMap.put("sltu", "000000");
		opMap.put("j", "000010");
		opMap.put("jal", "000011");
		opMap.put("jr", "001000");
		opMap.put("lw", "100011");
		opMap.put("lb", "100000");
		opMap.put("lbu", "100100");
		opMap.put("sw", "101011");
		opMap.put("sb", "101000");
		opMap.put("lui", "001111");
		opMap.put("addi", "001000");
		opMap.put("beq", "000100");
		opMap.put("bne", "000101");
		// Function codes
		fMap.put("add", "100000");
		fMap.put("sub", "100010");
		fMap.put("sll", "000000");
		fMap.put("srl", "000010");
		fMap.put("and", "100100");
		fMap.put("nor", "100111");
		fMap.put("slt", "101010");
		fMap.put("sltu", "101001");
		// Register codes
		rMap.put("zero", "00000");
		rMap.put("at", "00001");
		rMap.put("v0", "00010");
		rMap.put("v1", "00011");
		rMap.put("a0", "00100");
		rMap.put("a1", "00101");
		rMap.put("a2", "00110");
		rMap.put("a3", "00111");
		rMap.put("t0", "01000");
		rMap.put("t1", "01001");
		rMap.put("t2", "01010");
		rMap.put("t3", "01011");
		rMap.put("t4", "01100");
		rMap.put("t5", "01101");
		rMap.put("t6", "01110");
		rMap.put("t7", "01111");
		rMap.put("s0", "10000");
		rMap.put("s1", "10001");
		rMap.put("s2", "10010");
		rMap.put("s3", "10011");
		rMap.put("s4", "10100");
		rMap.put("s5", "10101");
		rMap.put("s6", "10110");
		rMap.put("s7", "10111");
		rMap.put("t8", "11000");
		rMap.put("t9", "11001");
		rMap.put("k0", "11010");
		rMap.put("k1", "11011");
		rMap.put("gp", "11100");
		rMap.put("sp", "11101");
		rMap.put("fp", "11110");
		rMap.put("ra", "11111");
	}
}
