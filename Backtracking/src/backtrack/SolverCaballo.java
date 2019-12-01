package backtrack;

import java.util.ArrayList;

import util.GridCoordinates;


public class SolverCaballo {
	
	static boolean[][] visitado = new boolean[8][8];
	
	public static GridCoordinates[] solver(GridCoordinates origen) {
		ArrayList<GridCoordinates> lista = new ArrayList<>();
		lista.add(origen);
		return backtrack(lista, visitado);
	}
	
	
	/**
	 * 
	 * @param e
	 * 			
	 * 			secuencia de casillas sin repeticiones, por la cuales ha pasado el
	 * 			caballo.
	 * @return solucion si la hay, no ha modificado el ensayo e.
	 */
	public static GridCoordinates[] backtrack(ArrayList<GridCoordinates> e, boolean[][] visitado) {
		
		if (e.size() == 64) return e.toArray(new GridCoordinates[0]);
		
		GridCoordinates ultimaPosCamino = e.get(e.size()-1);
		for (int row = ultimaPosCamino.row-2; row < ultimaPosCamino.row+2; row++) {
			for (int col = ultimaPosCamino.column-2; col < ultimaPosCamino.column+2; col++) {
				if (esValida(row, col, ultimaPosCamino) && !haSidoVisitada(row, col, visitado)) {
					
					e.add(new GridCoordinates(col, row)); //añadiendo al ensayo
					visitado[row][col] = true;
					GridCoordinates[] sol = backtrack(e, visitado);
					e.remove(e.size()-1);
					visitado[row][col] = false;
					
					if (sol != null) return sol;
					
					
				}
			}
		}
		
		return null;
	}

	private static boolean haSidoVisitada(int row, int col, boolean[][] visitado) {
		// TODO Auto-generated method stub
		return visitado[row][col] == true;
	}

	private static boolean esValida(int row, int col, GridCoordinates ultimaPosCamino) {
		// TODO Auto-generated method stub
		return false;
	}

}
