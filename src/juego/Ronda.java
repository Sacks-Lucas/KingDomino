package juego;

import java.util.ArrayList;
import java.util.List;
public class Ronda {
	private List <Ficha> fichasEnMesa =null;
	private int nroRonda = 0;
	private ArrayList<Jugador> ordenJugadores=null;
	

	public Ronda(ArrayList<Jugador> jug,Mazo mazo) {
		int cantJugadores=jug.size();
		fichasEnMesa = new ArrayList<Ficha>(cantJugadores);
			
		this.nroRonda = 1;
		this.ordenJugadores=new ArrayList<Jugador>(cantJugadores);
		for (int i = 0; i < cantJugadores; i++) {
			this.ordenJugadores.add(jug.get(i));
		}
		this.ponerFichasEnMesa(mazo);	
		fichasEnMesa.sort(null);	
	}

	public void avanzar() {
		nroRonda++;
		
		fichasEnMesa.clear();
		ordenJugadores.sort(null);
		
	}
	
	public int getRonda(){
		return nroRonda;
	}

	public List<Ficha> obtenerFichasEnMesa() {
		return this.fichasEnMesa;
	}
	
	public Ficha sacarFicha(int indexFicha,int indexJugador) {
		ordenJugadores.get(indexJugador).getRey().posicionar(indexFicha);
		return fichasEnMesa.get(indexFicha);
		
	}

	public  List<Jugador> getOrdenJ() {
		return  ordenJugadores;
	}
	
	public void ponerFichasEnMesa(Mazo mazo){
		for (int i = 0; i < ordenJugadores.size(); i++) {
			fichasEnMesa.add(mazo.sacarFicha());
		}
		fichasEnMesa.sort(null);
	}
}
