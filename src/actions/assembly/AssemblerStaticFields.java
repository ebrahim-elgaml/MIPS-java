package actions.assembly;

import java.util.regex.Pattern;

abstract class AssemblerStaticFields {
	static final int inull = Integer.MAX_VALUE;
	// Argument formats
	static final String regRx = "\\$(zero|at|v[01]|a[0-3]|t[0-9]|s[0-7]|k[01]|gp|sp|fp|ra)";
	static final String constRx = "(0|(?:\\-?[1-9][0-9]*))";
	static final String shiftRx = String.format("%1$s\\(%2$s\\)", constRx, regRx);
	// General format
	static final String opRx = "(j|jal|jr|lw|lb|lbu|sw|sb|lui|add|addi|sub|sll|srl|and|nor|beq|bne|slt|sltu)";
	static final String insRx = String.format("^%1$s (?:%2$s|%3$s|%4$s)(?:, (?:%2$s|%3$s|%4$s)){0,2}$", opRx, regRx,
			constRx, shiftRx);
	static final Pattern insPat = Pattern.compile(insRx);
	// R format
	static final String rfOpRx = "(add|sub|and|nor|slt|sltu)";
	static final String rfInsRx = String.format("^%1$s %2$s, %2$s, %2$s$", rfOpRx, regRx);
	static final Pattern rPat = Pattern.compile(rfInsRx);
	// RS format
	static final String rsfOpRx = "(sll|srl)";
	static final String rsfInsRx = String.format("^%1$s %2$s, %2$s, %3$s$", rsfOpRx, regRx, constRx);
	static final Pattern rsPat = Pattern.compile(rsfInsRx);
	// I format
	static final String ifOpRx = "(addi|beq|bne)";
	static final String ifInsRx = String.format("^%1$s %2$s, %2$s, %3$s$", ifOpRx, regRx, constRx);
	static final Pattern iPat = Pattern.compile(ifInsRx);
	// M format
	static final String mfOpRx = "(lw|lb|lbu|sw|sb)";
	static final String mfInsRx = String.format("^%1$s %2$s, %3$s$", mfOpRx, regRx, shiftRx);
	static final Pattern mPat = Pattern.compile(mfInsRx);
	// MI format
	static final String mifOpRx = "(lui)";
	static final String mifInsRx = String.format("^%1$s %2$s, %3$s$", mifOpRx, regRx, constRx);
	static final Pattern miPat = Pattern.compile(mifInsRx);
	// J format
	static final String jfOpRx = "(j|jal)";
	static final String jfInsRx = String.format("^%1$s %2$s$", jfOpRx, constRx);
	static final Pattern jPat = Pattern.compile(jfInsRx);
	// JR format
	static final String jrfOpRx = "(jr)";
	static final String jrfInsRx = String.format("^%1$s %2$s$", jrfOpRx, regRx);
	static final Pattern jrPat = Pattern.compile(jrfInsRx);
	// With function code
	static final String fOpRx = "(add|sub|sll|srl|and|nor|slt|sltu)";
	static final Pattern fPat = Pattern.compile(fOpRx);
	// Starting address declaration format
	static final String saRx = "^.start (0|(?:[1-9][0-9]*))$";
	static final Pattern saPat = Pattern.compile(saRx);
	// Data format
	static final String dRx = "^(0|(?:[1-9][0-9]*)) (0|(?:\\-?[1-9][0-9]*))$";
	static final Pattern dPat = Pattern.compile(dRx);
}
