package msjClienteAServidor;

import java.io.Serializable;

import graphics.FrameJuego;
import graphics.JPanelPartida;
import juego.Jugador;
import respServidorACliente.RespDescartarFicha;
import servidor.HiloServidor;

public class MsjDescartarFicha implements Serializable,Mensaje{
	private int idFi;
	private int codPartida;
	private int codJugador;
	public MsjDescartarFicha(int idFichaSel, int codigoPartida, int codJugador) {
		this.idFi = idFichaSel;
		this.codJugador = codJugador;
		this.codPartida= codigoPartida;
	}
	@Override
	public String realizarOperacion(HiloServidor hilo) {
		FrameJuego frame = hilo.getInfoPartida(this.codPartida);
		JPanelPartida partida = frame.getJPanelPartida();
		Jugador jugador = partida.getRonda().getJugador(this.codJugador);
		jugador.terminaTurno();
		Jugador j = partida.getRonda().siguienteTurno(idFi);
		j.leTocaTurno();	
		int codJugSigue = j.getCodJugador();
		
		
		hilo.broadcastPartida(new RespDescartarFicha(this.codJugador,codJugSigue,this.idFi),hilo.getClientes_conectados());
		
		return null;
	}

}
