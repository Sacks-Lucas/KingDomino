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
	
	public static final int X_OFFSET_STR_ID_FICHA = 957 - Ronda.X0_FICHAS_MESA;
	public static final int Y_OFFSET_STR_ID_FICHA = 649 - Ronda.Y0_FICHAS_MESA;
	public static final int X0_TABLEROS = 0;
	public static final int Y0_TABLEROS = 0;
	private Ronda ronda;
	private List <Jugador> jugadores;
	private int idFichaSel = -1;
	private Jugador jugador;
	public JPanelPartida(Ronda ronda) {
		this.ronda = ronda;
		this.jugadores = ronda.getOrdenJ();
		this.jugador = ronda.getOrdenJ().get(0);
		setLayout(null);
		JButton btn = new JButton ("Rotar");
		btn.setBounds(966, 667, 100, 30);
		add(btn);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idFichaSel != -1) {
					ronda.obtenerFichasEnMesa().get(idFichaSel).rotarFicha();
				}
			}
		});
		
		JButton btn2 = new JButton ("Rotar terreno");
		btn2.setBounds(1133, 667, 120, 30);
		add(btn2);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idFichaSel != -1) {
					ronda.obtenerFichasEnMesa().get(idFichaSel).rotarTerreno();
				}
			}
		});
		addMouseListener((MouseListener) new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent me) {
				super.mouseClicked(me);
				Point point = me.getPoint();
				System.out.print("Click en: [" + (point.x ) + ", ");
				System.out.println(point.y  + "]");
				
				if(idFichaSel == -1) {
					idFichaSel= jugador.elegirFicha(ronda.obtenerFichasEnMesa(),point.x,point.y);
				}else {
					int aux_f = idFichaSel;
					idFichaSel = jugador.elegirFicha(ronda.obtenerFichasEnMesa(),point.x,point.y);
					if(idFichaSel == -1) {
						// selecciono un lugar para poner la ficha seleccionada anteriormente!
						idFichaSel = aux_f;
						int x = convertirCoordAMatriz(point.x,jugador.getTablero().getX0_tablero());
						int y = convertirCoordAMatriz(point.y,jugador.getTablero().getY0_tablero());
						Ficha fichaElegida = ronda.obtenerFichaSeleccionada(idFichaSel);
						int offset_x = fichaElegida.getX() == fichaElegida.getX1()?0:1;
						int offset_y = fichaElegida.getY() == fichaElegida.getY1()?0:1;
						boolean pudoPoner = jugador.agregarFichaTablero(fichaElegida, x, y, x+offset_x, y+offset_y);
						if(pudoPoner) {
							jugador = ronda.siguienteTurno(idFichaSel);
							idFichaSel=-1; // la ficha se deselecciona cuando se pudo poner en el tablero
						}

					}else if(aux_f != idFichaSel) {
						//Seleccionó otra ficha. Quito seleccion de ficha anterior
						jugador.deseleccionarFicha(ronda.obtenerFichasEnMesa(),aux_f); 
					}
				}
			}
		});
	}
	private int convertirCoordAMatriz(int x, int offsetTablero) {
		return (x-offsetTablero)/Ficha.TAM_TERRENO;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}
	@Override
	protected void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D) g1;
		ronda.draw(g);
		int i =0;
		g.drawString("Puntaje de jugadores:", 995,249); //Lista de jugadores 
		for (Jugador j : jugadores) {
			j.draw(g);
			g.drawString(j.getColor()+" = "+j.getPuntaje(), 1032,275+i); //Lista de jugadores 
			i+=20;
		}
	}	
}
