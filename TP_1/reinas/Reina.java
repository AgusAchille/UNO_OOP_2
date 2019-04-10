package reinas;

import java.util.*;

public class Reina {
	private static int next_id = 1;
	private int id_reina;
	Coordenada coordenada;
	ArrayList<Integer> conflictos = new ArrayList<Integer>();

	public Reina(int fila, int columna) {
		this.coordenada = new Coordenada(fila, columna);
		id_reina = next_id++;
	}

	public void agregarConflicto(int id_reina) {
		conflictos.add(id_reina);
	}

	public int getId() {
		return id_reina;
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}
	
	public static void resetReinaId() {
		next_id = 1;
	}
	
	
	@Override
	public String toString() {
		Collections.sort(conflictos);
		String linea = conflictos.size() + "";

		for (Integer conflicto : conflictos)
			linea = linea + " " + conflicto;

		return linea;
	}
}
