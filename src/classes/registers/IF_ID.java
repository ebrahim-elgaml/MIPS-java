package classes.registers;

public class IF_ID {
	public boolean[] pcplus4;
	public boolean[] instruction;

	public IF_ID() {
		this.pcplus4 = new boolean[32];
		this.instruction = new boolean[32];
	}

	public void copy(IF_ID src) {
		for (int i = 0; i < 32; i++) {
			this.pcplus4 = src.pcplus4;
			this.instruction = src.instruction;
		}
	}
}
