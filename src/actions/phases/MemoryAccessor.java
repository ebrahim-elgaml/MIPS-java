package actions.phases;

import static actions.toolsets.Maggiebox.mask;
import static actions.toolsets.Toolbox.getRegisterBinary;
import static actions.toolsets.Toolbox.getRegisterValue;

import java.util.Arrays;
import java.util.TreeMap;

import classes.registers.EX_MEM;
import classes.registers.MEM_WB;

public abstract class MemoryAccessor {

	public static void accessMemory(TreeMap<Long, boolean[]> datamemory, EX_MEM exmem_i, MEM_WB memwb_o) {
		memwb_o.aluResult = exmem_i.aluResult;
		Long address = new Long(getRegisterValue(exmem_i.aluResult));
		
		if (exmem_i.memRead) {
			if (datamemory.containsKey(address)) {
				mask(datamemory.get(address), memwb_o.memReadData, 0);
			} else {
				System.out.println("Address not found");
			}
		} else {
			Arrays.fill(memwb_o.memReadData, false);
		}

		if (exmem_i.memWrite) {
			mask(exmem_i.memWriteData, datamemory.get(address), 0);
		}
		memwb_o.rd = exmem_i.rd;
		memwb_o.regWrite = exmem_i.regWrite;
		memwb_o.memToReg = exmem_i.memToReg;
	}

}
