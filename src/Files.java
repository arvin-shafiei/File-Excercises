import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class is being used to allow the user to read, write & copy from a file,
 * decipher given text in mystery.txt and process, format & calculate the
 * average time of details.txt as well as output a 2D-Number array
 *
 * @author A.Shafiei
 * @version 1.0
 */

public class Files {
	private static final String crypt1 = "cipherabdfgjk";
	private static final String crypt2 = "lmnoqstuvwxyz";

	public void runFileTests() {
		System.out.print("Running file tests");
	}

	/**
	 * Takes in text from a file and prints it line by line
	 */
	public void readFromFile() {
		String fileName = null;

		Scanner sc = new Scanner(System.in);

		System.out.print("\nEnter the file to read's name\n");
		fileName = sc.nextLine();

		if (fileExists(fileName)) {
			try {
				File myObj = new File(fileName + ".txt");
				Scanner myReader = new Scanner(myObj);
				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					System.out.println(data);
				}

			} catch (FileNotFoundException e) {
				System.out.println("\nAn error occurred.");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Writes text given by the user to a file of there choice
	 * 
	 * @throws IOException
	 */
	public void writeToFile() throws IOException {
		String fileName = null;

		Scanner sc = new Scanner(System.in);

		System.out.print("\nEnter the file to write's name\n");
		fileName = sc.nextLine();

		FileWriter myWriter = new FileWriter(fileName + ".txt");

		try {
			boolean continueLoop = true;

			while (continueLoop) {
				System.out.print("\nEnter the next line of text:\n");
				String nextLine = sc.nextLine();
				continueLoop = !(nextLine).isEmpty();
				myWriter.write(nextLine + "\n");
			}

			System.out.println("\nSuccessfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("\nAn error occurred.");
			e.printStackTrace();
		} finally {
			myWriter.close();
		}
	}

	/**
	 * Copies data from an 'input' file and writes it to an 'output' file
	 */
	public void copyFile() throws IOException {
		String inputFileName = null;
		String outputFileName = null;

		Scanner sc = new Scanner(System.in);

		System.out.print("\nEnter the file to copies name\n");
		inputFileName = sc.nextLine();

		System.out.print("\nEnter the output files name\n");
		outputFileName = sc.nextLine();

		if (fileExists(inputFileName)) {
			FileWriter myWriter = new FileWriter(outputFileName + ".txt");

			File myObj = new File(inputFileName + ".txt");

			Scanner myReader = new Scanner(myObj);
			try {
				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					myWriter.write(data + "\n");
					System.out.print("\n" + data);
				}
				System.out.print("\nCompleted Copy\n");
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			} finally {
				myReader.close();
				myWriter.close();
			}
		}
	}

	private static String cipherDecipherString(String text) {
		// declare variables we need
		int i, j;
		boolean found = false;
		String temp = ""; // empty String to hold converted text

		for (i = 0; i < text.length(); i++) // look at every character in text
		{
			found = false;
			if ((j = crypt1.indexOf(text.charAt(i))) > -1) // is char in crypt1?
			{
				found = true; // yes!
				temp = temp + crypt2.charAt(j); // add the cipher character to temp
			} else if ((j = crypt2.indexOf(text.charAt(i))) > -1) // and so on
			{
				found = true;
				temp = temp + crypt1.charAt(j);
			}

			if (!found) // to deal with cases where char is NOT in crypt2 or 2
			{
				temp = temp + text.charAt(i); // just copy across the character
			}
		}
		return temp;
	}

	/**
	 * Deciphers the mystery.txt file
	 */
	public void decipherMystery() throws IOException {
		if (fileExists("mystery")) {
			File myObj = new File("mystery.txt");

			Scanner myReader = new Scanner(myObj);

			FileWriter myWriter = new FileWriter("deciphered.txt");
			try {
				while (myReader.hasNextLine()) {
					String decipheredText = cipherDecipherString(myReader.nextLine());

					System.out.println(decipheredText);

					myWriter.write(decipheredText + "\n");
				}

				System.out.println("\nWrote deciphered text to deciphered.txt");
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			} finally {
				myReader.close();
				myWriter.close();
			}
		}
	}

	/**
	 * Takes the details.txt file and formats it and calculates the average time.
	 */
	public void splitData() throws IOException {
		if (fileExists("details")) {
			File myObj = new File("details.txt");

			Scanner myReader = new Scanner(myObj);

			FileWriter myWriter = new FileWriter("RaceDetails.txt");
			try {
				while (myReader.hasNextLine()) {
					String dataLine = myReader.nextLine();
					;
					String[] splitData = dataLine.split(" ");

					float averageTime;
					averageTime = ((Float.parseFloat(splitData[2]) + Float.parseFloat(splitData[3])
							+ Float.parseFloat(splitData[4]) + Float.parseFloat(splitData[5])
							+ Float.parseFloat(splitData[6])) / 5);

					System.out.printf("%s" + ", " + "%s" + ": Average score is " + "%.2f\n", splitData[1], splitData[0],
							averageTime);

					myWriter.write(String.format("%s" + ", " + "%s" + ": Average score is " + "%.2f\n", splitData[1],
							splitData[0], averageTime));
				}
				System.out.println("\nSuccessfully wrote details file.");
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			} finally {
				myWriter.close();
				myReader.close();
			}
		}
	}

	/**
	 * Checks if file from passed through directory exists
	 */
	public boolean fileExists(String filePath) {
		File file = new File(filePath + ".txt");
		if (file.exists() && !file.isDirectory()) {
			return true;

		}
		return false;
	}

	/**
	 * Takes a 2D array of int's and put's them into a file
	 */
	public void arrayToFile() {
		int[][] numbers = { { 1, 2, 3 }, { 4, 5, 6 } };
		
		System.out.print("\n");
		
		try {
			FileWriter myWriter = new FileWriter("numberArray.txt");

			for (int[] x : numbers) {
				for (int y : x) {
					myWriter.write(y + " ");
					System.out.print(y + " ");
				}
				myWriter.write("\n");
				System.out.println();
			}

			myWriter.close();
			System.out.println("\nSuccessfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("\nAn error occurred.");
			e.printStackTrace();
		}
	}

	/**
	 * Method used to process the users choices
	 */
	public static void processUserChoices() throws IOException {
		Files Files = new Files();
		int userChoice;

		/**
		 * Calls menu method.
		 */
		displayMenu();

		Scanner sc1 = new Scanner(System.in);
		userChoice = sc1.nextInt();

		/**
		 * Switch case used to interpret the users input to a method.
		 */
		switch (userChoice) {
		case 0:
			System.exit(0);
			break;
		case 1:
			/**
			 * The initial read, write & copy file methods.
			 */
			System.out.println("\n");

			Files.runFileTests();

			System.out.println("\nThis choice will run the read, write & copy file methods.");

			Files.readFromFile();

			System.out.println("Read to file method completed.");

			Files.writeToFile();

			System.out.println("\nWrite to file method completed.");

			Files.copyFile();

			System.out.println("\nCopy file method completed.");

			break;
		case 2:
			/**
			 * Deciphers mystery.txt
			 */
			System.out.println("\nThis choice will decipher the mystery.txt file.\n");

			Files.decipherMystery();
			break;
		case 3:
			/**
			 * Method which formats details.txt & Calculates the average time.
			 */
			System.out.println("\nThis choice format the details.txt file as well as calculate the average time.\n");

			Files.splitData();
			break;
		case 4:
			/**
			 * Optional Extra Methods (print integer array & object array to file)
			 */
			Files.arrayToFile();
			break;
		case 5:
			/**
			 * Stores a DVD collection of 3 movies and allows the user to Update & Save the object to a file and load object from a file and display the object.
			 */
			dvd[] DeeVeeDee;

			DeeVeeDee = new dvd[3];

			System.out.print("\nOptions");
			System.out.print("\n1. Update & Save");
			System.out.print("\n2. Load & Display\n");
			int chosenOption = sc1.nextInt();

			switch (chosenOption) {
			case 1:
				for (int i = 0; i < DeeVeeDee.length; i++) {
					DeeVeeDee[i] = new dvd();
					DeeVeeDee[i].setAllValues();
				}

				dvd.saveObject(DeeVeeDee);
				break;
			case 2:
				DeeVeeDee = dvd.loadFile();

				for (int i = 0; i < DeeVeeDee.length; i++) {
					System.out.printf("\nTitle: %s, Director: %s, Runtime (minutes): %s\n", DeeVeeDee[i].getTitle(),
							DeeVeeDee[i].getDirector(), DeeVeeDee[i].getRunTime());
				}
			}

			break;
		default:
			System.out.print("\0. nInvalid choice try again");

			processUserChoices();

			break;
		}

		/**
		 * Continues to call menu until program is exited.
		 */
		do {
			processUserChoices();
		} while (true);
	}

	/**
	 * Display's the menu with the users choices
	 */
	public static void displayMenu() {
		System.out.print("\nAC11001 Lab Practical 8 and Assignment 4 – Working with Text Files");
		System.out.print("\n1. Initial File Exercises");
		System.out.print("\n2. Deciphering");
		System.out.print("\n3. Processing numerical data");
		System.out.print("\n4. Output 2D Array to file");
		System.out.print("\n5. DVD Object Options");
		System.out.print("\n0. Exit");
		System.out.print("\n");
	}

	public static void main(String args[]) throws IOException {
		/**
		 * Calls initial menu & user choice methods
		 */
		processUserChoices();
	}
}
