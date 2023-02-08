import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class is being used to store the fields of the DVD collection as well as the method's used.
 *
 * @author A.Shafiei
 * @version 1.0
 */

public class dvd {
	private String title;
	private String director;
	private int runTime;

	public dvd() {
		title = "unknown";
		director = "unknown";
		runTime = 0;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getRunTime() {
		return runTime;
	}

	public void setRunTime(int runtime) {
		this.runTime = runtime;
	}

	/**
	 * Checks if file from passed through directory exists
	 */
	public static boolean fileExists(String filePath) {
		File file = new File(filePath + ".txt");
		if (file.exists() && !file.isDirectory()) {
			return true;

		}
		return false;
	}

	/**
	 * Takes in movie data and sets all the fields
	 * 
	 * @throws IOException
	 */
	public void setAllValues() throws IOException {
		String newTitle;

		String newDirectorName;

		int newRunTime;

		Scanner sc = new Scanner(System.in);

		System.out.print("\nEnter the movie's title: ");
		newTitle = sc.nextLine();
		setTitle(newTitle);

		System.out.print("\nEnter the movie's director's name: ");
		newDirectorName = sc.nextLine();
		setDirector(newDirectorName);

		System.out.print("\nEnter the movie's run time: ");
		newRunTime = sc.nextInt();
		setRunTime(newRunTime);
	}

	/**
	 * Saves movie data to file
	 * 
	 * @throws IOException
	 */
	public static void saveObject(dvd[] deeVeeDee) throws IOException {
		FileWriter myWriter = new FileWriter("dvd.txt");

		try {
			for (int i = 0; i < deeVeeDee.length; i++) {
				myWriter.write((deeVeeDee[i]).getTitle() + " / " + deeVeeDee[i].getDirector() + " / "
						+ deeVeeDee[i].getRunTime() + "\n");
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
	 * Loads movie data from file
	 * 
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static dvd[] loadFile() throws IOException, NumberFormatException {
		dvd[] deeVeeDee;

		deeVeeDee = new dvd[3];

		try {
			// Not sure why I needed to reference the fileExists function through the array
			if (fileExists("dvd")) {
				File myObj = new File("dvd.txt");

				Scanner myReader = new Scanner(myObj);

				try {
					int i = 0;
					while (myReader.hasNextLine()) {
						String dataLine = myReader.nextLine();
						String[] splitData = dataLine.split(" / ");

						deeVeeDee[i] = new dvd();

						deeVeeDee[i].setTitle(splitData[0]);

						deeVeeDee[i].setDirector(splitData[1]);

						deeVeeDee[i].setRunTime(Integer.parseInt(splitData[2]));

						i++;
					}
					System.out.println("\nSuccessfully wrote details file.");
				} finally {
					myReader.close();
				}
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		return deeVeeDee;
	}
}
