package test;

import java.awt.Color;

import org.junit.Assert;
import org.junit.Test;

import juego.Ficha;
import juego.Jugador;
import juego.Tablero;
import juego.Terreno;

public class testTablero {
	@Test

	public void testNoPuedoPonerEnLugarOcupado() {
		Jugador j = new Jugador("rojo", new Tablero(0, 0));
		// se tiene que sacar null de los terrenos
		Ficha f = new Ficha(new Terreno( 0,1,Color.BLACK), new Terreno( 0,3,Color.BLACK), 0);
		Ficha f2 = new Ficha(new Terreno( 0,1,Color.BLACK), new Terreno( 0,3,Color.BLACK), 0);
		
		Assert.assertTrue(j.agregarFichaTablero(f, 4, 5, 3, 5));
		Assert.assertFalse(j.agregarFichaTablero(f2, 3, 5, 2, 5));
	
	}
	
	@Test
	public void testNoPuedoPonerEnLugarNoCombinado() {
		Jugador j = new Jugador("rojo", new Tablero(0,0));
		
		Ficha f = new Ficha(new Terreno( 0,1,Color.BLACK), new Terreno( 0,3,Color.BLACK), 1);// ficha [1-3]
		Ficha f2 = new Ficha(new Terreno( 0,1,Color.BLACK), new Terreno( 0,3,Color.BLACK), 2);// ficha [1-3]
		
		Assert.assertTrue(j.agregarFichaTablero(f, 4, 5, 4, 6));
		Assert.assertFalse(j.agregarFichaTablero(f2, 3, 6, 3, 7));
	}
	
	@Test
	public void testRotandoYCombinando() {
		Jugador j = new Jugador("rojo",new Tablero(0,0));
		
		Ficha f = new Ficha(new Terreno( 0,1,Color.BLACK), new Terreno( 0,3,Color.BLACK), 1);
		Ficha f2 = new Ficha(new Terreno( 0,1,Color.BLACK), new Terreno( 0,3,Color.BLACK), 2);
		
		j.rotarTerreno(f2);
		
		Assert.assertTrue(j.agregarFichaTablero(f, 4,5, 3, 5));
		Assert.assertTrue(j.agregarFichaTablero(f2, 2, 5, 1,5));
		
		
	}
	
	@Test
	public void testCompletandoFilas() {
		Jugador j = new Jugador("rojo",new Tablero(0,0));
		
		Ficha f  = new Ficha(new Terreno( 0,1,Color.BLACK), new Terreno( 0,3,Color.BLACK), 1);
		Ficha f2 = new Ficha(new Terreno( 0,3,Color.BLACK), new Terreno( 0,1,Color.BLACK), 2);
		Ficha f3 = new Ficha(new Terreno( 0,1,Color.BLACK), new Terreno( 0,3,Color.BLACK), 3);

		Assert.assertTrue(j.agregarFichaTablero(f,  5, 6, 5, 7));
		Assert.assertTrue(j.agregarFichaTablero(f2, 5, 8, 5, 9));
		Assert.assertTrue(j.agregarFichaTablero(f3, 6, 6, 7, 6));
		Assert.assertTrue(j.agregarFichaTablero(f2, 8, 6, 9, 6));
		Assert.assertFalse(j.agregarFichaTablero(f2, 4, 6, 3, 6));
		Assert.assertTrue(j.agregarFichaTablero(f2, 8, 6, 9, 6));
	}
}
