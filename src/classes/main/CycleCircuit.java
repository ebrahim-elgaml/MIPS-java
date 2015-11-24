package classes.main;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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


public class CycleCircuit {

		// --Variables--
		public TreeMap<Long, boolean[]> instructionmemory;
		public TreeMap<Long, boolean[]> datamemory;
		public RegisterFile registerfile_i;
		public boolean[] pc_i;
		public IF_ID ifid_i;
		public ID_EX idex_i;
		public EX_MEM exmem_i;
		public MEM_WB memwb_i;
		public boolean[] halted;
		public int[] riseAndFall;
		public int clock ;


		// --Constructor--
		public CycleCircuit() {
			instructionmemory = new TreeMap<Long, boolean[]>();
			datamemory = new TreeMap<Long, boolean[]>();
			registerfile_i = new RegisterFile();
			pc_i = new boolean[32];
			ifid_i = new IF_ID();
			idex_i = new ID_EX();
			exmem_i = new EX_MEM();
			memwb_i = new MEM_WB();
			halted = new boolean[1];
			clock = 0; 
		}


		// --Interface methods--
		public void init() throws FileNotFoundException {
			Assembler.assembleInstructions(new Scanner(new FileInputStream("res/ins.txt")), instructionmemory, pc_i);
			Assembler.assembleData(new Scanner(new FileInputStream("res/dat.txt")), datamemory);
		}

		public void tick() {
			if (!halted[0]){
				Fetcher.fetch(instructionmemory, registerfile_i, pc_i, registerfile_i, pc_i, ifid_i, halted, riseAndFall);
				Decoder.decode(registerfile_i, ifid_i, idex_i);
				Executor.execute(idex_i, pc_i, exmem_i);
				MemoryAccessor.accessMemory(datamemory, exmem_i, memwb_i);
				BackWriter.writeBack(memwb_i, registerfile_i);
			   clock++;
			}
			}

		public TreeMap<String, String> display() {
			return Displayer.display(this);
		}

		public boolean halted() {
			return halted[0] ;
		}
	}

