package edu.uga.cs1302.mp3manager;
import java.io.File;
import java.util.Comparator;


public class MP3Collection {
	
	private Object[] Mp3Library;
	private Comparator<Mp3File> comp;
	private int number;
	
	/**
	 *Creates an empty collection 
	 */
	public MP3Collection()
	{
		Mp3Library = new Mp3File[20];
	}
	/**
	 *Creates a collection containing files in the specified directory.
	 *The collection should include only files with the .mp3 extension.
	 *@param the path name of the Mp3File 
	 */
	public MP3Collection(String directoryPathname)
	{
		File folder = new File(directoryPathname);
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
		Mp3Library = mp3List.toArray();
		
	}


	/**
	 *Sorts collection according to a given comparator
	 *@param Comparator of type Mp3File 
	 */
	public void sort(Comparator<Mp3File> c)
	{
		comp = c;
		number = Mp3Library.length;
		quicksort(0, number - 1);
	}

		/**
		 * an internal sorting method using the quick sort algorithm
		 * @param left
		 * @param right
		 */
		private void quicksort(int left, int right)
		{
			int i = left, j = right;
			// Get the pivot element from the middle of the list
			Object pivot = Mp3Library[left + (right-left)/2];

			// Divide into two lists
			while (i <= j) {
				// If the current value from the left list is smaller then the pivot
				// element then get the next element from the left list
				while (comp.compare( (Mp3File) Mp3Library[i] , (Mp3File) pivot) < 0) {
					i++;
				}
				// If the current value from the right list is larger then the pivot
				// element then get the next element from the right list
				while (comp.compare( (Mp3File) Mp3Library[j] , (Mp3File) pivot) > 0) {
					j--;
				}

				// If a value in the left list is larger than the pivot element and 
				// a value in the right list is smaller than the pivot element 
				// then we exchange the values.
				// As we are done we can increase i and j
				if (i <= j) {
					exchange(i, j);
					i++;
					j--;
				}
			}
			// Recursion
			if (left < j)
				quicksort(left, j);
			if (i < right)
				quicksort(i, right);
		}
	
		/**
		 * an internal method to help encapsulation and 
		 * the quick sort method
		 * @param i
		 * @param j
		 */
		private void exchange(int i, int j) {
			Object temp = Mp3Library[i];
			Mp3Library[i] = Mp3Library[j];
			Mp3Library[j] = temp;
		} 
	
	/**
	 *Searches for a given mp3file, using given comparator.  
	 *Returns index of file in the collection or -1 if not found
	 *@param Mp3File and Comparator of type Mp3File 
	 *@return index of file or -1 if not found
     */
	public int search (Mp3File file, Comparator<Mp3File> comp)
	{
		 int left = 0;
	        int right = Mp3Library.length - 1;
	        int mid;

	        while( left <= right )
	        {
	            mid = ( left + right ) / 2;

	            if(comp.compare((Mp3File)Mp3Library[mid], file) < 0)
	                left = mid + 1;
	            else if(comp.compare((Mp3File)Mp3Library[mid], file) > 0)
	                right = mid - 1;
	            else
	                return mid;
	        }

	        return -1;    
	}
	/**
	 *Returns the size of the collection
	 *@return size of the collection 
	 */
	public int size()
	{
		return Mp3Library.length;
	}
	/**
	 *Returns an Mp3File at given index position in collection
	 *@param index of array that points to Mp3File
	 *@return an Mp3File at given index
	 *@throws an IndexOutOfBoundsException if index is out of bounds
	 */
	public Mp3File getFile(int index) throws IndexOutOfBoundsException
	{
		return (Mp3File) Mp3Library[index];
	}
	/**
	 *Plays an Mp3File at given index position in collection
	 *@param index of array that points to Mp3File
	 *@throws an IndexOutOfBoundsException if index is out of bounds
	 */
	public void play (int index) throws IndexOutOfBoundsException
	{
		MP3Player player = new MP3Player();
		player.play(((Mp3File)Mp3Library[index]).getPath());
		
	}
}
