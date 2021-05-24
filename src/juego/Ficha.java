package juego;

public class Ficha implements Comparable<Ficha>{
	private Terreno t1;
	private Terreno t2;
	private int numero;
	
	public Ficha(Terreno t1, Terreno t2, int numero) {
		
		this.t1 = t1;
		this.t2 = t2;
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

	public int getCode() {
		// TODO Auto-generated method stub
		return this.numero;
	}

	public Terreno getT1() {
		return t1;
	}

	public Terreno getT2() {
		return t2;
	}

	public void rotarTerreno() {
		Terreno a = t1;
		t1 = t2;
		t2 = a;
	}

	public int getTipoTerreno1() {
		return this.t1.getTipo();
	}

	public int getTipoTerreno2() {
		return this.t2.getTipo();
	}

}
