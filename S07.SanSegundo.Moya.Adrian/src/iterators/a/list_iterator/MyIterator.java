package iterators.a.list_iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import hr.base.IntSinglyLinkedListNode;

/**
 * Iteradores para recorrer los elementos de una "Singly-Linked-List" de enteros.
 * 
 * La lista a recorrer se fija en la constructora.
 *
 * @Observacion un iterador tiene una implementacion para eliminar de la lista
 *              recorrida el ultimo elemento devuelto por "next". El ejercicio
 *              consiste en implementar esa operacion.
 *
 * @Observacion para hacer eso, parece aconsejable que el iterador mantenga
 *              tambien la referencia del nodo ANTERIOR al ultimo devuelto.
 *
 * @Observacion el contrato que se incluye abajo para la operacion a implementar
 *              (remove) esta tomado de la documentacion de la libreria
 *              estandar.
 *
 * @Observacion el contrato establece que SOLO puede llamars a "remove" UNA vez
 *              por cada llamada completada a "next". Por tanto, parece
 *              aconsejable que el iterador mantenga tambien la referencia del
 *              ANTERIOR al devuelto.
 *
 */

public class MyIterator implements Iterator<Integer> {
	IntSinglyLinkedListNode lastReturned;
	IntSinglyLinkedListNode current;
	IntSinglyLinkedListNode head;

	/*
	 * COMPLETAR
	 */
	
	/**
	 * Constructora
	 * 
	 * @param lst
	 *            la lista a recorrer por el iterador construido.
	 */
	public MyIterator(IntSinglyLinkedListNode lst) {
		current = lst;
		head = lst;
		/*
		 * COMPLETAR
		 */
		lastReturned = null;
		
	}

	@Override
	public boolean hasNext() {
		/*
		 * COMPLETAR
		 */
		return current != null;
	}

	@Override
	public Integer next() {
		int res;
		if (current == null) {
			throw new NoSuchElementException();
		} else {
		/*
		 * COMPLETAR
		 */
		res = current.data;
		lastReturned = current;
		current = current.next;
		return res;
		}
	}

	/**
	 * Removes from the underlying collection the last element returned by this
	 * iterator (optional operation). This method can be called only once per call
	 * to {@link #nextCell}. The behavior of an iterator is unspecified if the
	 * underlying collection is modified while the iteration is in progress in any
	 * way other than by calling this method.
	 *
	 * @throws IllegalStateException
	 *             if the {@code next} method has not yet been called, or the
	 *             {@code remove} method has already been called after the last call
	 *             to the {@code next} method
	 */
	@Override
	public void remove() {
		if (lastReturned == null) {
			throw new IllegalStateException();
		}
		/*
		 * COMPLETAR
		 */
		IntSinglyLinkedListNode aux = head;
		while (aux.next != lastReturned)
			aux = aux.next;
		aux.next = current;
		lastReturned = null;
	}
}