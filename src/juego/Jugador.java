package juego;

import java.util.List;

public class Jugador implements Comparable<Jugador>{
	private Rey rey;
	private String color;
	private Tablero tablero; 
	
	public Jugador(String color) {
		rey = new Rey(color);
		this.color = color;
		this.tablero = new Tablero(null);
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
		return this.rey.compareTo(o.rey);
	}

	public Rey getRey() {
		return this.rey;
	}
	
	public boolean agregarFichaTablero(Ficha f,int x, int y) {
		if(tablero.puedeAgregar(f,x,y, f.getSentidoFicha(), f.getSentidoDir())) {
			tablero.agregarFicha(f,x,y, f.getSentidoFicha(), f.getSentidoDir());
			return true;
		}
		
		return false;
	}
	
	public void rotarTerreno(Ficha f) {
		f.rotarTerreno();
	}
	
	public void rotarFicha(Ficha f) {
		f.rotarFicha();
	}

	public Tablero getTablero() {
		return this.tablero;
	}
	
}
