package adapter;

/**
 * This interface provides an API of buildAuto from a file, and print the 
 * Automobile object after it's built.
 * @author ShuqinYe
 *
 */
public interface CreateAuto {

	public void buildAuto(String filename);
	public void printAuto(String modelName);
}
