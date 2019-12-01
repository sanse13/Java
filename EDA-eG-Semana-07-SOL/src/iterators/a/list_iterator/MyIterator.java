package iterators.a.list_iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import base.IntSinglyLinkedListNode;

/**
 * Iteradores para recorrer los elementos de una "Singly-Linked-List".
 *
 * La lista a recorrer se fija en la constructora.
 */
public class MyIterator implements Iterator<Integer> {
	/*
	 * Una implementacion simple.
	 *
	 * El iterador mantiene la referencia del nodo cuyo valor devolvera en la proxima llamada a next.
	 *
	 * Y tambien las referencias del ultimo devuelto y el anteultimo para poder eliminar el ultimo
	 * devuelto.
	 *
	 * Esas dos referencias deben mantenerse actualizadas en todo momento: incluso cuando el ultimo
	 * devuelto ha sido retirado de a lista.
	 */
	IntSinglyLinkedListNode current;

	boolean					lastReturnedAvailable;
	IntSinglyLinkedListNode	currentPrev;
	IntSinglyLinkedListNode	currentPrevPrev;

	/**
	 * Constructora
	 *
	 * @param lst
	 *            la lista a recorrer por el iterador construido.
	 */
	public MyIterator(IntSinglyLinkedListNode lst) {
		current = lst;
	}

	@Override
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public Integer next() {
		if (current == null) { throw new NoSuchElementException(); }
		Integer old = current.data;
		currentPrevPrev = currentPrev;
		currentPrev = current;
		lastReturnedAvailable = true;

		current = current.next;
		return old;
	}

	/**
	 * Removes from the underlying collection the last element returned by this iterator
	 * (optional operation). This method can be called only once per call to
	 * {@link #nextCell}. The behavior of an iterator is unspecified if the underlying
	 * collection is modified while the iteration is in progress in any way other than by
	 * calling this method.
	 *
	 * @throws IllegalStateException
	 *             if the {@code next} method has not yet been called, or the
	 *             {@code remove} method has already been called after the last call to
	 *             the {@code next} method
	 */
	@Override
	public void remove() {
		if (!lastReturnedAvailable) { throw new IllegalStateException(); }
		currentPrevPrev.next = current;
		currentPrev = currentPrevPrev;
		lastReturnedAvailable = false;
	}
}