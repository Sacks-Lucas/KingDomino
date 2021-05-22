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
		
		Ficha f = new Ficha(new Terreno(null, 0,1), new Terreno(null, 0,3), 0);
		Ficha f2 = new Ficha(new Terreno(null, 0,1), new Terreno(null, 0,3), 0);
		
		Assert.assertTrue(j.agregarFichaTablero(f, 3, 4));
		Assert.assertFalse(j.agregarFichaTablero(f2, 2, 4));
	
	}
	
	@Test
	public void testNoPuedoPonerEnLugarNoCombinado() {
		Jugador j = new Jugador("rojo");
		
		Ficha f = new Ficha(new Terreno(null, 0,1), new Terreno(null, 0,3), 1);
		Ficha f2 = new Ficha(new Terreno(null, 0,1), new Terreno(null, 0,3), 2);
		
		Assert.assertTrue(j.agregarFichaTablero(f, 4, 5));
		Assert.assertFalse(j.agregarFichaTablero(f2, 3, 5));
	}
	
	@Test
	public void testRotandoYCombinando() {
		Jugador j = new Jugador("rojo");
		
		Ficha f = new Ficha(new Terreno(null, 0,1), new Terreno(null, 0,3), 1);
		Ficha f2 = new Ficha(new Terreno(null, 0,1), new Terreno(null, 0,3), 2);
		f2.rotarTerreno();
		Ficha f3 = new Ficha(new Terreno(null, 0,3), new Terreno(null, 0,1), 3);
		f3.rotarFicha();
		f3.rotarSentido();
		
		Assert.assertTrue(j.agregarFichaTablero(f, 3,4));
		Assert.assertTrue(j.agregarFichaTablero(f2, 1, 4));
		Assert.assertTrue(j.agregarFichaTablero(f3, 2, 5));
		
		
	}
	
}
