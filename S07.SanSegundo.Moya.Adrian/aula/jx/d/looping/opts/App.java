package jx.d.looping.opts;

import java.util.Iterator;
import java.util.function.IntConsumer;

/**
 * Cliente para mostrar el uso de las diferentes formas de recorrer la secuencia de
 * enteros "v" ("from" <= "v" < "to",) que son multiplos de "x".
 */
public class App {

	static final int X = 5;

	static final int	FROM	= 100;
	static final int	TO		= 200;

	public static void main(String[] args) {
		System.out.println("*** Loop procedural");
		Opts.loop(X, FROM, TO);

		System.out.println("*** MyIterator");
		MyIterator it = new MyIterator(X, FROM, TO);
		Opts.useIntIterator(it);

		System.out.println("*** Iterator<Integer>");
		Iterator<Integer> itObject = new MyIterator(X, FROM, TO);
		Opts.useIntObjectIterator(itObject);

		System.out.println("*** Iterable");
		MyIterable iterable = new MyIterable(X, FROM, TO);
		Opts.useIterable(iterable);

		System.out.println("*** Internal Iteration: MyConsumer");
		MyConsumer consumer = new MyConsumer();
		Opts.useMyConsumer(X, FROM, TO, consumer);

		System.out.println("*** Internal Iteration: lamda");
		IntConsumer lambda = (int x) -> {
			System.out.println(x);
		}; // lambda expression => evita tener que definir una clase como MyConsumer
		Opts.useMyLambda(X, FROM, TO, lambda);
	}
}
