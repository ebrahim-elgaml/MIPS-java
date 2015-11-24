package actions.toolsets;

public abstract class Toolbox {

	public static final long MAX_REGISTER_VALUE = 4294967295l;

	public static long getRegisterValue(boolean[] register) {
		long value = 0;
		long multiplier = 1;
		for (int i = 0; i < register.length; multiplier *= 2, i++) {
			value += (register[register.length-i-1]) ? multiplier : 0;
		}
		
		return value;
	}

	public static void setRegisterValue(boolean[] register, long value) { // Void, since arrays are passed by reference
		assert (value <= MAX_REGISTER_VALUE); // Check for bugs
		for (int i = 0; i < register.length; value /= 2, i++) {
			register[register.length-i-1] = (value % 2 == 1);
		}
	}

	public static String getRegisterBinary(boolean[] register) {
		String binary = "";
		for (int i = 0; i < register.length; i++) {
			binary += ((register[i]) ? 1 : 0);
		}
		return binary;
	}
}
