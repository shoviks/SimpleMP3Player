package edu.uga.cs1302.mp3manager;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Map;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;

/**
 * A simple class that allows a user to access and change the 
 * pathname, author, album, title and date of an mp3 file.
 * Mp3 files can also be played.  Basically this class
 * represents some information about an Mp3 file.
 */
public class Mp3File {
private String pathname, author, album, date, title;
private int year;

/**
 * Creates an Mp3file
 */
public Mp3File (String pathname)
{
	try {
	    FileInputStream fis        = new FileInputStream( pathname );
	    BufferedInputStream bis    = new BufferedInputStream(fis);
	    AudioFileFormat mpegFormat = AudioSystem.getAudioFileFormat(bis);
	    Map properties             = mpegFormat.properties();

	    author =  (String) properties.get( "author" );
	    album = (String) properties.get( "album" );
	    title = (String) properties.get( "title" );
	    date = (String) properties.get( "date" );
	    year = Integer.parseInt(date);
	    this.pathname = pathname;
        }
        catch( ArrayIndexOutOfBoundsException oobe ) {
            System.err.println( "Usage: java PrintMP3Properties file.mp3" );
        }
        catch( Exception e ) {
            System.out.println(e);
        }
    }
/**
 *Returns the pathname of the Mp3File
 *@return pathname of the Mp3File 
 */
public String getPath()
{
	return pathname;
}
/**
 *Sets the pathname of the Mp3File
 *@param pathname of the Mp3File 
 */
public void setPath(String pathname)
{
	this.pathname = pathname;
}
/**
 *Returns the author of the Mp3File
 *@return author of the Mp3File 
 */
public String getAuthor()
{
	if (author.equals(""))
		return "Unknown";
	else
		return author;
}
/**
 *Sets the author of the Mp3File
 *@param author of the Mp3File 
 */
public void setAuthor(String author)
{
	this.author = author;
}
/**
 *Returns the album of the Mp3File
 *@return album of the Mp3File 
 */
public String getAlbum()
{
	if (album.equals(""))
		return "Unknown";
	else
		return album;
}
/**
 *Sets the album of the Mp3File
 *@param album of the Mp3File 
 */
public void setAlbum(String album)
{
	this.album = album;
}
/**
 *Returns the date of the Mp3File
 *@return date of the Mp3File 
 */
public String getDate()
{	
	if (date.equals(""))
		return "1900";
	else
		return date;
}
/**
 *Set the date of the Mp3File
 *@param date of the Mp3File 
 */
public void setDate(String date)
{
	this.date = date;
}
/**
 *Returns the title of the Mp3File
 *@return title of the Mp3File 
 */
public String getTitle()
{
	if (title.equals(""))
		return "Unknown";
	else
		return title;
}
/**
 *Set the title of the Mp3File
 *@param the title of the Mp3File 
 */
public void setTitle(String title)
{
	this.title = title;
}
/**
 *Returns the year of the Mp3File
 *@return year of the Mp3File 
 */
public int getYear()
{
	return year;
}
/**
 *Set the year of the Mp3File
 *@param the year of the Mp3File 
 */
public void setYear(int year)
{
	this.year = year;
}
/**
 *Returns the author, title, album and date of the Mp3File
 *@return author, title, album and date of the Mp3File 
 */
public String toString()
{
	return "Author: " + author + "\nTitle: " + title + "\nAlbum: " + album + "\nDate: " + date;
}
/**
 *Returns true if Mp3File equals another Mp3File
 *@param another Mp3File
 *@return true if this.Mp3File equals Mp3File m
 */
public boolean equals(Mp3File m)
{
	if (m.author.equals(author)&&m.title.equals(title)&&m.album.equals(album)&&m.date.equals(date))
		return true;
	else
		return false;
}
/**
 *Plays the Mp3File 
 */
public void play()
{
	MP3Player player = new MP3Player();
	player.play(pathname);

}
}
