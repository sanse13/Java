package hr.base;

public class StructMinMax {
	public int	min;
	public int	max;

	public StructMinMax() {
	}

	public StructMinMax(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public String toString() {
		return "StructMinMax [min=" + min + ", max=" + max + "]";
	}
}
