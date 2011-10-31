package ets.log120.tp2;

import java.awt.geom.Point2D;

/**
 * Spécialisation d'une forme permettant d'afficher une ligne.
 * 
 * @author Samuel Milette-Lacombe
 * @author Martin Desharnais
 */
public class Line extends Shape {
	
	//////////////////////////////////////////////////
	// Constructeur(s)
	//////////////////////////////////////////////////
	
	/**
	 * Construit une ligne avec les informations sur sa taille et sa position.
	 */
	public Line(int sequenceNumber, int x1, int y1, int x2, int y2) {
		super(java.awt.Color.GREEN, sequenceNumber,
				Math.min(x1, x2),   // x
				Math.min(y1, y2),   // y
				Math.abs(x2 - x1),  // width
				Math.abs(y2 - y1)); // height
		
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	//////////////////////////////////////////////////
	// Accesseur(s)
	//////////////////////////////////////////////////
	
	@Override
	public double getArea() {
		return Point2D.distance(x1, y1, x2, y2);
	}
	
	@Override
	public double getMaxDistanceBetweenPoints() {
		return Point2D.distance(x1, y1, x2, y2);
	}
	
	//////////////////////////////////////////////////
	// Méthode(s)
	//////////////////////////////////////////////////
	
	@Override
	public void doDraw(java.awt.Graphics g, int x, int y) {
		if(x1 <= x2) {
			if(y1 <= y2)
				g.drawLine(x, y, x + getWidth(), y + getHeight());
			else
				g.drawLine(x, y + getHeight(), x + getWidth(), y);
		} else {
			if(y1 <= y2)
				g.drawLine(x, y + getHeight(), x + getWidth(), y);
			else
				g.drawLine(x, y, x + getWidth(), y + getHeight());
		}
	}
	
	//////////////////////////////////////////////////
	// Attribut(s)
	//////////////////////////////////////////////////
	
	int x1;
	int y1;
	int x2;
	int y2;
}
