package hr.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class IntStacks {

	private IntStacks() {
	}

	public static void addToHead(IntStack q, int n) {
		IntSinglyLinkedListNode node = new IntSinglyLinkedListNode();
		node.data = n;

		if (q.head == null) {
			q.head = node;
		}
	}

	public static void println(IntStack inputData, String sep, BufferedWriter writer)
			throws IOException {
		IntSinglyLinkedLists.println(inputData.head, sep, writer);
	}

	public static IntStack read(BufferedReader bufferedReader) throws IOException {
		IntStack q = new IntStack();
		q.head = IntSinglyLinkedLists.read(bufferedReader);
		return q;
	}

	public static int rmvFirst(IntStack q) {
		int n = q.head.data;
		q.head = q.head.next;
		return n;
	}
}
