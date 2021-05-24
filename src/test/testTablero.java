package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import juego.App;
import juego.Castillo;
import juego.Ficha;
import juego.Jugador;
import juego.Ronda;
import juego.Tablero;
import juego.Terreno;

class TestTablero {

	@Test
	public void testAgregarFichas() {

		Jugador j = new Jugador("rojo");
		Castillo c = new Castillo("Rojo");
		Tablero t = new Tablero(c);

		Ficha f = new Ficha(new Terreno("Minas", 0, 1), new Terreno("Molino", 0, 3), 22);
		Ficha f2 = new Ficha(new Terreno("Lago", 0, 2), new Terreno("Molino", 0, 3), 32);
		System.out.println("Tablero vacio \n");
		t.mostrarTablero();
		j.agregarFichaTablero(t, f, 3, 4);
		System.out.println("\n");
		System.out.println("Tablero con primera parte de ficha\n");
		t.mostrarTablero();
		System.out.println("\n");
		System.out.println("Elegir direccion de la ficha:\n" + t.getPosAdyacentes());
		j.elegirAdyacente(t, "Arriba", f, 3, 4);
		System.out.println("\n");
		System.out.println("Tablero con primera ficha\n");
		t.mostrarTablero();
		j.agregarFichaTablero(t, f2, 1, 4);
		System.out.println("\n");
		System.out.println("Tablero con primera parte de segunda ficha\n");
		t.mostrarTablero();
		System.out.println("\n");
		System.out.println("Elegir direccion de la ficha:\n" + t.getPosAdyacentes());
		j.elegirAdyacente(t, "Arriba", f2, 1, 4);
		System.out.println("\n");
		System.out.println("Tablero con segunda ficha\n");
		t.mostrarTablero();
		j.agregarFichaTablero(t, f2, 5, 4);
		System.out.println("\n");
		System.out.println("Tablero con primera parte de tercera ficha\n");
		t.mostrarTablero();
		System.out.println("\n");
		System.out.println("Elegir direccion de la ficha:\n" + t.getPosAdyacentes());
		j.elegirAdyacente(t, "Izquierda", f2, 5, 4);
		System.out.println("\n");
		System.out.println("Tablero con tercera ficha\n");
		t.mostrarTablero();

//		Assert.assertFalse(j.agregarFichaTablero(t, f2, 2, 4));


	}

//	@Test
//	public void testNoPuedoPonerEnLugarNoCombinado() {
//		Jugador j = new Jugador("rojo");
//		
//		Ficha f = new Ficha(new Terreno(null, 0,1), new Terreno(null, 0,3), 1);
//		Ficha f2 = new Ficha(new Terreno(null, 0,1), new Terreno(null, 0,3), 2);
//		
//		Assert.assertTrue(j.agregarFichaTablero(f, 4, 5));
//		j.elegirAdyacente("Derecha",f, 4, 5);
//		Assert.assertFalse(j.agregarFichaTablero(f2, 3, 5));
//	}
//	
//	@Test
//	public void testRotandoYCombinando() {
//		Jugador j = new Jugador("rojo");
//		
//		Ficha f = new Ficha(new Terreno(null, 0,1), new Terreno(null, 0,3), 1);
//		Ficha f2 = new Ficha(new Terreno(null, 0,1), new Terreno(null, 0,3), 2);
//		f2.rotarTerreno();
//		Ficha f3 = new Ficha(new Terreno(null, 0,3), new Terreno(null, 0,1), 3);
//
//		
//		Assert.assertTrue(j.agregarFichaTablero(f, 3,4));
//		Assert.assertTrue(j.agregarFichaTablero(f2, 1, 4));
//		Assert.assertTrue(j.agregarFichaTablero(f3, 2, 5));
//		
//		
//	}

}
