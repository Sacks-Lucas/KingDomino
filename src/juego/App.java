package juego;

import java.util.ArrayList;

public class App {

	private final String [] COLORES = {"Rojo", "Azul", "Amarillo", "Verde"};
	
	private ArrayList<Jugador> jugadores;
	private Mazo mazo;
	
	public App(int cantJugadores) {
		jugadores = new ArrayList<Jugador>(cantJugadores);
		this.mazo = new Mazo(cantJugadores*12);
		
		for(int i = 0; i < cantJugadores; i++) {
			jugadores.add(new Jugador(COLORES[i]));
		}
	}


	public void mezclarMazo() {
		mazo.mezclar();	
	}

	public Jugador getJugador(int i) {
		return jugadores.get(i);
	}

	public Mazo obtenerMazo() {
		return this.mazo;
	}

	public ArrayList<Jugador> getJugadores() {
		return this.jugadores;
	}

	public Mazo getMazo() {
		return this.mazo;
	}
	
	

}
