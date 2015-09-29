package exceptions;

/**
 * This enum set consists of all custom exceptions that can occur while 
 * processing Automobile object or ProxyAutomobile object.
 * @author ShuqinYe
 *
 */
public enum ExceptionSets {

	SETNAMENOTFOUND(1), MODELNAMENOTFOUND(2), OPTIONNAMENOTFOUND(3), 
	OPTIONSIZESNOTFOUND(4), FILENOTFOUND(5);
	
	private int errNum;
	
	/**
	 * Constructor for the enum type
	 * @param errNum the error number of an exception
	 */
	private ExceptionSets(int errNum) {
		this.errNum = errNum;
	}
	
	/**
	 * Get the error number of the exception
	 * @return the error number
	 */
	public int getErrNum() {
		return errNum;
	}
	
}
