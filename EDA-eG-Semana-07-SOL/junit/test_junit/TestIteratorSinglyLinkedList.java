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

import base.IntSinglyLinkedListNode;
import base.IntSinglyLinkedLists;
import iterators.a.list_iterator.MyIterator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestIteratorSinglyLinkedList {
	static {
		printInfo();
	}

	@Test
	public void a_uno() {
		chk("a_uno", new int[] { 1 }, new int[] { 1 });
	}

	@Test
	public void b1_dos() {
		chk("b1_dos", new int[] { 1, -1, 3 }, new int[] { 1, 3 });
	}

	@Test
	public void b2_dos() {
		chk("b2_dos", new int[] { 1, 2 }, new int[] { 1, 2 });
	}

	@Test
	public void c_tres() {
		chk("c_tres", new int[] { 1, 2, 3, -1, -1, 6 }, new int[] { 1, 2, 3, 6 });
	}

	void chk(String id, int[] inputData, int[] expected) {
		System.out.println("[CASO DE PRUEBA@" + id + "] Lista: " + Arrays.toString(inputData));
		System.out.println("Prueba: while (iterator.hasNext(){iterator.next();}");
		System.out.println();

		IntSinglyLinkedListNode lst = IntSinglyLinkedLists.buildFrom(inputData);

		MyIterator iterator = new MyIterator(lst);

		chkHasNext(inputData.length != 0, iterator.hasNext(), "@Iterador nuevo");

		int[] real = toArray(() -> iterator);

		chkHasNext(inputData.length == 0, iterator.hasNext(), "@Iterador consumido");

		System.out.println("[Test] Valores del iterador (esperado): " + Arrays.toString(inputData));
		System.out.println("[Test] Valores del iterador (real) " + Arrays.toString(real));
		assertArrayEquals("[ERROR]  Valores del iterador", inputData, real);

		System.out.println("[Test] next throws NoSuchElementException ");
		assertThrows(NoSuchElementException.class, () -> {
			iterator.next();
		});

		System.out.println("Completado!");
		System.out.println();
	}

	void chkHasNext(boolean expected, boolean real, String msg) {
		System.out.println("[Test] hasNext[" + msg + "] esperado: " + expected + "; real: " + real);
		assertEquals("ERROR hasNext() " + msg, expected, real);
	}

	private static void printInfo() {
		System.out.println("[INFO] " + System.getProperty("user.home"));
		System.out.println("[INFO] " + System.getProperty("user.dir"));
		System.out.println("[INFO] " + LocalDateTime.now());
		System.out.println();
	}

	private static int[] toArray(Iterable<Integer> iterable) {
		Stream<Integer> stream = StreamSupport.stream(iterable.spliterator(), false);
		return stream.mapToInt(x -> x.intValue()).toArray();
	}
}
