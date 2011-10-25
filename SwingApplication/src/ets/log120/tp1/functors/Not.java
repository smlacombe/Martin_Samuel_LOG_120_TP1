package ets.log120.tp1.functors;

import ets.log120.tp1.Shape;

/**
 * Foncteur inversant la valeur retournée par un autre foncteur.
 * 
 * @author Martin Desharnais
 */
public class Not implements java.util.Comparator<Shape> {

	// ////////////////////////////////////////////////
	// Constructeur(s)
	// ////////////////////////////////////////////////

	public Not(java.util.Comparator<Shape> comp) {
		this.comp = comp;
	}

	// ////////////////////////////////////////////////
	// Accesseur(s)
	// ////////////////////////////////////////////////

	@Override
	public int compare(Shape left, Shape right) {
		return -1 * comp.compare(left, right);
	}

	// ////////////////////////////////////////////////
	// Attributs(s)
	// ////////////////////////////////////////////////

	private java.util.Comparator<Shape> comp;
}