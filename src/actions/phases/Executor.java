package actions.phases;

import static actions.toolsets.Maggiebox.*;
import static actions.toolsets.Toolbox.*;
import static actions.toolsets.Myriamebox.getALUControl;
import static actions.toolsets.Myriamebox.myr;
import static actions.toolsets.Myriamebox.zeroSignal;
import classes.registers.EX_MEM;
import classes.registers.ID_EX;


public abstract class Executor {

	public static void execute(ID_EX idex_i, boolean[] pc_o, EX_MEM exmem_o) {
		boolean[] aluArg2 = idex_i.aluSrc? idex_i.extendedConstant: idex_i.read2;
		exmem_o.zero = zeroSignal(idex_i.read1, aluArg2);
		boolean[] function = subset(26, 31, idex_i.extendedConstant);
		boolean[] aluControl = getALUControl(function, idex_i.aluOP);
		exmem_o.aluResult = myr(idex_i.read1, aluArg2, aluControl);
		exmem_o.memWriteData = idex_i.read2;
		mask((idex_i.regDest? idex_i.rd: idex_i.rt), exmem_o.rd, 0);
		exmem_o.regWrite = idex_i.regWrite;
		exmem_o.memToReg = idex_i.memToReg;
		exmem_o.branch = idex_i.branch;
		exmem_o.memWrite = idex_i.memWrite;
		exmem_o.memRead = idex_i.memRead;
	}
}
