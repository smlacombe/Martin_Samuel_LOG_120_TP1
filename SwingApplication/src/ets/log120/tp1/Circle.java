package ets.log120.tp1;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Oval {
	
	public Circle(int sequenceNumber, int x, int y, int radius) {
		super(Color.YELLOW, sequenceNumber, x, y, radius, radius);
	}
}
