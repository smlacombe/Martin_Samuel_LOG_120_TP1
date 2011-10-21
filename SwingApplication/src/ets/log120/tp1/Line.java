package ets.log120.tp1;

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
		super(java.awt.Color.GREEN, sequenceNumber, x1, y1);
		this.x2 = x2;
		this.y2 = y2;
	}

	//////////////////////////////////////////////////
	// Méthode(s)
	//////////////////////////////////////////////////
	
	@Override
	public void draw(java.awt.Graphics g) {
		super.draw(g);
		g.drawLine(getX(), getY(), x2, y2);
	}
	
	@Override
	public double getArea() {
		return Point2D.distance(getX(), getY(), x2, y2);
	}

	//////////////////////////////////////////////////
	// Attribut(s)
	//////////////////////////////////////////////////
	
	private int x2;
	private int y2;
}
