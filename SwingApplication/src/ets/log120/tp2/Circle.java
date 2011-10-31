package ets.log120.tp2;

/**
 * Spécialisation d'un oval permettant d'afficher un cercle.
 * 
 * @author Martin Desharnais
 */
public class Circle extends Oval {
	
	//////////////////////////////////////////////////
	// Constructeur(s)
	//////////////////////////////////////////////////
	
	/**
	 * Construit un cercle avec les informations sur sa taille et sa position.
	 */
	public Circle(int sequenceNumber, int x, int y, int radius) {
		super(java.awt.Color.YELLOW, sequenceNumber, x, y, radius, radius);
	}
}
