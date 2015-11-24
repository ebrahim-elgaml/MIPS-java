package classes.registers;

public class EX_MEM {
	public boolean[] branchAddress;
	public boolean zero;
	public boolean[] aluResult;
	public boolean[] memWriteData;
	public boolean[] rd;
	// WB control
	public boolean regWrite;
	public boolean memToReg;
	// MEM control
	public boolean branch; // TODO use branch
	public boolean memWrite;
	public boolean memRead;

	public EX_MEM() {
		branchAddress = new boolean[32];
		aluResult = new boolean[32];
		memWriteData = new boolean[32];
		rd = new boolean[5];
	}

	public void copy(EX_MEM src) {
		for (int i = 0; i < 32; i++) {
			this.branchAddress[i] = src.branchAddress[i];
			this.aluResult[i] = src.aluResult[i];
			this.memWriteData[i] = src.memWriteData[i];
		}

		for (int i = 0; i < 5; i++) {
			this.rd[i] = src.rd[i];
		}

		this.branch = src.branch;
		this.memRead = src.memRead;
		this.memToReg = src.memToReg;
		this.memWrite = src.memWrite;
		this.zero = src.zero;
		this.regWrite = src.regWrite;
	}
}
