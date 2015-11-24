package classes.registers;

public class ID_EX {
	public boolean[] pcPlus4;
	public boolean[] read1;
	public boolean[] read2;
	public boolean[] extendedConstant;
	public boolean[] rt;
	public boolean[] rd;
	// WB control
	public boolean regWrite;
	public boolean memToReg;
	// MEM control
	public boolean branch;
	public boolean memWrite;
	public boolean memRead;
	// EX control
	public boolean aluSrc;
	public boolean regDest;
	public boolean[] aluOP;
	// Manzar
	public boolean jump;
	public boolean link;

	public ID_EX() {
		pcPlus4 = new boolean[32];
		read1 = new boolean[32];
		read2 = new boolean[32];
		extendedConstant = new boolean[32];
		rt = new boolean[5];
		rd = new boolean[5];
		aluOP = new boolean[2];
	}

	public void copy(ID_EX src) {
		for (int i = 0; i < 32; i++) {
			pcPlus4[i] = src.pcPlus4[i];
			read1[i] = src.read1[i];
			read2[i] = src.read2[i];
			extendedConstant[i] = src.extendedConstant[i];
		}
		
		for (int i = 0; i < 5; i++) {
			rt[i] = src.rt[i];
			rd[i] = src.rd[i];
		}
		
		for (int i = 0; i < 2; i++) {
			aluOP[i] = src.aluOP[i];
		}
		
		this.aluSrc = src.aluSrc;
		this.branch = src.branch;
		this.jump = src.jump;
		this.link = src.link;
		this.memRead = src.memRead;
		this.memToReg = src.memToReg;
		this.memWrite = src.memWrite;
		this.regDest = src.regDest;
		this.regWrite = src.regWrite;
	}
}
