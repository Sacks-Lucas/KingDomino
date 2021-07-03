package servidor;

import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import graphics.FrameJuego;

public class InfoPartida {
	FrameJuego fPartida ;
	List <Socket> usuariosConectados = new LinkedList<Socket>();
	public InfoPartida(FrameJuego f , List<Socket> usuarios) {
		fPartida = f;
		usuariosConectados = usuarios;
	}

}
