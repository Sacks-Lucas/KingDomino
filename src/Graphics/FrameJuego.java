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
	private App juego= new App(4);
	

	public FrameJuego() {
		
		JPanelPartida = new JPanelPartida(new Ronda(juego.getJugadores(),juego.getMazo()));
		add(JPanelPartida);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setFocusable(true);
		
		
	}
	public void iniciarJuego() {
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
				update();
			}
			if (System.currentTimeMillis() > next_game_frame) {
				frames++;
				next_game_frame += SKIP_FRAMES;
				display();
			}
			if (System.currentTimeMillis() > next_frame_calc) {
				fps = frames;
				next_frame_calc += SECOND;
				frames = 0;
			}
		}
	}
	public void display() {
		JPanelPartida.repaint();
	}

	private void update() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		FrameJuego g = new FrameJuego();
		g.iniciarJuego();
		
	}

	
}