package jx.a.Lists_Singly_Linked.procedural;

public class IntSinglyLinkedFIFOQueue {
	private IntSinglyLinkedListNode head;
	private IntSinglyLinkedListNode tail;

	/**
	 * Anyade el entero x a la cola q (al final de la cola).
	 */
	public static void addTail(IntSinglyLinkedFIFOQueue q, int x) {
		IntSinglyLinkedListNode oldTail = q.tail;
		IntSinglyLinkedListNode n = new IntSinglyLinkedListNode();
		n.data = x;
		q.tail = n;
		if (oldTail == null) {
			q.head = q.tail;
		} else {
			oldTail.next = n;
		}
	}

	/**
	 * Devuelve el primer elemento de la cola.
	 */
	public static int getHead(IntSinglyLinkedFIFOQueue q) {
		assert q.head != null;
		return q.head.data;
	}

	public static IntSinglyLinkedFIFOQueue init() {
		return new IntSinglyLinkedFIFOQueue();
	}

	/**
	 * Retira y devuelve el primer elemento de la cola.
	 */
	public static int rmvHead(IntSinglyLinkedFIFOQueue q) {
		assert q.head != null;
		int rs = q.head.data;
		q.head = q.head.next;
		if (q.head == null) {
			q.tail = q.head;
		}
		return rs;
	}

	static private class IntSinglyLinkedListNode {
		int data;
		IntSinglyLinkedListNode next;
	}
}
