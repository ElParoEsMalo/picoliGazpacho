package modelo.vista;

public enum Tamano {
	Grande(1000),Mediana(400),Peque�a(100);
	private int capacidad;
	private Tamano(int capacidad) {
		this.capacidad = capacidad;
	}
	public int getCapacidad() {
		return capacidad;
	}
}
