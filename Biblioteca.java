package biblio;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import obras.Obra;

public class Biblioteca {
		private final int MAX_OBRAS = 100;
		private Obra [] catalogo;
		private final String NOM_FICHERO_BIBLIOTECA = "catalogoP.txt";
		private static Biblioteca b;
		private int cuantasObras;
		private Scanner es;
		private final String NOM_FICHERO_PRESTAMOS = "obrasPrestadas.txt";
		private int ultimoNumeroRegistro;
		
		
		/**
		 * Constructora que inicializa el catalogo, cuantasObras y ultimoNumeroRegistro
		 */
		private Biblioteca() {
			catalogo = new Obra [MAX_OBRAS];
			cuantasObras = 0;
			ultimoNumeroRegistro = 0;
		}
		
		/**
		 * Devuelve el objeto b
		 * @return b
		 */
		public static Biblioteca getInstance() {
			if (b==null) {
				b = new Biblioteca();
			}
			return b;
		}
		
		/**
		 * Inserta en orden creciente por numero de registro las obras.
		 * @param o
		 */
		private void insertarOrdenado(Obra o) {
			int registro, i = 0;
	        registro = o.getRegistro();
	        	while (i < cuantasObras && catalogo[i].getRegistro() < registro) {
	        		i++;
	        	}
	        	int j = cuantasObras;
	        	while (i < j) {
	        		catalogo[j] = catalogo[j-1];
	        		j--;
	        	}
	        	catalogo[i] = o;
	        	cuantasObras++;
	        }
	        
		/**
		 * Llena el array catalogo con las obras del fichero en orden creciente.
		 */
		public void cargarCatalogoDelFichero() {
			System.out.println("Cargando obras de la biblioteca ...");

			
			try {
				es = new Scanner(new FileReader(NOM_FICHERO_BIBLIOTECA));
				String linea; 

				while (es.hasNextLine()) { 

					linea = es.nextLine(); // Lee una línea completa del fichero
					System.out.println(linea);
					String[] lineaPartes = linea.split(" "); //trocea la línea en fragmaentos utilizando como separado el espacio en blanco
					Obra o = new Obra(Integer.parseInt(lineaPartes[1]), lineaPartes[2], lineaPartes[3], Boolean.parseBoolean(lineaPartes[4]));
					insertarOrdenado(o);
					// los valores siguientes corresponden a: Integer.parseInt(lineaPartes[1])=> código de registro (int), lineaPartes[2]=> signatura,
					//	 lineaPartes[3]=> título, Boolean.parseBoolean(lineaPartes[4]) => si está o no prestada (boolean)
					// COMPLETA CON LA LLAMADA PERTINENTE PARA CREAR LA OBRA E INSERTARLA ORDENADAMENTE EN EL CATÁLOGO
				}
				ultimoNumeroRegistro = catalogo[cuantasObras-1].getRegistro();
				// Completar para obtener el último número de registro, el mayor ....

				System.out.println("... se ha cargado el catalogo de obras del fichero "+ ultimoNumeroRegistro);
				
			} catch (Exception e) {
				System.out.println("... no se ha podido cargar el catalogo del fichero.");
				System.out.println();
				e.printStackTrace();
			} finally {
				es.close();
			}
		}
		
		/**
		 * Sobreescribe el fichero catalogoP.txt con lo modificado.
		 */
		public void guardarCatalogoEnFichero() {
			System.out.println("Guardando el catalogo ...");
			FileWriter fw;
			try {
				// COMPLETAR EL SUBOPROGRAMA: CREAR-ABRIR EL FICHERO CON FileWriter 
				// Y RECORRER EL CATALOGO Y ESCRIBIR CADA OBRA USANDO toString
				fw = new FileWriter(NOM_FICHERO_BIBLIOTECA);
				for (int i = 0; i<cuantasObras; i++) {
					fw.write("OBRA ");
					fw.write(catalogo[i].toString());
					fw.write("\n");
				}
				fw.close();
				System.out.println("... obras guardas en el fichero");
				System.out.println();
			} catch (IOException e) {
				System.out.println("... no se han podido guardar las obras en el fichero");// ezin izan dira aleak gorde.");
				System.out.println();
				e.printStackTrace();
			}
		}
		
		
		/**
		 * Devuelve el ultimo registro del catalogo + 1
		 * @return el ultimo registro + 1
		 */
		public int getSiguienteNumeroRegistro() {
			int nuevoRegistro = this.ultimoNumeroRegistro + 1;
			return nuevoRegistro;
		}
		
