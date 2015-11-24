package application.main;

import java.io.FileNotFoundException;
import java.util.TreeMap;

import actions.display.Displayer;
import classes.GUI.Window;
import classes.main.Circuit;


public abstract class Application {
	public static void main(String... args) throws FileNotFoundException {
		Circuit circuit = new Circuit();
		circuit.init();
		TreeMap<String, String> display;
	    Window  window = new Window(Displayer.display(circuit));
 		for (; !circuit.halted();) {
 			
			circuit.tick();	
 			window.Refresh(Displayer.display(circuit));

		}
		display = circuit.display();
		System.out.println(circuit.riseAndFall[0]);
			for (String key: display.keySet()) {
				System.out.println(key+": "+display.get(key));
			}
			System.out.println("----\n");
	}
	
	public static void tick()throws FileNotFoundException {
		Circuit circuit = new Circuit();
		circuit.init();
		TreeMap<String, String> display;
		if(!circuit.halted())
			circuit.tick();	
		display = circuit.display();
		System.out.println(circuit.riseAndFall[0]);
			for (String key: display.keySet()) {
				System.out.println(key+": "+display.get(key));
			}
			System.out.println("----\n");
	
}
	}
