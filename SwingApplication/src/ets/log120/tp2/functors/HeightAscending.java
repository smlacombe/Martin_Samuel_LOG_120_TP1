package ets.log120.tp2.functors;

import ets.log120.tp2.Shape;

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