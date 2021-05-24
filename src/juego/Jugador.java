package juego;

import java.util.List;

public class Jugador implements Comparable<Jugador> {
	private Rey rey;
	private String color;
	private Tablero tablero;

	public Jugador(String color) {
		rey = new Rey(color);
		this.color = color;
		this.tablero = new Tablero(null);
	}

	public int elegirFicha(List<Ficha> fichasMesa, int codFicha) {

		for (int i = 0; i < fichasMesa.size(); i++) {
			if (codFicha == fichasMesa.get(i).getCode()) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public String toString() {
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

	public boolean agregarFichaTablero(Tablero tablero, Ficha f, int x, int y) {
		if (tablero.puedeAgregar(f, x, y)) {
			tablero.agregarFicha(f, x, y, 1);

			return true;
		}
		return false;
	}

	public void elegirAdyacente(Tablero tablero, String lado, Ficha f, int x, int y) {
		String t = null;
		for (int i = 0; i < tablero.getPosAdyacentes().size(); i++) {
			if (lado == tablero.getPosAdyacentes().get(i)) {
				t = tablero.getPosAdyacentes().get(i);
				tablero.getPosAdyacentes().clear();
			}
		}

		if (t == "Abajo") {
			if (tablero.dentroDeTablero()) {
				tablero.agregarFicha(f, x + 1, y, 2);
				return;
			}
		}
		if (t == "Arriba") {
			if (tablero.dentroDeTablero()) {
				tablero.agregarFicha(f, x - 1, y, 2);
				return;
			}
		}
		if (t == "Derecha") {
			if (tablero.dentroDeTablero()) {
				tablero.agregarFicha(f, x, y + 1, 2);
				return;
			}
		}
		if (t == "Izquierda") {
			if (tablero.dentroDeTablero()) {
				tablero.agregarFicha(f, x, y - 1, 2);
			}
		}
	}

	public void rotarTerreno(Ficha f) {
		f.rotarTerreno();
	}

	public Tablero getTablero() {
		return this.tablero;
	}

}
