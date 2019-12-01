package backtrack;

import java.util.ArrayList;

public class Mochila {

	
	public static int[] solver(int volumenMaximo, int[] productosVolumenes, int[] productosBeneficio) {
		ArrayList<Integer> lista = new ArrayList<>();
		return backtrack(lista, 0, volumenMaximo, productosVolumenes, productosBeneficio);
	}

	private static int[] backtrack(ArrayList<Integer> e, int desde, int volumenMaximo, int[] productosVolumenes,
			int[] productosBeneficio) {
		// TODO Auto-generated method stub
		
		
		return null;
	}
}
