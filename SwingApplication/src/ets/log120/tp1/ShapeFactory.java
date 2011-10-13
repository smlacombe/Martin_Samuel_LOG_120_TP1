package ets.log120.tp1;

/**
 * Fabriquant fournissant les formes utilisées par l'application.
 * 
 * @author Martin Desharnais
 */
public class ShapeFactory {
	
	/**
	 * Retourne une forme correspondant à la requête reçu en paramètre.
	 */
	public static Shape makeShape(String request) throws IllegalArgumentException {
		RequestParser parser = new RequestParser(request);

		if (parser.getShapeType().equalsIgnoreCase("carre")) {
			return new Square(
					parser.getSequenceNumber(),
					parser.getValueAt(0),
					parser.getValueAt(1),
					parser.getValueAt(2),
					parser.getValueAt(3));
		} else if (parser.getShapeType().equalsIgnoreCase("rectangle")) {
			return new Rectangle(
					parser.getSequenceNumber(),
					parser.getValueAt(0),
					parser.getValueAt(1),
					parser.getValueAt(2),
					parser.getValueAt(3));
		} else if (parser.getShapeType().equalsIgnoreCase("cercle")) {
			return new Circle(
					parser.getSequenceNumber(),
					parser.getValueAt(0),
					parser.getValueAt(1),
					parser.getValueAt(2));
		} else if (parser.getShapeType().equalsIgnoreCase("ovale")) {
			return new Oval(parser.getSequenceNumber(),
					parser.getValueAt(0),
					parser.getValueAt(1),
					parser.getValueAt(2),
					parser.getValueAt(3));
		} else if (parser.getShapeType().equalsIgnoreCase("ligne")) {
			return new Line(
					parser.getSequenceNumber(),
					parser.getValueAt(0),
					parser.getValueAt(1),
					parser.getValueAt(2),
					parser.getValueAt(3));
		} else
			throw new IllegalArgumentException();
	}
}