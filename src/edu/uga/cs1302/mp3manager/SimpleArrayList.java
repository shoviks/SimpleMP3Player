package edu.uga.cs1302.mp3manager;
// very similar to the ArrayList class in OpenJDK


import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.Arrays;
import java.util.ListIterator;
import org.junit.*;



/**
 * This class provides a simple generic list implemented as 
 * an array. It is similar to the ArrayList class included 
 * in the java.util package.
 *
 * The first element of the list is at position 0 and the 
 * last element is at position list.size() - 1.
 *
 * Except for the method next(), implementation of the 
 * SimpleArrayListIterator methods is left as part of your 
 * project.
 */
public class SimpleArrayList<E>{
	/**
	 * @value is the default array size
	 */
    public static final int DEFAULTSZ = 20; // default array size
    private Object[] list;		    // contents of the list
    private int count;			    // number of the list elements
    private int modCount = 0;		    // the total number of modifications 
    					    // (add and remove calls)

    /**
     * Creates an empty SimpleArrayList with the default capacity
     */
    public SimpleArrayList()
    {
	list = new Object[ DEFAULTSZ ];
	count = 0;
    }
    
    /**
     * Converts SimpleArrayList to an array.
     * @return an array.
     */
    public Object[] toArray()
    {
    	Object[] array;
    	array = new Object[this.size()];
    	for (int i = 0; i < this.size(); i++)
    	{
    		array[i] = this.get(i);
    	}
    	return array;
    }
    
    /**
     * Checks if this SimpleArrayList is empty.
     * @return true if and only if the list is empty.
     */
    public boolean isEmpty()
    {
	return count == 0;
    }

    /**
     * Returns the number of elements in this SimpleArrayList
     * @return the number of elements in this list
     */
    public int size()
    {
	return count;
    }

    /**
     * Adds an element at the end of the list.
     * @param e the element to be added to the list.
     * @return true
     */
    public boolean add( E e )
    {
	// make sure that the list has sufficient space
	ensureCapacity( count + 1 );
	list[ count ] = e;
	count++;
	modCount++;
	return true;
    }

    /**
     * Returns the element of the list at the indicated position.
     * @param index the position of the list element to return.
     * @return the element at position index.
     * throws IndexOutOfBoundsException if the index is < 0 or >= the size of the list.
     */
    public E get( int index )
    { 
	// check if the given index is valid
	if( index < 0 || index >= count )
	    throw new IndexOutOfBoundsException( "Index value: " + index );

	@SuppressWarnings("unchecked")
	E e = (E) list[ index ];
	return e;
    }   
	    
    /**
     * Adds an element to the list at the specified position.
     * @param index the position where the element should be added.
     * @param e the element to be added to the list.
     * @return true
     * throws IndexOutOfBoundsException if the index out of range, i.e. index < 0 or index >= the size().
     */
    public boolean add( int index, E e )
    {
	// check if the given index is valid
	if( index < 0 || index > count )
	    throw new IndexOutOfBoundsException( "Index value: " + index );

	// make sure that the list has sufficient space
	ensureCapacity( count + 1 );

	// shift the contents of the array one spot to the right from index
	System.arraycopy( list, index, list, index + 1, count - index);
	list[ index ] = e;
	modCount++;
	count++;
	return true;
    }

    /**
     * Removes an element from the list at the specified position.
     * @param index the position where the element should be removed.
     * @return the removed element
     * throws IndexOutOfBoundsException if the index out of range, i.e. index < 0 or index >= the size().
     */
    public E remove( int index ) 
    {
	// check if the given index is valid
	if( index < 0 || index >= count )
	    throw new IndexOutOfBoundsException( "Index value: " + index );

	// get the element removed
	@SuppressWarnings("unchecked")
	E removed = (E) list[ index ];

	// shift the contents of the array one cell to the left from index, but only if
	// it is not the last cell
	if( index < count - 1 )
	    System.arraycopy( list, index+1, list, index, count - index - 1 );

	list[ --count ] = null;
	modCount++;
	return removed;
    }

    /**
     * Returns the index of the first occurrence of a given element on the list equal 
     * or -1 if the given element is not on the list.  If the argument element is null,
     * the method returns the index of the first null element on the list, or -1 if the list
     * has no null elements.
     * @param e the element to be located on the list.
     * @return the index of the first occurrence of a given element on the list equal
     * or -1 if the given element is not on the list.
     */
    public int indexOf( E e )
    {
	if( e != null ) {
	    // look for the first non-null element equal to the argument e
	    for( int i = 0; i < count; i++ ) 
		if( list[ i ] != null && list[ i ].equals( e ) )
		    return i;
	}
	else {
	    // look for the first null element, since the argument is null
	    for( int i = 0; i < count; i++ ) 
		if( list[ i ] == null )
		    return i;
	}
	return -1;
    }

    /**
     * Returns a ListIterator of the list elements, starting at the given position in this list.
     * @param index the position of the first element on the list to be returned from the iterator.
     * @return the created ListIterator
     * throws IndexOutOfBoundsException if the index is < 0 or >= the size of the list.
     */
    public ListIterator<E> listIterator( int index ) 
    {
        if( index < 0 || index > count )
            throw new IndexOutOfBoundsException( "Index value: " + index );

        return new SimpleArrayListIterator( index );
    }