		/**
		 * Añade al final del catalogo la obra pasada con el registro que le corresponde.
		 * @param Obra o
		 */
		public void catalogarObra(Obra o) {
		    o.setRegistro(getSiguienteNumeroRegistro());
			catalogo[cuantasObras] = o;
			cuantasObras++;
		}
		
		/**
		 * Elimina del catalogo la obra que contenga el numero de registro pasado.
		 * En caso de que no exista la obra, se indicara con un mensaje.
		 * @param int registro
		 */
		public void descatalogarObra(int registro) {
			int i = 0;
			Obra o = new Obra();
			o = obtenObra(registro);
			while (i < cuantasObras && o.getRegistro()!=catalogo[i].getRegistro()) {
				i++;
			}
			if (i != cuantasObras) {
				obtenObra(o.getRegistro());
				for (int j = i; j<cuantasObras; j++) {
					catalogo[j] = catalogo[j+1];
				}
				cuantasObras--;
			}
		}
		
		/**
		 * Devuelve la cantidad de obras que hay en el catalogo
		 * @return la cantidad de obras del catalogo
		 */
		public int cuantasObras() {
			return cuantasObras;
		}
		
		/**
		 * Devuelve la obra correspondiente al registro pasado. 
		 * En caso de no existir la obra se indicara con un mensaje.
		 * @param int registro
		 * @return la obra que tenga el registro pasado.
		 */
		public Obra obtenObra(int registro) {
			boolean esta = false;
			int i = 0;
			while (i < cuantasObras && esta == false) {
				if (registro == catalogo[i].getRegistro()) {
					esta = true;
				}
				i++;
			}
			if (esta) {
			return catalogo[i-1];
			} else {
				Obra o = new Obra();
				System.out.println("La obra no existe.");
				return o;
			}
		}
		
		/**
		 * Imprime el catalogo.
		 */
		public void imprimirCatalogo() {
			System.out.println("      CATALOGO");
			System.out.println(" Reg  | Sig.   | Titulo,      ¿prestado?");
			System.out.println("-----------------------------------------------");
			for (int i = 0; i<cuantasObras; i++) {
				System.out.print(i+":   ");
				catalogo[i].imprimir();
			}
		}
 		
		/**
		 * Realiza el prestamo para la obra que tenga el registro pasado (true).
		 * @param int registro
		 */
		public void tomarEnPrestamo (int registro) {
			Obra o = obtenObra(registro);
			o.realizarPrestamo();
		}
		
		/**
		 * Devuelve el prestamo para la obra que tenga el registro pasado (false).
		 * @param int registro
		 */
		public void devolverPrestamo(int registro) {
			Obra o = obtenObra(registro);
			o.devolverPrestamo();
		}
		
		/**
		 * Devuelve un entero indicando el numero de obras que esten prestadas.
		 * @return las obras que estan prestadas.
		 */
		public int cuantosPrestamos() {
			int cont = 0;
			for (int i = 0; i<cuantasObras; i++) {
				if (catalogo[i].estaPrestada()) {
					cont++;
				}
			}
			return cont;
		}
		
		/**
		 * Crea un fichero y escribe en el las obras que estan prestadas.
		 */
		public void generarInformePrestados()  {
			 FileWriter fw;

		        try
		        {
		            fw = new FileWriter(NOM_FICHERO_PRESTAMOS);
		            fw.write("----- Biblioteca: Lista de obras en préstamo ------\n");
            		fw.write("Num.Reg. Signatura  Título \n");
            		fw.write("-------- ----------- -------------------------\n");
		            for (int i = 0; i<cuantasObras; i++) {
		            	if(catalogo[i].estaPrestada()) {
		            		fw.write(Integer.toString(catalogo[i].getRegistro()));
		            		fw.write(" 	  "); 
		            		fw.write(catalogo[i].getSignatura());
		            		fw.write("     "); 
		            		fw.write(catalogo[i].getTitulo());
		            		fw.write("\n");
		            	}
		            }
		            fw.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        } 
		}
}


