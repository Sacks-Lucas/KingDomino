package graphics;
import javax.swing.JFrame;

import juego.App;
import juego.Ronda;

public class FrameJuego extends JFrame implements Runnable{

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
	private App juego;
	private int cantJugadores=0;
	private int codigoPartida;
	public FrameJuego(int cantJugadores, int codigo) {
		this.cantJugadores = cantJugadores;
		this.codigoPartida = codigo;
		juego= new App(cantJugadores);

	}
	public void iniciarJuego() {

		JPanelPartida = new JPanelPartida(new Ronda(juego.getJugadores(),juego.getMazo()));
		add(JPanelPartida);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setFocusable(true);
		is_running = true;
		run();
	}
	public void terminarJuego() {
		is_running = false;
	}
	@Override
	public void run() {
		long next_game_tick = System.currentTimeMillis();
		long next_game_frame = System.currentTimeMillis();
		long next_frame_calc = System.currentTimeMillis();
		int frames = 0;
		while (is_running) {
			if (System.currentTimeMillis() > next_game_tick) {
				loops++;
				next_game_tick += SKIP_TICKS;
//				update();
			}
			if (System.currentTimeMillis() > next_game_frame) {
				frames++;
				next_game_frame += SKIP_FRAMES;
				display();
				if(juego.juegoFinalizado()) {
					terminarJuego();
				}
			}
			if (System.currentTimeMillis() > next_frame_calc) {
				fps = frames;
				next_frame_calc += SECOND;
				frames = 0;
			}
		}
		
		setFocusable(false);
		new VentanaJuegoFinalizado(this,juego.getGanador());
	}
	public void display() {
		JPanelPartida.repaint();
	}
	public static void main(String[] args) {
		int jugadores= 4;
		FrameJuego g = new FrameJuego(jugadores,1);
		g.iniciarJuego();
	}
	public void jugarDeNuevo() {
		this.juego = new App(cantJugadores);
		iniciarJuego();	
	}
	public App getApp() {
		// TODO Auto-generated method stub
		return juego;
	}
	
}
