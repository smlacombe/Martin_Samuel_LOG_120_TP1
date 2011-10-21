package ets.util.containers;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

public class ListTest {

	@Test
	public void testSize() {
		List<Integer> l = new List<Integer>();
		assertEquals(0, l.size());

		l.pushBack(1);
		assertEquals(1, l.size());

		l.pushBack(2);
		assertEquals(2, l.size());

		l.pushBack(3);
		assertEquals(3, l.size());

		l.popBack();
		assertEquals(2, l.size());

		l.popBack();
		assertEquals(1, l.size());

		l.popBack();
		assertEquals(0, l.size());
	}

	@Test
	public void testEmpty() {
		List<Integer> l = new List<Integer>();
		assertTrue(l.empty());

		l.pushBack(1);
		assertFalse(l.empty());

		l.popBack();
		assertTrue(l.empty());
	}

	@Test
	public void testPushBack() {
		List<Integer> l = new List<Integer>();

		l.pushBack(1);
		l.pushBack(2);
		l.pushBack(3);
	}

	@Test
	public void testPopBack() {
		fail("Not yet implemented");
	}

	@Test
	public void testPushFront() {
		fail("Not yet implemented");
	}

	@Test
	public void testPopFront() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testClear() {
		List<Integer> l = new List<Integer>();
		
		l.pushBack(1);
		l.pushBack(2);
		l.pushBack(3);
		
		assertEquals(3, l.size());
		assertEquals("1 2 3 ", l.toString());
		
		l.clear();
		
		assertEquals(0, l.size());
		assertEquals("", l.toString());
	}
	
	@Test
	public void testSort() {
		List<Integer> l = new List<Integer>();
		
		l.pushBack(2);
		l.pushBack(1);
		l.pushBack(3);
		
		assertEquals("2 1 3 ", l.toString());
		l.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer left, Integer right) {
				if (left < right)
					return -1;
				else if(left > right)
					return 1;
				else
					return 0;
			}
		});
		assertEquals("1 2 3 ", l.toString());
	}
}
