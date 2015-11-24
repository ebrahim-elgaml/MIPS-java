package actions.toolsets;

public abstract class Myriamebox {
	
	public static boolean[] myr(boolean[] a, boolean[] b, boolean[] aluControl) {
		boolean[] result = new boolean[32];
		String aluI = "";
		for (int i = 0; i < aluControl.length; i++)
			aluI += aluControl[i]? '1':'0';
		if (aluI.equals("0010"))
			result = adder(a, b);
		if (aluI.equals("0110"))
			result = subtract(a, b);
		if (aluI.equals("0000"))
			result = and(a, b);
		if (aluI.equals("0001"))
			result = or(a, b);
		if (aluI.equals("0111")) {
			boolean[] temp = subtract(a, b);
			if (!temp[0])
				result[31] = true;
		}
		return result;
	}
	
	public static boolean[] getALUControl(boolean[] function, boolean[] aluOP) {
		String fun = "";
		String alu = "";
		for (int i = 0; i < 6; i++) {
			if (function[i])
				fun = fun + "1";
			else
				fun = fun + "0";
		}
		for (int i = 0; i < 2; i++) {
			if (aluOP[i])
				alu = alu + "1";
			else
				alu = alu + "0";
		}
		boolean[] aluControl = new boolean[4];
		if (alu.equals("00")) {
			aluControl[0] = false;
			aluControl[1] = false;
			aluControl[2] = true;
			aluControl[3] = false;
		} else if (alu.equals("01")) {
			aluControl[0] = false;
			aluControl[1] = true;
			aluControl[2] = true;
			aluControl[3] = false;

		} else if (alu.equals("10")) {
			if (fun.equals("100000")) {
				aluControl[0] = false;
				aluControl[1] = false;
				aluControl[2] = true;
				aluControl[3] = false;
			} else if (fun.equals("100100")) {
				aluControl[0] = false;
				aluControl[1] = false;
				aluControl[2] = false;
				aluControl[3] = false;
			} else if (fun.equals("100101")) {
				aluControl[0] = false;
				aluControl[1] = false;
				aluControl[2] = false;
				aluControl[3] = true;
			} else if (fun.equals("101010")) {
				aluControl[0] = false;
				aluControl[1] = true;
				aluControl[2] = true;
				aluControl[3] = true;
			} else if (fun.equals("100010")) {
				aluControl[0] = false;
				aluControl[1] = true;
				aluControl[2] = true;
				aluControl[3] = false;
			}
		}
		return aluControl;
	}

	public static boolean[] mux(boolean signal, boolean[] input0, boolean[] input1) {
		if (signal)
			return input1;
		return input0;
	}

	public static boolean[] shiftLeft(boolean[] input) {
		boolean[] result = new boolean[32];
		int index = 0;
		for (int i = 2; i < 30; i++) {
			result[index] = input[i];
			index++;
		}
		result[30] = false;
		result[31] = false;
		return result;
	}

	public static boolean[] adder(boolean[] a, boolean[] b) {
		boolean[] result = new boolean[32];
		boolean overflow = false;
		boolean[] x = new boolean[32];
		boolean[] y = new boolean[32];
		for (int i = 0; i < 32; i++) {
			x[i] = a[i];
			y[i] = b[i];
		}

		for (int i = a.length - 1; i >= 0; i--) {
			if (a[i] && b[i] && overflow) {
				result[i] = true;
				overflow = true;
			} else if ((a[i] && !b[i] && !overflow) || (!a[i] && b[i] && !overflow)) {
				result[i] = true;
				overflow = false;
			} else if ((a[i] && !b[i] && overflow) || (!a[i] && b[i] && overflow)) {
				result[i] = false;
				overflow = true;
			} else if (!a[i] && !b[i] && !overflow) {
				result[i] = false;
				overflow = false;
			} else if (!a[i] && !b[i] && overflow) {
				result[i] = true;
				overflow = false;
			} else if (a[i] && b[i] & !overflow) {
				result[i] = false;
				overflow = true;
			}
		}
		return result;
	}

	public static boolean[] and(boolean[] a, boolean[] b) {
		boolean[] result = new boolean[32];
		for (int i = 0; i < 32; i++)
			result[i] = a[i] && b[i];
		return result;
	}

	public static boolean[] or(boolean[] a, boolean[] b) {
		boolean[] result = new boolean[32];
		for (int i = 0; i < a.length; i++)
			result[i] = a[i] || b[i];
		return result;
	}

	public static boolean[] subtract(boolean[] a, boolean[] b) {
		boolean[] x = new boolean[32];
		boolean[] y = new boolean[32];
		for (int i = 0; i < 32; i++) {
			x[i] = a[i];
			y[i] = b[i];
		}
		for (int i = 0; i < 32; i++)
			y[i] = !y[i];
		boolean[] temp = new boolean[32];
		temp[31] = true;
		boolean[] temp2 = adder(y, temp);
		return adder(temp2, x);
	}

	public static boolean zeroSignal(boolean[] x, boolean[] y) {
		boolean[] z = subtract(x, y);
		for (int i = 0; i < z.length; i++) {
			if (z[i])
				return false;
		}
		return true;
	}
}
