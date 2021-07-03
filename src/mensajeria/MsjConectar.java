package mensajeria;

import java.io.Serializable;

import cliente.HiloCliente;
import servidor.HiloServidor;

public class MsjConectar implements Mensaje,Serializable{

	private static final long serialVersionUID = 111L;
	private String a = "Msjconectar";
	@Override
	public String realizarOperacion(HiloServidor hilo) {
		System.out.println(a+": desde servidor ! ");
		return null;
	}

	@Override
	public String realizarOperacion(HiloCliente hilo) {
		System.out.println(a+": desde Cliente ! ");
		return null;
	}

}
