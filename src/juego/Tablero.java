package juego;

import java.util.LinkedList;
import java.util.List;

public class Tablero {
   private Terreno centro;
   private int xParedIzq=2;
   private int xParedDer=2;
   private int yTecho=2;
   private int yPiso=2;
   private Terreno[][] matrizOcupados = new Terreno [9][9];
   private List<List<Terreno>> ListasTerrenos = new LinkedList<List<Terreno>>();
   private int contadorAsoc=1;
   public Tablero(Castillo castillo) {
	   centro = castillo;
	   matrizOcupados[4][4] = new Terreno(0, -1);
   }
   
   public void agregarFichaATablero(Ficha f,int x0,int y0,int x1,int y1) {
	   validarTerrAdy(x0, y0, f.getTipoTerrenoIzq());
	   validarTerrAdy(x1, y1, f.getTipoTerrenoDer());
   }
   
   public void validarTerrAdy(int x0,int y0,Terreno t_aux) {
	   boolean combino=false;
	   Terreno a0 = new Terreno(t_aux.getCoronas(),t_aux.getTipo());
	   Terreno[] a = {matrizOcupados[x0-1][y0],
		   		  matrizOcupados[x0+1][y0],
		   		  matrizOcupados[x0][y0-1],
		   		  matrizOcupados[x0][y0+1]}; 
		for (Terreno terreno : a) {
		   if(t_aux.equals(terreno)) {
			   if(t_aux.getGrupo() == null ) {
				   terreno.relTerrenoAGrupo(t_aux);
			   }else if(t_aux.getCodAsoc() != terreno.getCodAsoc()){
				   terreno.combinarGrupos(t_aux);
				   t_aux.setGrupo(terreno.getGrupo());
			   }
			   combino=true;
		   }
		}
		a0.setCodAsoc(t_aux.getCodAsoc());
		a0.setGrupo(t_aux.getGrupo());
		if(!combino) {
			// no combino con ningun caso ady. creo la lista inicial
			a0.crearRelacion();
			a0.setCodAsoc(contadorAsoc);
			ListasTerrenos.add(a0.getGrupo());
			contadorAsoc++;
		}
		matrizOcupados[x0][y0] = a0;
   }
   
