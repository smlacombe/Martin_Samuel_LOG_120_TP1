package ets.log120.tp2;

/**
 * Spécialisation d'une forme permettant d'afficher un oval.
 * 
 * @author Samuel Milette-Lacombe
 * @author Martin Desharnais
 */
public class Oval extends Shape {

	// ////////////////////////////////////////////////
	// Constructeur(s)
	// ////////////////////////////////////////////////

	/**
	 * Construit un oval avec les informations sur sa taille et sa position.
	 */
	public Oval(int sequenceNumber, int x, int y, int hRadius, int vRadius) {
		this(java.awt.Color.blue, sequenceNumber, x, y, hRadius, vRadius);
	}
	
	/**
	 * Construit un oval avec les informations sur sa taille, sa position et sa couleur.
	 */
	protected Oval(java.awt.Color color, int sequenceNumber, int x, int y, int hRadius, int vRadius) {
		super(color, sequenceNumber, x - hRadius, y - vRadius, hRadius * 2, vRadius * 2);
	}

	// ////////////////////////////////////////////////
	// Accesseur(s)
	// ////////////////////////////////////////////////
	
	@Override
	public double getArea() {
		return Math.PI * getWidth() * getHeight();
	}
	
	@Override
	public double getMaxDistanceBetweenPoints() {
		return (getHeight() > getWidth()) ? getHeight() : getWidth();
	}
	
	// ////////////////////////////////////////////////
	// Méthode(s)
	// ////////////////////////////////////////////////

	@Override
	protected void doDraw(java.awt.Graphics g, int x, int y) {
		g.drawOval(x, y, getWidth(), getHeight());
		g.fillOval(x, y, getWidth(), getHeight());
	}
}
