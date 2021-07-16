package msjClienteAServidor;

import java.io.Serializable;

import graphics.FrameJuego;
import graphics.JPanelPartida;
import respServidorACliente.RespRotarFicha;
import servidor.HiloServidor;

public class MsjRotarFicha implements Mensaje,Serializable {

	private int idFichaSel;
	private int codigoPartida;
	private boolean rotar;
	private boolean rotarTerr;


	public MsjRotarFicha(int idFichaSel, int codigoPartida,boolean rotar,boolean rotarTerr) {
		this.idFichaSel = idFichaSel;
		this.codigoPartida = codigoPartida;
		this.rotar = rotar;
		this.rotarTerr = rotarTerr;
	}

	@Override
	public String realizarOperacion(HiloServidor hilo) {
		FrameJuego frame = hilo.getInfoPartida(this.codigoPartida);
		JPanelPartida partida = frame.getJPanelPartida();
		if(rotar) {
			partida.getRonda().obtenerFichasEnMesa().get(idFichaSel).rotarFicha();
		}
		if(rotarTerr) {
			partida.getRonda().obtenerFichasEnMesa().get(idFichaSel).rotarTerreno();
		}
		
		hilo.broadcastPartida(new RespRotarFicha(idFichaSel,rotar,rotarTerr), hilo.getClientes_conectados());
		return null;
	}

}