   public int sumarizar() {
	   int x=0,y=0;
	   for (List<Terreno> list : ListasTerrenos) {
		   x=0;
		   for (Terreno terreno : list) {
			   x+=terreno.getCoronas();
		   }
		   y+=(x*list.size());
		   
	   }
	   return y; 
   }
   
//   public boolean puedeAgregar(Ficha f, int x, int y,int sentido,int sentido2) {
//	   if(dentroDeTablero( f,  x,  y) && combinaTerreno(f,x,y, sentido2, sentido2)) {
//		   return sentido == Ficha.SENTIDO_VERTICAL?puedeAgregarVertical(x,y,sentido2):puedeAgregarHorizontal(x,y,sentido2);
//	   }
//	   return false;  
//   }

//	private boolean dentroDeTablero(Ficha f, int x, int y) {		
//		return true;
//	}
//	
//	private boolean combinaTerreno(Ficha f,int x, int y, int sentido,int sentido2) {
//		if(sentido == Ficha.SENTIDO_HORIZONTAL && sentido2 == Ficha.SENTIDO_IZQ_TOP) {
//			if(f.getTipoTerrenoIzq() == matrizOcupados[x][y+1] || f.getTipoTerrenoIzq() == matrizOcupados[x-1][y] || matrizOcupados[x+1][y] == f.getTipoTerrenoIzq()) {
//				return true;
//			}	
//			
//			if(f.getTipoTerrenoDer() == matrizOcupados[x-1][y-1] || f.getTipoTerrenoDer() == matrizOcupados[x+1][y-1] || f.getTipoTerrenoDer() == matrizOcupados[x][y-2]) {
//				return true;
//			}	
//			
//		} else if(sentido == Ficha.SENTIDO_HORIZONTAL && sentido2 == Ficha.SENTIDO_DER_DOWN) {
//			if(f.getTipoTerrenoIzq() == matrizOcupados[x+1][y] || matrizOcupados[x-1][y] == f.getTipoTerrenoIzq() || f.getTipoTerrenoIzq() == matrizOcupados[x][y-1]) {
//				return true;
//			}	
//			if(f.getTipoTerrenoDer() == matrizOcupados[x-1][y+1] || f.getTipoTerrenoDer() == matrizOcupados[x+1][y+1] || f.getTipoTerrenoDer() == matrizOcupados[x][y+2]) {
//				return true;
//			}
//		} else if(sentido == Ficha.SENTIDO_VERTICAL && sentido2 == Ficha.SENTIDO_DER_DOWN) {
//			if(f.getTipoTerrenoIzq() == matrizOcupados[x-1][y] || f.getTipoTerrenoIzq() == matrizOcupados[x][y-1] || f.getTipoTerrenoIzq() == matrizOcupados[x][y+1]) {
//				return true;
//			}	
//			if(f.getTipoTerrenoDer() == matrizOcupados[x+1][y-1] || f.getTipoTerrenoDer() == matrizOcupados[x+1][y+1] || f.getTipoTerrenoDer() == matrizOcupados[x+2][y]) {
//				return true;
//			}
//		}else if(sentido == Ficha.SENTIDO_VERTICAL && sentido2 == Ficha.SENTIDO_IZQ_TOP) {
//			if(f.getTipoTerrenoIzq() == matrizOcupados[x+1][y] || f.getTipoTerrenoIzq() == matrizOcupados[x][y-1] || f.getTipoTerrenoIzq() == matrizOcupados[x][y+1]) {
//				return true;
//			}	
//			if(f.getTipoTerrenoDer() == matrizOcupados[x-1][y-1] || f.getTipoTerrenoDer() == matrizOcupados[x-1][y+1] || f.getTipoTerrenoDer() == matrizOcupados[x-2][y]) {
//				return true;
//			}
//		}
//		return false;
//	}
//	private boolean puedeAgregarHorizontal(int x, int y, int sentido2) {
//		if(sentido2==Ficha.SENTIDO_IZQ_TOP &&  matrizOcupados[x][y-1] == 0 && matrizOcupados[x][y] == 0 && (xParedIzq-1 >= 0 || xParedDer-1>=0)) {
//			return true;
//		}else if(sentido2==Ficha.SENTIDO_DER_DOWN &&  matrizOcupados[x+1][y+1] == 0 && matrizOcupados[x][y] == 0 && (xParedIzq-1 >= 0 || xParedDer-1>=0)) {
//			return true;
//		}
//		return false;
//	}
//	private boolean puedeAgregarVertical(int x, int y, int sentido2) {
//
//		if(sentido2==Ficha.SENTIDO_IZQ_TOP &&  matrizOcupados[x-1][y] == 0 && matrizOcupados[x][y] == 0 && (yTecho-1 >= 0 || yPiso-1>=0)) {
//			return true;
//		}else if(sentido2==Ficha.SENTIDO_DER_DOWN &&  matrizOcupados[x+1][y] == 0 && matrizOcupados[x][y] == 0 && (yTecho-1 >= 0 || yPiso-1>=0)) {
//			return true;
//		}
//		return false;
//	}
//	public void agregarFicha(Ficha f, int x, int y,int sentido,int sentido2) {
//		int incx=0,incy=0;
//		if (sentido == Ficha.SENTIDO_VERTICAL && sentido2 == Ficha.SENTIDO_DER_DOWN) {
//			incx = 1;
//			incy = 0;
//		}
//		if (sentido == Ficha.SENTIDO_VERTICAL && sentido2 == Ficha.SENTIDO_IZQ_TOP) {
//			incx = -1;
//			incy = 0;
//		}
//		
//		if (sentido == Ficha.SENTIDO_VERTICAL && sentido2 == Ficha.SENTIDO_DER_DOWN) {
//			incx = 1;
//			incy = 0;
//		}
//		if (sentido == Ficha.SENTIDO_HORIZONTAL && sentido2 == Ficha.SENTIDO_DER_DOWN) {
//			incx = 0;
//			incy = 1;
//		}
//		
//		if (sentido == Ficha.SENTIDO_HORIZONTAL && sentido2 == Ficha.SENTIDO_IZQ_TOP) {
//			incx = 0;
//			incy = -1;
//		}
//		
//		matrizOcupados[x][y] = f.getTipoTerrenoIzq();
//		matrizOcupados[x+incx][y+incy] = f.getTipoTerrenoDer();
//		
//	}
	@Override
	public String toString() {
		return "Tablero [matrizOcupados=" +matrizOcupados + "]";
	}
   
   
   
}
