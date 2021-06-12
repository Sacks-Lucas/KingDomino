package juego;

import java.util.List;

public class Jugador implements Comparable<Jugador> {
	// private Rey rey;
	private String color;
	private Tablero tablero;
	private int posicion;

	// cambiar Rey x variable posición
	public Jugador(String color, Tablero tablero2) {
		// rey = new Rey(color);
		this.color = color;
		this.tablero = tablero2;
	}

	public int elegirFicha(List<Ficha> fichasMesa, int codFicha) {
		return fichasMesa.indexOf(new Ficha(null, null, codFicha));
	}

	@Override
	public String toString() {
		return color;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public String getColor() {

		return this.color;
	}

	@Override
	public int compareTo(Jugador o) {
		return posicion - o.posicion;
	}

	public boolean agregarFichaTablero(Ficha f, int x0, int y0,int x1, int y1) {
		return tablero.agregarFichaATablero(f, x0, y0, x1, y1);
	}

	public void rotarTerreno(Ficha f) {
		f.rotarTerreno();
	}

	public Tablero getTablero() {
		return this.tablero;
	}

}
