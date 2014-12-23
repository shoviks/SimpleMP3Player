package edu.uga.cs1302.mp3manager;
import java.util.Scanner;
import java.util.StringTokenizer;
public class MP3CollectionManager {
	public static void main(String[] args)
	{
	Scanner key = new Scanner(System.in);
	
	System.out.println("Enter pathname of mp3 collection:");
	String path = key.next();
	MP3Collection collection = new MP3Collection(path);
	//Creates TitleComparator
	TitleComparator t = new TitleComparator();
	//Sorts collection according to title
	collection.sort(t);
	//Saves first mp3file of collection to a variable
	Mp3File current = collection.getFile(0);
	//Saves index of current mp3file after sorting according to title
	int index = collection.search(current, t);
	AlbumComparator al = new AlbumComparator();
	AuthorComparator au = new AuthorComparator();
	DateComparator d = new DateComparator();
	System.out.println("Would you like to use the MP3 collection manager?  Enter y or n:");
	String permission = key.next();
	Boolean go = true;
	if (permission.equals("y"))
	{
		do 
		{
			String first = "";
			String second = "";
			String name = "";
			//Updates current mp3file
			current = collection.getFile(index);
			Integer integer = 1;
			System.out.println("Enter a command:");
			String command = key.nextLine();
			StringTokenizer tokenizer = new StringTokenizer(command);
			//checks if there are more tokens
			if(tokenizer.hasMoreTokens())
			{
				first = tokenizer.nextToken();
			}
			if (first.equals("list"))
			{

				 System.out.println("The current mp3 file is: ");
				 System.out.println(collection.getFile(index));
				 System.out.println("");
				 if(index - 1 >= 0)
				 {
					 System.out.println("The previous mp3 file is:");
					 System.out.println(collection.getFile(index-1));
					 System.out.println("");
				 }
				 if(index + 1  <= collection.size() - 1)
				 {
					 System.out.println("The next mp3 file is:");
					 System.out.println(collection.getFile(index + 1));
					 System.out.println("");
				 }
				 System.out.println();
			}
			else if (first.equals("next"))
			{
				if(tokenizer.hasMoreTokens())
				 {
					 second = tokenizer.nextToken();
					 integer = Integer.parseInt(second);
				 }
				 
				 if((index + integer) < collection.size())
					 index += integer; //updates current index
				 else
					 System.out.println("end of the list");
				 
			}
			else if (first.equals("prev"))
			{
				if(tokenizer.hasMoreTokens())
				 {
					 second = tokenizer.nextToken();
					 integer = Integer.parseInt(second);
				 }
				 
				 if((index - integer) >= 0)
					 index -= integer; //updates current index
				 else
					 System.out.println("start of the list");
			}
			else if (first.equals("play"))
			{
				collection.play(index);
			}
			else if (first.equals("info"))
			{
				System.out.println(collection.getFile(index));
			}
			else if (first.equals("sort"))
			{
				if (tokenizer.hasMoreTokens())
				{
					second = tokenizer.nextToken();
					if (second.equals("album"))
					{
						collection.sort(al);
					}
					else if (second.equals("author"))
					{
						collection.sort(au);
					}
					else if (second.equals("title"))
					{
						collection.sort(t);
					}
					else if (second.equals("date"))
					{
						collection.sort(d);
					}
				}
				
			}
			else if (first.equals("find"))
			{
				 if(tokenizer.hasMoreTokens())
				 {
					 second = tokenizer.nextToken();
				 }
				 int numOfIter = tokenizer.countTokens();
				 for (int i = 0; i<= numOfIter-1; i++)//this loop handles strings with multiple tokens
				 {
					 if(i > 0 && i <= numOfIter-1)
						 name+=" ";
					 name += tokenizer.nextToken();
					 
				 }
				 System.out.println(name);
				 
				 if(second.equals("author"))
				 {
					 collection.sort(au);
					 Mp3File temp = new Mp3File(path);
					 temp.setAuthor(name);
					 int tempInt = collection.search(temp, au);
					 if(tempInt >= 0)
					 {
						 System.out.println("Author found!");
						 index = tempInt;
					 }
					 
				 }
				 
				 else if(second.equals("album"))
				 {
					 collection.sort(al);
					 Mp3File temp = new Mp3File(path);
					 temp.setAuthor(name);
					 int tempInt = collection.search(temp, al);
					 if(tempInt >= 0)
					 {
						 System.out.println("Album found!");
						 index = tempInt;
					 }
					 
				 }
				 
				 else if(second.equals("title"))
				 {
					 collection.sort(t);
					 Mp3File temp = new Mp3File(path);
					 temp.setAuthor(name);
					 int tempInt = collection.search(temp, t);
					 if(tempInt >= 0)
					 {
						 System.out.println("Title found!");
						 index = tempInt;
					 }
					 
				 }
				 
				 else if(second.equals("date"))
				 {
					 collection.sort(d);
					 Mp3File temp = new Mp3File(path);
					 try{
					 Integer tempcom = Integer.parseInt(name);
					 temp.setYear(tempcom);
					 int tempInt = collection.search(temp, d);
					 if(tempInt >= 0)
					 {
						 System.out.println("Date found!");
						 index = tempInt;
					 }
					 }
					 catch(NumberFormatException e)//catche any exception ParseInt throws
					 {
						 System.out.println("Enter a valid number");
					 }
				 }
			
			}
			else if (first.equals("q"))//to quit program
			{
				go = false;
			}
			
		}while (go);
	}
	else if (permission.equals("n"))//choosing not to implement program
	{
		System.exit(0);
	}
	else
	{
		System.out.println("You did not enter y or n.  Restart the program.");
	}
	
	
	
	}

}
