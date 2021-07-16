package cliente;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import graphics.SalaPartida;
import msjClienteAServidor.Mensaje;
import servidor.InfoPartida;



public class Cliente {
	private final int PUERTO = 1234; //Puerto para la conexión
    private final String HOST = "localhost"; //Host para la conexión
    private String mensajeServidor; //Mensajes entrantes (recibidos) en el servidor
    private Socket cs; //Socket del cliente
    ObjectOutputStream salidaServidor;
    private DataOutputStream salidaCliente; //Flujo de datos de salida
    private HiloCliente hilo;
	private SalaPartida entrarAPartida;

    public Cliente () throws UnknownHostException, IOException {
    	cs = new Socket(HOST, PUERTO);
    	hilo = new HiloCliente(this);
    }
    void infoPartida(InfoPartida f ) {
    	this.hilo.guardarInfoPartida(f);
    }
    public void enviarMsj(Mensaje msj) { //Método para iniciar el cliente
    
        try{
        	salidaServidor = new ObjectOutputStream(cs.getOutputStream());
            salidaServidor.writeObject(msj);
            salidaServidor.flush();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
	public void desconectarse() {
		this.hilo.setEstaConectado(false);
	}
	public Socket getSocket() {
		
		return this.cs;
	}
	public void setSalaPartida(SalaPartida entrarAPartida) {
		hilo.setSalaPartida(entrarAPartida);
	}
}
