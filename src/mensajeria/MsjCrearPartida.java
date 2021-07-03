package mensajeria;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import cliente.HiloCliente;
import graphics.FrameJuego;
import juego.Ficha;
import juego.Mazo;
import servidor.HiloServidor;
import servidor.InfoPartida;

public class MsjCrearPartida implements Mensaje,Serializable{
	private int cantJugadores=0;
	private int codigoPartida;
	private List<Integer> listaFichasMazo = new LinkedList<Integer>();
	private static final long serialVersionUID = 1L;

	//Respuesta para Cliente
	public  MsjCrearPartida(int cantJugadores, int codigo,List<Integer> f) {
		this.cantJugadores = cantJugadores;
		this.codigoPartida = codigo;
		this.listaFichasMazo.addAll(f);
		for (Integer integer : listaFichasMazo) {
			System.out.println(integer);
		}
	}
	
	//Para servidor
	public  MsjCrearPartida(int cantJugadores) {
		this.cantJugadores = cantJugadores;
	}	
	
	@Override
	public String realizarOperacion(HiloServidor hilo) {
		System.out.println("Estopy recoboemdp eñ ,amasdasmdasd");
		int codigo = hilo.obtenerCodigoPartida();
		FrameJuego f = new FrameJuego(cantJugadores,codigo);
		f.getApp().crearMazo();
		hilo.guardarInfoPartida(new InfoPartida(f,hilo.getClientes_conectados()));
		
		Mazo m  = f.getApp().getMazo();
		
		for (Ficha ficha: m.getFichas() ) {
			System.out.println("asdsadds "+ ficha.getCode());
			listaFichasMazo.add(ficha.getCode());
		}
		Mensaje msj = new MsjCrearPartida(cantJugadores,codigo,listaFichasMazo);
		hilo.broadcastPartida(msj,hilo.getClientes_conectados());
		return null;
	}

	@Override
	public String realizarOperacion(HiloCliente hilo) {
		
		System.out.println("Datos: "+cantJugadores+" "+codigoPartida);
		FrameJuego f = new FrameJuego(cantJugadores,this.codigoPartida);
		
		f.getApp().crearMazo(this.listaFichasMazo);
		
		f.iniciarJuego();
		
		hilo.guardarInfoPartida(new InfoPartida(f,null));
		return null;
	}

}
