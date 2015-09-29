package driver;

import model.Automobile;
import util.FileIO;

/**
 * This class tests how an Automobile object is built.
 * @author ShuqinYe
 *
 */
public class Test {

	public static void main(String[] args) {
		String filename = "Focus_Wagon_ZTW.txt";
		FileIO buildCar = new FileIO();
		
		// Populate Automobile object.
		Automobile auto = buildCar.buildAutoObj(filename);
		
		// Print the Automobile object.
		System.out.println(auto.getAuto());
		
		// Serialize auto.
		String serFile = buildCar.SerializeAuto(auto);
		
		// Deserialize auto.
		Automobile recoverAuto = buildCar.DeserializeAuto(serFile);
		
		// Print the recovered Automobile object.
		System.out.println(recoverAuto.getAuto());
		
	}
}
