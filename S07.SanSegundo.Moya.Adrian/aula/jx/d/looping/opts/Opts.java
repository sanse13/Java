package jx.d.looping.opts;

import java.util.Iterator;
import java.util.function.IntConsumer;

/**
 * Diferentes formas de recorrer la secuencia de enteros "v" ("from" <= "v" < "to",) que
 * son multiplos de "x".
 */
public class Opts {

	public static void loop(int x, int from, int to) {
		int index = from;
		while (index < to) {
			if (index % x == 0) {
				System.out.println(index);
			}
			index = index + 1;
		}
	}

	public static void useIntIterator(MyIterator it) {
		while (it.hasNext()) {
			int v = it.nextInt();

			System.out.println(v);
		}
	}

	public static void useIntObjectIterator(Iterator<Integer> it) {
		while (it.hasNext()) {
			Integer obj = it.next(); // Objeto!!!
			int v = obj.intValue();

			System.out.println(v);
		}
	}

	public static void useIterable(Iterable<Integer> iterable) {
		for (Integer x : iterable) {
			System.out.println(x);
		}
	}

	public static void useMyConsumer(int x, int from, int to, MyConsumer object) {
		int index = from;
		while (index < to) {
			if (index % x == 0) {
				object.accept(index);
			}
			index = index + 1;
		}
	}

	public static void useMyLambda(int x, int from, int to, IntConsumer object) {
		int index = from;
		while (index < to) {
			if (index % x == 0) {
				object.accept(index);
			}
			index = index + 1;
		}
	}
}
