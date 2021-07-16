package msjClienteAServidor;

import java.io.Serializable;
import java.util.LinkedList;

import respServidorACliente.RespConectarSala;
import servidor.HiloServidor;

public class MsjConectarSala implements Mensaje,Serializable {
	String usuarioConecta ;
	public MsjConectarSala(String usr) {
		this.usuarioConecta = usr;
	}

	@Override
	public String realizarOperacion(HiloServidor hilo) {
		if(hilo.getNombresUsuariosConectados() == null) {
			hilo.setNombresUsuariosConectados(new LinkedList<String>());
		}
		hilo.agregarNombreUsuario(usuarioConecta);
		hilo.broadcastPartida(new RespConectarSala(hilo.getNombresUsuariosConectados()), hilo.getClientes_conectados());
		return null;
	}
}
