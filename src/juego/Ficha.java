package juego;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ficha implements Comparable<Ficha>, Drawable{
	private Terreno izquierdo;
	private Terreno derecho;
	private int numero;
	private int x;
	private int y;
	private int x1;
	private int y1;
	private boolean estaSeleccionada=false;
	public static final int X_OFFSET_STR_ID_FICHA = 957 - Ronda.X0_FICHAS_MESA;
	public static final int Y_OFFSET_STR_ID_FICHA = 649 - Ronda.Y0_FICHAS_MESA;
	public static final int ANCHO_FICHA = 50;
	public static final int ALTO_FICHA = 25;
	public static final int TAM_TERRENO = ANCHO_FICHA/2;
	
	public Ficha(Terreno izquierdo, Terreno derecho, int numero) {
		
		this.izquierdo = izquierdo;
		this.derecho = derecho;
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Ficha numero=" + numero;
	}

	@Override
	public int compareTo(Ficha o) {
		return this.numero - o.numero;
	}

	public int getCode() {
		return this.numero;
	}

	public Terreno getIzquierdo() {
		return izquierdo;
	}

	public Terreno getDerecho() {
		return derecho;
	}

	public void rotarTerreno() {
		Terreno a = izquierdo.clone();
		izquierdo = derecho;
		derecho = a;
	}
	
	public void rotarFicha() {
		if(this.x != this.x1) {
			this.x1 = this.x;
			y1=y+TAM_TERRENO;
		}else {
			x1=x+TAM_TERRENO;
			y1=y;
		}
	}

	public Terreno getTipoTerrenoIzq() {
		return this.izquierdo;
	}

	public Terreno getTipoTerrenoDer() {
		return this.derecho;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numero;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ficha other = (Ficha) obj;
		if (numero != other.numero)
			return false;
		return true;
	}

	public void setX(int x0) {
		this.x = x0;
		this.x1 = x0+TAM_TERRENO;
	}

	public void setY(int y0) {
		this.y = y0;
		this.y1 = y0;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getX1() {
		return this.x1;
	}

	public int getY1() {
		return this.y1;
	}

	public void setX1(int x2) {
		this.x1=x2;
	}

	public void setY1(int i) {
		this.y1 = i;
	}

	@Override
	public void draw(Graphics2D g) {
		
		int x1 = x+X_OFFSET_STR_ID_FICHA;
		int y1 = y+Y_OFFSET_STR_ID_FICHA;
		
		g.setColor(getTipoTerrenoIzq().getColor());
		g.fillRect(x, y, Ficha.TAM_TERRENO, Ficha.TAM_TERRENO);
		g.setColor(getTipoTerrenoDer().getColor());
		
		g.fillRect(this.x1,this.y1, Ficha.TAM_TERRENO, Ficha.TAM_TERRENO);
		if(estaSeleccionada) {
			Color aux = g.getColor();
			g.setColor(Color.BLACK);
			g.drawRoundRect(x, y, Ficha.TAM_TERRENO, Ficha.TAM_TERRENO, 5, 5);
			g.drawRoundRect(this.x1,this.y1, Ficha.TAM_TERRENO, Ficha.TAM_TERRENO, 5, 5);
			g.setColor(aux);	
		}
		g.setColor(Color.BLACK);
		g.drawString(""+getCode(), x1, y1);
		
	}

	public void seleccionar() {
		this.estaSeleccionada=!this.estaSeleccionada;
	}

	public void deseleccionar() {
		this.estaSeleccionada = false;
	}

	public void descartarFicha() {
		
	}

	
	

}
