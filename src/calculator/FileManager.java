package calculator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Data handling class for reading data input from files.
 * @author Xiang
 * @version 1.0
 *
 */
public class FileManager {

	private File file;
	private Scanner scan;
	private int lineNumber;
	private String error="";

	/**
	 * Creates a file handling manager for the specified file.
	 * @param selectedFile	The file to be read
	 */
	public FileManager(File selectedFile) {
		this.file = selectedFile;
		lineNumber = 0;
	}

	/**
	 * Returns true if the format of the file is valid.
	 * @return	true if the format of the file is valid.
	 */
	public boolean validateFile() {
		if(file.getName().endsWith(".txt"))
			return true;
		return false;
	}

	/**
	 * Returns true if the input of the file is valid.
	 * @return	true if the input of the file is valid
	 */
	public boolean validateInput() {
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Error during validateInput, can't read from file");
			e.printStackTrace();
		}
		String[] inputs;
		int quantityV;
		double priceV;
		lineNumber=0;
		while(scan.hasNextLine()) {
			lineNumber++;
			inputs=scan.nextLine().split(", ?");
			//check Quantity input
			try {
				quantityV = Integer.parseInt(inputs[0]);
			} catch (Exception e) {
				error = "Invalid input for Quantity: \""+inputs[0]+"\", at line "+lineNumber;
				return false;
			}
			if(quantityV<0) {
				error = "Quantity cannot be: \""+inputs[0]+"\", negative value at line "+lineNumber;
				return false;
			}
			//check Category input
			if(Item.Category.categoryOf(inputs[1].toLowerCase())==null) {
				error = "Category cannot be: \""+inputs[1]+"\", at line "+lineNumber;
				return false;
			}
			//check Price input
			try {
				priceV = Double.parseDouble(inputs[2]);
			} catch (Exception e) {
				error = "Invalid input for Price: \""+inputs[2]+"\", at line "+lineNumber;
				return false;
			}
			if(priceV<0) {
				error = "Price cannot be: \""+inputs[2]+"\", negative value at line "+lineNumber;
				return false;
			}
		}
		if(lineNumber == 0) {
			error = "Nothing to import";
			return false;
		}
		return true;
	}

	/**
	 * Returns the error input.
	 * @return	the error input
	 */
	public String getError() {
		return error;
	}

	/**
	 * Returns the number of lines read.
	 * @return	the number of lines read
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	/**
	 * Import all items read from the file into the given calculator
	 * @param calculator	the calculator to import to
	 * @return	all item imported in an ArrayList
	 */
	public ArrayList<Item> importFromFile(Calculator calculator) {
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Error during validateInput, can't read from file");
			e.printStackTrace();
		}
		String[] inputs;
		Item item;
		while(scan.hasNextLine()) {
			inputs = scan.nextLine().split(", ?");
			item = new Item(inputs[0],Item.Category.categoryOf(inputs[1].toLowerCase()),inputs[2]);
			calculator.add(item);
			items.add(item);
		}
		return items;
	}
}
