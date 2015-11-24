package actions.assembly;

import static actions.assembly.AssemblerCodec.encode;
import static actions.assembly.AssemblerCodec.parse;
import static actions.assembly.AssemblerCodec.parseStartingAddress;
import static actions.assembly.AssemblerCodec.parseData;
import static actions.assembly.AssemblerCodec.encodeData;
import static actions.assembly.AssemblerStaticFields.insPat;
import static actions.assembly.AssemblerStaticFields.dPat;
import static actions.assembly.AssemblerStaticFields.saPat;
import static actions.toolsets.Toolbox.setRegisterValue;

import java.util.Scanner;
import java.util.TreeMap;

public abstract class Assembler {

	public static void assembleInstructions(Scanner reader, TreeMap<Long, boolean[]> memory, boolean[] pc_o) {
		String line = reader.nextLine();
		
		if (!saPat.matcher(line).matches()) {
			throw new RuntimeException("Syntax error in line 1.");
		}
		long startingAddress = parseStartingAddress(line);
		setRegisterValue(pc_o, startingAddress);
		AssemblerInstruction instruction = new AssemblerInstruction();
		boolean[] instructionCode;

		for (int lineCount = 2; reader.hasNextLine(); lineCount++) {
			line = reader.nextLine();
			instructionCode = new boolean[32];
			if (line.equals("")) {
				continue;
			}
			if(line.equals("-")) {
				startingAddress += 4;
				continue;
			}
			if (!insPat.matcher(line).matches()) {
				throw new RuntimeException("Syntax error in line " + lineCount + ".");
			}
			instruction.nullify();
			parse(line, lineCount, instruction);
			encode(instruction, instructionCode);
			memory.put(new Long(startingAddress), instructionCode);
			startingAddress += 4;
		}
	}

	public static void assembleData(Scanner reader, TreeMap<Long, boolean[]> datamemory) {
		String line;
		boolean[] data;
		long[] pair = new long[2];
		for (; reader.hasNext();) {
			line = reader.nextLine();
			data = new boolean[32];
			if (!dPat.matcher(line).matches()) {
				throw new RuntimeException("Syntax error in data file.");
			}
			parseData(line, pair);
			encodeData(pair[1], data);
			datamemory.put(new Long(pair[0]), data);
		}
	}
}
