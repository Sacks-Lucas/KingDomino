package msjClienteAServidor;

import java.awt.Frame;
import java.io.Serializable;

import javax.swing.JPanel;

import graphics.FrameJuego;
import graphics.JPanelPartida;
import juego.App;
import juego.Ficha;
import juego.Jugador;
import respServidorACliente.RespMensaje;
import respServidorACliente.RespPonerFicha;
import servidor.HiloServidor;

public class MsjPonerFicha implements Mensaje, Serializable{
	private Integer ficha_x0;
	private Integer ficha_y0;
	private Integer ficha_x1;
	private Integer ficha_y1;
	private Integer jugadorMueve;
	private Integer codPartida;
	private Integer fichaSel;
	private static final long serialVersionUID = 1L;
	
	public MsjPonerFicha(int fichaSel,int ficha_x0, int ficha_y0, int ficha_x1, int ficha_y1, int jugadorMueve, int codPartida) {
		this.fichaSel = fichaSel;
		this.ficha_x0 = ficha_x0;
		this.ficha_y0 = ficha_y0;
		this.ficha_x1 = ficha_x1;
		this.ficha_y1 = ficha_y1;
		this.jugadorMueve = jugadorMueve;
		this.codPartida = codPartida;
	}

	

	@Override
	public String realizarOperacion(HiloServidor hilo) {
		
		FrameJuego frame = hilo.getInfoPartida();
		
		JPanelPartida partida = frame.getJPanelPartida();
		Jugador jugador = partida.getJugador();
		Ficha fichaElegida = partida.getRonda().obtenerFichaSeleccionada(fichaSel);
		boolean pudoPoner = jugador.agregarFichaTablero(fichaElegida, ficha_x0, ficha_y0, ficha_x1, ficha_y1);
		RespMensaje RespPonerFicha =  new RespPonerFicha(false,fichaSel, ficha_x0, ficha_y0, ficha_x1, ficha_y1);
		
		if(pudoPoner) {
			System.out.println("Pudo poner ! "+fichaSel+" - "+ficha_x0+" - "+ ficha_y0+" - "+ ficha_x1+" - "+ ficha_y1);
			jugador.terminaTurno();
			jugador = partida.getRonda().siguienteTurno(fichaSel);
			jugador.leTocaTurno();	
			RespPonerFicha = new RespPonerFicha(true,fichaSel, ficha_x0, ficha_y0, ficha_x1, ficha_y1);
		}
		
		hilo.broadcastPartida(RespPonerFicha,hilo.getClientes_conectados());
		return null;
	}
}
