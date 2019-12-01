package backtrack;


public class EightQueens {
	
	//devuelve un array que describe la situacion de 8 reinas en un tablero
	//de ajedrez
	public int[] solve() {
		return backtrack(new int[0]);
	}

	//explorar el arbol de busqueda enraizado en situacionDeReinas
	//y devuelve una solucion, null si no hay
	//precondicion: situacionDeReinas describe una situacion SEGURA de reinas en las primeras
	//situacionDeReinas.length columnas, empezando por la primera columna
	private int[] backtrack(int[] situacionDeReinas) {
		if (situacionDeReinas.length == 8)
			return situacionDeReinas;
		//explorar todas las posibles formas de poner una nueva reina
		//en la columna: situacionDeReinas.length
		int col = situacionDeReinas.length;
		for (int row = 0; row < 8; row++) {
			if (esSegura(row, col, situacionDeReinas)) {
				int[] hijo = new int[col+1];
				System.arraycopy(situacionDeReinas, 0, hijo, 0, col);
				hijo[col] = row;
				
				int[] sol = backtrack(hijo);
				if (sol != null)
					return sol;
			}
		}
		return null;
	}

	private boolean esSegura(int row, int col, int[] situacionDeReinas) {
		
		return false;
	}
	
}

