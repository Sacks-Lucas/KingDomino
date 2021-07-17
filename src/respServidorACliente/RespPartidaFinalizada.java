package respServidorACliente;

import java.io.Serializable;

import cliente.HiloCliente;
import graphics.FrameJuego;
import graphics.JPanelPartida;
import juego.Ficha;
import juego.Jugador;

public class RespPartidaFinalizada implements Serializable,RespMensaje{

	boolean pudoPoner = false;
	private int ficha_x0;
	private int ficha_y0;
	private int ficha_x1;
	private int ficha_y1;
	private int fichaSel;
	private int codJugSigue;
	private int jugadorMovio;
	private int codPartida;
	private static final long serialVersionUID = 1L;
	public RespPartidaFinalizada(int jugadorMovio, boolean pudoPoner, int fichaSel, int ficha_x0, int ficha_y0, int ficha_x1, int ficha_y1) {
		this.pudoPoner = pudoPoner;
		this.fichaSel = fichaSel;
		this.ficha_x0 = ficha_x0;
		this.ficha_y0 = ficha_y0;
		this.ficha_x1 = ficha_x1;
		this.ficha_y1 = ficha_y1;
		this.jugadorMovio = jugadorMovio;
		this.codPartida = codPartida;
	}
	@Override
	public String realizarOperacion(HiloCliente hilo) {
		if(pudoPoner) {
//			System.out.println("Finaliza");
			FrameJuego frame = hilo.getInfoPartida();
			JPanelPartida partida = frame.getJPanelPartida();
			Jugador jugador = partida.getRonda().getJugador(this.jugadorMovio);
			Ficha fichaElegida = partida.getRonda().obtenerFichaSeleccionada(fichaSel);
			boolean pudoPoner = jugador.agregarFichaTablero(fichaElegida, ficha_x0, ficha_y0, ficha_x1, ficha_y1);
			Jugador j = partida.getRonda().siguienteTurno(fichaSel);
			if(partida.isEnded()) frame.terminarJuego();
		}
		return null;
	}

}
