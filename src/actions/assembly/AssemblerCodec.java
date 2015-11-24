package actions.assembly;

import static actions.assembly.AssemblerInsertion.insertC16;
import static actions.assembly.AssemblerInsertion.insertC26;
import static actions.assembly.AssemblerInsertion.insertC5;
import static actions.assembly.AssemblerInsertion.insertOPAndFunction;
import static actions.assembly.AssemblerInsertion.insertRD;
import static actions.assembly.AssemblerInsertion.insertRS;
import static actions.assembly.AssemblerInsertion.insertRT;
import static actions.assembly.AssemblerStaticFields.iPat;
import static actions.assembly.AssemblerStaticFields.inull;
import static actions.assembly.AssemblerStaticFields.jPat;
import static actions.assembly.AssemblerStaticFields.jrPat;
import static actions.assembly.AssemblerStaticFields.mPat;
import static actions.assembly.AssemblerStaticFields.miPat;
import static actions.assembly.AssemblerStaticFields.rPat;
import static actions.assembly.AssemblerStaticFields.rsPat;
import static actions.assembly.AssemblerStaticFields.saPat;
import static actions.assembly.AssemblerStaticFields.dPat;
import static actions.toolsets.Toolbox.*;

import java.util.regex.Matcher;

class AssemblerCodec {

	// --instruction--
	static void parse(String line, int lineCount, AssemblerInstruction instruction) {
		Matcher matcher;

		manualswitch: {
			matcher = rPat.matcher(line);
			if (matcher.matches()) {
				instruction.opcode = matcher.group(1);
				instruction.rd = matcher.group(2);
				instruction.rs = matcher.group(3);
				instruction.rt = matcher.group(4);
				break manualswitch;
			}
			matcher = rsPat.matcher(line);
			if (matcher.matches()) {
				instruction.opcode = matcher.group(1);
				instruction.rd = matcher.group(2);
				instruction.rt = matcher.group(3);
				instruction.constant5 = Integer.parseInt(matcher.group(4));
				break manualswitch;
			}
			matcher = iPat.matcher(line);
			if (matcher.matches()) {
				instruction.opcode = matcher.group(1);
				instruction.rt = matcher.group(2);
				instruction.rs = matcher.group(3);
				instruction.constant16 = Integer.parseInt(matcher.group(4));
				break manualswitch;
			}
			matcher = mPat.matcher(line);
			if (matcher.matches()) {
				instruction.opcode = matcher.group(1);
				instruction.rt = matcher.group(2);
				instruction.constant16 = Integer.parseInt(matcher.group(3));
				instruction.rs = matcher.group(4);
				break manualswitch;
			}
			matcher = miPat.matcher(line);
			if (matcher.matches()) {
				instruction.opcode = matcher.group(1);
				instruction.rt = matcher.group(2);
				instruction.constant16 = Integer.parseInt(matcher.group(3));
				break manualswitch;
			}
			matcher = jPat.matcher(line);
			if (matcher.matches()) {
				instruction.opcode = matcher.group(1);
				instruction.constant26 = Integer.parseInt(matcher.group(2));
				break manualswitch;
			}
			matcher = jrPat.matcher(line);
			if (matcher.matches()) {
				instruction.opcode = matcher.group(1);
				instruction.rs = matcher.group(2);
				break manualswitch;
			}
			throw new RuntimeException("Syntax error in line " + lineCount + ".");
		}
		return;
	}

	static void encode(AssemblerInstruction instruction, boolean[] instructionCode) {
		insertOPAndFunction(instruction.opcode, instructionCode);
		if (instruction.rs != null) {
			insertRS(instruction.rs, instructionCode);
		}
		if (instruction.rt != null) {
			insertRT(instruction.rt, instructionCode);
		}
		if (instruction.rd != null) {
			insertRD(instruction.rd, instructionCode);
		}
		if (instruction.constant5 != inull) {
			insertC5(instruction.constant5, instructionCode);
		}
		if (instruction.constant16 != inull) {
			insertC16(instruction.constant16, instructionCode);
		}
		if (instruction.constant26 != inull) {
			insertC26(instruction.constant26, instructionCode);
		}
	}

	// --Starting address--
	public static long parseStartingAddress(String line) {
		Matcher matcher = saPat.matcher(line);
		matcher.matches();
		return Long.parseLong(matcher.group(1));
	}

	// --Data--
	public static void parseData(String line, long[] pair) {
		Matcher matcher = dPat.matcher(line);
		matcher.matches();
		pair[0] = Long.parseLong(matcher.group(1));
		pair[1] = Long.parseLong(matcher.group(2));
	}

	public static void encodeData(long value, boolean[] data) {
		setRegisterValue(data, value);
	}
}
