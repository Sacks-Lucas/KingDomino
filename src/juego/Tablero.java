package juego;

public class Tablero {
   private Casillero centro;
   
   Tablero(Castillo castillo) {
	   this.centro = new Casillero(castillo);
   }
}
