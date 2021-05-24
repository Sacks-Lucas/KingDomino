package juego;

public class Terreno {
	// tipos: Minas = 1	lago = 2	Molino = 3	Bosque = 4	Pantano = 5	Llanuras = 6	
	private String tipo;
	private int cantCoronas;
	protected int valor;
	
	public Terreno(String tipo, int cantCoronas,int valor) {
		this.tipo = tipo;
		this.valor = valor;
		this.cantCoronas = cantCoronas;
	}

	public int getTipo() {
		
		return this.valor;
	}
	
	
}
