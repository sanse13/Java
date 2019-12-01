package hr.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class IntFIFOQueues {

	private IntFIFOQueues() {
	}

	public static void addToEnd(IntFIFOQueue q, int n) {
		IntSinglyLinkedListNode node = new IntSinglyLinkedListNode();
		node.data = n;

		if (q.head == null) {
			q.head = node;
		} else {
			q.tail.next = node;
		}

		q.tail = node;
	}

	public static void print(IntFIFOQueue inputData, String sep, BufferedWriter bufferedWriter) throws IOException {
		boolean inv = inputData.head != null && inputData.tail != null
				|| inputData.head == null && inputData.tail == null;
		if (!inv) {
			String s = "*** Error: cola en estado incorrecto [tail = head = null ||...!!!]";
			throw new RuntimeException(s);
		}

		IntSinglyLinkedListNode current = inputData.head;
		while (current != inputData.tail) {
			bufferedWriter.write(String.valueOf(current.data));
			bufferedWriter.write(sep);

			current = current.next;
		}
		if (inputData.tail != null) {
			bufferedWriter.write(String.valueOf(inputData.tail.data));
		}
		bufferedWriter.newLine();
	}

	public static IntFIFOQueue read(BufferedReader bufferedReader) throws IOException {
		IntFIFOQueue q = new IntFIFOQueue();
		int n = ReadPrimitiveTypes.readInt(bufferedReader);
		for (int i = 0; i < n; i++) {
			int v = ReadPrimitiveTypes.readInt(bufferedReader);
			IntFIFOQueues.addToEnd(q, v);
		}
		return q;
	}

	public static int rmvFirst(IntFIFOQueue q) {
		int n = q.head.data;
		q.head = q.head.next;
		if (q.head == null) {
			q.tail = null;
		}
		return n;
	}
}
