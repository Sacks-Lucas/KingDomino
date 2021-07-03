package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import mensajeria.Mensaje;
import servidor.InfoPartida;

public class HiloCliente extends Thread{

	private Socket socketCliente;
	private boolean estaConectado;
	private InfoPartida infoPartida;

	public boolean isEstaConectado() {
		return estaConectado;
	}

	public void setEstaConectado(boolean estaConectado) {
		this.estaConectado = estaConectado;
	}

	public HiloCliente(Socket cs) {
		this.socketCliente= cs;
		this.estaConectado = true;
		start();
	}


	@Override
	public void run() {
		while(estaConectado) {
	        try {
	        	ObjectInputStream in = new ObjectInputStream(socketCliente.getInputStream());	     
				Mensaje msjRecibido = (Mensaje) in.readObject();
				msjRecibido.realizarOperacion(this);
			} catch (IOException e) {
				e.printStackTrace();
			}  catch (ClassNotFoundException e) {
				e.printStackTrace();
			}				
		}	
	}

	public void cerrarSocket() {
		try {
			socketCliente.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public Socket getCliente() {
		// TODO Auto-generated method stub
		return socketCliente;
	}

	public void guardarInfoPartida(InfoPartida f) {
		this.infoPartida = f;
	}
}