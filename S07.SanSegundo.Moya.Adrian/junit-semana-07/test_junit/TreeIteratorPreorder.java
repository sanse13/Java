package test_junit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import hr.base.IntBinaryTreeNode;
import hr.base.IntBinaryTrees;
import iterators.b.preorder.MyTreeIterator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TreeIteratorPreorder {
	static {
		printInfo();
	}

	@Test
	public void a_uno() throws Exception {
		chkCase(new int[] { 1 }, new int[] { 1 });
	}

	@Test
	public void b1_dos() throws Exception {
		chkCase(new int[] { 1, -1, 3 }, new int[] { 1, 3 });
	}

	@Test
	public void b2_dos() throws Exception {
		chkCase(new int[] { 1, 2 }, new int[] { 1, 2 });
	}

	@Test
	public void c_tres() throws Exception {
		chkCase(new int[] { 1, 2, 3, -1, -1, 6 }, new int[] { 1, 2, 3, 6 });
	}

	void chkCase(int[] binArray, int[] expectedPreorder) throws Exception {
		IntBinaryTreeNode root = IntBinaryTrees.toTree(binArray);
		System.out.println("[CASO DE PRUEBA] Arbol en preorden: ");
		IntBinaryTrees.showPreOrder(root, "    ");

		MyTreeIterator iterator = new MyTreeIterator(root);

		chkHasNext(expectedPreorder.length != 0, iterator.hasNext(), "@Iterador nuevo");

		Iterable<IntBinaryTreeNode> iterable = () -> iterator;
		Stream<IntBinaryTreeNode> stream = StreamSupport.stream(iterable.spliterator(), false);
		int[] actual = stream.mapToInt(node -> node.label).toArray();

		chkHasNext(expectedPreorder.length == 0, iterator.hasNext(), "@Iterador consumido");

		System.out.println("[Test] Valores en preorden(esperado): " + Arrays.toString(expectedPreorder));
		System.out.println("[Test] Valores en preorden(result) " + Arrays.toString(actual));
		assertArrayEquals("[ERROR]  Valores en preorden", expectedPreorder, actual);

		System.out.println("[Test] next throws NoSuchElementException ");
		assertThrows(NoSuchElementException.class, () -> {
			iterator.next();
		});

		System.out.println("Completado!");
		System.out.println();
	}

	void chkHasNext(boolean expected, boolean actual, String msg) {
		System.out.println("[Test] hasNext[" + msg + "] esperado: " + expected + "; actual: " + actual);
		assertEquals("ERROR hasNext() " + msg, expected, actual);
	}

	private static void printInfo() {
		System.out.println("[INFO] user.home: " + System.getProperty("user.home"));
		System.out.println("[INFO] user.dir: " + System.getProperty("user.dir"));
		System.out.println("[INFO] " + LocalDateTime.now());
		System.out.println();
	}
}
