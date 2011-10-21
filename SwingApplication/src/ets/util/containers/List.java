package ets.util.containers;

import java.util.Comparator;

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

		l.pushBack("A"); System.out.println(l);
		l.pushBack("B"); System.out.println(l);
		l.pushBack("C"); System.out.println(l);
		l.pushBack("D"); System.out.println(l); 

		l.popBack(); System.out.println(l);
		l.popBack(); System.out.println(l);
		l.popBack(); System.out.println(l);
		l.popBack(); System.out.println(l);
		
		l.pushFront("A"); System.out.println(l);
		l.pushFront("B"); System.out.println(l);
		l.pushFront("C"); System.out.println(l);
		l.pushFront("D"); System.out.println(l);
		
		l.popFront(); System.out.println(l);
		l.popFront(); System.out.println(l);
		l.popFront(); System.out.println(l);
		l.popFront(); System.out.println(l);
	}

	// ////////////////////////////////////////////////
	// Constructeur(s)
	// ////////////////////////////////////////////////

	public List() {
	}

	// ////////////////////////////////////////////////
	// Accesseur(s)
	// ////////////////////////////////////////////////

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
	
	public void sort(Comparator<T> comp) {
        //Enter loop only if there are elements in list
        boolean swapped = (begin != null);

        // Only continue loop if a swap is made
        while (swapped)
        {
            swapped = false;

            // Maintain pointers
            Node<T> curr = begin;
            Node<T> next = curr.next;
            Node<T> prev = null;

            // Cannot swap last element with its next
            while (next != null)
            {
                // swap if items in wrong order
                if (comp.compare(curr.data, next.data) < 0)
                {
                    // notify loop to do one more pass
                    swapped = true;

                    // swap elements (swapping head in special case
                    if (curr == begin)
                    {
                        begin = next;
                        Node<T> temp = next.next;
                        next.next = curr;
                        curr.next = temp;
                        curr = begin;
                    }
                    else
                    {
                        prev.next = curr.next;
                        curr.next = next.next;
                        next.next = curr;
                        curr = next;
                    }
                }

                // move to next element
                prev = curr;
                curr = curr.next;
                next = curr.next;
            }
        }
    }

	// ////////////////////////////////////////////////
	// Mutateur(s)
	// ////////////////////////////////////////////////

	/**
	 * Insère un nouvel élément à la fin de la liste.
	 */
	public void pushBack(T newElement) {
		Node<T> newNode = new Node<T>();
		newNode.data = newElement;

		if (rbegin != null) {
			newNode.prior = rbegin;
			newNode.prior.next = newNode;
		} else {
			begin = newNode;
		}

		rbegin = newNode;

		++elementCount;
	}

	/**
	 * Supprime l'élément situé à la fin de la liste.
	 */
	public void popBack() {
		if (rbegin != null) {
			if (rbegin.prior != null) {
				rbegin.prior.next = null;
				rbegin = rbegin.prior;
			} else {
				begin = null;
				rbegin = null;
			}
			--elementCount;
		}
	}

	/**
	 * Insère un nouvel élément au début de la liste.
	 */
	public void pushFront(T newElement) {
		Node<T> newNode = new Node<T>();
		newNode.data = newElement;

		if (begin != null) {
			newNode.next = begin;
			begin.prior = newNode;
		} else {
			rbegin = newNode;
		}

		begin = newNode;
		++elementCount;
	}
	
	/**
	 * Supprime l'élément situé au début de la liste.
	 */
	public void popFront() {
		if (begin != null) {
			if(begin.next != null) {
				begin.next.prior = null;
				begin = begin.next;
			} else {
				begin = null;
				rbegin = null;
			}
			--elementCount;
		}
	}

	// ////////////////////////////////////////////////
	// Attribut(s)
	// ////////////////////////////////////////////////

	private Node<T> begin = null;
	private Node<T> rbegin = null;
	private int elementCount = 0;

	// ////////////////////////////////////////////////
	// Classe(s) interne(s)
	// ////////////////////////////////////////////////

	private class Node<D> {
		public Node<D> prior;
		public Node<D> next;
		public D data;
	}
}
