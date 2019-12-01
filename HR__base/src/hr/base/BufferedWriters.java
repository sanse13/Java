package hr.base;

import java.io.BufferedWriter;
import java.io.IOException;

public class BufferedWriters {

	public static void print(BufferedWriter writer, boolean b) throws IOException {
		writer.write(String.valueOf(b));
	}

	public static void print(BufferedWriter writer, char ch) throws IOException {
		writer.write(ch);
	}

	public static void print(BufferedWriter writer, int x) throws IOException {
		writer.write(String.valueOf(x));
	}

	public static void print(BufferedWriter writer, Object o) throws IOException {
		writer.write(o.toString());
	}

	public static void print(BufferedWriter writer, String s) throws IOException {
		writer.write(s);
	}

	public static void println(BufferedWriter writer) throws IOException {
		writer.newLine();
	}

	public static void println(BufferedWriter writer, boolean x) throws IOException {
		writer.write(String.valueOf(x));
		writer.newLine();
	}

	public static void println(BufferedWriter writer, char ch) throws IOException {
		writer.write(ch);
		writer.newLine();
	}

	public static void println(BufferedWriter writer, int x) throws IOException {
		writer.write(String.valueOf(x));
		writer.newLine();
	}

	public static void println(BufferedWriter writer, int[] array) throws IOException {
		IntArrays.println(array, " ", writer);
	}

	public static void println(BufferedWriter writer, IntSinglyLinkedListNode head)
			throws IOException {
		IntSinglyLinkedLists.println(head, " ", writer);
	}

	public static void println(BufferedWriter writer, Object o) throws IOException {
		writer.write(o.toString());
		writer.newLine();
	}

	public static void println(BufferedWriter writer, String s) throws IOException {
		writer.write(s);
		writer.newLine();
	}

	public static void printPreOrder(BufferedWriter writer, IntBinaryTreeNode root)
			throws IOException {
		IntBinaryTrees.printPreOrder(root, " ", writer);
	}

}
