package jx.d.looping.opts;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iteradores para recorrer la secuencia de enteros "v" ("from" <= "v" < "to",) que son
 * multiplos de "x".
 *
 * "from", "to", "x" se establecen en la constructora.
 */
public class MyIterator implements Iterator<Integer> {
	private boolean	hasNext;
	private int		next;
	private int		x;
	private int		to;

	public MyIterator(int x, int from, int to) {
		next = from;
		this.x = x;
		this.to = to;

		update();
	}

	@Override
	public boolean hasNext() {
		return hasNext;
	}

	@Override
	public Integer next() {
		return nextInt();
	}

	public int nextInt() {
		if (!hasNext) { throw new NoSuchElementException(); }
		int tmp = next;
		update();
		return tmp;
	}

	private void update() {
		while (next < to && next % x != 0) {
			next = next + 1;
		}
		hasNext = next < to;
	}

	public static void main(String[] args) {

	}
}
