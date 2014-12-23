package edu.uga.cs1302.mp3manager;
import java.util.Comparator;
/**
 * A simple class that allows a user to compare
 * titles of Mp3Files
 */
public class TitleComparator implements Comparator<Mp3File> {
	/**
	 * Compares titles of Mp3Files.
	 * If titles of first Mp3File comes first
	 * alphabetically -1 is returned.  If title
	 * is same for both Mp3Files 0 is returned.  If
	 * title of second Mp3File comes first 1 
	 * is returned.
	 * @param Two Mp3Files
	 * @return int indicating title comparison
	 */
	public int compare(Mp3File one, Mp3File two)
	{
		return one.getTitle().compareTo(two.getTitle());
	}

}
