package juego;

public class Ficha implements Comparable<Ficha>{
	private Terreno izquierdo;
	private Terreno derecho;
	private int numero;
	
	public Ficha(Terreno izquierdo, Terreno derecho, int numero) {
		
		this.izquierdo = izquierdo;
		this.derecho = derecho;
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Ficha numero=" + numero;
	}

	@Override
	public int compareTo(Ficha o) {
		return this.numero - o.numero;
	}
	
	
	
}
