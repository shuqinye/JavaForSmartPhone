package fixerrors;

import java.util.Scanner;

/**
 * This class contains all methods that fix custom exceptions - some of them
 * are not created yet.
 * @author ShuqinYe
 * @andrewID shuqiny
 */
public class Fix1To5 {

	/**
	 * Fix for SETNAMENOTFOUND exception.
	 * The exception is fixed by prompting user to input a valid option set name.
	 */
	public String fix1() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please input the name of the option set you would"
				+ "like to change: (Please input one of the option set names "
				+ "and press enter after you finish.)");
		
		return input.nextLine();		

	}
	
	/**
	 * Fix for MODELNAMENOTFOUND exception.
	 * This exception is not fixed, but only returned by the program.
	 */
	public String fix2() {
		return null;
	}
	
	
	/**
	 * Fix for OPTIONNAMENOTFOUND exception.
	 * This exception is fixed by prompting user to input a valid option name.
	 */
	public String fix3() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please input the name of the option you would"
				+ "like to change: (Please input one of the option names "
				+ "and press enter after you finish.)");
		
		return input.nextLine();		

	}
	
	
	/**
	 * Fix for OPTIONPRICENOTFOUND exception.
	 * This exception is not fixed, but only returned by the program.
	 */
	public String fix4() {
		return null;
	}
	
	
	/**
	 * Fix for FILENOTFOUND exception.
	 * This exception is not fixed, but only returned by the program.
	 */
	public String fix5() {
		return null;
	}
	
	
	
}
