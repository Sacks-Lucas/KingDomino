package respServidorACliente;

import java.io.Serializable;

import cliente.HiloCliente;
import graphics.FrameJuego;
import graphics.JPanelPartida;

public class RespRotarFicha implements RespMensaje,Serializable{

	private int idF;
	private boolean rotar;
	private boolean rotarTerr;

	public RespRotarFicha(int idFichaSel, boolean rotar, boolean rotarTerr) {
		this.idF = idFichaSel;
		this.rotar = rotar;
		this.rotarTerr = rotarTerr;
	}

	@Override
	public String realizarOperacion(HiloCliente hilo) {
		
		FrameJuego frame = hilo.getInfoPartida();
		JPanelPartida partida = frame.getJPanelPartida();
		if(rotar) {
			System.out.println("Rotar ficha");
			partida.getRonda().obtenerFichasEnMesa().get(idF).rotarFicha();
		}
		if(rotarTerr) {
			System.out.println("Rotar ficha y rotar terreno");
			partida.getRonda().obtenerFichasEnMesa().get(idF).rotarTerreno();
		}
		
		return null;
	}

}
