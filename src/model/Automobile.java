package model;

import java.io.Serializable;
import exceptions.AutoException;
import exceptions.ExceptionSets;

/**
 * This class represents a specific car model and all its possible
 * properties (OptionSets)
 * @author ShuqinYe
 * @andrewID shuqiny
 */
public class Automobile implements Serializable {

	private String name; // The car model's name.
	private float basePrice; // The car model's base price.
	private OptionSet[] opSets; // All possible option sets for the car.
	
	/**
	 * This is a constructor for a car model.
	 * @param size the number of properties specified by the car model.
	 * @param name the name of the car model.
	 */
	public Automobile(String name, float basePrice, int setSize, 
			int[] opSizes) {
		this.name = name;
		this.basePrice = basePrice;
		
		// Define the number of OptionSets of the car.
		opSets = new OptionSet[setSize];
		
		for (int i = 0; i < setSize; ++i) {
			opSets[i] = new OptionSet(opSizes[i]);
//			for (int j = 0; j < opSizes[i]; ++j) 
		}

			
	}
	
	/**
	 * Get the car model name.
	 * @return car model as a String.
	 */
	public String getName() {
		return name;
	}
	
	
	public float getBasePrice() {
		return basePrice;
	}
	
	
	/**
	 * Get the complete OptionSet of the car.
	 * @return the OptionSet of the car.
	 */
	public OptionSet[] getAllOptionSets() {
		return opSets;
	}
	
