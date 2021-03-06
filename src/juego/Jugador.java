package juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.List;

public class Jugador implements Comparable<Jugador>,Drawable {
	private int codJugador;


	private String color;
	private Tablero tablero;
	private int posicion;
	private int puntaje=0;
	private boolean turnoJugador = false;
	
	public Jugador(String color, Tablero tablero2,int cod) {
		this.codJugador = cod;
		this.color = color;
		this.tablero = tablero2;
	}
	
	public int getCodJugador() {
		return codJugador;
	}

	public int elegirFicha(List<Ficha> fichasMesa, int x, int y) {
		
		int i=0;
		for (Ficha f : fichasMesa) {
			
			if(f.getX() == f.getX1()) {
				if(x <= (f.getX() + Ficha.TAM_TERRENO)  && x >= f.getX() && y >= f.getY() && y <=(Ficha.ANCHO_FICHA+f.getY())) {
					f.seleccionar();
					return i;
				}
				
			}
			if(f.getY() == f.getY1()){
				if(x <= (f.getX() + Ficha.ANCHO_FICHA)  && x >= f.getX() && y >= f.getY() && y <= (Ficha.ALTO_FICHA + f.getY())) {
					f.seleccionar();
					return i; 
				}	
			}
			i++;
		}
		return -1;
	}

	@Override
	public String toString() {
		return color;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public String getColor() {

		return this.color;
	}

	@Override
	public int compareTo(Jugador o) {
		return posicion - o.posicion;
	}

	public boolean agregarFichaTablero(Ficha f, int x0, int y0,int x1, int y1) {
		return tablero.agregarFichaATablero(f, x0, y0, x1, y1);
	}

	public void rotarTerreno(Ficha f) {
		f.rotarTerreno();
	}

	public Tablero getTablero() {
		return this.tablero;
	}

	@Override
	public void draw(Graphics2D g) {
		Font f = g.getFont();
		if(turnoJugador) {
			g.setColor(Color.RED);
			g.setFont( new Font("Times new Roman",Font.BOLD,18));
		}
		g.drawString("Tablero: "+getColor(), getTablero().getX0_tablero(),getTablero().getY0_tablero()+15);
		g.setColor(Color.black);
		g.setFont(f);
		getTablero().draw(g);
	}

	public void deseleccionarFicha(List<Ficha> list, int f) {
		list.get(f).deseleccionar();
	}
	
	public void calcularPuntaje() {
		this.puntaje = this.tablero.sumarizar();
	}
	
	public int getPuntaje() {
		return puntaje;
	}

	public int extendTerreno() {
		return tablero.getExtension();
	}

	public void leTocaTurno() {
		this.turnoJugador = true;
	}

	public void terminaTurno() {
		this.turnoJugador = false;
	}

	public boolean tieneTurno() {
		
		return turnoJugador;
	}

	public void setTurno(boolean turnoJugador2) {
		this.turnoJugador = turnoJugador2;
	}

	public boolean esSuTurno() {
		// TODO Auto-generated method stub
		return this.turnoJugador;
	}
}
