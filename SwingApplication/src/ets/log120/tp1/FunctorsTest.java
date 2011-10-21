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
}