	/**
	 * get OptionSet by index value
	 * @param setIndex the index of the OptionSet in the array of all OptionSets.
	 * @return the OptionSet for the specified index.
	 */
	public OptionSet getOpSetByIndex(int setIndex) {
		return opSets[setIndex];
	}
	
	
	/**
	 * find the option set of name setName.
	 * @param setName the name of option set to be found.
	 * @return -1 if not found, index of the option set if found.
	 */
	public int findOpSetByName(String setName) {
		int i = 0;
		while (i < opSets.length && !opSets[i].getName().equals(setName)) ++i;
		return i < opSets.length ? i : -1;
	}
	
	
	/**
	 * Find an option with a certain name in all option sets
	 * @param opName the option name
	 * @return -1 if not found, index within its option set if found.
	 */
	public int findOpByName(String opName) {
		int i = 0;
		while (i < opSets.length && opSets[i].findOpByName(opName) == -1) ++i;
		return i < opSets.length ? opSets[i].findOpByName(opName) : -1;
	}
	
	
	/**
	 * Set car model name.
	 * @param name car model name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Set car base price.
	 * @param basePrice the car base price.
	 */
	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}
	
	
	/**
	 * Set all option sets.
	 * @param opSets the new option sets.
	 */
	public void setOpSets(OptionSet[] opSets) {
		this.opSets = opSets;
	}
	
	/**
	 * Set the option set to opSet at the index of opSetIndex
	 * @param opSet the new option set
	 * @param opSetIndex the index of the option set that needs replacement
	 */
	public void setOpSet(int setIndex, OptionSet opSet) {
		opSets[setIndex] = opSet;
	}
	
	
	/**
	 * Set the name of a certain option set.
	 * @param setIndex index of option set
	 * @param opSetName name of option set.
	 */
	public void setOpSetName(int setIndex, String opSetName) {
		opSets[setIndex].setName(opSetName);
	}
	
	/**
	 * set the option in a certain option set.
	 * @param name the name of the option set.
	 * @param opIndex the index of the option in the option set.
	 */
	public void setOpBySetName(String setName, int opIndex, 
			String opName, float opPrice) {
		int i = 0;
		while (i < opSets.length && !opSets[i].getName().equals(setName)) ++i;
		opSets[i].setOp(opIndex, opName, opPrice);
	}
	
	
	/**
	 * Set option value at a certain index for option set at a certain index.
	 * @param setIndex the option set index
	 * @param opIndex the option index in which the option is to be set
	 * @param option the option
	 */
	public void setOpBySetIndex(int setIndex, int opIndex, 
			String opName, float opPrice) {
		opSets[setIndex].setOp(opIndex, opName, opPrice);
	}
	
	/**
	 * update an option set of a certain name.
	 * @param setName the set to be updated
	 * @param opSet the new option set
	 */
	public void updateOpSet(String setName, OptionSet opSet) {
		setOpSet(findOpSetByName(setName), opSet);
	}
	
	/**
	 * Update the opSet name of a give opSet
	 * @param setName the opSet name to be changed
	 * @param newName the new opSet name
	 */
	public void updateOpSetName(String setName, String newName) {
		int index = findOpSetByName(setName);
		
		try {
			if (index == -1) throw new AutoException(ExceptionSets.SETNAMENOTFOUND);		
		}
		catch(AutoException a) {
			int errNum = a.getErrNum();
			
			// Print the error message
			a.printException(errNum);
			
			// Output the log into a file called log.txt.
			a.log();
			
			// Assign the new setName
			while(true) {
				setName = a.fix(errNum);
				index = findOpSetByName(setName);
				if (index != -1) break;
				
				// Print the error message
				a.printException(errNum);
				// Output the log into a file called log.txt.
				a.log();
			}
		}
		
		setOpSetName(index, newName);
	}
	
	/**
	 * update an option in a certain option set.
	 * @param setName the set to be updated.
	 * @param opName the option to be updated.
	 * @param option the new option.
	 */
	public void updateOp(String setName, String opName, float opPrice) {
		int setIndex = findOpSetByName(setName);
		
		try {
			if (setIndex == -1) throw new AutoException(ExceptionSets.SETNAMENOTFOUND);		
		}
		catch(AutoException a) {
			int errNum = a.getErrNum();
			
			// Print the error message
			a.printException(errNum);
			
			// Output the log into a file called log.txt.
			a.log();
			
			// Assign the new setName
			while(true) {
				setName = a.fix(errNum);
				setIndex = findOpSetByName(setName);
				if (setIndex != -1) break;
				
				// Print the error message
				a.printException(errNum);
				// Output the log into a file called log.txt.
				a.log();
			}
		}
		
		int opIndex = opSets[setIndex].findOpByName(opName);
		opSets[setIndex].setOp(opIndex, opName, opPrice);
	}
	
	/**
	 * Update the option price
	 * @param setName the opSet name
	 * @param opName the option name
	 * @param opPrice the new price of the option
	 */
	public void updateOpPrice(String setName, String opName, float opPrice) {
		int setIndex = findOpSetByName(setName);
		
		try {
			if (setIndex == -1) throw new AutoException(ExceptionSets.SETNAMENOTFOUND);		
		}
		catch(AutoException a) {
			int errNum = a.getErrNum();
			
			// Print the error message
			a.printException(errNum);
			
			// Output the log into a file called log.txt.
			a.log();
			
			// Assign the new setName
			while(true) {
				setName = a.fix(errNum);
				setIndex = findOpSetByName(setName);
				if (setIndex != -1) break;
				
				// Print the error message
				a.printException(errNum);
				// Output the log into a file called log.txt.
				a.log();
			}
		}
		
		opSets[setIndex].getOpByName(opName).setPrice(opPrice);
	}
	
	
	/**
	 * Delete an option set at a certain index
	 * @param setIndex index of the set to be deleted
	 */
	public void deleteOpSetByIndex(int setIndex) {
		opSets[setIndex] = null;
	}
	
	
	/**
	 * Delete an option set of a certain name
	 * @param setName name of option set to be deleted
	 */
	public void deleteOpSetByName(String setName) {
		opSets[findOpSetByName(setName)] = null;
	}
	
	/**
	 * Delete an option at a certain index, within option set at a certain index.
	 * @param setIndex index of option set
	 * @param opIndex index of option to be deleted
	 */
	public void deleteOpByIndex(int setIndex, int opIndex) {
		opSets[setIndex].deleteOpByIndex(opIndex);
	}
	
	/**
	 * Delete an option at a certain index, within option set of name setName
	 * @param setName name of option set the option is in
	 * @param opIndex index of option to be deleted
	 */
	public void deleteOpByIndex(String setName, int opIndex) {
		opSets[findOpSetByName(setName)].deleteOpByIndex(opIndex);
	}
	
	/**
	 * Delete an option of a certain name, within option set at a certain index.
	 * @param setIndex option set index.
	 * @param opName name of option to be deleted
	 */
	public void deleteOpByName(int setIndex, String opName) {
		opSets[setIndex].deleteOpByName(opName);
	}
	
	/**
	 * Delete an option of a certain name, within option set of a certain name
	 * @param setName name of option set the option is in
	 * @param opName name of option to be deleted
	 */
	public void deleteOpByName(String setName, String opName) {
		opSets[findOpSetByName(setName)].deleteOpByName(opName);
	}
	
	
	/**
	 * Output all option sets and options within each option set.
	 * @return a String representation of all option sets and options.
	 */
	public String getAuto() {
		StringBuffer str = new StringBuffer();
		str.append("The car model ");
		str.append(name);
		str.append(" has ");
		str.append(opSets.length);
		str.append(" property settings.\n\n");
		
		for (int i = 0; i < opSets.length; ++i) {
			str.append(opSets[i].getOpSet());
		}
	
		return str.toString();
		
	}
	
}
	
