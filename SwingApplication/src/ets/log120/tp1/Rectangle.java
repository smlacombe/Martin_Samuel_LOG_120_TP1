package ets.log120.tp1;

import java.awt.geom.Point2D;

/**
 * Spécialisation d'une forme permettant d'afficher un rectangle.
 * 
 * @author Samuel Milette-Lacombe
 * @author Martin Desharnais
 */
public class Rectangle extends Shape {

	// ////////////////////////////////////////////////
	// Constructeur(s)
	// ////////////////////////////////////////////////
	
	/**
	 * Construit un rectangle avec les informations sur sa taille et sa position.
	 */
	public Rectangle(int sequenceNumber, int x1, int y1, int x2, int y2) {
		this(java.awt.Color.RED, sequenceNumber, x1, y1, x2, y2);
	}
	
	/**
	 * Construit un rectangle avec les informations sur sa taille, sa position et sa couleur.
	 */
	protected Rectangle(java.awt.Color color, int sequenceNumber, int x1, int y1, int x2, int y2) {
		super(color, sequenceNumber, x1, y1, x2 - x1, y2 - y1);
	}
	
	// ////////////////////////////////////////////////
	// Accesseur(s)
	// ////////////////////////////////////////////////
	
	@Override
	public double getArea() {
		return getWidth() * getHeight();
	}
	
	@Override
	public double getMaxDistanceBetweenPoints() {
		return Point2D.distance(getX(), getY(), getX() + getWidth(), getY() + getHeight());
	}

	// ////////////////////////////////////////////////
	// Méthode(s)
	// ////////////////////////////////////////////////

	@Override
	protected void doDraw(java.awt.Graphics g, int x, int y) {
		g.drawRect(x, y, getWidth(), getHeight());
		g.fillRect(x, y, getWidth(), getHeight());
	}
}
