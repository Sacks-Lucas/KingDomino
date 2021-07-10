package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import graphics.EntrarAPartida;
import graphics.FrameJuego;
import msjClienteAServidor.Mensaje;
import respServidorACliente.RespMensaje;
import servidor.InfoPartida;

public class HiloCliente extends Thread{

	private Socket socketCliente;
	private boolean estaConectado;
	private InfoPartida infoPartida;
	private Cliente clt;
	private EntrarAPartida setEntrarAPartida;

	public boolean isEstaConectado() {
		return estaConectado;
	}

	public void setEstaConectado(boolean estaConectado) {
		this.estaConectado = estaConectado;
	}

	public HiloCliente(Cliente cliente) {
		this.socketCliente= cliente.getSocket();
		this.clt = cliente;
		this.estaConectado = true;
		start();
	}


	@Override
	public void run() {
		while(estaConectado) {
			System.out.println("Entra en peticion");
	        try {
	        	ObjectInputStream in = new ObjectInputStream(socketCliente.getInputStream());	     
				RespMensaje msjRecibido = (RespMensaje) in.readObject();
				System.out.println("Llegó mensaje ! ");
				msjRecibido.realizarOperacion(this);
				System.out.println("Termino atender peticion");
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

	public Cliente getCliente() {
		return this.clt;
	}

	public void guardarInfoPartida(InfoPartida f) {
		this.infoPartida = f;
	}

	public FrameJuego getInfoPartida() {
		return this.infoPartida.getfPartida();
	}

	public void setEntrarAPartida(EntrarAPartida entrarAPartida) {
		this.setEntrarAPartida = entrarAPartida;
	}

	public EntrarAPartida getEntrarAPartida() {
		return setEntrarAPartida;
	}
}