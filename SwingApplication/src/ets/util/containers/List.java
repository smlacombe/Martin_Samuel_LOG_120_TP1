package ets.util.containers;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Conteneur implémentant une liste doublement chainée.
 * 
 * @author Martin Desharnais
 * @author Samuel Milette-Lacombe
 */
public class List<T> implements Iterable<T> {

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
		String result = "";
		Node<T> current = begin;

		while (current != null) {
			result += current.data + " ";
			current = current.next;
		}

		return result;
	}

	@Override
	public Iterator<T> iterator() {
		return new ListIterator<T>(begin);
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
	
	/**
	 * Supprime tous les éléments de la liste.
	 */
	public void clear() {
		Node<T> current = begin;

		while (current != null) {
			if (current.prior != null) {
				current.prior.next = null;
				current.prior = null;
			}
			current = current.next;
		}
		
		begin = null;
		rbegin = null;
		elementCount = 0;
	}
	
	/**
	 * Tri la liste doublement chainée selon le critère du foncteur en paramètre.
	 * 
	 * @param comp Foncteur comp qui définit l'ordre de tri.
		CODE EMPRUNTÉ :
		Cette fonction de tri est tiré du site stackoverflow.com.
		Description: Cette classe permet le tri d'une liste doublement chainée.
		Auteur à l'origine du code: Carl Smotricz
		Référence:http://stackoverflow.com/questions/1854870/manually-sorting-a-linked-list-in-java-lexically
	 */
	public void sort(Comparator<T> comp) {
        //Enter loop only if there are elements in list
        boolean swapped = (begin != null);

        // Only continue loop if a swap is made
        while (swapped) {
            swapped = false;

            // Maintain pointers
            Node<T> curr = begin;
            Node<T> next = curr.next;
            Node<T> prev = null;

            // Cannot swap last element with its next
            while (next != null) {
                // swap if items in wrong order
                if (comp.compare(curr.data, next.data) > 0) {
                    // notify loop to do one more pass
                    swapped = true;

                    // swap elements (swapping head in special case
                    if (curr == begin) {
                        begin = next;
                        Node<T> temp = next.next;
                        next.next = curr;
                        curr.next = temp;
                        curr = begin;
                    }
                    else {
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
	// Attribut(s)
	// ////////////////////////////////////////////////

	private Node<T> begin = null;
	private Node<T> rbegin = null;
	private int elementCount = 0;

	// ////////////////////////////////////////////////
	// Classe(s) interne(s)
	// ////////////////////////////////////////////////

	/**
	 * Structure représentant un maillon de la liste chainée.
	 */
	private class Node<D> {
		public Node<D> prior;
		public Node<D> next;
		public D data;
	}
	
	/**
	 * Itérateur permettant de parcourir la liste.
	 */
	private class ListIterator<E> implements Iterator<E> {
		public ListIterator(Node<E> node) {
			next = node;
		}
		
		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public E next() {
			if (next == null)
				throw new NoSuchElementException();
			E data = next.data;
			next = next.next;
			return data;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		Node<E> next;
	}
}
