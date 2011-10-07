package ets.log120.tp1;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {

	private int x2;
	private int y2;
	
	//////////////////////////////////////////////////
	// Constructor(s)
	//////////////////////////////////////////////////
	
	public Line(int sequenceNumber, int x1, int y1, int x2, int y2) {
		super(Color.GREEN, sequenceNumber, x1, y1);
		this.x2 = x2;
		this.y2 = y2;
	}

	//////////////////////////////////////////////////
	// Method(s)
	//////////////////////////////////////////////////
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.drawLine(x, y, x2, y2);
	}

}
