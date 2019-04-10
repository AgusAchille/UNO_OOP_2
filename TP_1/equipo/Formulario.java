package equipo;

import java.io.*;
import java.util.*;

public class Formulario {
	int preguntas;
	int colaboradores;
	ArrayList<Equipo> equipos = new ArrayList<Equipo>();
	ArrayList<String> respuestas = new ArrayList<String>();
	HashMap<String, Integer> palabras;

	public Formulario(String ruta_archivo) throws IOException {
		File file = new File(ruta_archivo);
		String ruta_salida = ruta_archivo.substring(0, ruta_archivo.length()-2) + "out"; 
		FileWriter salida = new FileWriter(ruta_salida);
		Scanner sc = new Scanner(file);
		

		this.preguntas = sc.nextInt();
		this.colaboradores = sc.nextInt();

		for (int i = 0; i < colaboradores; i++)
			respuestas.add(sc.next());

		analizarRespuestas();

		sc.close();

		if(equipos.size()>0)
			salida.write(Collections.max(equipos).output());
		else
			salida.write("0");
		salida.close();
	}

	private void analizarRespuestas() {
		/*
		 * Analizamos todas las respuestas con solo una respuesta y al equipo con mayor
		 * afinidad lo agregamos al array de equipos. Luego repetimos el proceso con
		 * solo 2 respuestas, luego con solo 3... hasta solo n.
		 */

		for (int i = 0; i < preguntas; i++) {
			//Reseteamos el HashMap de palabras
			palabras = new HashMap<String, Integer>();
			
			for (String respuesta : respuestas) {
				//Cortamos la palabra segun la cantidad de respuestas que queremos analizar
				String palabra = respuesta.substring(0, i + 1);

				if (palabras.get(palabra) == null)
					// Si la palabra no se encuentra en el HashMap, la agregamos y le inicializamos el contador en 1.
					palabras.put(palabra, 1);
				else
					// Si la palabra se encuentra en el HashMap, incrementamos en 1 el contador asociado.
					palabras.put(palabra, palabras.get(palabra) + 1);
			}
			
			// Una vez listada la cantidad de veces que las palabras se repiten, buscamos la que más se repite
			// Y agregamos el equipo al array con su afinidad calculada.
			buscarMejorEquipo();
		}
	}

	private void buscarMejorEquipo() {
		String respuesta = "";
		int integrantes = 0;
		int afinidad;
		int cantidad_respuestas;

		// ----------------------------------------------
		// entrySet() me devuelve un Set de Map.Entry<K, V>
		Set<Map.Entry<String, Integer>> set_palabras = palabras.entrySet();

		// Recorro el Set
		for (Map.Entry<String, Integer> entry : set_palabras) {
			String clave = entry.getKey();
			Integer valor = entry.getValue();

			if (valor > integrantes) {
				integrantes = valor;
				respuesta = clave;
			}
		}

		if (integrantes >= 2) {
			cantidad_respuestas = respuesta.length();
			afinidad = (int) Math.pow(cantidad_respuestas, 2) * integrantes;
			equipos.add(new Equipo(respuesta, integrantes, afinidad));
		}
	}
}
