package edu.uga.cs1302.mp3manager;
import java.util.Comparator;
/**
 * A simple class that allows a user to compare
 * a dates of an Mp3Files
 */
public class DateComparator implements Comparator<Mp3File> {
	/**
	 * Compares date of Mp3Files.
	 * If date of first Mp3File comes first
	 * alphabetically -1 is returned.  If date
	 * is same for both Mp3Files 0 is returned.  If
	 * date of second Mp3File comes first 1 
	 * is returned.
	 * @param Two Mp3Files
	 * @return int indicating date comparison
	 */
	
	public int compare(Mp3File one, Mp3File two)
	{
		if (one.getYear() < two.getYear())
		{
			return -1;
		}
		else if (one.getYear() == two.getYear())
		{
			return 0;
		}
		else 
			return 1;
	}


}
