import static org.junit.Assert.*;
import java.util.ListIterator;
import org.junit.Test;

import edu.uga.cs1302.mp3manager.SimpleArrayList;



public class SimpleArrayListTester {
	SimpleArrayList<String> aList = new SimpleArrayList();
	ListIterator xyz;
	
	

	@Test
	public void testSimpleArrayListIterator() {
	try{
		aList.add("a");
		aList.add("b");
		xyz = aList.listIterator(0);
		xyz.hasPrevious();
	}
	catch (IndexOutOfBoundsException e)
	{
		assertTrue(true);
	}
	}

	@Test
	public void testHasNext() {
		aList.add("a");
		aList.add("b");
		xyz = aList.listIterator(0);
		assertTrue(xyz.hasNext());
		xyz = aList.listIterator(aList.size());
		assertFalse(xyz.hasNext());
	}

	@Test
	public void testHasPrevious() {
		aList.add("a");
		aList.add("b");
		xyz = aList.listIterator(0);
		assertFalse(xyz.hasPrevious());
		xyz = aList.listIterator(aList.size());
		assertTrue(xyz.hasPrevious());
	}

	@Test
	public void testNextIndex() {
		aList.add("a");
		aList.add("b");
		xyz = aList.listIterator(0);
		assertTrue(xyz.nextIndex()==1);
	}

	@Test
	public void testPrevious() {
		aList.add("a");
		aList.add("b");
		xyz = aList.listIterator(1);
		String statement = (String) xyz.previous();
		assertTrue(statement.equals("a"));
	}

	@Test
	public void testPreviousIndex() {
		aList.add("a");
		aList.add("b");
		xyz = aList.listIterator(1);
		assertTrue(xyz.previousIndex()==0);
	}

}
