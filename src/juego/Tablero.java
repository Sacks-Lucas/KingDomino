package juego;

import java.util.ArrayList;
import java.util.Arrays;

public class Tablero {
	private Terreno centro;
	private Terreno[][] matrizOcupados = new Terreno[9][9];

	private int maxX = 4;
	private int minX = 4;
	private int maxY = 4;
	private int minY = 4;
	private int totalX = 1;
	private int totalY = 1;
	// ArrayList<Terreno> posAdyacentes;
	private ArrayList<String> posAdyacentes = new ArrayList<String>();;

	public ArrayList<String> getPosAdyacentes() {
		return posAdyacentes;
	}

	public Terreno[][] getMatrizOcupados() {
		return matrizOcupados;
	}

	public Tablero(Castillo castillo) {
		centro = castillo;
		matrizOcupados[4][4] = centro;
	}

	public boolean puedeAgregar(Ficha f, int x, int y) {
		if (dentroDeTablero() && hayLugar(x, y) && combinaTerreno(f, x, y)) {

			return true;
		}
		return false;
	}

	private boolean hayLugar(int x, int y) {
		if (matrizOcupados[x][y] != null) {
			return false;
		}
		if ((x + 1) <= 9) {
			if (matrizOcupados[x + 1][y] == null) {
				posAdyacentes.add("Abajo");
			}

		}
		if ((x - 1) >= 0) {
			if (matrizOcupados[x - 1][y] == null) {
				posAdyacentes.add("Arriba");
			}
		}
		if ((y + 1) <= 9) {
			if (matrizOcupados[x][y + 1] == null) {
				posAdyacentes.add("Derecha");
			}
		}
		if ((y - 1) >= 0) {
			if (matrizOcupados[x][y - 1] == null) {
				posAdyacentes.add("Izquierda");
			}

		}
		if (posAdyacentes == null) {
			return false;
		}
		return true;
	}

	public boolean dentroDeTablero() {

		if (totalX < 4 && totalY < 4) {
			return true;
		}
		return false;
	}

	private boolean combinaTerreno(Ficha f, int x, int y) {
		int terreno1 = f.getTipoTerreno1();
		int terreno2 = f.getTipoTerreno2();

		if ((x + 1) <= 9) {
			if (matrizOcupados[x + 1][y] != null) {
				if (matrizOcupados[x + 1][y].getTipo() == 0 || terreno1 == matrizOcupados[x + 1][y].getTipo())
					return true;
			}
			if (matrizOcupados[x + 1][y] != null) {
				if (matrizOcupados[x + 1][y].getTipo() == 0 || terreno2 == matrizOcupados[x + 1][y].getTipo())
					f.rotarTerreno();
				return true;
			}
		}
		if ((x - 1) >= 0) {
			if (matrizOcupados[x - 1][y] != null) {
				if (matrizOcupados[x - 1][y].getTipo() == 0 || terreno1 == matrizOcupados[x - 1][y].getTipo())
					return true;
			}
			if (matrizOcupados[x - 1][y] != null) {
				if (matrizOcupados[x - 1][y].getTipo() == 0 || terreno2 == matrizOcupados[x - 1][y].getTipo())
					f.rotarTerreno();
				return true;
			}
		}
		if ((y + 1) <= 9) {
			if (matrizOcupados[x][y + 1] != null) {
				if (matrizOcupados[x][y + 1].getTipo() == 0 || terreno1 == matrizOcupados[x][y + 1].getTipo())
					return true;
			}
			if (matrizOcupados[x][y + 1] != null) {
				if (matrizOcupados[x][y + 1].getTipo() == 0 || terreno2 == matrizOcupados[x][y + 1].getTipo())
					f.rotarTerreno();
				return true;
			}
		}
		if ((y - 1) >= 0) {
			if (matrizOcupados[x][y - 1] != null) {
				if (matrizOcupados[x][y - 1].getTipo() == 0 || terreno1 == matrizOcupados[x][y - 1].getTipo())
					return true;
			}
			if (matrizOcupados[x][y - 1] != null) {
				if (matrizOcupados[x][y - 1].getTipo() == 0 || terreno2 == matrizOcupados[x][y - 1].getTipo())
					f.rotarTerreno();
				return true;
			}
		}
		return false;
	}

	public void agregarFicha(Ficha f, int x, int y, int primera) {
		if (x < minX) {
			minX--;
		}
		if (x > maxX) {
			maxX++;
		}
		if (y < minY) {
			minY--;
		}
		if (y > maxY) {
			maxY++;
		}
		totalX = maxX - minX;
		totalY = maxY - minY;
		if (primera == 1) {
			matrizOcupados[x][y] = f.getT1();
		} else {
			matrizOcupados[x][y] = f.getT2();
		}
	}

	@Override
	public String toString() {
		return "Tablero [matrizOcupados=" + matrizOcupados + "]";
	}

	public void mostrarTablero() {
		System.out.print("  0	1	2	3	4	5	6	7	8 \n");
		for (int x = 0; x < matrizOcupados.length; x++) {
			System.out.print(x);
			System.out.print("|");
			for (int y = 0; y < matrizOcupados[x].length; y++) {
				if (matrizOcupados[x][y] == null)
					System.out.print(matrizOcupados[x][y]);
				else
					System.out.print(matrizOcupados[x][y].getTipo());
				if (y != matrizOcupados[x].length - 1)
					System.out.print("\t");
			}
			System.out.println("|");
		}
	}

}
