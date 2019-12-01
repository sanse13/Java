package c.pixelated_image;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import util.IntArrays;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJUnit__TODO {
	static {
		printInfo();
	}

	@Test
	public void enunciado() {
		int[][] img = { { 1, 1, 1, 0, 0, 1, 0 }, { 0, 1, 0, 0, 1, 1, 0 }, { 1, 0, 0, 0, 0, 0, 0 },
				{ 1, 1, 1, 0, 1, 0, 0 }, { 0, 0, 1, 1, 1, 0, 1 } };
		int[] areas = { 6, 4, 15, 1 };
		chk("enunciado", img, areas);
	}

	void chk(String id, int[][] img, int[] expected) {
		System.out.println("[CASO DE PRUEBA@" + id + "]");
		IntArrays.show("Imagen: ", img);

		System.out.println("Areas esperadas: ");
		IntArrays.show("Areas", expected);

		BFS bfs = new BFS(img);
		int real = bfs.call();
		assertEquals(expected.length, real);

		int[] areas = bfs.getAreas();
		System.out.println("[Test] Areas de objetos esperadas: ");
		IntArrays.show("Imagen: ", expected);
		IntArrays.show("Imagen: ", areas);

		assertArrayEquals(expected, areas);

		System.out.println("Completado!");
		System.out.println();
	}

	private static void printInfo() {
		System.out.println("[INFO] " + System.getProperty("user.home"));
		System.out.println("[INFO] " + System.getProperty("user.dir"));
		System.out.println("[INFO] " + LocalDateTime.now());
		System.out.println();
	}
}
