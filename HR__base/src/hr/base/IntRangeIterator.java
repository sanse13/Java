package hr.base;

import java.util.NoSuchElementException;

/**
 * Iteradores para recorrer un rango de enteros.
 */
public class IntRangeIterator implements IntIterator {
	private final int from;
	private final int to;

	private int current;

	public IntRangeIterator(int to) {
		this(0, to);
	}

	public IntRangeIterator(int from, int to) {
		this.from = from;
		this.to = to;
		current = from;
	}

	@Override
	public boolean hasNext() {
		return current < to;
	}

	@Override
	public int nextInt() {
		if (!hasNext())
			throw new NoSuchElementException(toString());
		int tmp = current;
		current = current + 1;
		return tmp;
	}

	@Override
	public String toString() {
		return "IntRangeIterator [from=" + from + ", to=" + to + ", current="
				+ current + "]";
	}

	/**
	 * Ejemplo de uso.
	 */
	public static void main(String[] args) {
		IntIterator itr = new IntRangeIterator(0, 1);
		for (Integer i : itr) {
			System.out.print(i);
		}
		/*
		 * el iterador ha consumido sus elementos
		 */
		itr.nextInt(); // => NoSuchElementException!
	}
}
