package ets.log120.tp1;

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
		super(color, sequenceNumber, x - hRadius, y - vRadius);
		height = vRadius * 2;
		width = hRadius * 2;
	}

	// ////////////////////////////////////////////////
	// Méthode(s)
	// ////////////////////////////////////////////////

	@Override
	public void draw(java.awt.Graphics g) {
		super.draw(g);
		g.drawOval(getX(), getY(), width, height);
		g.fillOval(getX(), getY(), width, height);
	}
	
	@Override
	public double getArea() {
		return Math.PI * width * height;
	}

	// ////////////////////////////////////////////////
	// Attribut(s)
	// ////////////////////////////////////////////////

	private int height;
	private int width;
}
