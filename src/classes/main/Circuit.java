package classes.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

import actions.assembly.Assembler;
import actions.display.Displayer;
import actions.phases.BackWriter;
import actions.phases.Decoder;
import actions.phases.Executor;
import actions.phases.Fetcher;
import actions.phases.MemoryAccessor;
import classes.registerfile.RegisterFile;
import classes.registers.EX_MEM;
import classes.registers.ID_EX;
import classes.registers.IF_ID;
import classes.registers.MEM_WB;

public class Circuit {

	// --Variables--
	public TreeMap<Long, boolean[]> instructionmemory;
	public TreeMap<Long, boolean[]> datamemory;
	public RegisterFile registerfile_i;
	RegisterFile registerfile_o;
	public boolean[] pc_i;
	public boolean[] pc_o;
	public IF_ID ifid_i;
	IF_ID ifid_o;
	public ID_EX idex_i;
	ID_EX idex_o;
	public EX_MEM exmem_i;
	EX_MEM exmem_o;
	public MEM_WB memwb_i;
	MEM_WB memwb_o;
	public boolean[] halted;
	public int[] riseAndFall;

	// --Constructor--
	public Circuit() {
		instructionmemory = new TreeMap<Long, boolean[]>();
		datamemory = new TreeMap<Long, boolean[]>();
		registerfile_i = new RegisterFile();
		registerfile_o = new RegisterFile();
		pc_i = new boolean[32];
		pc_o = new boolean[32];
		ifid_i = new IF_ID();
		ifid_o = new IF_ID();
		idex_i = new ID_EX();
		idex_o = new ID_EX();
		exmem_i = new EX_MEM();
		exmem_o = new EX_MEM();
		memwb_i = new MEM_WB();
		memwb_o = new MEM_WB();
		halted = new boolean[1];
		riseAndFall = new int[1];
	}

	// --Internal methods--
	private void updateRegisters() {
		for(int i = 0; i < 32; pc_i[i] = pc_o[i], i++);
		ifid_i.copy(ifid_o);
		idex_i.copy(idex_o);
		memwb_i.copy(memwb_o);
		exmem_i.copy(exmem_o);
		registerfile_i.copy(registerfile_o);
	}

	void riseAndFall() {
		if (!halted[0] && riseAndFall[0] < 4) {
			riseAndFall[0]++;
		}
		if (halted[0] && riseAndFall[0] > 0) {
			riseAndFall[0]--;
		}
	}

	// --Interface methods--
	public void init() throws FileNotFoundException {
		Assembler.assembleInstructions(new Scanner(new FileInputStream("res/ins.txt")), instructionmemory, pc_o);
		Assembler.assembleData(new Scanner(new FileInputStream("res/dat.txt")), datamemory);
		registerfile_o.gp[0] = true;
		Arrays.fill(registerfile_o.sp, true);
		Arrays.fill(registerfile_o.fp, true);
		updateRegisters();
	}

	public void tick() {
		if (!halted[0])
			Fetcher.fetch(instructionmemory, registerfile_i, pc_i, registerfile_o, pc_o, ifid_o, halted, riseAndFall);
		if (!halted[0] ? riseAndFall[0] > 0 : riseAndFall[0] > 3)
			Decoder.decode(registerfile_i, ifid_i, idex_o);
		if (!halted[0] ? riseAndFall[0] > 1 : riseAndFall[0] > 2)
			Executor.execute(idex_i, pc_o, exmem_o);
		if (!halted[0] ? riseAndFall[0] > 2 : riseAndFall[0] > 1)
			MemoryAccessor.accessMemory(datamemory, exmem_i, memwb_o);
		if (!halted[0] ? riseAndFall[0] > 3 : riseAndFall[0] > 0)
			BackWriter.writeBack(memwb_i, registerfile_o);
		//if (!halted[0] || riseAndFall > 0)
			updateRegisters();
		riseAndFall();
	}

	public TreeMap<String, String> display() {
		return Displayer.display(this);
	}

	public boolean halted() {
		return halted[0] && riseAndFall[0] == 0;
	}
}