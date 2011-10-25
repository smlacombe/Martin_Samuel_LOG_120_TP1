package ets.log120.tp1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ets.log120.tp1.functors.AreaAscending;
import ets.log120.tp1.functors.MaxDistanceBetweenPointsAscending;
import ets.log120.tp1.functors.Not;
import ets.log120.tp1.functors.SequenceNumberAscending;
import ets.log120.tp1.functors.ShapeTypeAscending;

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
		l.pushBack(square    = ShapeFactory.makeShape("200 <CARRE> 100 100 200 200 </CARRE>"));
		l.pushBack(rectangle = ShapeFactory.makeShape("300 <RECTANGLE> 100 100 150 200 </RECTANGLE>"));
		l.pushBack(line      = ShapeFactory.makeShape("250 <LIGNE> 10 10 10 100 </LIGNE>"));
		l.pushBack(circle    = ShapeFactory.makeShape("100 <CERCLE> 250 250 200 </CERCLE>"));
		l.pushBack(oval      = ShapeFactory.makeShape("500 <OVALE> 100 100 50 75 </OVALE>"));
	}

	@Test
	public void testSequenceNumberAscending() {
		java.util.Iterator<Shape> i = l.iterator();
		assertEquals(i.next(), square);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), line);
		assertEquals(i.next(), circle);
		assertEquals(i.next(), oval);

		l.sort(new SequenceNumberAscending());

		i = l.iterator();
		assertEquals(i.next(), circle);
		assertEquals(i.next(), square);
		assertEquals(i.next(), line);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), oval);
	}

	@Test
	public void testSequenceNumberDescending() {
		java.util.Iterator<Shape> i = l.iterator();
		assertEquals(i.next(), square);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), line);
		assertEquals(i.next(), circle);
		assertEquals(i.next(), oval);

		l.sort(new Not(new SequenceNumberAscending()));

		i = l.iterator();
		assertEquals(i.next(), oval);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), line);
		assertEquals(i.next(), square);
		assertEquals(i.next(), circle);
	}

	@Test
	public void testAreaAscending() {
		java.util.Iterator<Shape> i = l.iterator();
		assertEquals(i.next(), square);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), line);
		assertEquals(i.next(), circle);
		assertEquals(i.next(), oval);

		l.sort(new AreaAscending());

		i = l.iterator();
		assertEquals(i.next(), line);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), square);
		assertEquals(i.next(), oval);
		assertEquals(i.next(), circle);
	}

	@Test
	public void testAreaDescending() {
		java.util.Iterator<Shape> i = l.iterator();
		assertEquals(i.next(), square);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), line);
		assertEquals(i.next(), circle);
		assertEquals(i.next(), oval);

		l.sort(new Not(new AreaAscending()));

		i = l.iterator();
		assertEquals(i.next(), circle);
		assertEquals(i.next(), oval);
		assertEquals(i.next(), square);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), line);
	}

	@Test
	public void testShapeTypeAscending() {
		java.util.Iterator<Shape> i = l.iterator();
		assertEquals(i.next(), square);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), line);
		assertEquals(i.next(), circle);
		assertEquals(i.next(), oval);

		l.sort(new ShapeTypeAscending());

		i = l.iterator();
		assertEquals(i.next(), square);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), circle);
		assertEquals(i.next(), oval);
		assertEquals(i.next(), line);
	}

	@Test
	public void testShapeTypeDescending() {
		java.util.Iterator<Shape> i = l.iterator();
		assertEquals(i.next(), square);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), line);
		assertEquals(i.next(), circle);
		assertEquals(i.next(), oval);

		l.sort(new Not(new ShapeTypeAscending()));

		i = l.iterator();
		assertEquals(i.next(), line);
		assertEquals(i.next(), oval);
		assertEquals(i.next(), circle);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), square);
	}

	@Test
	public void testMaxDistanceBetweenPointsAscending() {
		java.util.Iterator<Shape> i = l.iterator();
		assertEquals(i.next(), square);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), line);
		assertEquals(i.next(), circle);
		assertEquals(i.next(), oval);

		l.sort(new MaxDistanceBetweenPointsAscending());

		i = l.iterator();
		assertEquals(i.next(), line);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), square);
		assertEquals(i.next(), oval);
		assertEquals(i.next(), circle);
	}

	@Test
	public void testMaxDistanceBetweenPointsDescending() {
		java.util.Iterator<Shape> i = l.iterator();
		assertEquals(i.next(), square);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), line);
		assertEquals(i.next(), circle);
		assertEquals(i.next(), oval);

		l.sort(new Not(new MaxDistanceBetweenPointsAscending()));

		i = l.iterator();
		assertEquals(i.next(), circle);
		assertEquals(i.next(), oval);
		assertEquals(i.next(), square);
		assertEquals(i.next(), rectangle);
		assertEquals(i.next(), line);
	}
}
