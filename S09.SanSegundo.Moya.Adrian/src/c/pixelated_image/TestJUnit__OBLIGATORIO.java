package c.pixelated_image;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import util.IntArrays;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJUnit__OBLIGATORIO {
	static {
		printInfo();
	}

	@Test
	public void enunciado() {
		int[][] img = { { 1, 1, 1, 0, 0, 1, 0 }, { 0, 1, 0, 0, 1, 1, 0 }, { 1, 0, 0, 0, 0, 0, 0 },
				{ 1, 1, 1, 0, 1, 0, 0 }, { 0, 0, 1, 1, 1, 0, 1 } };
		chk("enunciado", img, 4);
	}

	void chk(String id, int[][] img, int expected) {
		System.out.println("[CASO DE PRUEBA@" + id + "]");
		IntArrays.show("Imagen: ", img);

		System.out.println("[Test] Numero de objetos esperado en imagen: " + expected);
		BFS bfs = new BFS(img);
		int real = bfs.call();

		assertEquals(expected, real);
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
