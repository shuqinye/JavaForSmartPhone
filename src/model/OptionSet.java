package model;

import java.io.Serializable;

import exceptions.AutoException;
import exceptions.ExceptionSets;
/**
 * This class is an option set of an automobile, containing all options available.
 * @author ShuqinYe
 *
 */

public class OptionSet implements Serializable {

	private String name; // The name of the car property.
	private Option[] options; // The possible options of the property (OptionSet).
	
	/**
	 * Constructor is the name is unknown.
	 * @param opSize the number of options in one option set.
	 */
	public OptionSet(int opSize) {
		this("noNameYet", opSize);
	}
	
	/**
	 * Construct the OptionSet
	 * @param opSize the number of options within one OptionSet
	 * @param name the name of the OptionSet.
	 */
	public OptionSet(String name, int opSize) {
		this.name = name;		
				
		// Initialize the array of options
		options = new Option[opSize];
		
		// Initialize each Option object
		for (int i = 0; i < opSize; ++i) 
			options[i] = new Option();

	}
	
	/**
	 * Get the OptionSet name.
	 * @return the name of the OptionSet.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the whole OptionSet with an array of Options.
	 * @return the OptionSet with all options.
	 */
	public Option[] getfAllOptions() {
		return options;
	}
	
	/**
	 * Get an option with a certain index.
	 * @param opIndex the index of the option that needs to be found.
	 * @return the Option with an index of opIndex.
	 */
	public Option getOpByIndex(int opIndex) {
		return options[opIndex];
	}
	
	/**
	 * Find an option with a certain name.
	 * @param name the name of the Option that needs to be found.
	 * @return the Option with a certain name.
	 */
	public Option getOpByName(String name) {
		int i = findOpByName(name);
		try {
			if (i == -1) throw new AutoException(ExceptionSets.OPTIONNAMENOTFOUND);
			
		}
		catch(AutoException a) {
			int errNum = a.getErrNum();
			
			// Print the error message
			a.printException(errNum);
			
			// Output the log into a file called log.txt.
			a.log();
			
			// Assign the new setName
			while(true) {
				name = a.fix(errNum);
				i = findOpByName(name);
				if (i != -1) break;
				
				// Print the error message
				a.printException(errNum);
				// Output the log into a file called log.txt.
				a.log();
			}
		}
		return options[i];
	}
	
	/**
	 * Find the index of an option
	 * @param name the name of the option to be found.
	 * @return -1 if the option is not found, returns the index if found.
	 */
	public int findOpByName(String name) {
		int i = 0;
		while (i < options.length && !options[i].getName().equals(name)) ++i;
		return i < options.length ? i : -1;
	}
	
	/**
	 * Find the index of an option of a certain price.
	 * @param price the price of the option to be found.
	 * @return -1 if not found, returns the index if found.
	 */
	public int findOpByPrice(float price) {
		int i = 0;
		while (i < options.length && options[i].getPrice() != price) ++i;
		return i < options.length ? i : -1;
	}
	
	
	/**
	 * Sets the name of OptionSet.
	 * @param name the name of the OptionSet we need to set to.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the options for the OptionSet.
	 * @param options the new set of options available for the OptionSet.
	 */
	public void setOptions(String[] opNames, float[] prices) {
		for (int i = 0; i < options.length; ++i) {
			options[i].setName(opNames[i]);
			options[i].setPrice(prices[i]);
		}
	}
	
	
	/**
	 * Update a certain Option in the OptionSet.
	 * @param option the option to be updated into the new option.
	 * @param opIndex the index in the OptionSet the option needs to updated.
	 */
	public void setOp(int opIndex, String opName, float price) {
		options[opIndex].setName(opName);
		options[opIndex].setPrice(price);
	}
	
	
	/**
	 * Delete the option at a certain index.
	 * @param opIndex the index of the option to be deleted.
	 */
	public void deleteOpByIndex(int opIndex) {
		options[opIndex] = null;
	}
	
	/**
	 * Delete the option that has a certain name.
	 * @param name the name of the option to be deleted.
	 */
	public void deleteOpByName(String name) {
		
		int i = findOpByName(name);
		try {
			// If the name of the option is not found.
			if (i == -1) throw new AutoException(ExceptionSets.OPTIONNAMENOTFOUND);
			
		}
		catch(AutoException a) {
			int errNum = a.getErrNum();
			
			// Print the error message
			a.printException(errNum);
			
			// Output the log into a file called log.txt.
			a.log();
			
			// Assign the new setName
			while(true) {
				name = a.fix(errNum);
				i = findOpByName(name);
				if (i != -1) break;
				
				// Print the error message
				a.printException(errNum);
				// Output the log into a file called log.txt.
				a.log();
			}
		}
		options[i] = null;
	}
	
	/**
	 * Get the whole OptionSet with details of its name and options.
	 * @return a String representation of the OptionSet.
	 */
	public String getOpSet() {
		StringBuffer str = new StringBuffer();
		str.append("You can choose the following options and prices for ");
		str.append(name);
		str.append(":\n");
		str.append("Option");
		
		// Insert 40 spaces to separate the options and prices.
		for (int i = 0; i < 40; ++i) str.append(" ");

		str.append("Price($)\n");
		
		for (int i = 0; i < options.length; ++i) {
			str.append(options[i].getOption());
		}
		
		str.append("\n");
		return str.toString();
	}

}
