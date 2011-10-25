package ets.log120.tp1.functors;

import ets.log120.tp1.Shape;

public class AreaAscending implements java.util.Comparator<Shape> {
	@Override
	public int compare(Shape left, Shape right) {
		return (int) (left.getArea() - right.getArea());
	}
}
