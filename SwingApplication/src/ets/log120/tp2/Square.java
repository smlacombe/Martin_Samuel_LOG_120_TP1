package ets.log120.tp2;

/**
 * Spécialisation d'un rectangle permettant d'afficher un carré.
 * 
 * @author Martin Desharnais
 */
public class Square extends Rectangle {
	
	// ////////////////////////////////////////////////
	// Constructeur(s)
	// ////////////////////////////////////////////////

	/**
	 * Construit un carré avec les informations sur sa taille et sa position.
	 */
	public Square(int sequenceNumber, int x1, int y1, int x2, int y2) {
		super(java.awt.Color.ORANGE, sequenceNumber, x1, y1, x2, y2);
	}
}
