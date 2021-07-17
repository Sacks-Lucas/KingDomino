package graphics;

import juego.App;

public class MotorGrafico extends Thread{
	private boolean is_running=false;
	private int loops=0;
	private int fps=0;
	private final int SECOND = 1000;
	private final int FRAMES_PER_SECOND = 60;
	private final int SKIP_FRAMES = SECOND / FRAMES_PER_SECOND;
	private final int TICKS_PER_SECOND = 1000;
	private final int SKIP_TICKS = SECOND / TICKS_PER_SECOND;
	private  JPanelPartida jPanelPartida;
	private App juego;
	private FrameJuego frame;
	public MotorGrafico(App juego,JPanelPartida panelPartida,FrameJuego frame) {
		super("Mi Motor");
		this.jPanelPartida = panelPartida;
		this.juego = juego;
		this.frame = frame;
		this.is_running = true;
	}
	public void terminarJuego() {
		is_running = false;
	}
	public void display() {
		jPanelPartida.repaint();
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
			}
			if (System.currentTimeMillis() > next_frame_calc) {
				fps = frames;
				next_frame_calc += SECOND;
				frames = 0;
			}
		}
		
		frame.setFocusable(false);
		new VentanaJuegoFinalizado(this.frame,"");
	}
	
}
