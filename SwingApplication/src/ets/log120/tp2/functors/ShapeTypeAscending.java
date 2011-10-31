package ets.log120.tp2.functors;

import ets.log120.tp2.Circle;
import ets.log120.tp2.Oval;
import ets.log120.tp2.Rectangle;
import ets.log120.tp2.Shape;
import ets.log120.tp2.Square;

/**
 * Foncteur indiquant si le type de forme est supérieur à une autre forme
 * 
 * @author Samuel Milette-Lacombe
 */
public class ShapeTypeAscending implements java.util.Comparator<Shape> {
	@Override
	public int compare(Shape left, Shape right) {
		int orderLeft;
		int orderRight;
		
		if (left instanceof Square)
			orderLeft = 1;
		else if (left instanceof Rectangle)
			orderLeft = 2;
		else if (left instanceof Circle)
			orderLeft = 3;
		else if (left instanceof Oval)
			orderLeft = 4;
		else
			orderLeft = 5;
		
		if (right instanceof Square)
			orderRight = 1;
		else if (right instanceof Rectangle)
			orderRight = 2;
		else if (right instanceof Circle)
			orderRight = 3;
		else if (right instanceof Oval)
			orderRight = 4;
		else
			orderRight = 5;
		
		return orderLeft - orderRight;				
	}
}
