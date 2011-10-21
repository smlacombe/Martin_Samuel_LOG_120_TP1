package ets.log120.tp1;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class FunctorsTest {
	
	ets.util.containers.List<Shape> l;
	Shape square;
	Shape rectangle;
	Shape line;
	Shape circle;
	Shape oval;
	
	@Before
	public void before() {
		l = new ets.util.containers.List<Shape>();
		l.pushBack(square = ShapeFactory.makeShape("200 <CARRE> 100 100 200 200 </CARRE>"));
		l.pushBack(rectangle = ShapeFactory.makeShape("300 <RECTANGLE> 100 100 150 200 </RECTANGLE>"));
		l.pushBack(line = ShapeFactory.makeShape("250 <LIGNE> 10 10 10 100 </LIGNE>"));
		l.pushBack(circle = ShapeFactory.makeShape("100 <CERCLE> 250 250 200 </CERCLE>"));
		l.pushBack(oval = ShapeFactory.makeShape("500 <OVALE> 100 100 50 75 </OVALE>"));
	}
	@Test
	public void testSequenceNumberAscending() {
		Iterator<Shape> i = l.iterator();
		
		assertEquals(i.next(), square);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), line);
		assertEquals(i.next(), circle);
		assertEquals(i.next(), oval);
		
		l.sort(new Functors.SequenceNumberAscending());
		
		i = l.iterator();
		assertEquals(i.next(), circle);
		assertEquals(i.next(), square);
		assertEquals(i.next(), line);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), oval);
	}
	
	@Test
	public void testAreaAscending() {
		Iterator<Shape> i = l.iterator();
		
		assertEquals(i.next(), square);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), line);
		assertEquals(i.next(), circle);
		assertEquals(i.next(), oval);

		l.sort(new Functors.AreaAscending());
		
		i = l.iterator();
		
		assertEquals(i.next(), line);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), square);
		assertEquals(i.next(), oval);
		assertEquals(i.next(), circle);
	}
	
	@Test
	public void testNotAreaAscending() {
		Iterator<Shape> i = l.iterator();

		assertEquals(i.next(), square);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), line);
		assertEquals(i.next(), circle);
		assertEquals(i.next(), oval);
		
		l.sort(new Functors.Not(new Functors.AreaAscending()));
		
		i = l.iterator();

		assertEquals(i.next(), circle);
		assertEquals(i.next(), oval);
		assertEquals(i.next(), square);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), line);
	}
	
	@Test
	public void testShapeTypeAscending() {
		Iterator<Shape> i = l.iterator();
		
		assertEquals(i.next(), square);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), line);
		assertEquals(i.next(), circle);
		assertEquals(i.next(), oval);

		l.sort(new Functors.ShapeTypeAscending());
		
		i = l.iterator();

		assertEquals(i.next(), square);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), circle);
		assertEquals(i.next(), oval);
		assertEquals(i.next(), line);
	}
	
	@Test
	public void testNotShapeTypeAscending() {
		Iterator<Shape> i = l.iterator();
		
		assertEquals(i.next(), square);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), line);
		assertEquals(i.next(), circle);
		assertEquals(i.next(), oval);

		l.sort(new Functors.Not(new Functors.ShapeTypeAscending()));
		
		i = l.iterator();

		assertEquals(i.next(), line);
		assertEquals(i.next(), oval);
		assertEquals(i.next(), circle);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), square);
		
		l.sort(new Functors.Not(new Functors.SequenceNumberAscending()));
		
		i = l.iterator();
		assertEquals(i.next(), oval);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), line);
		assertEquals(i.next(), square);
		assertEquals(i.next(), circle);
	}
	
	@Test
	public void testMaxDistanceBetweenPointsAscending() {
		Iterator<Shape> i = l.iterator();
		
		assertEquals(i.next(), square);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), line);
		assertEquals(i.next(), circle);
		assertEquals(i.next(), oval);
		
		l.sort(new Functors.MaxDistanceBetweenPointsAscending());
		
		i = l.iterator();
		assertEquals(i.next(), line);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), square);
		assertEquals(i.next(), oval);
		assertEquals(i.next(), circle);
	}

	@Test
	public void testMaxDistanceBetweenPointsDescending() {
		Iterator<Shape> i = l.iterator();
		
		assertEquals(i.next(), square);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), line);
		assertEquals(i.next(), circle);
		assertEquals(i.next(), oval);
		
		l.sort(new Functors.Not(new Functors.MaxDistanceBetweenPointsAscending()));
		
		i = l.iterator();
		assertEquals(i.next(), circle);
		assertEquals(i.next(), oval);
		assertEquals(i.next(), square);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), line);
	}
}
