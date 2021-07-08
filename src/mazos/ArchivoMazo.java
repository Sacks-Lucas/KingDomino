package mazos;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import juego.Ficha;
import juego.Mazo;
import juego.Terreno;

public class ArchivoMazo {

	private static final Color COLORES[] = {Color.YELLOW, Color.RED, Color.BLUE, 
											Color.CYAN, Color.GRAY, Color.GREEN,
											Color.MAGENTA, Color.ORANGE, Color.PINK};
	
	public static Mazo cargarMazo (String ruta) {

		Mazo m = null;
		
		try {
			Scanner file = new Scanner(new File(ruta));
	
			int cantFichas = file.nextInt();
			
			ArrayList<Ficha> fichas = new ArrayList<Ficha>(cantFichas);
			
			for(int i = 0; i < cantFichas; i++) {

				ArrayList<Terreno> terrenos = new ArrayList<Terreno>(2);
				
				for(int t = 0; t < 2; t++) {
					int cantCoronas = file.nextInt();
					int valorTerreno = file.nextInt();
					int posColor = file.nextInt();
					
					terrenos.add(new Terreno(cantCoronas, valorTerreno, COLORES[t]));
				}
				
				int valorFicha = file.nextInt();
				
				fichas.add(new Ficha(terrenos.get(0), terrenos.get(1), valorFicha));
				
				m = new Mazo(fichas);
				
			}
			
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
	}

	/*
		--Mazo se construye con un array de fichas
	
		cantFichas
		ficha1
		ficha2
		fichaN
		
		--Ficha se construye con 2 terrenos
		
		t1a t1b
		t2a t2b
		tNa tNb
		
		--Terreno se construye con 
		int(cantCoronas) 
		int(valorTerreno)
		int(pos del color en un array de colores)
	 
	 */
}
