package test;

import org.junit.Assert;
import org.junit.Test;

import juego.Ficha;
import juego.Jugador;
import juego.Terreno;

public class testTablero {
	@Test

	public void testNoPuedoPonerEnLugarOcupado() {
		Jugador j = new Jugador("rojo");
		// se tiene que sacar null de los terrenos
		Ficha f = new Ficha(new Terreno( 0,1), new Terreno( 0,3), 0);
		Ficha f2 = new Ficha(new Terreno( 0,1), new Terreno( 0,3), 0);
		
		Assert.assertTrue(j.agregarFichaTablero(f, 4, 5, 3, 5));
		Assert.assertFalse(j.agregarFichaTablero(f2, 3, 5, 2, 5));
	
	}
	
	@Test
	public void testNoPuedoPonerEnLugarNoCombinado() {
		Jugador j = new Jugador("rojo");
		
		Ficha f = new Ficha(new Terreno( 0,1), new Terreno( 0,3), 1);// ficha [1-3]
		Ficha f2 = new Ficha(new Terreno( 0,1), new Terreno( 0,3), 2);// ficha [1-3]
		
		Assert.assertTrue(j.agregarFichaTablero(f, 4, 5, 4, 6));
		Assert.assertFalse(j.agregarFichaTablero(f2, 3, 6, 3, 7));
	}
	
	@Test
	public void testRotandoYCombinando() {
		Jugador j = new Jugador("rojo");
		
		Ficha f = new Ficha(new Terreno( 0,1), new Terreno( 0,3), 1);
		Ficha f2 = new Ficha(new Terreno( 0,1), new Terreno( 0,3), 2);
		
		j.rotarTerreno(f2);
		
		Assert.assertTrue(j.agregarFichaTablero(f, 4,5, 3, 5));
		Assert.assertTrue(j.agregarFichaTablero(f2, 2, 5, 1,5));
		
		
	}
	
}
