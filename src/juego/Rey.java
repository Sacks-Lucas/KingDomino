package juego;

public class Rey implements Comparable<Rey>{
	private String color;
	private int posicion;
	
	Rey(String color) {
		this.color = color;
		this.posicion = 0;
	}

	@Override
	public int compareTo(Rey o) {
		// TODO Auto-generated method stub
		return this.posicion-o.posicion;
	}

	@Override
	public String toString() {
		return "Rey [color=" + color + ", posicion=" + posicion + "]";
	}

	public void posicionar(int indexFicha) {
		this.posicion = indexFicha;
	}

	public int getPosicion() {
		// TODO Auto-generated method stub
		return posicion;
	}
	
	
	
}
