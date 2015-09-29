package adapter;

import util.FileIO;
import model.Automobile;

/**
 * This class hides the object Automobile but provides an API for user to access
 * API.
 * @author ShuqinYe
 * @andrewID shuqiny
 *
 */
public abstract class ProxyAutomobile {
	
	private static Automobile auto;
	
	/**
	 * Build an Automobile object from a file.
	 */
	public void buildAuto(String filename) {
		
		FileIO fileio = new FileIO();
		auto = fileio.buildAutoObj(filename);
		
	}
	
	/**
	 * Print the whole Automobile object from a file.
	 */
	public void printAuto(String modelName) {
		System.out.println(auto.getAuto());
		
	}
	
	/**
	 * Get the model name of the proxyauto.
	 * @return the model name.
	 */
	public String getModelName() {
		return auto.getName();
	}
	
	/**
	 * Update option set name for an automobile with a given name
	 */
	public void updateOptionSetName(String modelName, String setName,
			String newName) {
		auto.updateOpSetName(setName, newName);
	}


	/**
	 * Update option price
	 */
	public void updateOptionPrice(String modelName, String setName, 
			String opName, float newPrice) {
		auto.updateOpPrice(setName, opName, newPrice);
	}

}
