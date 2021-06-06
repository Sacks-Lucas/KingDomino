package juego;

public class Ficha implements Comparable<Ficha>{
	private Terreno izquierdo;
	private Terreno derecho;
	private int numero;
	private int x=0;
	private int y=0;
	private int x1;
	private int y1;

	public static final int ANCHO_FICHA = 120;
	public static final int ALTO_FICHA = 60;
	public static final int TAM_TERRENO = ANCHO_FICHA/2;
	
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

	public int getCode() {
		return this.numero;
	}

	public Terreno getIzquierdo() {
		return izquierdo;
	}

	public Terreno getDerecho() {
		return derecho;
	}

	public void rotarTerreno() {
		Terreno a = izquierdo.clone();
		izquierdo = derecho;
		derecho = a;
	}
	

	public Terreno getTipoTerrenoIzq() {
		return this.izquierdo;
	}

	public Terreno getTipoTerrenoDer() {
		return this.derecho;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numero;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ficha other = (Ficha) obj;
		if (numero != other.numero)
			return false;
		return true;
	}

	public void setX(int x0) {
		this.x = x0;
		this.x1 = x0+TAM_TERRENO;
	}

	public void setY(int y0) {
		this.y = y0;
		this.y1 = y0;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getX1() {
		return this.x1;
	}

	public int getY1() {
		return this.y1;
	}

	public void setX1(int x2) {
		this.x1=x2;
	}

	public void setY1(int i) {
		this.y1 = i;
	}

	
	

}
