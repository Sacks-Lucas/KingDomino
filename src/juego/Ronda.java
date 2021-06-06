package juego;

import java.util.ArrayList;
import java.util.List;

public class Ronda {
	private List<Ficha> fichasEnMesa = null;
	private int nroRonda = 0;
	private ArrayList<Jugador> ordenJugadores = null;

	
	//atributos para utilización de Graficos 
	public static final int X_STR_RONDA = 381;
	public static final int Y_STR_RONDA = 15;
	public static final int X0_FICHAS_MESA = 125;
	public static final int Y0_FICHAS_MESA = 300;
	public static final int GAP_ENTRE_FICHAS = 30;
	
	public Ronda(ArrayList<Jugador> jug, Mazo mazo) {
		int cantJugadores = jug.size();
		fichasEnMesa = new ArrayList<Ficha>(cantJugadores);

		this.nroRonda = 1;
		this.ordenJugadores = new ArrayList<Jugador>(cantJugadores);
		for (int i = 0; i < cantJugadores; i++) {
			this.ordenJugadores.add(jug.get(i));
		}
		this.ponerFichasEnMesa(mazo);
		fichasEnMesa.sort(null);
	}

	public void avanzar() {
		nroRonda++;

		fichasEnMesa.clear();
		ordenJugadores.sort(null);

	}

	public int getRonda() {
		return nroRonda;
	}

	public List<Ficha> obtenerFichasEnMesa() {
		return this.fichasEnMesa;
	}

	public Ficha sacarFicha(int indexFicha, int indexJugador) {
		ordenJugadores.get(indexJugador).setPosicion(indexFicha);
		return fichasEnMesa.get(indexFicha);

	}

	public List<Jugador> getOrdenJ() {
		return ordenJugadores;
	}

	public void ponerFichasEnMesa(Mazo mazo) {
		int x0 = X0_FICHAS_MESA;
		int y0 = Y0_FICHAS_MESA;
		for (int i = 0; i < ordenJugadores.size(); i++) {
			Ficha f = mazo.sacarFicha();
			f.setX(x0);
			f.setY(y0);
			fichasEnMesa.add(f);
		}
		fichasEnMesa.sort(null);
		
		for (Ficha ficha : fichasEnMesa) {
			ficha.setX(x0);
			ficha.setY(y0);
			x0 += Ficha.ANCHO_FICHA + GAP_ENTRE_FICHAS;
		}
		
	}
}
