package hr.base;

import java.io.BufferedWriter;
import java.io.IOException;

public class Expressions {

	private Expressions() {
	}

	public static ExpressionNode parse(String s) {
		ExpressionParser parser = new ExpressionParser();
		ExpressionNode exp = parser.parse(s);
		return exp;
	}

	public static void print(ExpressionNode root, String sep,
			BufferedWriter bufferedWriter) throws IOException {
		print(root, sep, "", bufferedWriter);
	}

	private static void print(ExpressionNode root, String sep, String mark,
			BufferedWriter bufferedWriter) throws IOException {
		if (root == null) { return; }
		bufferedWriter.write(sep);
		bufferedWriter.write(mark);
		bufferedWriter.write(String.valueOf(ExpressionNode.label(root)));
		bufferedWriter.newLine();
		print(root.left, sep + sep, "[L] ", bufferedWriter);
		print(root.right, sep + sep, "[R] ", bufferedWriter);
	}

}

/**
 * Una clase para construir el arbol correspondiente a una expresion representada por un
 * texto en notacion infija.
 *
 * Los simbolos de la expresion deben estar separados por al menos un espacio.
 */
class ExpressionParser {
	private String source;

	private LexSymbol	lexSymbol;
	private int			currentChar;

	public ExpressionNode parse(String s) {
		// System.out.println("Parsing @" + s + "@");
		String tmp = s.trim();
		if (tmp.length() == 0) {
			String msg = "Illegal text: epmty or white spaces";
			throw new RuntimeException(msg);
		}
		source = tmp;
		currentChar = 0;
		nextSymbol();
		return parseExp();
	}

	private void nextSymbol() {
		lexSymbol = null;
		while (currentChar < source.length()
				&& Character.isWhitespace(source.charAt(currentChar))) {
			currentChar++;
		}
		if (currentChar < source.length()) {
			char ch = source.charAt(currentChar);
			if (Character.isDigit(ch)) {
				int value = 0;
				while (currentChar < source.length()
						&& Character.isDigit(ch = source.charAt(currentChar))) {
					currentChar++;
					int dgt = Integer.valueOf(String.valueOf(ch));
					value = value * 10 + dgt;
				}
				lexSymbol = LexSymbol.ofInt(value);
			} else {
				if (LexSymbol.isReservedLetter(ch)) {
					lexSymbol = LexSymbol.ofOperator(ch);
				} else if (Character.isLetter(ch)) {
					lexSymbol = LexSymbol.ofLetter(ch);
				} else {
					String s = "Illegal character at char position " + currentChar
							+ ". Parsed #[" + source.substring(0, currentChar) + "]#";
					throw new RuntimeException(s);
				}
				currentChar++;
			}
		}
	}

	private ExpressionNode parseExp() {
		ExpressionNode resultExp = parseMultiplicativeExp();
		while (lexSymbol != null && lexSymbol.isAdditive()) {
			LexSymbol symb = lexSymbol;
			nextSymbol();
			ExpressionNode e = parseMultiplicativeExp();
			resultExp = ExpressionNode.ofOperator(resultExp, (char) symb.value, e);
		}
		return resultExp;
	}

	private ExpressionNode parseMultiplicativeExp() {
		ExpressionNode resultExp = parseUnaryExp();
		while (lexSymbol != null && lexSymbol.isMultiplicative()) {
			LexSymbol symb = lexSymbol;
			nextSymbol();
			ExpressionNode e = parseUnaryExp();
			resultExp = ExpressionNode.ofOperator(resultExp, (char) symb.value, e);
		}
		return resultExp;
	}

	private ExpressionNode parsePrimaryExp() {
		int lastChar = currentChar - 1;
		if (lexSymbol == null) {
			String s = "Unexpected end of input at char position " + lastChar
					+ ". Parsed: " + source.substring(0, currentChar);
			throw new RuntimeException(s);
		}
		if (lexSymbol.typeOf == LexSymbol.INT_TYPE) {
			int v = lexSymbol.value;
			nextSymbol();
			return ExpressionNode.ofInt(v);
		} else if (lexSymbol.typeOf == LexSymbol.VAR_TYPE) {
			char ch = (char) lexSymbol.value;
			nextSymbol();
			return ExpressionNode.ofLetter(ch);
		} else if (lexSymbol.value != '(') {
			String s = "Expected int|name|'(' at char position " + lastChar + ". Parsed: "
					+ source.substring(0, currentChar);
			throw new RuntimeException(s);
		} else {
			nextSymbol();
			ExpressionNode e = parseExp();
			if (lexSymbol.value != ')') {
				String s = "')' Expected at char position " + lastChar + ". Parsed: "
						+ source.substring(0, currentChar);
				throw new RuntimeException(s);
			} else {
				nextSymbol();
				return e;
			}
		}
	}

	private ExpressionNode parseUnaryExp() {
		if (lexSymbol != null && lexSymbol.isAdditive()) {
			char ch = (char) lexSymbol.value;
			nextSymbol();
			ExpressionNode e = parsePrimaryExp();
			return ExpressionNode.ofOperator(ch, e);
		} else {
			return parsePrimaryExp();
		}
	}
}

/**
 * Los objetos de esta clase representan simbolos de una expresion.
 */
class LexSymbol {
	static final int	INT_TYPE	= 0;
	static final int	OPR_TYPE	= 1;
	static final int	VAR_TYPE	= 2;

	public final int	typeOf;
	public final int	value;

	private LexSymbol(int symbType, int v) {
		typeOf = symbType;
		value = v;
	}

	public boolean isAdditive() {
		return typeOf == OPR_TYPE && ExpressionNode.isAdditive((char) value);
	}

	public boolean isMultiplicative() {
		return typeOf == OPR_TYPE && ExpressionNode.isMultiplicative((char) value);
	}

	@Override
	public String toString() {
		String tmp = "LexSymbol [typeOf=" + typeOf + "; "
				+ (typeOf == INT_TYPE ? "int: " + String.valueOf(value)
						: typeOf == OPR_TYPE ? "operator: " + (char) value
								: "letter: " + (char) value)
				+ "]";
		return tmp;
	}

	public static boolean isReservedLetter(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')';
	}

	static LexSymbol ofInt(int v) {
		return new LexSymbol(INT_TYPE, v);
	}

	static LexSymbol ofLetter(char v) {
		return new LexSymbol(VAR_TYPE, v);
	}

	static LexSymbol ofOperator(char c) {
		return new LexSymbol(OPR_TYPE, c);
	}
}