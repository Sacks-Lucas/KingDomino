package juego;

public class Tablero {
   private Terreno centro;
   
   private int xParedIzq;
   private int xParedDer;
   private int yTecho;
   private int yPiso;
   
   Tablero(Castillo castillo) {
	   centro = castillo;
   }
}
