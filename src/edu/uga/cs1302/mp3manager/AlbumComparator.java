package edu.uga.cs1302.mp3manager;
import java.util.Comparator;
/**
 * A simple class that allows a user to compare
 * albums of Mp3Files
 */
public class AlbumComparator implements Comparator<Mp3File> {
	/**
	 * Compares album titles of Mp3Files.
	 * If album of first Mp3File comes first
	 * alphabetically -1 is returned.  If album
	 * is same for both Mp3Files 0 is returned.  If
	 * album of second Mp3File comes first 1 
	 * is returned.
	 * @param Two Mp3Files
	 * @return int indicating album title comparison
	 */
	public int compare(Mp3File one, Mp3File two)
	{
		return one.getAlbum().compareTo(two.getAlbum());
	}

}
