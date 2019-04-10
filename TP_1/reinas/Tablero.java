package reinas;

import java.io.*;
import java.util.*;

public class Tablero {
	private int dimension;
	private int cantidad_reinas;
	private ArrayList<Reina> reinas = new ArrayList<Reina>();
	private HashMap<Coordenada, Integer> coordenadas = new HashMap<Coordenada, Integer>();

	public Tablero(String ruta_archivo) throws IOException {
		File archivo = new File(ruta_archivo);
		String ruta_salida = ruta_archivo.substring(0, ruta_archivo.length()-2) + "out"; 
		FileWriter salida = new FileWriter(ruta_salida);
		Scanner sc = new Scanner(archivo);
		
		Reina.resetReinaId();//Por si se crea más de un tablero en el main
		this.dimension = sc.nextInt();
		this.cantidad_reinas = sc.nextInt();

		for (int i = 0; i < cantidad_reinas; i++) {
			int fila = sc.nextInt();
			int columna = sc.nextInt();
			int ultimo_id_ingresado;

			reinas.add(new Reina(fila, columna));
			ultimo_id_ingresado = reinas.get(reinas.size() - 1).getId();
			coordenadas.put(new Coordenada(fila, columna), ultimo_id_ingresado);
		}

		detertarConflictos();
		
		//Escribir archivo de salida
		for (Reina reina : reinas) {
			if(reina.getId()!=reinas.size())//si no es la última reina agregar un salto de linea
				salida.write(reina.toString() + "\r\n");				
			else
				salida.write(reina.toString());
		}
		
		salida.close();
	}

	private void detertarConflictos() {
		for (Reina reina : reinas) {
			int fila = reina.getCoordenada().getFila();
			int columna = reina.getCoordenada().getColumna();
			int oponente;

			// Fila, parte izquierda
			for (int i = columna - 1; i >= 1; i--) {
				oponente = hayReina(fila, i);
				if (oponente != 0) {
					reina.agregarConflicto(oponente);
					break;
				}
			}

			// Fila, parte derecha
			for (int i = columna + 1; i <= dimension; i++) {
				oponente = hayReina(fila, i);
				if (oponente != 0) {
					reina.agregarConflicto(oponente);
					break;
				}
			}

			// Columna, parte superior
			for (int i = fila - 1; i >= 1; i--) {
				oponente = hayReina(i, columna);
				if (oponente != 0) {
					reina.agregarConflicto(oponente);
					break;
				}
			}

			// Columna, parte inferior
			for (int i = fila + 1; i <= dimension; i++) {
				oponente = hayReina(i, columna);
				if (oponente != 0) {
					reina.agregarConflicto(oponente);
					break;
				}
			}

			// Diagonal Izquierda Superior
			for (int i = fila - 1, j = columna - 1; i >= 1 && j >= 1; i--, j--) {
				oponente = hayReina(i, j);
				if (oponente != 0) {
					reina.agregarConflicto(oponente);
					break;
				}
			}

			// Diagonal Derecha Superior
			for (int i = fila - 1, j = columna + 1; i >= 1 && j <= dimension; i--, j++) {
				oponente = hayReina(i, j);
				if (oponente != 0) {
					reina.agregarConflicto(oponente);
					break;
				}
			}

			// Diagonal Derecha Inferior
			for (int i = fila + 1, j = columna + 1; i <= dimension && j <= dimension; i++, j++) {
				oponente = hayReina(i, j);
				if (oponente != 0) {
					reina.agregarConflicto(oponente);
					break;
				}
			}
			
			// Diagonal Izquierda Inferior
			for (int i = fila + 1, j = columna - 1; i <= dimension && j >= 1; i++, j--) {
				oponente = hayReina(i, j);
				if (oponente != 0) {
					reina.agregarConflicto(oponente);
					break;
				}
			}
		}
	}

	private int hayReina(int fila, int columna) {
		if (coordenadas.get(new Coordenada(fila, columna)) != null)
			return coordenadas.get(new Coordenada(fila, columna));

		return 0;
	}
}