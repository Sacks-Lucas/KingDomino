package juego;

import java.util.ArrayList;

public class Jugador {
	private Rey rey;
	private String color;
	
	Jugador(String color) {
		this.color = color;
	}

	public Ficha elegirFicha(ArrayList<Ficha> fichasMesa) {
		
		int posHardCodeada = 0;
		
		return fichasMesa.remove(posHardCodeada);
	}
	
	@Override
	public String toString () {
		return color;
	}
}
