package juego;

import java.util.List;

public class Jugador implements Comparable<Jugador>{
	private Rey rey;
	private String color;
	
	Jugador(String color) {
		rey = new Rey(color);
		this.color = color;
	}

	public int elegirFicha(List<Ficha> fichasMesa,int codFicha) {

		for (int i = 0; i < fichasMesa.size(); i++) {
			if(codFicha == fichasMesa.get(i).getCode()) {
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public String toString () {
		return color;
	}

	public String getColor() {
		
		return this.color;
	}

	@Override
	public int compareTo(Jugador o) {
		// TODO Auto-generated method stub
		return this.rey.compareTo(o.rey);
	}

	public Rey getRey() {
		// TODO Auto-generated method stub
		return this.rey;
	}
	
	
}
