package graphics;
import javax.swing.JFrame;

import cliente.Cliente;
import juego.App;
import juego.Ronda;

public class FrameJuego extends JFrame{

	private static final long serialVersionUID = 1L;
	private boolean is_running=false;
	private int loops=0;
	private int fps=0;
	private final int SECOND = 1000;
	private final int FRAMES_PER_SECOND = 60;
	private final int SKIP_FRAMES = SECOND / FRAMES_PER_SECOND;
	private final int TICKS_PER_SECOND = 1000;
	private final int SKIP_TICKS = SECOND / TICKS_PER_SECOND;
	private JPanelPartida JPanelPartida;
	private MotorGrafico motor;
	private App juego;
	private int cantJugadores=0;
	private int codigoPartida;
	private Cliente clt;
	public FrameJuego(int cantJugadores, int codigo,Cliente clt) {
		super("myThread");
		this.cantJugadores = cantJugadores;
		this.codigoPartida = codigo;
		this.clt = clt;
		juego= new App(cantJugadores);
	}
	
	public void crearJPanelPartida() {
		JPanelPartida = new JPanelPartida(new Ronda(juego.getJugadores(),juego.getMazo()),this.clt,codigoPartida);
	}
	public void iniciarJuego() {
		JPanelPartida = new JPanelPartida(new Ronda(juego.getJugadores(),juego.getMazo()),this.clt,codigoPartida);
		motor = new MotorGrafico(juego, JPanelPartida, this);
		add(JPanelPartida);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setFocusable(true);
		is_running = true;
		motor.start();
	}
	public void terminarJuego() {
		motor.terminarJuego();
	}

	public void jugarDeNuevo() {
		this.juego = new App(cantJugadores);
		iniciarJuego();	
	}
	public App getApp() {
		// TODO Auto-generated method stub
		return juego;
	}
	
	public JPanelPartida getJPanelPartida() {
		return JPanelPartida;
	}
	
}
