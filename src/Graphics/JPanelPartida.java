package Graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import juego.Ficha;
import juego.Jugador;
import juego.Ronda;

public class JPanelPartida extends JPanel{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	///////////////////////
	//constantes de Ronda: 
	///////////////////////
	private Color[] c = {Color.BLUE,Color.BLACK,Color.YELLOW,Color.RED,Color.CYAN};
	
	public static final int X_OFFSET_STR_ID_FICHA = 55;
	public static final int Y_OFFSET_STR_ID_FICHA = 97;
	
	private Ronda r;
	private List <Jugador> jugadores;
	
	public JPanelPartida(Ronda r) {
		this.r = r;
		this.jugadores = r.getOrdenJ();
		addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				super.mouseClicked(me);
				Point point = me.getPoint();
				System.out.print("Click en: [" + (point.x ) + ", ");
				System.out.println(point.y  + "]");
				int f= fichaSeleccionada(point.x,point.y);
				if(f != -1) {
					System.out.println("seleccione la ficha "+f);
				}
			}

			private int fichaSeleccionada(int x,int y) {
				int s = -1,i=0;
				for (Ficha f :r.obtenerFichasEnMesa()) {
					if(f.getX() <= x && x <= f.getX1() && f.getY() <=y && y <= f.getY1()) {
						return i;
					}
					i++;
				}
				return s;
			}
		});
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}
	@Override
	protected void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D) g1;
		
		dibujarRonda(g);
	}
	
	private void dibujarRonda(Graphics2D g) {
		
		Font f = g.getFont();
		
		g.setFont(new Font("TimesRoman", Font.BOLD, 16));
		g.drawString("Ronda "+r.getRonda(), Ronda.X_STR_RONDA, Ronda.Y_STR_RONDA);
		dibujarFichasEnMesa(g);
		
		g.setFont(f);
		
	}
	private void dibujarFichasEnMesa(Graphics2D g) {


		Color ini = g.getColor();
		
		for (Ficha ficha : r.obtenerFichasEnMesa()) {
			int x0 = ficha.getX();
			int y0 = ficha.getY();
			int x1 = x0+X_OFFSET_STR_ID_FICHA;
			int y1 = y0+Y_OFFSET_STR_ID_FICHA;
			
			g.setColor(c[1]);
			g.fillRect(x0, y0, Ficha.TAM_TERRENO, Ficha.TAM_TERRENO);
			x0+=Ficha.TAM_TERRENO;
			g.setColor(c[3]);
			g.fillRect(x0, y0, Ficha.TAM_TERRENO, Ficha.TAM_TERRENO);
			
			
			
			g.drawString(""+ficha.getCode(), x1, y1);
			
			
		}
		g.setColor(ini);
	}
	
}
