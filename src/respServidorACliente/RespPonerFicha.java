package respServidorACliente;

import java.io.Serializable;

import cliente.HiloCliente;
import graphics.FrameJuego;
import graphics.JPanelPartida;
import juego.Ficha;
import juego.Jugador;

public class RespPonerFicha implements RespMensaje, Serializable{
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
	public RespPonerFicha(int jugadorMovio, int codJugSigue2, boolean pudoPoner, int fichaSel, int ficha_x0, int ficha_y0, int ficha_x1, int ficha_y1) {
		this.pudoPoner = pudoPoner;
		this.fichaSel = fichaSel;
		this.ficha_x0 = ficha_x0;
		this.ficha_y0 = ficha_y0;
		this.ficha_x1 = ficha_x1;
		this.ficha_y1 = ficha_y1;
		this.codJugSigue = codJugSigue2;
		this.jugadorMovio = jugadorMovio;
		this.codPartida = codPartida;
	}
	
	@Override
	public String realizarOperacion(HiloCliente hilo) {
		System.out.println("Pudo?  "+fichaSel+" - "+ficha_x0+" - "+ ficha_y0+" - "+ ficha_x1+" - "+ ficha_y1);
		if(pudoPoner) {
			System.out.println("pudo poner la ficha !");
			FrameJuego frame = hilo.getInfoPartida();
			JPanelPartida partida = frame.getJPanelPartida();
			Jugador jugador = partida.getRonda().getJugador(this.jugadorMovio);
			Ficha fichaElegida = partida.getRonda().obtenerFichaSeleccionada(fichaSel);
			boolean pudoPoner = jugador.agregarFichaTablero(fichaElegida, ficha_x0, ficha_y0, ficha_x1, ficha_y1);
			if(pudoPoner) {
				jugador.terminaTurno();
				Jugador j = partida.getRonda().siguienteTurno(fichaSel);
				if(j.getCodJugador() == codJugSigue){
					j.leTocaTurno();	
				}
				partida.syncRotacionFichas();
				this.pudoPoner = false;
			}
		}else {
			System.out.println("no pudo");
		}
		return null;
	}

}
