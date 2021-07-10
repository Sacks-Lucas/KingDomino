package servidor;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import graphics.FrameJuego;
import msjClienteAServidor.Mensaje;
import msjClienteAServidor.MsjCrearPartida;
import respServidorACliente.RespMensaje;

public class HiloServidor extends Thread{

	private Socket socketCliente;
	private ObjectOutputStream salidaCliente;
	private boolean estaConectado;
	private List<Socket> clientes_conectados;
	private InfoPartida infoPartida;
	private List<InfoPartida> partidasCreadas;
	private static int codigoPartida;
	
	public boolean isEstaConectado() {
		return estaConectado;
	}

	public void guardarInfoPartida(InfoPartida f) {
		this.infoPartida = f;
	}
	public void setEstaConectado(boolean estaConectado) {
		this.estaConectado = estaConectado;
	}

	public HiloServidor(Socket cs, List<Socket> clientes_conectados, List<InfoPartida> partidasCreadas) {
		this.socketCliente = cs;
		this.estaConectado = true;
		this.partidasCreadas = partidasCreadas;
		this.setClientes_conectados(clientes_conectados);
		start();
	}

	@Override
	public void run() {
		while(estaConectado) {
	        try {
	        	ObjectInputStream in = new ObjectInputStream(socketCliente.getInputStream());
	        	try {
					Mensaje msjRecibido = (Mensaje) in.readObject();
					msjRecibido.realizarOperacion(this);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	
	}

	public void broadcast(Mensaje msj) {
		try {
			for (Socket socket : getClientes_conectados()) {
				ObjectOutputStream salidaCliente = new ObjectOutputStream(socket.getOutputStream());
	        	salidaCliente.writeObject(msj);
	        	salidaCliente.flush();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
//	public void responderCliente(String string) {
//		
//		try {
////			salidaCliente = new DataOutputStream(socketCliente.getOutputStream());
//			salidaCliente.writeUTF(string);
//			salidaCliente.flush();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//


	public Socket getSocket() {
		return socketCliente;
	}
	


	public void eliminarCliente() {
		getClientes_conectados().remove(socketCliente);
	}

	public List<Socket> getClientes_conectados() {
		return clientes_conectados;
	}

	public void setClientes_conectados(List<Socket> clientes_conectados) {
		this.clientes_conectados = clientes_conectados;
	}

	public void broadcastPartida(RespMensaje msj, List<Socket> list) {
		try {
			for (Socket socket : list) {
				System.out.println("Enviar msj");
				ObjectOutputStream salidaCliente = new ObjectOutputStream(socket.getOutputStream());
	        	salidaCliente.writeObject(msj);
				salidaCliente.flush();	
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public int obtenerCodigoPartida() {
		this.codigoPartida++;
		return codigoPartida;
	}

	public FrameJuego getInfoPartida() {
		// TODO Auto-generated method stub
		return infoPartida.getfPartida();
	}

}
