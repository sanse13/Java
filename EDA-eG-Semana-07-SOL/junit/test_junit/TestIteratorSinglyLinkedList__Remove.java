package test_junit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import base.IntSinglyLinkedListNode;
import base.IntSinglyLinkedLists;
import iterators.a.list_iterator.MyIterator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestIteratorSinglyLinkedList__Remove {
	static {
		printInfo();
	}

	@Test
	public void b1_dos() {
		chk("b1_dos", new int[] { -1, -2, -3, -4 }, 2, 1);
	}

	@Test
	public void b2_dos() {
		chk("b2_dos", new int[] { -1, -2, -3, -4 }, 2, 2);
	}

	@Test
	public void c_tres() {
		chk("c_tres", new int[] { -1, -2, -3, -4, -5 }, 1, 4);
	}

	private void chk(String id, int[] inputData, int skip, int removes) {
		assert inputData.length > 1 && skip >= 1 && removes >= 0 && skip + removes <= inputData.length;

		System.out.println("[CASO DE PRUEBA@" + id + "] Lista: " + Arrays.toString(inputData));
		System.out.println(
				"Prueba: saltar[" + skip + " primeros]; iterator.remove() " + removes + " siguientes");
		System.out.println();

		IntSinglyLinkedListNode lst = IntSinglyLinkedLists.buildFrom(inputData);

		MyIterator iterator = new MyIterator(lst);

		for (int i = 0; i < inputData.length && i < skip; i++) {
			boolean b = iterator.hasNext();
			assertEquals("ERROR hasNext(item index: " + i + ")", true, b);

			Integer n = iterator.next();
			assertNotNull(n, "ERROR next(" + i + ")");
			assertEquals("ERROR next()", inputData[i], n.intValue());
		}

		for (int i = 0; i < removes; i++) {
			boolean b = iterator.hasNext();
			assertEquals("ERROR hasNext(item index: " + i + ")", true, b);

			Integer n = iterator.next();
			assertNotNull(n, "ERROR next(" + i + ")");
			assertEquals("ERROR next()", inputData[i + skip], n.intValue());

			System.out.println("[Test] iterator.remove()...");

			iterator.remove();

			int[] currentExpected = extract(inputData, skip, i + 1);
			int[] currentReal = IntSinglyLinkedLists.toArray(lst);
			System.out.println("[Test] Valores en lista (esperado): " + Arrays.toString(currentExpected));
			System.out.println("[Test] Valores en lista (real) " + Arrays.toString(currentReal));
			assertArrayEquals("[ERROR] Estado de lista", currentExpected, currentReal);
		}

		if (removes + skip == inputData.length) {
			System.out.println("[Test] remove throws NoSuchElementException ");
			assertThrows(IllegalStateException.class, () -> {
				iterator.remove();
			});
		}

		System.out.println("Completado!");
		System.out.println();
	}

	private int[] extract(int[] inputData, int skipped, int removed) {
		int[] tmp = new int[inputData.length - removed];
		System.arraycopy(inputData, 0, tmp, 0, skipped);
		int n = inputData.length - skipped - removed;
		System.arraycopy(inputData, skipped + removed, tmp, skipped, n);
		return tmp;
	}

	private static void printInfo() {
		System.out.println("[INFO] " + System.getProperty("user.home"));
		System.out.println("[INFO] " + System.getProperty("user.dir"));
		System.out.println("[INFO] " + LocalDateTime.now());
		System.out.println();
	}
}
