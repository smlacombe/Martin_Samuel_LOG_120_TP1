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
	protected Shape(java.awt.Color color, int sequenceNumber, int x, int y, int width, int height) {
		this.color = color;
		this.sequenceNumber = sequenceNumber;
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
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
	
	/**
	 * Retourne la hauteur de la forme.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Retourne la largeur de la forme.
	 */
	public int getWidth() {
		return width;
	}

	// ///////////////////////////////////////////////
	// Méthode(s)
	// ///////////////////////////////////////////////

	/**
	 * Dessine la forme sur l'environnement graphique reçu en paramètre.
	 */
	public void draw(java.awt.Graphics g) {
		draw(g, x, y);
	}
	
	/**
	 * Dessine la forme sur l'environnement graphique reçu en paramètre à la position demandée à l'aide de la méthode doDraw().
	 */
	public void draw(java.awt.Graphics g, int x, int y) {
		g.setColor(color);
		doDraw(g, x, y);
	}
	
	/**
	 * Dessine la forme sur l'environnement graphique reçu en paramètre à la position demandée.
	 */
	protected abstract void doDraw(java.awt.Graphics g, int x, int y);
	
	/**
	 * Retourne l'aire de la forme.
	 */
	public abstract double getArea();
	
	/**
	 * Retourne la plus grande distance entre deux points de la forme.
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
	private int height;
	private int width;
}
