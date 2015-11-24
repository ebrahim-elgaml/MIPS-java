package actions.phases;

import java.util.Arrays;

import classes.registerfile.RegisterFile;
import classes.registers.MEM_WB;
import static actions.toolsets.Toolbox.getRegisterValue;
import static actions.toolsets.Maggiebox.mask;


public abstract class BackWriter {

	public static void writeBack(MEM_WB memwb_i, RegisterFile registerfile_o) {
		if(memwb_i.regWrite) {
			boolean[] data = memwb_i.memToReg? memwb_i.memReadData : memwb_i.aluResult;
			mask(data, registerfile_o.getRegister((int) getRegisterValue(memwb_i.rd)), 0);
		}
	}

}
