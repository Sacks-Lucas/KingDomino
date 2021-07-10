package respServidorACliente;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import cliente.HiloCliente;
import graphics.FrameJuego;
import servidor.InfoPartida;

public class RespCrearPartida implements RespMensaje,Serializable{
	private int cantJugadores=0;
	private int codigoPartida;
	private List<Integer> listaFichasMazo = new LinkedList<Integer>();
	private static final long serialVersionUID = 1L;

	//Respuesta para Cliente
	public  RespCrearPartida(int cantJugadores, int codigo,List<Integer> f) {
		this.cantJugadores = cantJugadores;
		this.codigoPartida = codigo;
		this.listaFichasMazo.addAll(f);
	}

	@Override
	public String realizarOperacion(HiloCliente hilo) {
		
		FrameJuego f = new FrameJuego(cantJugadores,this.codigoPartida,hilo.getCliente());
		f.getApp().crearMazo(this.listaFichasMazo);
		f.iniciarJuego();
		hilo.guardarInfoPartida(new InfoPartida(f,null));
		return null;
		
	}
}
