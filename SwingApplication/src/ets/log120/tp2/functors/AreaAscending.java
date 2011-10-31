package ets.log120.tp2.functors;

import ets.log120.tp2.Shape;

/**
 * Foncteur indiquant si une forme a une aire supérieure à une autre forme.
 * 
 * @author Samuel Milette-Lacombe
 */
public class AreaAscending implements java.util.Comparator<Shape> {
	@Override
	public int compare(Shape left, Shape right) {
		return (int) (left.getArea() - right.getArea());
	}
}
