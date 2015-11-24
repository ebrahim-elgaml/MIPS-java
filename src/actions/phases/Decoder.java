package actions.phases;

import static actions.toolsets.Maggiebox.mask;
import static actions.toolsets.Maggiebox.subset;
import static actions.toolsets.Youmnabox.fromTo;
import static actions.toolsets.Youmnabox.getFormat;
import static actions.toolsets.Toolbox.*;
import actions.toolsets.Toolbox;
import classes.registerfile.RegisterFile;
import classes.registers.ID_EX;
import classes.registers.IF_ID;

// Youmna
public abstract class Decoder {

	public static void decode(RegisterFile registerfile_i, IF_ID ifid_i, ID_EX idex_o) {
		ControlSignals controlSignals = new ControlSignals();
		boolean[] instruction = ifid_i.instruction;
		idex_o.pcPlus4 = ifid_i.pcplus4;
		idex_o.read1 = registerfile_i.getRegister((int) Toolbox.getRegisterValue(subset(6, 10, instruction)));
		idex_o.read2 = registerfile_i.getRegister((int) Toolbox.getRegisterValue(subset(11, 15, instruction)));
		signExtend(subset(16, 31, instruction), idex_o.extendedConstant);
		idex_o.rd = subset(16, 20, instruction);
		idex_o.rt = subset(11, 15, instruction);
		control(subset(0, 5, instruction), controlSignals);
		idex_o.regWrite = controlSignals.regWrite;
		idex_o.memToReg = controlSignals.memToReg;
		idex_o.branch = controlSignals.branch;
		idex_o.memWrite = controlSignals.memWrite;
		idex_o.memRead = controlSignals.memRead;
		idex_o.aluSrc = controlSignals.aluSrc;
		idex_o.regDest = controlSignals.regDest;
		idex_o.aluOP = controlSignals.aluOP.clone();
		idex_o.jump = controlSignals.jump;
		idex_o.link = controlSignals.link;
	}

	public static void signExtend(boolean[] constant, boolean[] extendedConstant) {
		mask(constant, extendedConstant, 16);
		for (int i = 0; i < 16; extendedConstant[i] = constant[0], i++)
			;
	}

	public static void control(boolean[] opcode, ControlSignals controlSignals) {
		char type = getFormat(fromTo(opcode, 0, opcode.length));
		if (type == 'r') {
			controlSignals.regWrite = true;
			controlSignals.memToReg = false;
			controlSignals.regDest = true;
			controlSignals.branch = false;
			controlSignals.memWrite = false;
			controlSignals.memRead = false;
			controlSignals.aluSrc = false;
			controlSignals.aluOP[0] = true;
			controlSignals.aluOP[1] = false;
			controlSignals.jump = false;
			controlSignals.link = false;
		} else if (type == 'o') {
			controlSignals.regWrite = true;
			controlSignals.memToReg = true;
			controlSignals.regDest = false;
			controlSignals.branch = false;
			controlSignals.memWrite = false;
			controlSignals.memRead = true;
			controlSignals.aluSrc = true;
			controlSignals.aluOP[0] = false;
			controlSignals.aluOP[1] = false;
			controlSignals.jump = false;
			controlSignals.link = false;
		} else if (type == 's') {
			controlSignals.regWrite = false;
			controlSignals.memToReg = false;
			controlSignals.regDest = false;
			controlSignals.branch = false;
			controlSignals.memWrite = true;
			controlSignals.memRead = false;
			controlSignals.aluSrc = true;
			controlSignals.aluOP[0] = false;
			controlSignals.aluOP[1] = false;
			controlSignals.jump = false;
			controlSignals.link = false;
		} else if (type == 'b') {
			controlSignals.regWrite = false;
			controlSignals.memToReg = false;
			controlSignals.regDest = false;
			controlSignals.branch = true;
			controlSignals.memWrite = false;
			controlSignals.memRead = false;
			controlSignals.aluSrc = false;
			controlSignals.aluOP[0] = false;
			controlSignals.aluOP[1] = true;
			controlSignals.jump = false;
			controlSignals.link = false;
		} else if (type == 'j') {
			controlSignals.regWrite = false;
			controlSignals.memToReg = false;
			controlSignals.regDest = false;
			controlSignals.branch = false;
			controlSignals.memWrite = false;
			controlSignals.memRead = false;
			controlSignals.aluSrc = false;
			controlSignals.aluOP[0] = false;
			controlSignals.aluOP[1] = false;
			controlSignals.jump = true;
			controlSignals.link = false;
		} else if (type == 'l') {
			controlSignals.regWrite = true;
			controlSignals.memToReg = false;
			controlSignals.regDest = false;
			controlSignals.branch = false;
			controlSignals.memWrite = false;
			controlSignals.memRead = false;
			controlSignals.aluSrc = false;
			controlSignals.aluOP[0] = false;
			controlSignals.aluOP[1] = false;
			controlSignals.jump = true;
			controlSignals.link = true;
		} else if (type == 'i') {
			controlSignals.regWrite = true;
			controlSignals.memToReg = false;
			controlSignals.regDest = false;
			controlSignals.branch = false;
			controlSignals.memWrite = false;
			controlSignals.memRead = false;
			controlSignals.aluSrc = true;
			controlSignals.aluOP[0] = false;
			controlSignals.aluOP[1] = false;
			controlSignals.jump = false;
			controlSignals.link = false;
		}
	}

	private static class ControlSignals {
		// WB control
		boolean regWrite;
		public boolean memToReg;
		// MEM control
		boolean branch;
		boolean memWrite;
		boolean memRead;
		// EX control
		boolean aluSrc;
		boolean regDest;
		boolean[] aluOP;
		// Manzar
		boolean jump;
		boolean link;
		
		ControlSignals() {
			aluOP = new boolean[2];
		}
	}
}
