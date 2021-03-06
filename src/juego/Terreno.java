package juego;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

public class Terreno {
	private int cantCoronas;
	private int tipo;
	private List<Terreno> terrenosRelacionados = null;
	private int codAsociacion=0;
	private Color color;
	
	public Terreno( int cantCoronas,int valor,Color c) {
		this.tipo = valor;
		this.cantCoronas = cantCoronas;
		this.color = c;
	}
	
	public void setCodAsoc(int n) {
		this.codAsociacion = n;
	}

	public int getTipo() {
		
		return this.tipo;
	}
	
	public void crearRelacion() {
		this.terrenosRelacionados = new LinkedList<Terreno>();
		this.terrenosRelacionados.add(this);
	}
	
	public void relTerrenoAGrupo(Terreno t) {
		t.setCodAsoc(codAsociacion);
		this.terrenosRelacionados.add(t);
		t.terrenosRelacionados = this.terrenosRelacionados;
	}
	public List<Terreno> getGrupo() {
		return terrenosRelacionados;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tipo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Terreno other = (Terreno) obj;
		if (tipo != other.tipo)
			return false;
		return true;
	}

	@Override
	protected Terreno clone()  {
		Terreno t = new Terreno (this.cantCoronas,this.tipo,this.color);
		return t;
	}

	public void combinarGrupos(Terreno t_aux)  {
		
		for (Terreno terreno : t_aux.getGrupo()) {
			terreno.setCodAsoc(this.codAsociacion);
		}
		this.terrenosRelacionados.addAll(t_aux.getGrupo());
		t_aux.terrenosRelacionados = this.terrenosRelacionados;
	}

	public int getCoronas() {
		return cantCoronas;
	}

	public void setGrupo(List<Terreno> grupo) {
		this.terrenosRelacionados = grupo;
	}

	public int getCodAsoc() {
		return this.codAsociacion;
	}

	public Color getGraficos() {
		
		return null;
	}

	public Color getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}


	
	
	
}
