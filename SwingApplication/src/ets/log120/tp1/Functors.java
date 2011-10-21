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
	
	public static class AreaAscending implements Comparator<Shape>{
		@Override
		public int compare(Shape left, Shape right) {
			return (int)(left.getArea() - right.getArea());
		}
	}
	
	public class ShapeTypeAscending {
		// easy
	}
	
	public static class MaxDistanceBetweenPointsAscending implements Comparator<Shape> {
		@Override
		public int compare(Shape left, Shape right) {
			return (int)(left.getMaxDistanceBetweenPoints() - right.getMaxDistanceBetweenPoints());
		}
	}
	
	public static class Not implements Comparator<Shape>{
		private Comparator<Shape> comp;
		
		public Not(Comparator<Shape> comp) {
			this.comp = comp;
		}
		
		@Override
		public int compare(Shape left, Shape right) {
			return -1 * comp.compare(left, right);
		}
		
	}
}