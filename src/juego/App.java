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

	public boolean add(Jugador e) {
		return jugadores.add(e);
	}

	public void mezclarMazo() {
		mazo.mezclar();
		
	}

	public ArrayList<Ficha> fichasSobreLaMesa() {
		
		ArrayList<Ficha> fichasEnMesa = new ArrayList<Ficha>(jugadores.size());
		
		for (Jugador jugador : jugadores) {
			fichasEnMesa.add(mazo.sacarFicha());
		}
		
		fichasEnMesa.sort(null);
		
		return fichasEnMesa;
	}

	public Jugador getJugador(int i) {
		// TODO Auto-generated method stub
		return jugadores.get(i);
	}

}
