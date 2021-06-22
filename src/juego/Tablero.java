package juego;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

public class Tablero implements Drawable{
   public static final int X_CASTLE  = 5;
   public static final int Y_CASTLE  = 5;
   public static final int TAM_TABLERO  = 11;
   private int aux1_altura = 4;
   private int aux2_ancho = 4;
   private boolean [] filasUsadas = new boolean [10];
   private boolean [] colUsadas = new boolean [10];
   private Terreno[][] matrizOcupados = new Terreno [TAM_TABLERO][TAM_TABLERO];
   private int x0_tablero;
   private int y0_tablero;
   private List<List<Terreno>> ListasTerrenos = new LinkedList<List<Terreno>>();
   private int contadorAsoc=1;
   
   public Tablero(int aux, int aux2) {
	   matrizOcupados[X_CASTLE][X_CASTLE] = new Terreno(0, -1,Color.BLACK);
	   filasUsadas[X_CASTLE] = true;
	   colUsadas[X_CASTLE] = true;
	   this.x0_tablero = aux;
	   this.y0_tablero = aux2;
   }
   
   public void actualizarLimites(int x0,int y0) {
		if(!filasUsadas[x0] ) {
			//la fila no está usada, entonces resto altura y pongo la fila en uso
			aux1_altura--;
			filasUsadas[x0] = true;
		}
		
		if(!colUsadas[y0]) {
			//la col no está usada, entonces resto el ancho y pongo la col en uso
			aux2_ancho--;
			colUsadas[y0] = true;
		}
   }
   
   public boolean agregarFichaATablero(Ficha f,int x0,int y0,int x1,int y1) {
	   boolean aux1 = false,aux2=false;
	   if(dentroDeTablero(x0,  y0) && dentroDeTablero(x1,  y1)) {
		   System.out.println("entró");
		   aux1 = validarTerrAdy(x0, y0, f.getTipoTerrenoIzq());
		   aux2 = validarTerrAdy(x1, y1, f.getTipoTerrenoDer());
		   
		   if(aux1 && !aux2) {
			   
				//no existe terreno en x0,y0			   
			    actualizarLimites(x0, y0);
			    actualizarLimites(x1, y1);
			   	f.getTipoTerrenoDer().crearRelacion();
			   	f.getTipoTerrenoDer().setCodAsoc(contadorAsoc);
				ListasTerrenos.add(f.getTipoTerrenoDer().getGrupo());
				contadorAsoc++;
				matrizOcupados[x1][y1] = f.getTipoTerrenoDer();
				return true;
		   } else if (!aux1 && aux2) {
			   
			    actualizarLimites(x0, y0);
			    actualizarLimites(x1, y1);
			   	f.getTipoTerrenoIzq().crearRelacion();
			   	f.getTipoTerrenoIzq().setCodAsoc(contadorAsoc);
				ListasTerrenos.add(f.getTipoTerrenoIzq().getGrupo());
				contadorAsoc++;
				matrizOcupados[x0][y0] = f.getTipoTerrenoIzq();
				return true; 
		   }else if (aux1 && aux2) {
			    actualizarLimites(x0, y0);
			    actualizarLimites(x1, y1);
				return true;
		   }
		  
	   }
	   System.out.println("paso por aca");
	   return false;  
   }
   
   private boolean dentroDeTablero(int x0, int y0) {
		if(x0>9 || y0 >9 || x0 < 1 || y0 < 1) {
			return false;
		}
		if(matrizOcupados[x0][y0] == null ) {
			
			
			if(!filasUsadas[x0] && colUsadas[y0] && aux1_altura > 0 ) {
				return true;
			}
			if(filasUsadas[x0] && !colUsadas[y0] && aux2_ancho > 0 ) {
				return true;
			}		
			
			if(filasUsadas[x0] && colUsadas[y0]) {
				return true;
			}
			
			if(!filasUsadas[x0] && !colUsadas[y0] && aux2_ancho > 0 && aux1_altura > 0) {
				return true;
			}
		}
		return false;
   }

	public boolean validarTerrAdy(int x0,int y0,Terreno t_aux) {
	   boolean combino=false,combinaCastillo=false;
	   Terreno a0 = new Terreno(t_aux.getCoronas(),t_aux.getTipo(),t_aux.getColor());
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
		   }else if (terreno != null && terreno.getTipo() == -1) {
			   //Es terreno Castillo. Combina siempre
			   combinaCastillo=true;
		   }
		}
		a0.setCodAsoc(t_aux.getCodAsoc());
		a0.setGrupo(t_aux.getGrupo());
		if(!combino && combinaCastillo) {
			// no combino con ningun caso ady. creo la lista inicial
				a0.crearRelacion();
				a0.setCodAsoc(contadorAsoc);
				ListasTerrenos.add(a0.getGrupo());
				contadorAsoc++;
				matrizOcupados[x0][y0] = a0;
				return true;
			}else if (combino) {
				matrizOcupados[x0][y0] = a0;
				return true;
			}
			return false;
			
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
	@Override
	public String toString() {
		return "Tablero [matrizOcupados=" +matrizOcupados + "]";
	}

	public Terreno obtenerTerreno(int i, int k) {
		
		return matrizOcupados[i][k];
	}

	public int getY0_tablero() {
		return y0_tablero;
	}

	public void setY0_tablero(int y0_tablero) {
		this.y0_tablero = y0_tablero;
	}

	public int getX0_tablero() {
		return x0_tablero;
	}

	public void setX0_tablero(int x0_tablero) {
		this.x0_tablero = x0_tablero;
	}

	@Override
	public void draw(Graphics2D g) {
		for (int i = 0; i < TAM_TABLERO; i++) {
			for (int k = 0; k < TAM_TABLERO; k++) {
				if(matrizOcupados[i][k] != null) {
					if((matrizOcupados[i][k]).getTipo() != -1) {
						g.setColor((matrizOcupados[i][k]).getColor());
					}else {
						g.setColor(Color.black);
					}
					g.fillRect(this.x0_tablero+i*Ficha.TAM_TERRENO,this.y0_tablero+k*Ficha.TAM_TERRENO, Ficha.TAM_TERRENO, Ficha.TAM_TERRENO);
				}
			}
		}
		g.setColor(Color.black);
	}
   
   
   
}
