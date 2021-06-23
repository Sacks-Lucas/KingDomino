package juego;

import java.util.ArrayList;

public class App {

	private final String [] COLORES = {"Rojo", "Azul", "Amarillo", "Verde"};
	
	private ArrayList<Jugador> jugadores;
	private Mazo mazo;

	private String ganador="";
	private static final int CANT_FICHAS_X_JUGADOR= 12;
	public App(int cantJugadores) {
		jugadores = new ArrayList<Jugador>(cantJugadores);
		this.mazo = new Mazo(cantJugadores*CANT_FICHAS_X_JUGADOR);
		mezclarMazo();
		int aux[][] = {{0+50,0+50},{Ficha.TAM_TERRENO*5+300,0+50},{0+50,Ficha.TAM_TERRENO*5+200},{Ficha.TAM_TERRENO*5+300,Ficha.TAM_TERRENO*5+200}};
		for(int i = 0; i < cantJugadores; i++) {
			jugadores.add(new Jugador(COLORES[i],new Tablero(aux[i][0],aux[i][1])));
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


	public boolean juegoFinalizado() {
		return this.mazo.estaVacio();
	}


	public String getGanador() {
		return "Rojo";
	}
	
	public void setGanador(String ganador) {
		this.ganador = ganador;
	}
	
}
