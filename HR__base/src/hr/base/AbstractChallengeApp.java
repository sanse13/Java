package hr.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class AbstractChallengeApp {

	private String			testName;
	private BufferedReader	reader;
	private BufferedWriter	writer;

	@SuppressWarnings("hiding")
	public void config(String testName, BufferedReader reader, BufferedWriter writer) {
		this.testName = testName;
		this.reader = reader;
		this.writer = writer;
	}

	public void printResult() throws Exception {
	}

	public void readArgs() throws Exception {
		throw new UnsupportedOperationException();
	}

	public void solve() throws Exception {
		throw new UnsupportedOperationException();
	}

	/**
	 * @return the bufferedReader
	 */
	protected BufferedReader getReader() {
		return reader;
	}

	/**
	 * @return the testName
	 */
	protected String getTestName() {
		return testName;
	}

	/**
	 * @return the bufferedWriter
	 */
	protected BufferedWriter getWriter() {
		return writer;
	}

	protected void println(boolean b) throws IOException {
		BufferedWriters.println(writer, b);
	}

	protected void println(int x) throws IOException {
		BufferedWriters.println(writer, x);
	}

	protected void println(int[] array) throws IOException {
		BufferedWriters.println(writer, array);
	}

	protected void println(IntSinglyLinkedListNode a) throws IOException {
		BufferedWriters.println(writer, a);
	}

	protected void println(String s) throws IOException {
		BufferedWriters.println(writer, s);
	}

	protected void printPreOrder(IntBinaryTreeNode a) throws IOException {
		BufferedWriters.printPreOrder(writer, a);
	}

	protected boolean readBoolean() throws IOException {
		return ReadPrimitiveTypes.readBoolean(reader);
	}

	protected int readInt() throws IOException {
		return ReadPrimitiveTypes.readInt(reader);
	}
}
