package msjClienteAServidor;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import graphics.FrameJuego;
import juego.Ficha;
import juego.Mazo;
import respServidorACliente.RespCrearPartida;
import respServidorACliente.RespMensaje;
import servidor.HiloServidor;
import servidor.InfoPartida;

public class MsjCrearPartida implements Mensaje,Serializable{
	private int cantJugadores=0;
	private static final long serialVersionUID = 1L;
	
	//Para servidor
	public  MsjCrearPartida(int cantJugadores) {
		this.cantJugadores = cantJugadores;
	}	
	
	@Override
	public String realizarOperacion(HiloServidor hilo) {
		int codigo = hilo.obtenerCodigoPartida();
		FrameJuego f = new FrameJuego(cantJugadores,codigo,null);
		f.getApp().crearMazo();
		f.crearJPanelPartida();
		hilo.guardarInfoPartida(new InfoPartida(f,hilo.getClientes_conectados()));
		List<Integer> listaFichasMazo = new LinkedList<Integer>();
		Mazo m  = f.getApp().getMazo();
		
		for (Ficha ficha: m.getFichas() ) {
			//System.out.println("Fichas creadas: "+ ficha.getCode());
			listaFichasMazo.add(ficha.getCode());
		}
		RespMensaje msj = new RespCrearPartida(cantJugadores,codigo,listaFichasMazo);
		hilo.broadcastPartida(msj,hilo.getClientes_conectados());
		return null;
	}

}
