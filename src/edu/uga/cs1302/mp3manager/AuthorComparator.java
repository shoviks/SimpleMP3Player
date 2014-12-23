package edu.uga.cs1302.mp3manager;
import java.util.Comparator;
/**
 * A simple class that allows a user to compare
 * authors of an Mp3Files
 */
public class AuthorComparator implements Comparator<Mp3File> {
	/**
	 * Compares author of Mp3Files.
	 * If author of first Mp3File comes first
	 * alphabetically -1 is returned.  If author 
	 * is same for both Mp3Files 0 is returned.  If
	 * author of second Mp3File comes first 1 
	 * is returned.
	 * @param Two Mp3Files
	 * @return int indicating album comparison
	 */
	public int compare(Mp3File one, Mp3File two)
	{
		return one.getAuthor().compareTo(two.getAuthor());
	}

}
