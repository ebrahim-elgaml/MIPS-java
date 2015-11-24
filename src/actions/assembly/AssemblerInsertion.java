package actions.assembly;

import static actions.assembly.AssemblerDictionary.fMap;
import static actions.assembly.AssemblerDictionary.opMap;
import static actions.assembly.AssemblerDictionary.rMap;
import static actions.assembly.AssemblerStaticFields.fPat;

class AssemblerInsertion {
	static void insertOPAndFunction(String opcode, boolean[] instructionCode) {
		char[] bits;
		bits = opMap.get(opcode).toCharArray();
		for (int i = 0; i < 6; i++) {
			instructionCode[i] = bits[i] == '1';
		}
		if (fPat.matcher(opcode).matches()) {
			bits = fMap.get(opcode).toCharArray();
			for (int i = 0; i < 6; i++) {
				instructionCode[26 + i] = bits[i] == '1';
			}
		}
	}

	static void insertRS(String rs, boolean[] instructionCode) {
		char[] bits;
		bits = rMap.get(rs).toCharArray();
		for (int i = 0; i < 5; i++) {
			instructionCode[6 + i] = bits[i] == '1';
		}
	}

	static void insertRT(String rt, boolean[] instructionCode) {
		char[] bits;
		bits = rMap.get(rt).toCharArray();
		for (int i = 0; i < 5; i++) {
			instructionCode[11 + i] = bits[i] == '1';
		}
	}

	static void insertRD(String rd, boolean[] instructionCode) {
		char[] bits;
		bits = rMap.get(rd).toCharArray();
		for (int i = 0; i < 5; i++) {
			instructionCode[16 + i] = bits[i] == '1';
		}
	}

	static void insertC5(int constant5, boolean[] instructionCode) {
		assert (constant5 >= 0);
		for (int i = 0; i < 5; constant5 /= 2, i++) {
			instructionCode[25 - i] = (constant5 % 2 == 1);
		}
	}

	static void insertC16(int constant16, boolean[] instructionCode) {
		boolean complement = false;
		if (constant16 < 0) {
			complement = true;
			constant16 *= -1;
			constant16--;
			instructionCode[16] = true;
		}

		for (int i = 0; i < 15; constant16 /= 2, i++) {
			instructionCode[31 - i] = complement ^ (constant16 % 2 == 1);
		}
	}

	static void insertC26(int constant26, boolean[] instructionCode) {
		assert (constant26 >= 0);
		for (int i = 0; i < 26; constant26 /= 2, i++) {
			instructionCode[31 - i] = (constant26 % 2 == 1);
		}
	}
}
