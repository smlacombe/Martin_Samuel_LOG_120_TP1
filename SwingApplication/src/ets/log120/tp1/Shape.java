package ets.log120.tp1;

/**
 * Forme qui pourra être affichée dans un environement graphique.
 * 
 * @author Samuel Milette-Lacombe & Martin Desharnais
 */
public abstract class Shape {

	// ////////////////////////////////////////////////
	// Constructeurs(s)
	// ////////////////////////////////////////////////

	/**
	 * Construit une forme avec une couleur, un numéro d'identification et ses
	 * coordonnées.
	 */
	public Shape(java.awt.Color color, int sequenceNumber, int x, int y) {
		this.color = color;
		this.sequenceNumber = sequenceNumber;
		this.x = x;
		this.y = y;
		ca.etsmtl.log.util.IDLogger.getInstance().logID(sequenceNumber);
	}

	// ////////////////////////////////////////////////
	// Méthode(s)
	// ////////////////////////////////////////////////

	/**
	 * Dessine la forme sur l'environement graphique reçu en paramètre.
	 */
	public void draw(java.awt.Graphics g) {
		g.setColor(color);
	}
	
	////////////////////////////////////////////////////
	// Attribut(s)
	////////////////////////////////////////////////////

	private java.awt.Color color;
	protected int sequenceNumber;
	protected int x;
	protected int y;
}