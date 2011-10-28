package ets.log120.tp1.functors;

import ets.log120.tp1.Shape;

/**
 * Foncteur indiquant si une forme a une hauteur inférieur à une seconde forme.
 * 
 * @author Martin Desharnais
 */
public class HeightAscending implements java.util.Comparator<Shape> {
	@Override
	public int compare(Shape left, Shape right) {
		return left.getHeight() - right.getHeight(); 
	}
}