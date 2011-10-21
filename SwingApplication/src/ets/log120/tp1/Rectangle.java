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
		super(color, sequenceNumber, x1, y1);
		this.x2 = x2;
		this.y2 = y2;
		height = y2 - y1;
		width = x2 - x1;
	}
	
	// ////////////////////////////////////////////////
	// Accesseur(s)
	// ////////////////////////////////////////////////
	
	@Override
	public double getArea() {
		return getX() * getY();
	}
	
	@Override
	public double getMaxDistanceBetweenPoints() {
		return Point2D.distance(getX(), getY(), x2, y2);
	}

	// ////////////////////////////////////////////////
	// Méthode(s)
	// ////////////////////////////////////////////////

	@Override
	public void draw(java.awt.Graphics g) {
		super.draw(g);
		g.drawRect(getX(), getY(), width, height);
		g.fillRect(getX(), getY(), width, height);
	}

	// ////////////////////////////////////////////////
	// Attribut(s)
	// ////////////////////////////////////////////////

	private int x2;
	private int y2;
	private int height;
	private int width;
}
