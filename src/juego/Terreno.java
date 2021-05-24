package juego;

public class Terreno {
	private int cantCoronas;
	private int tipo;
	
	public Terreno( int cantCoronas,int valor) {
		this.tipo = valor;
		this.cantCoronas = cantCoronas;
	}

	public int getTipo() {
		
		return this.tipo;
	}
	
	
}
