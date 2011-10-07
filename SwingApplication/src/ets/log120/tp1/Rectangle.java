package ets.log120.tp1;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {
	
	private int height;
	private int width;
	
	//////////////////////////////////////////////////
	// Constructor(s)
	//////////////////////////////////////////////////
	
	public Rectangle(int sequenceNumber, int x1, int y1,  int x2, int y2) {
		this(Color.RED, sequenceNumber, x1, y1, x2, y2);
		height = y2 - y1;		
		width = x2 - x1;
	}
	
	public Rectangle(Color color, int sequenceNumber, int x1, int y1,  int x2, int y2) {
		super(color, sequenceNumber, x1, y1);
		height = y2 - y1;		
		width = x2 - x1;
	}
	
	//////////////////////////////////////////////////
	// Methods(s)
	//////////////////////////////////////////////////

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.drawRect(x, y, width, height);
		g.fillRect(x, y, width, height);
	}

}
