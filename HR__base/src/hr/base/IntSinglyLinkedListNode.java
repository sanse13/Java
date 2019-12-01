package hr.base;

/**
 * Nodos de una <it>singly-linked-list</it> de enteros.
 *
 * <pre>
 * class IntSinglyLinkedListNode {
 * 	public int data;
 * 	public IntSinglyLinkedListNode next;
 * }
 * </pre>
 */
public class IntSinglyLinkedListNode {
	public int						data;
	public IntSinglyLinkedListNode	next;

	public IntSinglyLinkedListNode() {
	}

	public IntSinglyLinkedListNode(int nodeData) {
		data = nodeData;
	}

	@Override
	public String toString() {
		return "IntSinglyLinkedListNode [" + data + "]";
	}
}