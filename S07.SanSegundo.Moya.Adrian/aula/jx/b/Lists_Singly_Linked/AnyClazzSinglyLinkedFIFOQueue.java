package jx.b.Lists_Singly_Linked;

public class AnyClazzSinglyLinkedFIFOQueue<T> { // T: Any Reference Type
	private SinglyLinkedListNode<T>	head;
	private SinglyLinkedListNode<T>	tail;

	/**
	 * Anyade el entero x a la cola q (al final de la cola).
	 */
	public void addTail(T x) {
		SinglyLinkedListNode<T> oldTail = tail;
		SinglyLinkedListNode<T> n = new SinglyLinkedListNode<>();
		n.data = x;
		tail = n;
		if (oldTail == null) {
			head = tail;
		} else {
			oldTail.next = n;
		}
	}

	/**
	 * Devuelve el primer elemento de la cola.
	 */
	public T getHead() {
		assert head != null;
		return head.data;
	}

	/**
	 * Retira y devuelve el primer elemento de la cola.
	 */
	public T rmvHead() {
		assert head != null;
		T rs = head.data;
		head = head.next;
		if (head == null) {
			tail = head;
		}
		return rs;
	}

	static private class SinglyLinkedListNode<T> {
		T						data;
		SinglyLinkedListNode<T>	next;
	}
}
