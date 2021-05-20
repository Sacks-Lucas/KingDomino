package juego;

import java.util.ArrayList;

public class Mazo {

	private ArrayList<Ficha> fichas;
	
	public Mazo(int cant) {
		
		// Cargar todas las fichas y quedarme solo con cant
		
		fichas = new ArrayList<Ficha>(cant);
		
		for (int i = 0; i < cant; i++) {
			fichas.add(new Ficha(null, null, i));
		}
	}

	public void mezclar() {
		
		
	}

	public Ficha sacarFicha() {
		
		return fichas.remove(fichas.size()-1);
	}
	
}
