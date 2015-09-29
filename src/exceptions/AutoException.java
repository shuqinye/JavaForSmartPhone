package exceptions;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import fixerrors.Fix1To5;
import fixerrors.FixAuto;

/**
 * Exceptions that can occur during operations on Aubomobile object.
 * @author ShuqinYe
 *
 */
public class AutoException extends Exception implements FixAuto {

	private static PrintWriter writer = createLog();
	private ExceptionSets exception;
	private int errNum;
	
	public AutoException(ExceptionSets exception) {
		this.exception = exception;
		errNum = exception.getErrNum();
	}
	
	/**
	 * Get the error number of exception.
	 * @return the error number of exception
	 */
	public int getErrNum() {
		return errNum;
	}
	
	/**
	 * Fix the exception according to error number.
	 */
	public String fix(int errNum) {
		Fix1To5 f = new Fix1To5();
		
		switch(errNum) {
		
			case 1: return f.fix1();

			case 2: return f.fix2();

			case 3: return f.fix3();

			case 4: return f.fix4();

			case 5: return f.fix5();

			default: return null;
		
		}
		
	}
	
	/**
	 * Get error message
	 * @param errNum error number of exception
	 * @return error message as a String
	 */
	public String getMessage(int errNum) {
		switch(errNum) {
		
		case 1: return "The option set name was not found!";
		case 2: return "The model name was not found!";
		case 3: return "The option name was not found!";
		case 4: return "The option sizes were not found!";
		case 5: return "The file was not found!";

		default: return null;

}
	}
	
	
	/**
	 * Print the exception message.
	 * @param errNum error number of exception
	 */
	public void printException(int errNum) {
		switch(errNum) {
		
			case 1: System.out.println(getMessage(errNum));
					break;
			case 2: System.out.println(getMessage(errNum));
					break;
			case 3: System.out.println(getMessage(errNum));
					break;
			case 4: System.out.println(getMessage(errNum));
					break;
			case 5: System.out.println(getMessage(errNum));
					break;

			default: return;
	
	}
	}
	
	
	/**
	 * Output log file according to error number
	 * @param errNum error number of the exception
	 */
	public void log() {
		
		Calendar calendar = Calendar.getInstance();
		Date timestamp = calendar.getTime();
		writer.print(timestamp.toString());
		writer.print("\t");
		writer.print(getMessage(errNum));
		writer.println();
		
		// Make sure all output is printed to the file.
		writer.flush();

	}
	
	/**
	 * Reset the printwrite and create a new log file.
	 * Call the function before 
	 */
	public static PrintWriter createLog() {
		try {
			writer = new PrintWriter(new File("log.txt"));
		}
		catch(IOException i) {
			System.out.println("There was an error when trying to create a log"
					+ " file!");
			i.printStackTrace();
			return null;
		}
		return writer;
		
	}
	
	
}
