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
	
	public static class AreaAscending implements Comparator<Shape> {
		@Override
		public int compare(Shape left, Shape right) {
			return (int) (left.getArea() - right.getArea());
		}
	}
	
	public static class ShapeTypeAscending implements Comparator<Shape> {
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