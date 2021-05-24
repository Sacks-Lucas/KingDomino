package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import juego.App;
import juego.Ficha;
import juego.Ronda;

public class TestKingoDomino {

	@Test
	public void test4Jugadores() {
		
		final int CANTIDAD = 4;
		int f1,f2,f3,f4;
		App juego = new App(CANTIDAD);
		List<Ficha> fichasJ1 = new ArrayList<Ficha>();
		List<Ficha> fichasJ2 = new ArrayList<Ficha>();
		List<Ficha> fichasJ3 = new ArrayList<Ficha>();
		List<Ficha> fichasJ4 = new ArrayList<Ficha>();
		
		//juego.mezclarMazo(); NO MEZCLAMOS PORQUE FUNCIONA Y NO PODEMOS DETERMINAR EL NUMERO EXACTO
		
		//System.out.println(juego.getMazo());
		Ronda rondas = new Ronda(juego.getJugadores(),juego.getMazo());
		
		System.out.println("INICIO RONDA "+rondas.getRonda());
		System.out.println("Fichas de Ronda:  "+rondas.obtenerFichasEnMesa());
		System.out.println("Orden Ronda:  "+rondas.getOrdenJ());
		
		//Comienzo a elegir fichas
		
		f1=juego.getJugador(0).elegirFicha(rondas.obtenerFichasEnMesa(),45);
		f2=juego.getJugador(1).elegirFicha(rondas.obtenerFichasEnMesa(),44);
		f3=juego.getJugador(2).elegirFicha(rondas.obtenerFichasEnMesa(),47);
		f4=juego.getJugador(3).elegirFicha(rondas.obtenerFichasEnMesa(),46);	
		
		//Saco las fichas de la mesa que son válidas
		if(f1 != -1) fichasJ1.add(rondas.sacarFicha(f1,0));
		if(f2 != -1) fichasJ2.add(rondas.sacarFicha(f2,1));
		if(f3 != -1) fichasJ3.add(rondas.sacarFicha(f3,2));
		if(f4 != -1) fichasJ4.add(rondas.sacarFicha(f4,3));
		
		
		System.out.println("FIN RONDA "+rondas.getRonda());
		System.out.println();
		//Fin ronda.
		
		rondas.avanzar(); // avanzo la siguiente ronda
		rondas.ponerFichasEnMesa(juego.getMazo());
		System.out.println("INICIO RONDA "+rondas.getRonda());
		System.out.println("Fichas de Ronda:  "+rondas.obtenerFichasEnMesa());
		System.out.println("Orden Ronda:  "+rondas.getOrdenJ());
		
		//Comienzo a elegir fichas
		
		f1=juego.getJugador(0).elegirFicha(rondas.obtenerFichasEnMesa(),41);
		f2=juego.getJugador(1).elegirFicha(rondas.obtenerFichasEnMesa(),40);
		f3=juego.getJugador(2).elegirFicha(rondas.obtenerFichasEnMesa(),42);
		f4=juego.getJugador(3).elegirFicha(rondas.obtenerFichasEnMesa(),43);	
		
		//Saco las fichas de la mesa que son válidas
		if(f1 != -1) fichasJ1.add(rondas.sacarFicha(f1,0));
		if(f2 != -1) fichasJ2.add(rondas.sacarFicha(f2,1));
		if(f3 != -1) fichasJ3.add(rondas.sacarFicha(f3,2));
		if(f4 != -1) fichasJ4.add(rondas.sacarFicha(f4,3));
		
		
		System.out.println("FIN RONDA "+rondas.getRonda());
		System.out.println();
		//Fin ronda.
		
		rondas.avanzar(); // avanzo la siguiente ronda
		rondas.ponerFichasEnMesa(juego.getMazo());
		
		System.out.println("INICIO RONDA "+rondas.getRonda());
		System.out.println("Fichas de Ronda:  "+rondas.obtenerFichasEnMesa());
		System.out.println("Orden Ronda:  "+rondas.getOrdenJ());
		
		//Comienzo a elegir fichas
		
		f1=juego.getJugador(0).elegirFicha(rondas.obtenerFichasEnMesa(),39);
		f2=juego.getJugador(1).elegirFicha(rondas.obtenerFichasEnMesa(),38);
		f3=juego.getJugador(2).elegirFicha(rondas.obtenerFichasEnMesa(),36);
		f4=juego.getJugador(3).elegirFicha(rondas.obtenerFichasEnMesa(),37);	
		
		//Saco las fichas de la mesa que son válidas
		if(f1 != -1) fichasJ1.add(rondas.sacarFicha(f1,0));
		if(f2 != -1) fichasJ2.add(rondas.sacarFicha(f2,1));
		if(f3 != -1) fichasJ3.add(rondas.sacarFicha(f3,2));
		if(f4 != -1) fichasJ4.add(rondas.sacarFicha(f4,3));
		
		
		System.out.println("FIN RONDA "+rondas.getRonda());
		System.out.println();
		//Fin ronda.
		
		rondas.avanzar(); // avanzo la siguiente ronda
		rondas.ponerFichasEnMesa(juego.getMazo());
		
		System.out.println("INICIO RONDA "+rondas.getRonda());
		System.out.println("Fichas de Ronda:  "+rondas.obtenerFichasEnMesa());
		System.out.println("Orden Ronda:  "+rondas.getOrdenJ());
		
		//Comienzo a elegir fichas
		
		f1=juego.getJugador(0).elegirFicha(rondas.obtenerFichasEnMesa(),45);
		f2=juego.getJugador(1).elegirFicha(rondas.obtenerFichasEnMesa(),44);
		f3=juego.getJugador(2).elegirFicha(rondas.obtenerFichasEnMesa(),47);
		f4=juego.getJugador(3).elegirFicha(rondas.obtenerFichasEnMesa(),46);	
		
		//Saco las fichas de la mesa que son válidas
		if(f1 != -1) fichasJ1.add(rondas.sacarFicha(f1,0));
		if(f2 != -1) fichasJ2.add(rondas.sacarFicha(f2,1));
		if(f3 != -1) fichasJ3.add(rondas.sacarFicha(f3,2));
		if(f4 != -1) fichasJ4.add(rondas.sacarFicha(f4,3));
		
		
		System.out.println("FIN RONDA "+rondas.getRonda());
		System.out.println();
		//Fin ronda.
		
		rondas.avanzar(); // avanzo la siguiente ronda
		rondas.ponerFichasEnMesa(juego.getMazo());
		
	}

}
