package hr.base;

/**
 * Las instancias de esta clase son arboles binarios que representan expresiones
 * aritmeticas.
 *
 * Las expresiones estan formadas por operadores y letras (operandos).
 */
public class ExpressionNode {
	static final int	INT_TYPE	= 0;
	static final int	OPR_TYPE	= 1;
	static final int	VAR_TYPE	= 2;

	public final int typeOf;

	final int value;

	final ExpressionNode	left;
	final ExpressionNode	right;

	private ExpressionNode(int typeOf, ExpressionNode left, int x, ExpressionNode right) {
		this.typeOf = typeOf;
		value = x;
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return "ExpressionNode [typeOf=" + typeOf + ", value=" + value + ", left=" + left
				+ ", right=" + right + "]";
	}

	public static boolean isAdditive(char opr) {
		return opr == '+' || opr == '-';
	}

	public static boolean isMultiplicative(char opr) {
		return opr == '*' || opr == '/';
	}

	public static boolean isOperator(char opr) {
		return isAdditive(opr) || isMultiplicative(opr);
	}

	static String label(ExpressionNode node) {
		if (node.typeOf == ExpressionNode.INT_TYPE)
			return String.valueOf(node.value);
		else
			return String.valueOf((char) node.value);
	}

	static ExpressionNode ofInt(int x) {
		return new ExpressionNode(INT_TYPE, null, x, null);
	}

	static ExpressionNode ofLetter(char x) {
		if (!Character.isLetter(x))
			throw new IllegalArgumentException("Not letter char: " + x);
		return new ExpressionNode(VAR_TYPE, null, x, null);
	}

	static ExpressionNode ofOperator(char x, ExpressionNode right) {
		if (!isOperator(x))
			throw new IllegalArgumentException("Bad operator char: " + x);
		return new ExpressionNode(OPR_TYPE, null, x, right);
	}

	static ExpressionNode ofOperator(ExpressionNode left, char x, ExpressionNode right) {
		if (!isOperator(x))
			throw new IllegalArgumentException("Bad operator char: " + x);
		return new ExpressionNode(OPR_TYPE, left, x, right);
	}
}