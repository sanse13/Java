package b.array_preorder_2_postorder;

class My_Code {

	/**
	 * Devuelve el resultado del recorrido en POST-orden del arbol binario cuyo
	 * recorrido en PRE-orden es igual al contenido del array especificado.
	 *
	 * @precondicion el array es el resultado del recorrido en PRE-orden de un arbol
	 *               binario tipo BST.
	 */
	public static int[] solve(int[] array) {
		if (array.length == 0)
			return new int[0];

		
		int[] subLeft = subarbolIzquierdo(array);
		int[] subRight = subarbolDerecho(array);
		subLeft = solve(subLeft);
		subRight = solve(subRight);
		int[] post = new int[array.length];
		//concatenar subLeft y concatenar subRight
		int index = 0;
		for (int i = 0; i < subLeft.length; i++) {
			post[i] = subLeft[i];
			index++;
		}
		int indexj = 0;
		for (int j = index; j < post.length-1; j++) {
			post[j] = subRight[indexj];
			indexj++;
		}
		post[array.length-1] = array[0];
		return post;
		
	}
	
	
	public static int[] subarbolIzquierdo(int[] array) {
		if (array.length == 0)
			return new int[0];
		int cont = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] < array[0])
				cont++;
		}
		if (cont == 0)
			return new int[0];
		int[] res = new int[cont];
		int index = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] < array[0]) {
				res[index] = array[i];
				index++;
			}
		}
		return res;
	}
	
	public static int[] subarbolDerecho(int[] array) {
		if (array.length == 0)
			return new int[0];
		int cont = 0;
		int[] res = new int[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] > array[0]) {
				cont++;
			}
		}
		if (cont == 0)
			return new int[0];
		res = new int[cont];
		cont = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > array[0]) {
				res[cont] = array[i];
				cont++;
			}
		}
		return res;
	}
	
	public static int contarElementosArray(int[] array) {
		int cont = 0;
		for(int i = 0; i < array.length; i++)
			cont++;
		return cont;
	}
	
	public static void visualizar(int[] array) {
		for(int i = 0;  i < array.length; i++)
			System.out.println(array[i]);
	}
}