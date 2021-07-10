package juego;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import mazos.ArchivoMazo;

public class Mazo {
	
	private ArrayList<Ficha> fichas;
	
	public ArrayList<Ficha> getFichas() {
		return fichas;
	}

	public Mazo(int cant) {
		fichas = new ArrayList<Ficha>(cant);
		fichas = ArchivoMazo.cargarMazo("archivos mazos/ejemplo.in");
	}

	public Mazo(List<Integer> listaFichasMazo) {
		fichas = new ArrayList<Ficha>(listaFichasMazo.size());
		ArrayList<Ficha> fichas_aux = ArchivoMazo.cargarMazo("archivos mazos/ejemplo.in");
		for (Integer integer : listaFichasMazo) {
			Ficha aux_ficha_arch = fichas_aux.get(integer);
			fichas.add(new Ficha(aux_ficha_arch.getIzquierdo(), aux_ficha_arch.getDerecho(), integer));
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
