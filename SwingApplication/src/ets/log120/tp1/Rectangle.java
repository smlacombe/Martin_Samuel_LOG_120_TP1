package ets.log120.tp1;

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
		height = y2 - y1;
		width = x2 - x1;
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
	
	@Override
	public double getArea() {
		return getX() * getY();
	}

	// ////////////////////////////////////////////////
	// Attribut(s)
	// ////////////////////////////////////////////////

	private int height;
	private int width;
}
