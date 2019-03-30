package obras;

public class Obra {
	private int registro;
	private String signatura;
	private String titulo;
	private boolean prestada;
	
	/**
	 * constructora sin parámetros, vacía
	 */
	public Obra() {
	}
	//true si esta prestada, false si no esta prestada
	/**
	 * constructora con los parametros registro, signatura y titulo, con prestada inicilizada a false
	 */
	public Obra(int registro, String signatura, String titulo) {
		this.registro = registro;
		this.signatura = signatura;
		this.titulo = titulo;
		this.prestada = false;
	}
	/**
	 * constructora con todos los atributos inicializados
	 */
	public Obra(int registro, String signatura, String titulo, boolean prestada) {
		this.registro = registro;
		this.signatura = signatura;
		this.titulo = titulo;
		this.prestada = prestada;
	}
	/**
	 * devuelve el registro de la obra
	 * @return registro de la obra
	 */
	public int getRegistro() {
		return registro;
	}
	/**
	 * asigna al registro de la obra el registro pasado por parámetro
	 * @param int registro
	 */
	public void setRegistro(int registro) {
		this.registro = registro;
	}
	/**
	 * devuelve la signatura de la obra
	 * @return signatura de la obra
	 */
	public String getSignatura() {
		return signatura;
	}
	/**
	 * asigna a la signatura de la obra la signatura pasada
	 * @param String signatura
	 */
	public void setSignatura(String signatura) {
		this.signatura = signatura;
	}
	/**
	 * devuelve el titulo de la obra
	 * @return titulo de la obra
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * asigna al titulo de la obra el titulo pasado
	 * @param String titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * asigna al atributo "prestada" el "prestada" pasado por parámetro
	 * @param boolean prestada
	 */
	public void setPrestada(boolean prestada) {

		this.prestada = prestada;
	}
	
	/**
	 * crea un String con todos los datos de la obra
	 */
	public String toString() {
		String mensaje = getRegistro()+" "+getSignatura()+" "+getTitulo().replaceAll(" ", "_")+" "+estaPrestada();
		return mensaje;
	}
	/**
	 * Imprime los datos de la obra
	 */
	public void imprimir() {
		String mensaje = getRegistro()+" "+getSignatura()+"- "+getTitulo().replaceAll(" ", "_")+", "+estaPrestada();
		System.out.println(mensaje);
	}
	/**
	 * indica si la obra esta prestada o no y lo indica mediante un mensaje
	 */
	public boolean estaPrestada() {	
		//true si esta prestada
		return prestada;
	}
	/**
	 * si no esta prestada, la pone a prestada. Si ya estaba no prestada salta un mensaje de error
	 */
	public void realizarPrestamo() {
		if (estaPrestada()==false) {
			setPrestada(true);
		} else {
			System.out.println("Error. La obra ya esta prestada.");
		}
	}
	/**
	 * si esta prestada, la pone a no prestada. Si ya estaba prestada salta un mensaje de error
	 */
	public void devolverPrestamo() {
		if (estaPrestada()) {
			setPrestada(false);
		} else {
			System.out.println("Error. La obra ya estaba devuelta.");
		}
	}
}
