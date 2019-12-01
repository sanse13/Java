package jx.b.Lists_Singly_Linked;

public class IntSinglyLinkedFIFOQueue {
	private IntSinglyLinkedListNode	head;
	private IntSinglyLinkedListNode	tail;

	/**
	 * Anyade el entero x a la cola q (al final de la cola).
	 */
	public void addTail(int x) {
		IntSinglyLinkedListNode oldTail = tail;
		IntSinglyLinkedListNode n = new IntSinglyLinkedListNode();
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
	public int getHead() {
		assert head != null;
		return head.data;
	}

	public IntSinglyLinkedFIFOQueue init() {
		return new IntSinglyLinkedFIFOQueue();
	}

	/**
	 * Retira y devuelve el primer elemento de la cola.
	 */
	public int rmvHead() {
		assert head != null;
		int rs = head.data;
		head = head.next;
		if (head == null) {
			tail = head;
		}
		return rs;
	}

	static private class IntSinglyLinkedListNode {
		int						data;
		IntSinglyLinkedListNode	next;
	}
}
