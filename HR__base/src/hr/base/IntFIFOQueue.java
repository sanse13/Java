package hr.base;

/**
 * Una <it>first-in-first-out</it> cola de enteros:
 *
 * <ul>
 * <li>si la cola esta vacia: <code>head = tail = null</code>
 *
 * <li>si no, <code>head</code> es el <b>primer</b> nodo de la <it>singly-linked-list</it>
 * con los enteros de la cola y <code>tail</code> es el <b>ultimo</b>.
 * </ul>
 *
 * <pre>
 * public class IntFIFOQueue {
 * 	public IntSinglyLinkedListNode head;
 * 	public IntSinglyLinkedListNode tail;
 * }
 * </pre>
 */
public class IntFIFOQueue {
	public IntSinglyLinkedListNode	head;
	public IntSinglyLinkedListNode	tail;
}
