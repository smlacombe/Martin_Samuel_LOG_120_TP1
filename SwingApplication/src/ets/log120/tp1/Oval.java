package ets.log120.tp1;

import java.awt.Color;
import java.awt.Graphics;

public class Oval extends Shape {

	private int height;
	private int width;
	
	//////////////////////////////////////////////////
	// Constructor(s)
	//////////////////////////////////////////////////
	
	public Oval(int sequenceNumber, int x, int y, int hRadius, int vRadius) {
		this(Color.blue, sequenceNumber, x, y, hRadius, vRadius);
		height = vRadius * 2;
		width = hRadius * 2; 
	}
	
	public Oval(Color color, int sequenceNumber, int x, int y, int hRadius, int vRadius) {
		super(color, sequenceNumber, x - hRadius , y - vRadius);
		height = vRadius * 2;
		width = hRadius * 2; 
	}
	
	//////////////////////////////////////////////////
	// Method(s)
	//////////////////////////////////////////////////
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.drawOval(x, y, width, height);
		g.fillOval(x, y, width, height);
	}

}
