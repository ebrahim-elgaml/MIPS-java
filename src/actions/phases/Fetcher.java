package actions.phases;

import static actions.toolsets.Maggiebox.mask;
import static actions.toolsets.Maggiebox.subset;
import static actions.toolsets.Toolbox.getRegisterValue;
import static actions.toolsets.Toolbox.setRegisterValue;

import java.util.TreeMap;

import classes.registerfile.RegisterFile;
import classes.registers.IF_ID;

// Maggie
public abstract class Fetcher {
	static boolean[] jOP = { false, false, false, false, true, false }; // j = 000010
	static boolean[] jalOP = { false, false, false, false, true, true }; // jal = 000011
	static boolean[] jrOP = { false, false, true, false, false, false }; // jr = 001000

	public static void fetch(TreeMap<Long, boolean[]> instructionmemory, RegisterFile registerfile_i, boolean[] pc_i,
			RegisterFile registerfile_o, boolean[] pc_o, IF_ID ifid_o, boolean[] halted, int[] riseAndFall) {

		boolean[] myInstruction;
		long pc_num = getRegisterValue(pc_i);

		if (!instructionmemory.containsKey(new Long(pc_num))) {
			halted[0] = true;
			return;
		}
		myInstruction = instructionmemory.get(new Long(pc_num));
		if (myInstruction.equals(registerfile_i.zero)) {
			halted[0] = true;
			return;
		}
		ifid_o.instruction = myInstruction;
		boolean[] myInstructionOP = subset(0, 5, myInstruction);

		// React to jump
		if (myInstructionOP.equals(jOP)) {
			mask(subset(6, 31, myInstruction), ifid_o.pcplus4, 4);
			boolean[] mul4 = { false, false };
			mask(mul4, ifid_o.pcplus4, 30);
		} else if (myInstructionOP.equals(jalOP)) {
			setRegisterValue(registerfile_o.ra, pc_num + 4);
			mask(subset(6, 31, myInstruction), ifid_o.pcplus4, 4);
			boolean[] mul4 = { false, false };
			mask(mul4, ifid_o.pcplus4, 30);
		} else if (myInstructionOP.equals(jrOP)) {
			int sourceIndex = (int) getRegisterValue(subset(6, 10, myInstruction));
			setRegisterValue(ifid_o.pcplus4, getRegisterValue(registerfile_i.getRegister(sourceIndex)));
		} else {
			pc_num += 4;
			setRegisterValue(ifid_o.pcplus4, pc_num);
			mask(ifid_o.pcplus4, pc_o, 0);
		}
	}
}
