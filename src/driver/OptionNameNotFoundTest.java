package driver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import adapter.BuildAuto;
import adapter.ProxyAutomobile;

/**
 * This class tests how an the OPTIONNAMENOTFOUND exception is handled and fixed
 * by the program.
 * @author ShuqinYe
 *
 */
public class OptionNameNotFoundTest {

	public static void main(String[] args) {

		ProxyAutomobile auto = new BuildAuto();
		
		/**
		 * Test for OPTIONNAMENOTFOUND.
		 * The exception is handled by the program, but not fixed.
		 */
		
		auto.buildAuto("Focus_Wagon_ZTW.txt");
		// Print auto before we change the option name.
		auto.printAuto(auto.getModelName());
		
		// Update option price - input a wrong option name
		auto.updateOptionPrice(auto.getModelName(), "Color", "Blue", 25);
		
		// print auto after option price is updated - after user inputs the 
		// right option name(e.g. Liquid Grey Clearcoat Metallic)
		auto.printAuto(auto.getModelName());
		
		// Update option set name - input a wrong set name.
		auto.updateOptionSetName(auto.getModelName(), "Colors", "Color Choices");
		
		// Print auto after option set name is updated - after user inputs the
		// right set name (e.g. Color)
		auto.printAuto(auto.getModelName());
		
		// Check the log files - to see whether the log files have recorded
		// two exceptions.
		try {
			FileReader reader = new FileReader("log.txt");
			BufferedReader buffer = new BufferedReader(reader);
			System.out.println(buffer.readLine());
			System.out.println(buffer.readLine());
		}
		catch(IOException i) {
			i.printStackTrace();
		}
	}
}
