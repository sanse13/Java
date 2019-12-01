package jx.d.looping.opts;

import java.util.Iterator;

/**
 * Iterables para recorrer la secuencia de enteros "v" ("from" <= "v" < "to",) que son
 * multiplos de "x".
 *
 * "from", "to", "x" se establecen en la constructora.
 */
public class MyIterable implements Iterable<Integer> {
	private int	x;
	private int	from;
	private int	to;

	public MyIterable(int x, int from, int to) {
		this.x = x;
		this.from = from;
		this.to = to;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new MyIterator(x, from, to);
	}
}
