package respServidorACliente;

import java.io.Serializable;
import java.util.List;

import cliente.HiloCliente;

public class RespConectarSala implements RespMensaje,Serializable {
	List<String> listaUsuarios;
	public RespConectarSala(List<String> nombresUsuariosConectados) {
		listaUsuarios = nombresUsuariosConectados;
	}

	@Override
	public String realizarOperacion(HiloCliente hilo) {
		hilo.getSalaPartida().updateListUsr(listaUsuarios);
		return null;
	}

}
