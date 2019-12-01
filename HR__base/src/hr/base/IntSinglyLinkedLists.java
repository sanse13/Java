package hr.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class IntSinglyLinkedLists {
	private static final int DEFAULT_PRINT_TRUNCATED_AT = 30;

	private IntSinglyLinkedLists() {
	}

	public static void println(IntSinglyLinkedListNode node, String sep,
			BufferedWriter bufferedWriter) throws IOException {
		while (node != null) {
			bufferedWriter.write(String.valueOf(node.data));

			node = node.next;
			if (node != null) {
				bufferedWriter.write(sep);
			}
		}
		bufferedWriter.newLine();
	}

	public static IntSinglyLinkedListNode read(BufferedReader bufferedReader)
			throws IOException {
		IntFIFOQueue q = IntFIFOQueues.read(bufferedReader);
		return q.head;
	}

	public static void show(String infoMsg, IntSinglyLinkedListNode node) {
		show(infoMsg, node, " ", DEFAULT_PRINT_TRUNCATED_AT);
	}

	public static void show(String infoMsg, IntSinglyLinkedListNode node, String sep) {
		show(infoMsg, node, sep, DEFAULT_PRINT_TRUNCATED_AT);
	}

	public static void show(String infoMsg, IntSinglyLinkedListNode node, String sep,
			int truncAt) {
		printInfoMsg(infoMsg);
		System.out.print("[");
		int n = 0;
		int printed = 0;
		while (node != null) {
			if (n < truncAt) {
				System.out.print(node.data);
				printed++;
			}

			node = node.next;
			if (node != null && n < truncAt) {
				System.out.print(sep);
			}
			n++;
		}
		if (n >= truncAt) {
			System.out.print(sep);
			System.out.println("..." + printed + "; total: " + n);
		}
		System.out.println("]");
	}

	private static void printInfoMsg(String infoMsg) {
		if (infoMsg != null) {
			System.out.print(infoMsg);
		}
	}

}
