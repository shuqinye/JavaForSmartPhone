package model;

import java.io.Serializable;

/**
 * This class represents the possible option a car can have within a specific
 * OptionSet(property).
 * @author ShuqinYe
 *
 */
public class Option implements Serializable {
	
	private String name; // Name of the option.
	private float price; // Price of the option.
	
	/**
	 * Default constructor without formal parameters.
	 */
	public Option() {
		this("noNameYet", 0);
	}
	
	public Option(String name, float price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	/**
	 * This method displays the information of the option as a String.
	 * @return a String that describes the option.
	 */
	public String getOption() {
		StringBuffer str = new StringBuffer();
		str.append(name);
		int i = 46 - str.length();
		for (int c = 0; c < i; ++c) str.append(" ");
		
		str.append(price);
		str.append("\n");
		
		return str.toString();
	}
	
}
