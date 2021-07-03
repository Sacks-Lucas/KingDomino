package cliente;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import mensajeria.Mensaje;
import servidor.InfoPartida;



public class Cliente {
	private final int PUERTO = 1234; //Puerto para la conexión
    private final String HOST = "localhost"; //Host para la conexión
    protected String mensajeServidor; //Mensajes entrantes (recibidos) en el servidor
    protected Socket cs; //Socket del cliente
    ObjectOutputStream salidaServidor;
	protected DataOutputStream salidaCliente; //Flujo de datos de salida
    private HiloCliente hilo;

    public Cliente () throws UnknownHostException, IOException {
    	cs = new Socket(HOST, PUERTO);
    	hilo = new HiloCliente(cs);
    }
    void infoPartida(InfoPartida f ) {
    	this.hilo.guardarInfoPartida(f);
    }
    public void enviarMsj(Mensaje msj) { //Método para iniciar el cliente
    
        try{
        	salidaServidor = new ObjectOutputStream(cs.getOutputStream());
            salidaServidor.writeObject(msj);
            salidaServidor.flush();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
	public void desconectarse() {
		this.hilo.setEstaConectado(false);
	}
}
