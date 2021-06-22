package juego;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

public class Jugador implements Comparable<Jugador>,Drawable {

	private String color;
	private Tablero tablero;
	private int posicion;
	private int puntaje=0;

	public Jugador(String color, Tablero tablero2) {

		this.color = color;
		this.tablero = tablero2;
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
		g.drawString("Tablero: "+getColor(), getTablero().getX0_tablero(),getTablero().getY0_tablero()-10);
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
}
