package juego;

import java.util.List;

public class Jugador implements Comparable<Jugador> {
	// private Rey rey;
	private String color;
	private Tablero tablero;
	private int posicion;

	// cambiar Rey x variable posición
	public Jugador(String color) {
		// rey = new Rey(color);
		this.color = color;
		this.tablero = new Tablero(null);
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
		// return this.rey.compareTo(o.rey);
		return posicion - o.posicion;
	}

	/*
	 * public Rey getRey() { return this.rey; }
	 */

	public boolean agregarFichaTablero(Ficha f, int x, int y) {
//		if (tablero.puedeAgregar(f, x, y, f.getSentidoFicha(), f.getSentidoDir())) {
//			tablero.agregarFicha(f, x, y, f.getSentidoFicha(), f.getSentidoDir());
//			return true;
//		}

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
