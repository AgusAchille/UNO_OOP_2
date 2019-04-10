package reinas;

import java.util.*;

public class Coordenada {
	private int fila;
	private int columna;
	
	public Coordenada(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}
	
	public int getColumna() {
		return columna;
	}
	
	public int getFila() {
		return fila;
	}

	@Override
	public int hashCode() {
		return Objects.hash(columna, fila);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Coordenada))
			return false;
		Coordenada other = (Coordenada) obj;
		return columna == other.columna && fila == other.fila;
	}
}