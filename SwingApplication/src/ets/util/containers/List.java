package ets.util.containers;

/**
 * Conteneur implémentant une liste doublement chainée.
 * 
 * @author Martin Desharnais
 * @author Samuel Milette-Lacombe
 */
public class List<T> {
	
	public static void main(String[] args) {
		List<String> l = new List<String>();
		System.out.println(l);
		
		l.push_back("A");
		System.out.println(l);
		
		l.push_back("B");
		System.out.println(l);
		
		l.push_back("C");
		System.out.println(l);
		
		l.push_back("D");
		System.out.println(l);
	}
	
	//////////////////////////////////////////////////
	// Constructeur(s)
	//////////////////////////////////////////////////

	public List() {
	}
	
	//////////////////////////////////////////////////
	// Accesseur(s)
	//////////////////////////////////////////////////
	
	/**
	 * Retourne le nombre d'élément contenus dans la liste.
	 */
	public int size() {
		return elementCount;
	}
	
	/**
	 * Retourne si la liste est vide.
	 */
	public boolean empty() {
		return size() == 0;
	}
	
	/**
	 * Retourne une chaine de caractères représentant de la liste.
	 */
	public String toString() {
		String result = "- ";
		Node<T> current = begin;
		
		while (current != null) {
			result += " " + current.data;
			current = current.next;
		}
		
		return result;
	}
	
	//////////////////////////////////////////////////
	// Mutateur(s)
	//////////////////////////////////////////////////
	
	/**
	 * Insère un nouvel élément à la fin de la liste.
	 */
	public void push_back(T newElement) {
		Node<T> newNode = new Node<T>();
		newNode.data = newElement;
		
		if (rbegin != null) {
			newNode.prior = rbegin;
			newNode.prior.next = newNode;
		}
		
		if(empty())
			begin = newNode;
		
		rbegin = newNode;
		
		++elementCount;
	}
	
	//////////////////////////////////////////////////
	// Attribut(s)
	//////////////////////////////////////////////////
	
	private Node<T> begin    = null;
	private Node<T> rbegin   = null;
	private int elementCount = 0;
	
	//////////////////////////////////////////////////
	// Classe(s) interne(s)
	//////////////////////////////////////////////////
	
	private class Node<D> {
		public Node<D> prior;
		public Node<D> next;
		public D data;
	}
}
