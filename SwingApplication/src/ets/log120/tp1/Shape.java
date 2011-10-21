package ets.log120.tp1;

/**
 * Forme qui pourra être affichée dans un environnement graphique.
 * 
 * @author Samuel Milette-Lacombe
 * @author Martin Desharnais
 */
public abstract class Shape {

	// ///////////////////////////////////////////////
	// Constructeur(s)
	// ///////////////////////////////////////////////

	/**
	 * Construit une forme avec une couleur, un numéro d'identification et ses
	 * coordonnées.
	 */
	protected Shape(java.awt.Color color, int sequenceNumber, int x, int y) {
		this.color = color;
		this.sequenceNumber = sequenceNumber;
		this.x = x;
		this.y = y;
		logger.logID(sequenceNumber);
	}

	// ///////////////////////////////////////////////
	// Accesseur(s)
	// ///////////////////////////////////////////////
	
	/**
	 * Retourne le numéro de séquence de la forme.
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * Retourne la valeur en x de la position du coin supérieur gauche de la forme.
	 */
	protected int getX() {
		return x;
	}

	/**
	 * Retourne la valeur en y de la position du coin supérieur gauche de la forme.
	 */
	protected int getY() {
		return y;
	}

	// ///////////////////////////////////////////////
	// Méthode(s)
	// ///////////////////////////////////////////////

	/**
	 * Dessine la forme sur l'environnement graphique reçu en paramètre.
	 */
	public void draw(java.awt.Graphics g) {
		g.setColor(color);
	}
	
	/**
	 * Retourne l'aire de la forme.
	 */	
	public abstract double getArea();
	
	/**
	 * Retourne la plus grande entre deux points de la forme.
	 */
	public abstract double getMaxDistanceBetweenPoints();

	// ///////////////////////////////////////////////
	// Attribut(s)
	// ///////////////////////////////////////////////

	private static ca.etsmtl.log.util.IDLogger logger = ca.etsmtl.log.util.IDLogger.getInstance();
	private java.awt.Color color;
	private int sequenceNumber;
	private int x;
	private int y;
}
