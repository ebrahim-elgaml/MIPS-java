package application.main;

import java.io.FileNotFoundException;
import java.util.TreeMap;

import classes.main.Circuit;
import classes.main.CycleCircuit;

public class ApplicationCompare {
	public static void main(String... args) throws FileNotFoundException {
		Circuit circuit = new Circuit();
		circuit.init();
		TreeMap<String, String> display;
		for (; !circuit.halted();) {
			circuit.tick();

		}
		display = circuit.display();
		System.out.println(circuit.riseAndFall[0]);
		for (String key : display.keySet()) {
			System.out.println(key + ": " + display.get(key));
		}

		CycleCircuit cycleCircuit = new CycleCircuit();
		cycleCircuit.init();
		TreeMap<String, String> cycleDisplay;
		for (; !cycleCircuit.halted();) {
			cycleCircuit.tick();

		}
		cycleDisplay = cycleCircuit.display();
		for (String key : cycleDisplay.keySet()) {
			System.out.println(key + ": " + cycleDisplay.get(key));
		}
		System.out.println("Single Cycle clock :- "+cycleCircuit.clock);
		//System.out.println("pipelined Cycle clock :- "+circuit.clock);
	//	System.out.println("Time Difference :- " +cycleCircuit.clock -circuit.clock);
	}

}
