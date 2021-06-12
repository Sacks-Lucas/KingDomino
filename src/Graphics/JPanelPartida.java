package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import juego.Ficha;
import juego.Jugador;
import juego.Ronda;
import juego.Tablero;
import juego.Terreno;

public class JPanelPartida extends JPanel{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1366;
	public static final int HEIGHT = 768;
	
	///////////////////////
	//constantes de Ronda: 
	///////////////////////
	private Color[] c = {Color.BLUE,Color.YELLOW,Color.RED,Color.RED,Color.GREEN};
	
	public static final int X_OFFSET_STR_ID_FICHA = 55;
	public static final int Y_OFFSET_STR_ID_FICHA = 141;
	
	private Ronda r;
	private List <Jugador> jugadores;
	private int f = -1;
	private Jugador jugador;
	public JPanelPartida(Ronda ronda) {
		this.r = ronda;
		this.jugadores = r.getOrdenJ();
		this.jugador = r.getOrdenJ().get(0);
		setLayout(null);
		JButton btn = new JButton ("Rotar");
		btn.setBounds(966, 667, 100, 30);
		add(btn);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(f != -1) {
					r.obtenerFichasEnMesa().get(f).rotarFicha();
				}
			}
		});
		
		JButton btn2 = new JButton ("Rotar terreno");
		btn2.setBounds(1133, 667, 100, 30);
		add(btn2);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(f != -1) {
					r.obtenerFichasEnMesa().get(f).rotarTerreno();
				}
			}
		});
		addMouseListener((MouseListener) new MouseAdapter() {
			private int siguienteJugador;

			@Override
			public void mousePressed(MouseEvent me) {
				super.mouseClicked(me);
				Point point = me.getPoint();
				System.out.print("Click en: [" + (point.x ) + ", ");
				System.out.println(point.y  + "]");
				
				if(f == -1) {
					f= fichaSeleccionada(point.x,point.y);
					System.out.println("seleccione la ficha "+f);
				}else {
					if(fichaSeleccionada(point.x,point.y) == -1) {
						// selecciono un lugar para poner la ficha seleccionada anteriormente!
						int x = convertirCoordAMatriz(point.x);
						int y = convertirCoordAMatriz(point.y);
						Ficha fichaElegida = r.obtenerFichasEnMesa().get(f);
						int offset_x = fichaElegida.getX() == fichaElegida.getX1()?0:1;
						int offset_y = fichaElegida.getY() == fichaElegida.getY1()?0:1;
						if(x >= 0 && x <=Tablero.TAM_TABLERO && y>=0 && y<=Tablero.TAM_TABLERO) {
							boolean pudoPoner = jugador.agregarFichaTablero(r.obtenerFichasEnMesa().get(f), x, y, x+offset_x, y+offset_y);
							if(pudoPoner) {
								r.sacarFicha(f,siguienteJugador);
								siguienteJugador++;
								if(siguienteJugador > 3) {
									siguienteJugador = 0;
									r.avanzar();
								}
								jugador = jugadores.get(siguienteJugador);
							}
						}
						f=-1;
					}
				}
			}
		});
	}
	protected int convertirCoordAMatriz(int x) {
		return (x-5)/Ficha.TAM_TERRENO;
	}
	public int fichaSeleccionada(int x,int y) {
		int i=0;
		for (Ficha f :r.obtenerFichasEnMesa()) {
			
			if(f.getX() == f.getX1()) {
				if(x <= (f.getX() + Ficha.TAM_TERRENO)  && x >= f.getX() && y >= f.getY() && y <=(Ficha.ANCHO_FICHA+f.getY())) {
					return i;
				}
				
			}
			if(f.getY() == f.getY1()){
				if(x <= (f.getX() + Ficha.ANCHO_FICHA)  && x >= f.getX() && y >= f.getY() && y <= (Ficha.ALTO_FICHA + f.getY())) {
					return i; 
				}	
			}
			i++;
		}
		return -1;
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
		int i =0;
		for (Jugador j : jugadores) {
			dibujarTablero(g,j);
			g.drawString(j.getColor()+" - "+j.getPosicion(), 1194,37+i);
			i+=20;
		}
	}
	
	private void dibujarTablero(Graphics2D g,Jugador j) {
		int aux_ini_x = 5;
		int aux_ini_y = 5;
		for (int i = 0; i < Tablero.TAM_TABLERO; i++) {
			for (int k = 0; k < Tablero.TAM_TABLERO; k++) {
				Terreno  t = j.getTablero().obtenerTerreno(i,k);
				if(t != null) {
					if(t.getTipo() != -1) {
						g.setColor(c[t.getTipo()]);
					}else {
						g.setColor(Color.black);
					}
					g.fillRect(aux_ini_x+i*Ficha.TAM_TERRENO,aux_ini_y+k*Ficha.TAM_TERRENO, Ficha.TAM_TERRENO, Ficha.TAM_TERRENO);
				}
			}
		}
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
			
			g.setColor(c[ficha.getTipoTerrenoIzq().getTipo()]);
			g.fillRect(x0, y0, Ficha.TAM_TERRENO, Ficha.TAM_TERRENO);
			g.setColor(c[ficha.getTipoTerrenoDer().getTipo()]);
			
			g.fillRect(ficha.getX1(),ficha.getY1(), Ficha.TAM_TERRENO, Ficha.TAM_TERRENO);
			g.drawString(""+ficha.getCode(), x1, y1);	
		}
		g.setColor(ini);
	}
	
}
