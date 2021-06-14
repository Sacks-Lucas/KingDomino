package juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Ronda implements Drawable{
	private List<Ficha> fichasEnMesa = null;
	private int nroRonda = 0;
	private ArrayList<Jugador> ordenJugadores = null;
	private Mazo mazo;
	private int jugadorActual=0;

	
	//atributos para utilización de Graficos 
	public static final int X_STR_RONDA = 633;
	public static final int Y_STR_RONDA = 24;
	public static final int X0_FICHAS_MESA = 923;
	public static final int Y0_FICHAS_MESA = 563;
	public static final int GAP_ENTRE_FICHAS = 30;
	
	public Ronda(ArrayList<Jugador> jug, Mazo mazo) {
		this.mazo = mazo;
		int cantJugadores = jug.size();
		fichasEnMesa = new ArrayList<Ficha>(cantJugadores);

		this.nroRonda = 1;
		this.ordenJugadores = new ArrayList<Jugador>(cantJugadores);
		for (int i = 0; i < cantJugadores; i++) {
			this.ordenJugadores.add(jug.get(i));
		}
		ponerFichasEnMesa();
		fichasEnMesa.sort(null);
	}

	public void siguienteRonda() {
		nroRonda++;
		fichasEnMesa.clear();
		ordenJugadores.sort(null);
		ponerFichasEnMesa();
	}

	public int getRonda() {
		return nroRonda;
	}

	public List<Ficha> obtenerFichasEnMesa() {
		return this.fichasEnMesa;
	}

	public Ficha sacarFicha(int indexFicha, int indexJugador) {
		ordenJugadores.get(indexJugador).setPosicion(fichasEnMesa.get(indexFicha).getCode());
		return fichasEnMesa.remove(indexFicha);
	}

	public List<Jugador> getOrdenJ() {
		return ordenJugadores;
	}

	public void ponerFichasEnMesa() {
		int x0 = X0_FICHAS_MESA;
		int y0 = Y0_FICHAS_MESA;
		for (int i = 0; i < ordenJugadores.size(); i++) {
			Ficha f = mazo.sacarFicha();
			fichasEnMesa.add(f);
		}
		fichasEnMesa.sort(null);
		
		for (Ficha ficha : fichasEnMesa) {
			ficha.setX(x0);
			ficha.setY(y0);
			x0 += Ficha.ANCHO_FICHA + GAP_ENTRE_FICHAS;
		}
		
	}

	@Override
	public void draw(Graphics2D g) {
		Font f = g.getFont();
		
		g.setFont(new Font("TimesRoman", Font.BOLD, 16));
		g.drawString("Ronda "+this.nroRonda, X_STR_RONDA, Y_STR_RONDA);		
		Color ini = g.getColor();
	
		for (Ficha ficha : obtenerFichasEnMesa()) {
			ficha.draw(g);
		}
		
		g.setColor(ini);
		g.setFont(f);	
	}
	public Jugador siguienteTurno(int f) {
		obtenerFichasEnMesa().get(f).deseleccionar();
		sacarFicha(f,jugadorActual);
		this.jugadorActual++;
		if( jugadorActual > ordenJugadores.size()-1) {
			siguienteRonda();
			jugadorActual=0;
		}
		return this.ordenJugadores.get(jugadorActual);
	}
	
	public Ficha obtenerFichaSeleccionada(int f) {
		return obtenerFichasEnMesa().get(f);
	}
}
