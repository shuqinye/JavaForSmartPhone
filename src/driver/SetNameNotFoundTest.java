package driver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import adapter.BuildAuto;
import adapter.ProxyAutomobile;

/**
 * This class tests SETNAMENOTFOUND exception.
 * @author ShuqinYe
 *
 */

public class SetNameNotFoundTest {

	public static void main(String[] args) {
		
		ProxyAutomobile auto = new BuildAuto();
		
		/**
		 * Test for SETNAMENOTFOUND exception.
		 */
		auto.buildAuto("Focus_Wagon_ZTW.txt");
		auto.printAuto(auto.getModelName()); // print the auto after it's built.
		
		// Colors is not an option set name.
		auto.updateOptionSetName(auto.getModelName(), "Colors", "Color Choices");
		// Print the auto after the user inputs a valid option set name.
		auto.printAuto(auto.getModelName());
		
		// Check the log files - to see whether the log files have recorded the
		// exception
		try {
			FileReader reader = new FileReader("log.txt");
			BufferedReader buffer = new BufferedReader(reader);
			System.out.println(buffer.readLine());
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		
	}
	
	
}
