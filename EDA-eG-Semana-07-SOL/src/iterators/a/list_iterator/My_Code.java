package iterators.a.list_iterator;

import base.IntSinglyLinkedListNode;

class My_Code {

	/**
	 * Devuelve un iterador para recorrer una "singly-linked-list" de enteros.
	 *
	 * @param lst
	 *            la lista que recorrera el iterador devuelto (lst puede ser null)
	 *
	 * @return un iterador para recorrer una "singly-linked-list" de enteros.
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
	public static MyIterator solve(IntSinglyLinkedListNode lst) {
		MyIterator iterator = new MyIterator(lst);
		return iterator;
	}
}
