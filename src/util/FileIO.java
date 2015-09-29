package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import exceptions.AutoException;
import exceptions.ExceptionSets;
import model.Automobile;

/**
 * This class:
 * - Build an automobile from a file input.
 * - Serialize it to a .ser file.
 * - Deserialize the .ser file and retrieve the Automobile object.
 * @author ShuqinYe
 *
 */
public class FileIO {
	
	public FileIO() {};
	
	/**
	 * Reads a file and build Automobile object with all option sets and options.
	 * @param filename the file to read the input from.
	 * @return an automobile with all option sets and options.
	 */
	public Automobile buildAutoObj(String filename) {
		

		Automobile auto = null;
		
		try {
			FileReader reader = new FileReader(new File(filename));
			BufferedReader buffer = new BufferedReader(reader);
			
			String line = buffer.readLine(); // first line
			
			String[] tokens = line.split("\t");
			
			try {
				if (tokens.length == 3)
					throw new AutoException(ExceptionSets.OPTIONSIZESNOTFOUND);
			}
			catch(AutoException a) {
				int errNum = a.getErrNum();
				
				// Print the error message
				a.printException(errNum);
				
				// Output the log into a file called log.txt.
				a.log();
				buffer.close();
				return null;
			}
			
			float basePrice = (float) Integer.parseInt(tokens[1]);
			int setSize = Integer.parseInt(tokens[2]);
			int[] opSizes = new int[setSize];
			for (int i = 0; i < setSize; ++i)
				opSizes[i] = Integer.parseInt((tokens[3].split(" ")[i]));
			
			try {
				String modelName = tokens[0];
				
				// If the model name is blank.
				if (modelName.equals(" ")) 
					throw new AutoException(ExceptionSets.MODELNAMENOTFOUND);
			}
			
			catch(AutoException a) {
				int errNum = a.getErrNum();
				
				// Print the error message
				a.printException(errNum);
				
				// Output the log into a file called log.txt.
				a.log();
				buffer.close();
				return null;
			}
			
			
			// Create Automobile object and initializes it with sizes of option
			// sets and options.
			auto = new Automobile(tokens[0], basePrice, setSize, opSizes);
			
			// Read the rest of the file line by line until end of the file.
			while (line != null) {
				line = buffer.readLine();
				String[] values = line.split("\t");
				
				if (line == null) break;
				else {
					for (int i = 0; i < setSize; ++i) {
						// For every option set, initialize its options.
						for (int j = 0; j <= opSizes[i]; ++j) {
							// First row of an option set is the option name.
							if (j == 0) {
								auto.setOpSetName(i, line);
							}
							
							// The rest are details of options.
							else {
								auto.setOpBySetIndex(i, j - 1, 
										values[0], (float) Integer.parseInt(values[1]));
							}
							
							// Read next line.
							line = buffer.readLine();
							if (line != null)
								values = line.split("\t");
							
						} // Finish getting one option set.
					}
				} // Finish getting all option sets.
				
			} // Finish reading lines.
			
			buffer.close();

		}
		
		catch(FileNotFoundException f) {
			AutoException a = new AutoException(ExceptionSets.FILENOTFOUND);
			
			int errNum = a.getErrNum();
			
			// Print the error message
			a.printException(errNum);
			
			// Output the log into a file called log.txt.
			a.log();
		}
		
		catch(IOException i) {
			i.printStackTrace();
		}
		
		return auto;
		
	}
	
	/**
	 * Serialize the Automobile object to a .ser file.
	 * @param auto the Automobile object
	 * @return .ser file name 
	 */
	public String SerializeAuto(Automobile auto) {
		
		String filename = "auto.ser";
		
		try {
			FileOutputStream out = new FileOutputStream(filename);
			ObjectOutputStream objOut = new ObjectOutputStream(out);
			objOut.writeObject(auto);
			objOut.close();
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		
		return filename;
	}
	
	
	/**
	 * Read a .ser file and deserialize the file to the origina object.
	 * @param filename the name of the file
	 * @return an Automobile object
	 */
	public Automobile DeserializeAuto(String filename) {
		Automobile auto = null;
		
		try {
			FileInputStream in = new FileInputStream(filename);
			ObjectInputStream objIn = new ObjectInputStream(in);
			auto = (Automobile) objIn.readObject();
			objIn.close();
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		
		return auto;
	}
	
	
}
