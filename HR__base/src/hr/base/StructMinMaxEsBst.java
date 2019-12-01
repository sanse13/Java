package hr.base;

public class StructMinMaxEsBst {
	public int		min;
	public int		max;
	public boolean	esBST;

	public StructMinMaxEsBst() {
	}

	public StructMinMaxEsBst(int min, int max, boolean esBST) {
		this.min = min;
		this.max = max;
		this.esBST = esBST;
	}

	@Override
	public String toString() {
		return "StructMinMaxEsBst [min=" + min + ", max=" + max + ", esBST=" + esBST + "]";
	}
}
