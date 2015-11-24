package actions.toolsets;

public abstract class Youmnabox {

	public static char getFormat(int Opcode) {
		if (Opcode == 0)
			return 'r';
		else if (Opcode == 100101 || Opcode == 100011 || Opcode == 100100)
			return 'o';
		else if (Opcode == 1000 || Opcode == 1111)
			return 'i';
		else if (Opcode == 10)
			return 'j';
		else if (Opcode == 11)
			return 'l';
		else
			return 'm';
	}

	public static int fromTo(boolean[] fetchedInst, int from, int to) {
		String result = "";
		for (int i = from; i < fetchedInst.length || i < to; i++) {
			if (fetchedInst[i])
				result += "1";
			else
				result += "0";
		}
		return Integer.parseInt(result);
	}

	public static int fromBinaryToDecimal(int n) {
		int i = n;
		int j = 0;
		int result = 0;
		while (i != 0) {
			j++;
			i = i / 10;
			if (i == 1) {
				result += Math.pow(2, j);
			}
		}
		return result;
	}
}
