package modelo.vista;

public abstract class Ser implements Comparable<Ser>{

	private String nombre;
	private int numeroIdentificacion; // es unico y no var�a
	private float esperanzaVida; // aleatorio
	private float necesidadVital;
	private float ahorros;
	private int edad;

	public Ser(String nombre, int numeroIdentificacion, int esperanzaVida) {
		super();
		this.nombre = nombre;
		this.numeroIdentificacion = numeroIdentificacion;
		this.esperanzaVida = esperanzaVida;
		this.edad = 0;
	}

	public float getEsperanzaVida() {
		return esperanzaVida;
	}

	public void setEsperanzaVida(float esperanzaVida) {
		this.esperanzaVida = esperanzaVida;
	}

	public float getNecesidadVital() {
		return necesidadVital;
	}

	public void setNecesidadVital(float necesidadVital) {
		this.necesidadVital = necesidadVital;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public int getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	
	public float getAhorros() {
		return ahorros;
	}


	public void setAhorros(int ahorros) {
		this.ahorros = ahorros;
	}


	
//	public void morir() {
//		//TODO
//	}
	

	public int gestionEsperanzaVida(Ser humano, boolean trabaja) {
		
		if(!trabaja) {
			//para los jovenes necesitaremos saber el estado de las subvenciones que el estado les provee
		}
		
		return 0;
	}
	


	public boolean morir() {
		return edad >= esperanzaVida;
		//en Estado si es true, se elimina de colecciones.
	}
	@Override
	public int compareTo(Ser o) {
		if (this.equals(o)) {
			return 0;
		}else {
			return this.edad-o.edad;
		}
	}
	

}
