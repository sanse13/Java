package jx.d.looping.opts;

import java.util.function.IntConsumer;

/**
 * Objetos capaces de consumir numeros enteros.
 */
public class MyConsumer implements IntConsumer {

	@Override
	public void accept(int x) {
		System.out.println(x);
	}
}
