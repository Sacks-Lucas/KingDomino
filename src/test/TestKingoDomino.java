package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import juego.App;
import juego.Ficha;
import juego.Jugador;
import juego.Mazo;

public class TestKingoDomino {

	@Test
	public void test4Jugadores() {
		
		final int CANTIDAD = 4;
		
		App juego = new App(CANTIDAD);
		
		juego.mezclarMazo();
		
		ArrayList<Ficha> fichasEnMesa = juego.fichasSobreLaMesa();
		for (Ficha ficha : fichasEnMesa) {
			System.out.println(ficha);
		}
		
		// CAMBIAR TODO LO DEL HASHMAP POR VECTORES PORQUE NO SABEMOS USAR HASHMAP
		
		Map<Ficha, Jugador> mesa = new HashMap<Ficha, Jugador>();
		
		for(int i = 0; i < CANTIDAD; i++)
			mesa.put(fichasEnMesa.get(i), null);
		
		
		//SUPONGAMOS
		for(int i = 0; i < CANTIDAD; i++)
			mesa.replace(juego.getJugador(i).elegirFicha(fichasEnMesa), juego.getJugador(i));
		
		System.out.println(mesa.keySet().toString());
		
		
		
		//Comienza la segunda ronda de seleccion de fichas
		fichasEnMesa = juego.fichasSobreLaMesa();
		
		
		
		
		
		
	}

}
