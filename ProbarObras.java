package obras;

public class ProbarObras {
	
	public static void debePasar() {
		System.out.print("Debe pasar. ");
	}
	public static void noDebePasar() {
		System.out.print("No debe pasar. ");
	}
	public static void casoCrearObras(int caso, Obra o, int registro, String signatura, String titulo, boolean prestada) {
		System.out.print("CASO "+caso+" ");
		if (registro == o.getRegistro() & signatura == o.getSignatura() & titulo == o.getTitulo() & prestada == o.estaPrestada()) {
			System.out.println("→ PASA");
		} else System.out.println("→ NO PASA");
	}
	
	public static void casoEstaPrestada(int caso, boolean esperado) {
		Obra o = new Obra();
		System.out.print("CASO "+caso+" ");
		if (o.estaPrestada() == esperado) {
			System.out.println("→ PASA");
		} else System.out.println("→ NO PASA");
	}
	
	public static void main (String []args) {
		Obra o = new Obra(3, "MB-001", "Musikaz blai");
		debePasar();
		casoCrearObras(1, o, 3, "MB-001", "Musikaz blai", false);
		noDebePasar();
		casoCrearObras(2, o, 4, "MB-001", "Musikaz blai", true);
		System.out.println(o.getRegistro());
		System.out.println(o.getSignatura());
		System.out.println(o.getTitulo());
		System.out.println(o.estaPrestada());
		o.imprimir();
		noDebePasar();
		casoEstaPrestada(3, true);
		debePasar();
		casoEstaPrestada(4, false);
		o.setPrestada(true);
		o.realizarPrestamo();
		o.devolverPrestamo();
		System.out.println("Despues de devolverla, nos queda lo siguiente: "+o.estaPrestada());
	}
}
