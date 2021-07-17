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

import cliente.Cliente;
import juego.Ficha;
import juego.Jugador;
import juego.Ronda;
import juego.Tablero;
import juego.Terreno;
import msjClienteAServidor.MsjDescartarFicha;
import msjClienteAServidor.MsjPonerFicha;
import msjClienteAServidor.MsjRotarFicha;

public class JPanelPartida extends JPanel{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1366;
	public static final int HEIGHT = 768;
	public static final int X_OFFSET_STR_ID_FICHA = 957 - Ronda.X0_FICHAS_MESA;
	public static final int Y_OFFSET_STR_ID_FICHA = 649 - Ronda.Y0_FICHAS_MESA;
	public static final int X0_TABLEROS = 0;
	public static final int Y0_TABLEROS = 0;
	private Ronda ronda;
	private List <Jugador> jugadores;
	private int idFichaSel = -1;
	private Jugador jugador;

	private int contRotacion=0;
	private Cliente cliente; 
	private int codigoPartida;
	private int codJug;
	
	public Jugador getJugador() {
		return jugador;
	}
	public JPanelPartida(Ronda ronda,Cliente clt, int codigoPartida1, int codJugador) {
		this.ronda = ronda;
		this.cliente = clt;
		this.codJug = codJugador;
		this.codigoPartida = codigoPartida1;
		this.jugadores = this.ronda.getOrdenJ();
		this.jugador = this.ronda.getOrdenJ().get(codJugador);
		setLayout(null);
		JButton btn = new JButton ("Rotar");
		btn.setBounds(966, 667, 100, 30);
		add(btn);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idFichaSel != -1 && jugador.esSuTurno()) {
					switch (contRotacion) {
						case 0: {
							cliente.enviarMsj(new MsjRotarFicha(idFichaSel,codigoPartida,true,false));
							break;
						}
						case 1: {
							cliente.enviarMsj(new MsjRotarFicha(idFichaSel,codigoPartida,true,true));
							break;
						}
						case 2: {
							cliente.enviarMsj(new MsjRotarFicha(idFichaSel,codigoPartida,true,false));
							break;
						}
						case 3: {
							cliente.enviarMsj(new MsjRotarFicha(idFichaSel,codigoPartida,true,true));
							contRotacion = -1;
							break;
						}	
					}
					contRotacion++;	
				}
			}
		});
		
		JButton btn2 = new JButton ("Descartar ficha");
		btn2.setBounds(972, 125, 150, 40);
		add(btn2);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idFichaSel != -1) {
					cliente.enviarMsj(new MsjDescartarFicha(idFichaSel,codigoPartida,jugador.getCodJugador()));
//					jugador = ronda.siguienteTurno(idFichaSel);
					idFichaSel=-1; // la ficha se deselecciona cuando se pudo poner en el tablero
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
				
				if(idFichaSel == -1 && jugador.tieneTurno()) {
					idFichaSel= jugador.elegirFicha(ronda.obtenerFichasEnMesa(),point.x,point.y);
				}else if(idFichaSel != -1 && jugador.tieneTurno()){
					int aux_f = idFichaSel;
					idFichaSel = jugador.elegirFicha(ronda.obtenerFichasEnMesa(),point.x,point.y);
					if(idFichaSel == -1) {
						// selecciono un lugar para poner la ficha seleccionada anteriormente!
						idFichaSel = aux_f;
						int y = convertirCoordAMatriz(point.x,jugador.getTablero().getX0_tablero());
						int x = convertirCoordAMatriz(point.y,jugador.getTablero().getY0_tablero());
						Ficha fichaElegida = ronda.obtenerFichaSeleccionada(idFichaSel);
						int offset_y = fichaElegida.getX() == fichaElegida.getX1()?0:1;
						int offset_x = fichaElegida.getY() == fichaElegida.getY1()?0:1;
						System.out.println("Valor enviado: 0("+x+";"+y+") - ("+(x+offset_x)+";"+(y+offset_y)+")");
						cliente.enviarMsj(new MsjPonerFicha(idFichaSel,x, y, x+offset_x, y+offset_y, jugador.getCodJugador(), codigoPartida));
						idFichaSel=-1;
//						boolean pudoPoner = jugador.agregarFichaTablero(fichaElegida, x, y, x+offset_x, y+offset_y);
//						if(pudoPoner) {
//							jugador.terminaTurno();
//							jugador = ronda.siguienteTurno(idFichaSel);
//							jugador.leTocaTurno();
//							idFichaSel=-1; // la ficha se deselecciona cuando se pudo poner en el tablero
//						}

					}else if(aux_f != idFichaSel) {
						//Seleccionó otra ficha. Quito seleccion de ficha anterior
						jugador.deseleccionarFicha(ronda.obtenerFichasEnMesa(),aux_f); 
					}
				}
			}
		});
	}
	
	public Ronda getRonda() {
		return ronda;
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
		Font f = g.getFont();
		ronda.draw(g);
		
		int i = 0;
		g.drawString("Puntaje de jugadores:", 995,249); //Lista de jugadores 
		if(jugador.esSuTurno()) {
			g.setColor(Color.RED);
			g.setFont( new Font("Times new Roman",Font.BOLD,20));
			g.drawString("Es tu turno !", 755,362);			
		}
		g.setColor(Color.BLACK);
		g.setFont(f);
		for (Jugador j : jugadores) {
			
			j.draw(g);
			if(jugador.equals(j)) {
				g.setColor(Color.RED);
				g.setFont( new Font("Times new Roman",Font.BOLD,15));
			}
			g.drawString(j.getColor()+" = "+j.getPuntaje(), 1032,275+i); //Lista de jugadores 
			g.setColor(Color.black);
			g.setFont(f);
			i+=20;
		}
	}
	public int getCodigo() {
		// TODO Auto-generated method stub
		return this.codigoPartida;
	}
	public void syncRotacionFichas() {
		this.contRotacion = 0;
	}
	public boolean isEnded() {
		return ronda.isTerminoPartida();
	}	
}
