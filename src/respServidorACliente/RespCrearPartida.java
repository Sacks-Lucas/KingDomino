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
	private boolean turno ;
	private List<Integer> listaFichasMazo = new LinkedList<Integer>();
	private int codJug;
	private static final long serialVersionUID = 1L;

	//Respuesta para Cliente
	public  RespCrearPartida(int cantJugadores, int codigo,List<Integer> f, boolean turno, int codJug) {
		this.cantJugadores = cantJugadores;
		this.codigoPartida = codigo;
		this.listaFichasMazo.addAll(f);
		this.turno = turno;
		this.codJug = codJug;
	}

	@Override
	public String realizarOperacion(HiloCliente hilo) {
		
		FrameJuego f = new FrameJuego(cantJugadores,this.codigoPartida,hilo.getCliente());
		f.getApp().crearMazo(this.listaFichasMazo);
		f.iniciarJuego(this.turno,this.codJug);
		hilo.getSalaPartida().dispose();
		hilo.guardarInfoPartida(new InfoPartida(f,null));
		return null;
		
	}
}
