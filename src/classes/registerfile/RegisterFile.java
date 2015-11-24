package classes.registerfile;

public class RegisterFile {
	public final boolean[] zero = new boolean[] { false, false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false };
	public boolean[] at;
	public boolean[] v0;
	public boolean[] v1;
	public boolean[] a0;
	public boolean[] a1;
	public boolean[] a2;
	public boolean[] a3;
	public boolean[] t0;
	public boolean[] t1;
	public boolean[] t2;
	public boolean[] t3;
	public boolean[] t4;
	public boolean[] t5;
	public boolean[] t6;
	public boolean[] t7;
	public boolean[] t8;
	public boolean[] t9;
	public boolean[] s0;
	public boolean[] s1;
	public boolean[] s2;
	public boolean[] s3;
	public boolean[] s4;
	public boolean[] s5;
	public boolean[] s6;
	public boolean[] s7;
	public boolean[] k0;
	public boolean[] k1;
	public boolean[] gp;
	public boolean[] sp;
	public boolean[] fp;
	public boolean[] ra;

	public RegisterFile() {
		at = new boolean[32];
		v0 = new boolean[32];
		v1 = new boolean[32];
		a0 = new boolean[32];
		a1 = new boolean[32];
		a2 = new boolean[32];
		a3 = new boolean[32];
		t0 = new boolean[32];
		t1 = new boolean[32];
		t2 = new boolean[32];
		t3 = new boolean[32];
		t4 = new boolean[32];
		t5 = new boolean[32];
		t6 = new boolean[32];
		t7 = new boolean[32];
		s0 = new boolean[32];
		s1 = new boolean[32];
		s2 = new boolean[32];
		s3 = new boolean[32];
		s4 = new boolean[32];
		s5 = new boolean[32];
		s6 = new boolean[32];
		s7 = new boolean[32];
		t8 = new boolean[32];
		t9 = new boolean[32];
		k0 = new boolean[32];
		k1 = new boolean[32];
		gp = new boolean[32];
		sp = new boolean[32];
		fp = new boolean[32];
		ra = new boolean[32];
	}

	public void copy(RegisterFile source) {
		for (int j = 1; j < 32; j++) {
			for (int i = 0; i < 32; i++) {
				this.getRegister(j)[i] = source.getRegister(j)[i];
			}
		}
	}

	public boolean[] getRegister(int index) {
		boolean[] register;
		switch (index) {
		case 0:
			return zero;
		case 1:
			return at;
		case 2:
			return v0;
		case 3:
			return v1;
		case 4:
			return a0;
		case 5:
			return a1;
		case 6:
			return a2;
		case 7:
			return a3;
		case 8:
			return t0;
		case 9:
			return t1;
		case 10:
			return t2;
		case 11:
			return t3;
		case 12:
			return t4;
		case 13:
			return t5;
		case 14:
			return t6;
		case 15:
			return t7;
		case 16:
			return s0;
		case 17:
			return s1;
		case 18:
			return s2;
		case 19:
			return s3;
		case 20:
			return s4;
		case 21:
			return s5;
		case 22:
			return s6;
		case 23:
			return s7;
		case 24:
			return t8;
		case 25:
			return t9;
		case 26:
			return k0;
		case 27:
			return k1;
		case 28:
			return gp;
		case 29:
			return sp;
		case 30:
			return fp;
		case 31:
			return ra;
		}
		return null;
	}
}
