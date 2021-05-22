package juego;

import java.util.Arrays;

public class Tablero {
   private Terreno centro;
   private int xParedIzq=2;
   private int xParedDer=2;
   private int yTecho=2;
   private int yPiso=2;
   private int[][] matrizOcupados = new int [10][10];
   
   Tablero(Castillo castillo) {
	   centro = castillo;
	   matrizOcupados[4][4] = 1;
   }
   public boolean puedeAgregar(Ficha f, int x, int y,int sentido,int sentido2) {
	   if(dentroDeTablero( f,  x,  y) && combinaTerreno(f,x,y, sentido2, sentido2)) {
		   return sentido == Ficha.SENTIDO_VERTICAL?puedeAgregarVertical(x,y,sentido2):puedeAgregarHorizontal(x,y,sentido2);
	   }
	   return false;  
   }

	private boolean dentroDeTablero(Ficha f, int x, int y) {		
		return true;
	}
	private boolean combinaTerreno(Ficha f,int x, int y, int sentido,int sentido2) {
		if(sentido == Ficha.SENTIDO_HORIZONTAL && sentido2 == Ficha.SENTIDO_IZQ_TOP) {
			if(f.getTipoTerrenoIzq() == matrizOcupados[x][y+1] || f.getTipoTerrenoIzq() == matrizOcupados[x-1][y] || matrizOcupados[x+1][y] == f.getTipoTerrenoIzq()) {
				return true;
			}	
			
			if(f.getTipoTerrenoDer() == matrizOcupados[x-1][y-1] || f.getTipoTerrenoDer() == matrizOcupados[x+1][y-1] || f.getTipoTerrenoDer() == matrizOcupados[x][y-2]) {
				return true;
			}	
			
		} else if(sentido == Ficha.SENTIDO_HORIZONTAL && sentido2 == Ficha.SENTIDO_DER_DOWN) {
			if(f.getTipoTerrenoIzq() == matrizOcupados[x+1][y] || matrizOcupados[x-1][y] == f.getTipoTerrenoIzq() || f.getTipoTerrenoIzq() == matrizOcupados[x][y-1]) {
				return true;
			}	
			if(f.getTipoTerrenoDer() == matrizOcupados[x-1][y+1] || f.getTipoTerrenoDer() == matrizOcupados[x+1][y+1] || f.getTipoTerrenoDer() == matrizOcupados[x][y+2]) {
				return true;
			}
		} else if(sentido == Ficha.SENTIDO_VERTICAL && sentido2 == Ficha.SENTIDO_DER_DOWN) {
			if(f.getTipoTerrenoIzq() == matrizOcupados[x-1][y] || f.getTipoTerrenoIzq() == matrizOcupados[x][y-1] || f.getTipoTerrenoIzq() == matrizOcupados[x][y+1]) {
				return true;
			}	
			if(f.getTipoTerrenoDer() == matrizOcupados[x+1][y-1] || f.getTipoTerrenoDer() == matrizOcupados[x+1][y+1] || f.getTipoTerrenoDer() == matrizOcupados[x+2][y]) {
				return true;
			}
		}else if(sentido == Ficha.SENTIDO_VERTICAL && sentido2 == Ficha.SENTIDO_IZQ_TOP) {
			if(f.getTipoTerrenoIzq() == matrizOcupados[x+1][y] || f.getTipoTerrenoIzq() == matrizOcupados[x][y-1] || f.getTipoTerrenoIzq() == matrizOcupados[x][y+1]) {
				return true;
			}	
			if(f.getTipoTerrenoDer() == matrizOcupados[x-1][y-1] || f.getTipoTerrenoDer() == matrizOcupados[x-1][y+1] || f.getTipoTerrenoDer() == matrizOcupados[x-2][y]) {
				return true;
			}
		}
		return false;
	}
	private boolean puedeAgregarHorizontal(int x, int y, int sentido2) {
		if(sentido2==Ficha.SENTIDO_IZQ_TOP &&  matrizOcupados[x][y-1] == 0 && matrizOcupados[x][y] == 0 && (xParedIzq-1 >= 0 || xParedDer-1>=0)) {
			return true;
		}else if(sentido2==Ficha.SENTIDO_DER_DOWN &&  matrizOcupados[x+1][y+1] == 0 && matrizOcupados[x][y] == 0 && (xParedIzq-1 >= 0 || xParedDer-1>=0)) {
			return true;
		}
		return false;
	}
	private boolean puedeAgregarVertical(int x, int y, int sentido2) {

		if(sentido2==Ficha.SENTIDO_IZQ_TOP &&  matrizOcupados[x-1][y] == 0 && matrizOcupados[x][y] == 0 && (yTecho-1 >= 0 || yPiso-1>=0)) {
			return true;
		}else if(sentido2==Ficha.SENTIDO_DER_DOWN &&  matrizOcupados[x+1][y] == 0 && matrizOcupados[x][y] == 0 && (yTecho-1 >= 0 || yPiso-1>=0)) {
			return true;
		}
		return false;
	}
	public void agregarFicha(Ficha f, int x, int y,int sentido,int sentido2) {
		int incx=0,incy=0;
		if (sentido == Ficha.SENTIDO_VERTICAL && sentido2 == Ficha.SENTIDO_DER_DOWN) {
			incx = 1;
			incy = 0;
		}
		if (sentido == Ficha.SENTIDO_VERTICAL && sentido2 == Ficha.SENTIDO_IZQ_TOP) {
			incx = -1;
			incy = 0;
		}
		
		if (sentido == Ficha.SENTIDO_VERTICAL && sentido2 == Ficha.SENTIDO_DER_DOWN) {
			incx = 1;
			incy = 0;
		}
		if (sentido == Ficha.SENTIDO_HORIZONTAL && sentido2 == Ficha.SENTIDO_DER_DOWN) {
			incx = 0;
			incy = 1;
		}
		
		if (sentido == Ficha.SENTIDO_HORIZONTAL && sentido2 == Ficha.SENTIDO_IZQ_TOP) {
			incx = 0;
			incy = -1;
		}
		
		matrizOcupados[x][y] = f.getTipoTerrenoIzq();
		matrizOcupados[x+incx][y+incy] = f.getTipoTerrenoDer();
		
	}
	@Override
	public String toString() {
		return "Tablero [matrizOcupados=" +matrizOcupados + "]";
	}
   
   
   
}
