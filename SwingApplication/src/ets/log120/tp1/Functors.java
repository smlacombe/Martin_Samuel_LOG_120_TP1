package ets.log120.tp1;

import java.util.Comparator;

public class Functors {
	
	public class SequenceNumberAscending {
		// very easy
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
	
	public class MaxDistanceBetweenPointsAscending {
		// Difficult
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