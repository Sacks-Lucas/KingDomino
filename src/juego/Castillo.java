package juego;

public class Castillo extends Terreno{
	
	private String color;
	private Rey rey;
	
	
	public Castillo(String color) {
		super("Castillo", 0,0);
		this.rey = new Rey(color);
		valor = 0;
	}

}
