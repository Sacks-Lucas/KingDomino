package servidor;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;



public class Servidor{
	
	
    private final int PUERTO = 1234; //Puerto para la conexi�n
    private final String HOST = "localhost"; //Host para la conexi�n
    protected ServerSocket ss ; //Socket del servidor
    protected Socket cs; //Socket del cliente
	private boolean running;
	private static List<Socket> clientes_conectados = new LinkedList<Socket>();
	private static List<InfoPartida> partidasCreadas = new LinkedList<InfoPartida>();

    public Servidor() throws IOException {
    	ss = new ServerSocket(PUERTO);
    	cs = new Socket();
    }
    
    public void startServer()//M�todo para iniciar el servidor
    {
    	this.running=true;
        try
        {
            System.out.println("Esperando..."); //Esperando conexi�n
            while(running) {
                cs = ss.accept(); //Accept comienza el socket y espera una conexi�n desde un cliente
                clientes_conectados.add(cs);
                new HiloServidor(cs,clientes_conectados,partidasCreadas);
            }

            System.out.println("Fin de la conexi�n");

            ss.close();//Se finaliza la conexi�n con el cliente
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    void finalizarServidor() {
    	this.running=false;
    }
    public static void main(String[] args) throws IOException{
        Servidor serv = new Servidor(); //Se crea el servidor

        System.out.println("Iniciando servidor\n");
        serv.startServer(); //Se inicia el servidor
    }
}

