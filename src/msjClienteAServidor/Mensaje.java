package msjClienteAServidor;

import cliente.HiloCliente;
import servidor.HiloServidor;

public interface Mensaje {
	public static final int IR_A_SALA= 0;
	public static final int CREAR_SALA=1;
	public static final int DESCONECTAR= 2;
	public static final int CONECTAR= 3;
	public static final int ENVIAR_MSJ= 4;
	public static final int ACT_LISTA= 5;
	public static final int ADD_LISTA= 6;
	public static final int WISP= 7;
	public static final int ADD_USR_LIST = 8;
	
	public String realizarOperacion(HiloServidor hilo);
}
