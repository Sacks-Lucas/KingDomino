package juego;

import java.io.IOException;
import java.net.UnknownHostException;

import cliente.Cliente;
import graphics.FrameJuego;
import msjClienteAServidor.MsjCrearPartida;

public class mainPruebaCS {
	
	public static void main(String[] args) {
		Cliente c = null;
		try {
			c = new Cliente();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		c.enviarMsj(new MsjCrearPartida(4));
		int i = 0;
		while (true) {

		}
	}
}
