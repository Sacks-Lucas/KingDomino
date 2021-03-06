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
	private String ganador="";
	private boolean terminoPartida= true;
	
	//atributos para utilización de Graficos 
	public static final int X_STR_RONDA = 633;
	public static final int Y_STR_RONDA = 24;
	public static final int X0_FICHAS_MESA = 923;
	public static final int Y0_FICHAS_MESA = 563;
	public static final int GAP_ENTRE_FICHAS = 30;
	public String getGanador() {
		return ganador;
	}
	public Mazo getMazo() {
		return mazo;
	}
	public Ronda(ArrayList<Jugador> jug, Mazo mazo, boolean turnoJugador, int codJug) {
		this.mazo = mazo;
		int cantJugadores = jug.size();
		fichasEnMesa = new ArrayList<Ficha>(cantJugadores);

		this.nroRonda = 1;
		this.ordenJugadores = new ArrayList<Jugador>(cantJugadores);
		for (int i = 0; i < cantJugadores; i++) {
			this.ordenJugadores.add(jug.get(i));
		}
		ponerFichasEnMesa();
		this.ordenJugadores.get(codJug).setTurno(turnoJugador);
		fichasEnMesa.sort(null);
	}

	public boolean siguienteRonda() {
		nroRonda++;
		fichasEnMesa.clear();
		ordenJugadores.sort(null);
		actualizarPuntajes();
		return ponerFichasEnMesa();
	}

	private void actualizarPuntajes() {
		int puntajeMax = Integer.MIN_VALUE;
		List<Jugador> jugadoresEmpatados = new ArrayList<Jugador>();
		Jugador j_aux=null;
		for (Jugador j : this.ordenJugadores) {
			j.calcularPuntaje();
			if(puntajeMax < j.getPuntaje()) {
				j_aux = j;
				puntajeMax = j.getPuntaje();
				this.ganador = j.getColor();
				jugadoresEmpatados.removeAll(jugadoresEmpatados);
			}else if (puntajeMax == j.getPuntaje()) {
				jugadoresEmpatados.add(j);
			}
		}
		
		int extension = j_aux.extendTerreno();
		for (Jugador jugador : jugadoresEmpatados) {
			int aux = jugador.extendTerreno();
			if (extension < aux) {
				extension = aux;
				this.ganador = jugador.getColor();
			}else if (extension == aux ) {
				this.ganador+=", "+jugador.getColor();
			}
		}
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

	public boolean ponerFichasEnMesa() {
		int x0 = X0_FICHAS_MESA;
		int y0 = Y0_FICHAS_MESA;
		if(mazo.getCantFichas() == 0 ) return false;
		for (int i = 0; i < ordenJugadores.size(); i++) {
			Ficha f = mazo.sacarFicha();
			fichasEnMesa.add(f);
		}
		fichasEnMesa.sort(null);
		
		for (Ficha ficha : fichasEnMesa) {
			ficha.setX(x0);
			ficha.setY(y0);
			x0 += Ficha.ANCHO_FICHA + GAP_ENTRE_FICHAS;
//			System.out.println("Existen fichas: "+ficha.getCode());
		}
		return true;
		
	}

	@Override
	public void draw(Graphics2D g) {
		Font f = g.getFont();
		
		g.setFont(new Font("TimesRoman", Font.BOLD, 16));
		g.drawString("Ronda "+this.nroRonda, X_STR_RONDA, Y_STR_RONDA);		
		if(this.jugadorActual < ordenJugadores.size())g.drawString("Turno del jugador: \n"+ordenJugadores.get(this.jugadorActual).getColor(), 975, 218);
		
		
		for (Ficha ficha : obtenerFichasEnMesa()) {
			ficha.draw(g);
		}
		
		g.setColor(Color.BLACK);
		g.setFont(f);	
	}
	
	public Jugador siguienteTurno(int f) {
		obtenerFichasEnMesa().get(f).deseleccionar();
		sacarFicha(f,jugadorActual);
		this.jugadorActual++;
		if( jugadorActual > ordenJugadores.size()-1) {
			this.terminoPartida = siguienteRonda();
			jugadorActual=0;
		}
//		System.out.println("Termino partida: "+this.terminoPartida);
		return this.ordenJugadores.get(jugadorActual);
	}
	
	public boolean isTerminoPartida() {
//		System.out.println(""+this.getMazo().getCantFichas()+" - "+this.fichasEnMesa.size());
		return this.getMazo().getCantFichas() == 0 && this.fichasEnMesa.size() == 0;
	}

	public Ficha obtenerFichaSeleccionada(int f) {
		return obtenerFichasEnMesa().get(f);
	}

	public Jugador getJugador(Integer jugadorMueve) {
		for (Jugador j : this.ordenJugadores) {
			if(j.getCodJugador() == jugadorMueve) {
				return j;
			}
		}
		return null;
	}
}
