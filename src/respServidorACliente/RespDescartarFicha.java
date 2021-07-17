package respServidorACliente;

import java.io.Serializable;

import cliente.HiloCliente;
import graphics.FrameJuego;
import graphics.JPanelPartida;
import juego.Jugador;

public class RespDescartarFicha implements RespMensaje,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int fichaDescartada;
	private int jugadorConTurno;
	private int jugadorJugo;

	public RespDescartarFicha(int codJugador, int codJugSigue, int idFi) {
		this.jugadorJugo = codJugador;
		this.jugadorConTurno = codJugSigue;
		this.fichaDescartada = idFi;
	}

	@Override
	public String realizarOperacion(HiloCliente hilo) {
		FrameJuego frame = hilo.getInfoPartida();
		JPanelPartida partida = frame.getJPanelPartida();
		Jugador jugador = partida.getRonda().getJugador(this.jugadorJugo);
		jugador.terminaTurno();
		Jugador j = partida.getRonda().siguienteTurno(fichaDescartada);
		if(j.getCodJugador() == jugadorConTurno){
			j.leTocaTurno();	
		}
		partida.syncRotacionFichas();
		return null;
	}

}