    // Make sure that the capacity of the list is large enough to accomodate
    // sz number of elements.  If not, enlarge the list array by allocating
    // a larger array and copying the old content.
    private void ensureCapacity( int sz )
    {
	Object[] newList = null;

	// if the list is large enough, return immediately
	if( list.length >= sz )
	    return;

	// compute the next larger size, as a multiple of DEFAULTSZ
	int newSize = (sz / DEFAULTSZ + 1) * DEFAULTSZ;

	// create a new array and copy the old contents into it
	list = Arrays.copyOf( list, newSize );
    }

    /**
     * This class provides an iterator for the SimpleArrayList.
     * It partially implements the java.util.ListIterator interface.
     *
     * Except for the method next(), implementation of the 
     * SimpleArrayListIterator methods is left as part of your 
     * project.
     */
    private class SimpleArrayListIterator
	implements ListIterator<E>
    {
	private int currPos = 0; // index of the element to be returned next
        private int expectedModCount = modCount;

	// Creates a new iterator starting at position index.
    /**
     * Returns a SimpleArrayListIterator of the list elements, starting at the given position in this list.
     * @param index the position of the first element on the list to be returned from the iterator.
     * @return the created SimpleArrayListIterator
     * throws IndexOutOfBoundsException if the index is < 0 or >= the size of the list.
     */
    public SimpleArrayListIterator( int index )
	{
    	checkForComodification();
		currPos = index;
	    // your code
		
	}

    // Returns true if this list iterator has more elements when traversing the list forward.
    /**
	 * Returns true if list iterator has more elements when traversing the list forward
	 * @return true if list iterator has more elements when traversing the list forward
	 * @throw NoSuchElementException if the next element does not exist.
	 */
	public boolean hasNext() 
	{
	    // replace with your code
		checkForComodification();
		return (currPos!=(count));
		
	}

	// Returns true if this list iterator has more elements when traversing the list in the reverse direction.
	/**
	 * Returns the next index of the element that would be returned by a call to next.
	 * @return the next index of the element that would be returned by a call to next.
	 * @throw NoSuchElementException if the next element does not exist.
	 */
	public boolean hasPrevious() 
	{
	    // replace with your code
		checkForComodification();
		return (currPos!=0);
	}

	/**
	 * Returns the next element on the list.
	 * @return the next element on the list.
	 * @throw NoSuchElementException if the next element does not exist.
	 */
	@SuppressWarnings("unchecked")
	public E next() 
	{
	    checkForComodification();
	    if( currPos >= count ) 
		throw new NoSuchElementException();
	    return (E) SimpleArrayList.this.list[ currPos++ ];
	}

	// Returns the index of the element that would be returned by a call to next.
	/**
	 * Returns the next index of the element that would be returned by a call to next.
	 * @return the next index of the element that would be returned by a call to next.
	 * @throw NoSuchElementException if the next element does not exist.
	 */
	public int nextIndex() 
	{
	    // replace with your code
		checkForComodification();
	    if (currPos++<count)
	    	return currPos++;
	    else
	    	throw new NoSuchElementException();
	}

	// Returns the previous element in the list.
	/**
	 * Returns the previous element on the list.
	 * @return the previous element on the list.
	 * @throw NoSuchElementException if the next element does not exist.
	 */
	@SuppressWarnings("unchecked")
	public E previous() 
	{
		checkForComodification();
	    if( currPos <= 0 )
	    	throw new NoSuchElementException();
	    currPos--;
	    return (E) SimpleArrayList.this.list[ currPos ];
	    
	}

	// Returns the index of the element that would be returned by a call to previous.
	/**
	 * Returns the previous index of the element that would be returned by a call to previous.
	 * @return the previous index of the element that would be returned by a call to previous.
	 * @throw NoSuchElementException if the next element does not exist.
	 */
	public int previousIndex() 
	{
	    // replace with your code
		checkForComodification();
	    if (currPos-->=0)
	    	return currPos--;
	    else
	    	throw new NoSuchElementException();
	}

	// the following are optional operations which are not supported in the 
	// SimpleArrayList implementation

	// Adds a new element
	// not implemented here
	public void add(Object e)
	{
	    throw new UnsupportedOperationException( "add is not available" );
	}

	// Removes from the list the last element that was returned by next or previous (optional operation).
	// not implemented here
	public void remove() 
	{
	    throw new UnsupportedOperationException( "remove is not available" );
	}

	// Replaces the last element returned by next or previous with the specified element (optional operation).
	// not implemented here
	public void set(Object e)
	{
	    throw new UnsupportedOperationException( "set is not available" );
	}

	// check if there was a concurrent modification of the list contents.
	// if yes, throw a ConcurrentModificationException exception
	private final void checkForComodification() {
	    if (expectedModCount != SimpleArrayList.this.modCount)
		throw new ConcurrentModificationException();
	}
    }
}
