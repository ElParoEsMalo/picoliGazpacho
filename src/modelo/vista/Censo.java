package modelo.vista;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Censo {
	private ArrayList<Ser> poblacion = new ArrayList<Ser>();
	private LinkedList<Ser> demandantes = new LinkedList<Ser>();
	private HashSet<Integer> identificacion = new HashSet<Integer>();
	private int nacimientos=0; 
	private int muertos=0; 
	private int jubilados=0;
	
	public int getNacimientos() {
		return nacimientos;
	}
	public int getJubiladosNuevos() {
		return jubilados;
	}
	
	public int getMuertos() {
		return muertos;
	}
	
	public HashSet<Integer> getIdentificacion() {
		return identificacion;
	}


	public Comparator<Ser> getComparador() {
		return comparador;
	}

	public Comparator<Ser> getComparadorNV() {
		return comparadorNV;
	}
	 public int numeroJubilados() {
	    	int posInicial=0;
	    	for (int i = 0; i < poblacion.size(); i++) {
				if (poblacion.get(i).getEdad()<65) {
					posInicial=i;
				}
	    	}
			return poblacion.size()-posInicial-1;
		}
	 public int numeroMenores() {
	    	int posInicial=0;
	    	for (int i = 0; i < poblacion.size(); i++) {
				if (poblacion.get(i).getEdad()<18) {
					posInicial=i;
				}
	    	}
			return posInicial+1;
		}
	  public int numeroTrabajadores() {
	    	int total=0;
	    	for (int i = 0; i < poblacion.size(); i++) {
				if (poblacion.get(i).getEdad()>17 && poblacion.get(i).getEdad()<65) {
					if (!demandantes.contains(poblacion.get(i))) {
						total++;
					}
				}
	    	}
			return total;
		}

	public Censo() {
		super();
		for (int i = 0; i < 50;) {
			Ser ser = new Ser(crearNombre(), CrearIdentificacion(), (int) (Math.random() * (90)));
			ser.setEdad((int) (Math.random() * (18)));
			if (ser.getEdad()<ser.getEsperanzaVida()) {
				poblacion.add(ser);
				i++;
			}
		}
		for (int i = 0; i < 100;) {
			Ser ser = new Ser(crearNombre(), CrearIdentificacion(), (int) (Math.random() * (90)));
			ser.setEdad((int) (Math.random() * (65 - 18) + 18));
			if (ser.getEdad()<ser.getEsperanzaVida()) {
				poblacion.add(ser);
				demandantes.add(ser);
				i++;
			}
		}
		for (int i = 0; i < 30;) {
			Ser ser = new Ser(crearNombre(), CrearIdentificacion(), (int) (Math.random() * (90)));
			ser.setEdad((int) (Math.random() * (90 - 65) + 65));
			if (ser.getEdad()<ser.getEsperanzaVida()) {
				ser.setEsperanzaVida(365/2);
				poblacion.add(ser);
				i++;
			}
		}
		organizarColeccionciones();
	}

	private Comparator<Ser> comparador = new Comparator<Ser>() {
		@Override
		public int compare(Ser o1, Ser o2) {
			return o1.getEdad() - o2.getEdad();
		}
	};
	private Comparator<Ser> comparadorNV = new Comparator<Ser>() {

		@Override

		public int compare(Ser o1, Ser o2) {
			double cosa = o1.getAhorros() * 100000;
			double cosaDos = o2.getAhorros() * 100000;
			return (int) cosa - (int) (cosaDos);
		}
	};

	public LinkedList<Ser> getDemandantes() {
		return demandantes;
	}

	public void organizarColeccionciones() {
		Collections.sort(poblacion, comparador);
		Collections.sort(demandantes, comparadorNV);
	}

	public void setDemandantes(LinkedList<Ser> demandantes) {
		this.demandantes = demandantes;
	}

	public ArrayList<Ser> getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(ArrayList<Ser> poblacion) {
		this.poblacion = poblacion;
	}
	public void actualizarDemandantes(Stack <Ser> trabajadores) {
		for (Ser ser : poblacion) {
			if (ser.getEdad()>17&&!trabajadores.contains(ser)) {
				demandantes.add(ser);
			}
		}
	}
// recibes dos parametros nuevos produccion y demanda
// for lo recorres tantas veces como (demanda-produccion)/1000
	public void nacimiento(float demanda, float produccion) {
		int CuantiaNacimientos=(int) ((demanda-produccion)/1000);
		if (CuantiaNacimientos<0) {
			nacimientos=0;
		}else {
			nacimientos=CuantiaNacimientos;
		}
		for (int i = 0; i < nacimientos; i++) {
			Ser menor = new Ser(crearNombre(), CrearIdentificacion(), (int) (Math.random() * (90)));
			poblacion.add(menor);			
		}
	}
	public void jubilarSer() {
		for (Ser ser : poblacion) {
			if (ser.getEdad()>=65) {
				ser.setNecesidadVital(365f/2f);
			}
		}
	}
	public void jubiladosNuevos() {
		jubilados=0;
		for (Ser ser : poblacion) {
			if (ser.getEdad()==65) {
				jubilados++;
			}
		}
	}

	public ArrayList<Ser> morir() {
		ArrayList<Ser> trabajadoresMuertos = new ArrayList<>();
		for (Iterator<Ser> iterator = poblacion.iterator(); iterator.hasNext();) {
			Ser ser = (Ser) iterator.next();
			if (ser.getEdad() > ser.getEsperanzaVida()) {
				if (!demandantes.contains(ser) && ser.getEdad() > 17 && ser.getEdad() < 65) {
					trabajadoresMuertos.add(ser);
				}
				iterator.remove();
				demandantes.remove(ser);
				muertos++;
			}
		}
		return trabajadoresMuertos;
	}

	public int CrearIdentificacion() {
		int indice;
		do {
			indice = (int) (Math.random() * 9999999);
		} while (!identificacion.add(indice));
		return indice;
	}

	public String crearNombre() {
		String nombre = "";
		int indice;
		for (int i = 0; i < 10; i++) {
			indice = (int) (Math.random() * (122 - 97) + 97);
			nombre = nombre + (char) (indice);
		}
		return nombre;
	}

	public void reducirEV() {
		float reduccion=0f;
		for (Ser ser : poblacion) {
			reduccion = (ser.getNecesidadVital() - ser.getAhorros()) / ser.getNecesidadVital();
			if (reduccion > 0) {
				if (reduccion >= 0.5) {
					reduccion = 0.5f;
				}
			}
			ser.setEsperanzaVida(ser.getEsperanzaVida() - reduccion);
		}
	}

}
