package juego;

public class Ficha implements Comparable<Ficha>{
	private Terreno izquierdo;
	private Terreno derecho;
	private int numero;
	
	static final int  SENTIDO_VERTICAL = 1;
	static final int  SENTIDO_HORIZONTAL = 2;
	
	static final int  SENTIDO_IZQ_TOP = 1;
	static final int  SENTIDO_DER_DOWN = 2;
	
	private int sentidoFicha = SENTIDO_VERTICAL;
	private int sentidoDir = SENTIDO_IZQ_TOP;
	
	public Ficha(Terreno izquierdo, Terreno derecho, int numero) {
		
		this.izquierdo = izquierdo;
		this.derecho = derecho;
		this.numero = numero;
	}

	public int getSentidoFicha(){
		return sentidoFicha;
	}
	public int getSentidoDir(){
		return sentidoDir;
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
	

	public void rotarSentido() {
		sentidoDir = sentidoDir == SENTIDO_IZQ_TOP?SENTIDO_DER_DOWN: SENTIDO_IZQ_TOP;
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


	
	

}
