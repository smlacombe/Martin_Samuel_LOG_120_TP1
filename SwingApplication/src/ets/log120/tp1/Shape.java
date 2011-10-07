package ets.log120.tp1;

import java.awt.Color;
import java.awt.Graphics;
import ca.etsmtl.log.util.IDLogger;

public abstract class Shape {
	
	private Color color;
	protected int sequenceNumber;
	protected int x;
	protected int y;
		
	//////////////////////////////////////////////////
	// Constructor(s)
	//////////////////////////////////////////////////
	
	public Shape (Color color, int sequenceNumber, int x, int y) {
		this.color = color;
		this.sequenceNumber = sequenceNumber;
		this.x = x;
		this.y = y;
		IDLogger.getInstance().logID(sequenceNumber);		
	}
	
	//////////////////////////////////////////////////
	// Method(s)
	//////////////////////////////////////////////////
	
	public void draw(Graphics g) {
		g.setColor(color);
	}
	
}