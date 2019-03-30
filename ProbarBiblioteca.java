package biblio;


import obras.Obra;

public class ProbarBiblioteca {
		private static int caso = 1;
		public static void casoObtenObra(int registro, boolean esperado) {
			Biblioteca b = Biblioteca.getInstance();
			System.out.print("CASO "+caso+" ");
			Obra o = b.obtenObra(registro);
			if (o.getRegistro() == registro) {
				System.out.println("→ PASA");
			} 
			caso++;
		}
		
		public static void casoCuantasObras(int miTotal, boolean esperado) {
			System.out.print("CASO "+caso+" ");
			Biblioteca b = Biblioteca.getInstance();
			int total = b.cuantasObras();
			if (total == miTotal) {
				esperado = true;
				System.out.println("→ PASA");
			} else {
				esperado = false;
				System.out.println("→ ERROR");
			}
			caso++;
		}
		
		public static void casoCuantosPrestamos(int miTotal, boolean esperado) {
			System.out.print("CASO "+caso+" ");
			Biblioteca b = Biblioteca.getInstance();
			int total = b.cuantosPrestamos();
			if (total == miTotal) {
				esperado = true;
				System.out.println("→ PASA");
			} else System.out.println("→ NO PASA");
			caso++;
		}
		
		
		public static void casoTomarEnPrestamo(int registro, boolean esperado) {
			System.out.print("CASO "+caso+" ");
			Biblioteca b = Biblioteca.getInstance();
			if (esperado == false) {
				b.tomarEnPrestamo(registro);
			} else {
			Obra o = b.obtenObra(registro);
			b.tomarEnPrestamo(registro);
			if (o.estaPrestada()) {
				System.out.println("→ PASA");
				} 
			}
			caso++;
		}
		
		public static void casoDevolverPrestamo(int registro, boolean esperado) {
			System.out.print("CASO "+caso+" ");
			Biblioteca b = Biblioteca.getInstance();
			if (esperado == false) {
				b.devolverPrestamo(registro);
			} else {
				Obra o = b.obtenObra(registro);
				b.devolverPrestamo(registro);
				if (o.estaPrestada() == false) {
					System.out.println("→ PASA");
				}
			}
			caso++;
		}
		
		
		public static void main (String [] args)  {
			Biblioteca b = Biblioteca.getInstance();
			b.cargarCatalogoDelFichero();
			System.out.println();
			b.guardarCatalogoEnFichero();
			System.out.println("CASO CUANTAS OBRAS:");
			casoCuantasObras(9, true);
			casoCuantasObras(10, false);
			System.out.println("El numero de obras que hay es "+b.cuantasObras());
			System.out.println("El siguiente numero de registro es: "+b.getSiguienteNumeroRegistro());
			System.out.println("CASO OBTENER OBRAS:");
		    casoObtenObra(5, true);
			casoObtenObra(20, false);	
			System.out.println();
			b.imprimirCatalogo();
			System.out.println();
			System.out.println("CASO CUANTOS PRESTAMOS:");
			casoCuantosPrestamos(3, false);
			casoCuantosPrestamos(10, true);
			System.out.println();
			System.out.println("El numero de obras prestadas son: "+b.cuantosPrestamos());
			System.out.println("CASO TOMAR EN PRESTAMO Y DEVOLVER PRESTAMO");
			casoTomarEnPrestamo(7, true);
			casoTomarEnPrestamo(6, true);
			casoTomarEnPrestamo(2, true);
			casoTomarEnPrestamo(20, false);
			casoDevolverPrestamo(2, true);
			System.out.println("Ahora hay "+b.cuantosPrestamos()+" prestadas.");
			Obra a = new Obra(0, "prueba", "prueba prueba");
			System.out.println();
			System.out.println("CASOS CATALOGAR Y DESCATALOGAR OBRA:");
			b.catalogarObra(a);
			b.imprimirCatalogo();
			b.generarInformePrestados();
			Obra x = new Obra();
			x = b.obtenObra(8);
			System.out.println(x.toString());
			b.descatalogarObra(8);
			b.imprimirCatalogo();
			b.descatalogarObra(6);
			b.imprimirCatalogo();
			b.descatalogarObra(20);
		}
}
