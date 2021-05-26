package test;

import org.junit.Assert;
import org.junit.Test;

import juego.Castillo;
import juego.Ficha;
import juego.Tablero;
import juego.Terreno;

public class testTerreno {

	@Test
	public void testCombinandoTerrYCuentaPuntos() {
		Tablero a  = new Tablero(new Castillo(null));

		Ficha f1 = new Ficha(new Terreno(3,1), new Terreno(0,2), 1);
		Ficha f2 = new Ficha(new Terreno(0,2), new Terreno(3,1), 2);
		Ficha f3 = new Ficha(new Terreno(3,1), new Terreno(0,2), 3);

		a.agregarFichaATablero(f1, 3, 4, 2, 4); // ficha [1-2]
		a.agregarFichaATablero(f2, 1, 4, 1, 5); // ficha [2-1]
		a.agregarFichaATablero(f3, 3, 5, 3, 6); // ficha [1-2]
		Assert.assertEquals(a.sumarizar(),15);
	}
	
	@Test
	public void testCombinandoGruposTerrYCuentaPuntos() {
		Tablero a  = new Tablero(new Castillo(null));

		Ficha f1 = new Ficha(new Terreno(3,1), new Terreno(0,2), 1);
		Ficha f2 = new Ficha(new Terreno(0,2), new Terreno(3,1), 2);
		Ficha f3 = new Ficha(new Terreno(3,1), new Terreno(0,2), 3);
		Ficha f4 = new Ficha(new Terreno(1,2), new Terreno(0,2), 4);
		Ficha f5 = new Ficha(new Terreno(1,2), new Terreno(0,2), 5);

		a.agregarFichaATablero(f1, 3, 4, 2, 4); // ficha [1-2]
		a.agregarFichaATablero(f2, 1, 4, 1, 5); // ficha [2-1]
		a.agregarFichaATablero(f3, 3, 5, 3, 6); // ficha [1-2]
		a.agregarFichaATablero(f4, 1, 3, 1, 2); // ficha [2-2]
		a.agregarFichaATablero(f5, 2, 3, 2, 2); // ficha [2-2]
		Assert.assertEquals(a.sumarizar(),27);
	}
	
	@Test
	public void testTerrenosSinCoronas() {
		Tablero a  = new Tablero(new Castillo(null));

		Ficha f1 = new Ficha(new Terreno(0,1), new Terreno(0,2), 1);
		Ficha f2 = new Ficha(new Terreno(0,2), new Terreno(0,1), 2);

		a.agregarFichaATablero(f1, 3, 4, 2, 4); // ficha [1-2]
		a.agregarFichaATablero(f2, 1, 4, 1, 5); // ficha [2-1]
		Assert.assertEquals(a.sumarizar(),0);
	}
	
	@Test
	public void testCombinandoDosGruposParalelos() {
		Tablero a  = new Tablero(new Castillo(null));

		Ficha f1 = new Ficha(new Terreno(3,1), new Terreno(0,2), 1);
		Ficha f2 = new Ficha(new Terreno(0,2), new Terreno(3,1), 2);
		Ficha f3 = new Ficha(new Terreno(3,1), new Terreno(0,2), 3);
		Ficha f4 = new Ficha(new Terreno(1,1), new Terreno(2,2), 4);

		a.agregarFichaATablero(f1, 3, 4, 2, 4); // ficha [1-2]
		a.agregarFichaATablero(f2, 1, 4, 1, 5); // ficha [2-1]
		a.agregarFichaATablero(f3, 3, 5, 3, 6); // ficha [1-2]
		a.agregarFichaATablero(f4, 2, 5, 2,6); // ficha [1-2]
		Assert.assertEquals(a.sumarizar(),52);
	}

}
