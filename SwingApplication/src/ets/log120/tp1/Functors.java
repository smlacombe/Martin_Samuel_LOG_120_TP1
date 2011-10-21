package ets.log120.tp1;

import java.util.Comparator;

public class Functors {
	
	/**
	 * Foncteur indiquant si une forme a un numéro de séquence inférieur à une seconde forme.
	 * 
	 * @author Martin Desharnais
	 */
	public static class SequenceNumberAscending implements Comparator<Shape> {
		@Override
		public int compare(Shape left, Shape right) {
			return left.getSequenceNumber() - right.getSequenceNumber();
		}
	}
	
	public class AreaAscending {
		// medium
	}
	
	public class ShapeTypeAscending {
		// easy
	}
	
	public class MaxDistanceBetweenPointsAscending {
		// Difficult
	}
	
	public class Not {
		// Very easy
	}
}