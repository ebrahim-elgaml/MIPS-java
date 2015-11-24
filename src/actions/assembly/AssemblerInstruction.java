package actions.assembly;

import static actions.assembly.AssemblerStaticFields.inull;

class AssemblerInstruction {
	String opcode;
	String rs;
	String rt;
	String rd;
	int constant5;
	int constant16;
	int constant26;

	void nullify() {
		opcode = null;
		rs = null;
		rt = null;
		rd = null;
		constant5 = inull;
		constant16 = inull;
		constant26 = inull;
	}
}
