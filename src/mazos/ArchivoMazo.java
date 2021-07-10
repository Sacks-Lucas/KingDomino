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
											Color.MAGENTA, Color.ORANGE, Color.PINK, Color.PINK};
	
	public static ArrayList<Ficha> cargarMazo (String ruta) {

		ArrayList<Ficha> fichas=null;
		try {
			Scanner file = new Scanner(new File(ruta));
	
			int cantFichas = file.nextInt();
			
			fichas = new ArrayList<Ficha>(cantFichas);
			
			for(int i = 0; i < cantFichas; i++) {

				ArrayList<Terreno> terrenos = new ArrayList<Terreno>(2);
				
				for(int t = 0; t < 2; t++) {
					int cantCoronas = file.nextInt();
					int valorTerreno = file.nextInt();
					int posColor = file.nextInt();
					
					terrenos.add(new Terreno(cantCoronas, valorTerreno, COLORES[posColor]));
				}
				
				int valorFicha = file.nextInt()-1;
				
				fichas.add(new Ficha(terrenos.get(0), terrenos.get(1), valorFicha));	
			}
			
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fichas;
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
		int(tipoTerreno) va de 0 - 8
		int(pos del color en un array de colores es igual que tipo terreno)
		sacar ultimo color de array
	 	
	 */
}
