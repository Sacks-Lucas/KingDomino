package msjClienteAServidor;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
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
		f.crearJPanelPartida(true,0);
		hilo.guardarInfoPartida(new InfoPartida(f,hilo.getClientes_conectados()));
		List<Integer> listaFichasMazo = new LinkedList<Integer>();
		Mazo m  = f.getApp().getMazo();
		
		for (Ficha ficha: m.getFichas() ) {
			listaFichasMazo.add(ficha.getCode());
		}
		

		try {
			boolean turno = true;
			int codJug = 0;
			for (Socket socket : hilo.getClientes_conectados()) {
				RespMensaje msj = new RespCrearPartida(cantJugadores,codigo,listaFichasMazo,turno,codJug);
				
				ObjectOutputStream salidaCliente = new ObjectOutputStream(socket.getOutputStream());
	        	salidaCliente.writeObject(msj);
				salidaCliente.flush();	
				codJug++;
				turno = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
