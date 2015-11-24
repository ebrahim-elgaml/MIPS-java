package classes.registers;

public class MEM_WB {
	public boolean[] aluResult;
	public boolean[] memReadData;
	public boolean[] rd;
	// WB control
	public boolean regWrite;
	public boolean memToReg;

	public MEM_WB() {
		aluResult = new boolean[32];
		memReadData = new boolean[32];
		rd = new boolean[5];
	}

	public void copy(MEM_WB src) {
		this.regWrite = src.regWrite;
		this.memToReg = src.memToReg;
		for (int i = 0; i < 32; i++) {
			aluResult[i] = src.aluResult[i];
			memReadData[i] = src.memReadData[i];
		}
		for (int i = 0; i < 5; i++) {
			rd[i] = src.rd[i];
		}
	}
}
