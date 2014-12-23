import java.util.Scanner;
import java.io.File;
import java.util.ListIterator;

import edu.uga.cs1302.mp3manager.Mp3File;
import edu.uga.cs1302.mp3manager.SimpleArrayList;
/**
 * This class stores Mp3Files from a directory provided by the user
 * into a SimpleArrayList.  Each of SimpleArrayList element's 
 * information is presented through the use of the SimpleArrayListIterator.
 * The user then is allowed to use commands to access information and play
 * each mp3 file.
 */ 
public class MyMP3Files {

	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);
		System.out.println("Enter the directory name of the MP3 files:");
		String directory = key.next();
		//Creates a file object with the directory provided by the user
		File folder = new File(directory);
		//Puts abstract files from directory into an array
		File[] list = folder.listFiles();
			
		SimpleArrayList <Mp3File> mp3List = new SimpleArrayList<Mp3File>();
		
		//Puts mp3 files into SimpleArrayList called mp3List
		for (int i = 0; i<list.length; i++)
		{
			//Checks if the file is an mp3
			if(list[i].getAbsolutePath().endsWith(".mp3"))
			{
				Mp3File a = new Mp3File(list[i].getAbsolutePath());
				mp3List.add(a);
			}
		}
		ListIterator <Mp3File> mp3ListIterator;
		
		//Puts listIterator at index 0
		mp3ListIterator = mp3List.listIterator(0);

		//Iterates over all files and prints information abotu each file
		while(mp3ListIterator.hasNext())
		{
		    System.out.println(mp3ListIterator.next().toString());
			System.out.println("");
		}
		
		//Iterates over all files and gives user option to enter 
		//command after each file's information is printed
		for (int i = 0; i<mp3List.size(); i++)
		{
			System.out.println(mp3List.get(i).toString());
			System.out.println("");
			System.out.println("Enter command:");
			System.out.println("");
			String input = key.next();
			char letter = input.charAt(0);


			switch (letter) {
				case 'n': 
					if (i==(mp3List.size()-1))
					{
						System.out.println("end of the list");
					}
					break;
				case 'b': 
					if (i==0)
					{
						System.out.println("top of the list");
					}
					break;
				case 'i': 
					System.out.println(mp3List.get(i).toString());
					System.out.println(mp3List.get(i).getPath());
					break;
				case 'p': 
					mp3List.get(i).play();
					break;
				case 'h': 
					System.out.println("Enter n to move to the next file");
					System.out.println("Enter b to move to the previous file");
					System.out.println("Enter i to print information about the current file");
					System.out.println("Enter p to play the current file");
					System.out.println("Enter h for help");
					System.out.println("Enter q to quit the program");
					System.out.println("");
					break;
				case 'q': 
					System.exit(0);
		}
		}

	}

}
