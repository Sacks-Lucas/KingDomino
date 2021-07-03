package juego;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Mazo {

	private ArrayList<Ficha> fichas;
	
	public ArrayList<Ficha> getFichas() {
		return fichas;
	}

	public Mazo(int cant) {
		fichas = new ArrayList<Ficha>(cant);
		for (int i = 0; i < cant; i++) {
			fichas.add(new Ficha(new Terreno(1, 1,Color.YELLOW), new Terreno(2, 2,Color.RED), i));
		}
	}

	public Mazo(List<Integer> listaFichasMazo) {
		fichas = new ArrayList<Ficha>(listaFichasMazo.size());
		for (Integer integer : listaFichasMazo) {
			fichas.add(new Ficha(new Terreno(1, 1,Color.YELLOW), new Terreno(2, 2,Color.RED), integer));
		}

	}

	public void mezclar() {
	    for(int i = fichas.size() - 1; i > 0; i--) {
	        // calculamos un índice aleatorio dentro del rango permitido
	        int shuffled_index = (int)Math.floor(Math.random() * (i + 1));
	        //guardamos el elemento que estamos iterando
	        Ficha tmp = fichas.get(i);
	        // asignamos el elemento aleatorio al índice iterado
	        fichas.set(i, fichas.get(shuffled_index));
	        // asignamos el elemento iterado al índice aleatorio
	        fichas.set(shuffled_index, tmp);
	      }
	}

	public Ficha sacarFicha() {
		return fichas.remove(fichas.size()-1);
	}

	@Override
	public String toString() {
		return "Mazo [fichas=" + fichas + "]";
	}

	public boolean estaVacio() {
		// TODO Auto-generated method stub
		return this.fichas.isEmpty();
	}
	
}
