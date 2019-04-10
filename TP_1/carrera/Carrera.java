package carrera;

import java.io.*;
import java.util.*;

public class Carrera {
	private int cantidad_corredores;
	private int cat_fem;
	private int cat_masc;
	private int corredores_meta;
	private ArrayList<Categoria> categorias = new ArrayList<Categoria>();
	public ArrayList<Corredor> corredores = new ArrayList<Corredor>();

	public Carrera(String ruta_archivo) throws IOException {
		File file = new File(ruta_archivo);
		Scanner sc = new Scanner(file);
		String ruta_salida = ruta_archivo.substring(0, ruta_archivo.length() - 2) + "out";
		FileWriter salida = new FileWriter(ruta_salida);

		// Cargamos las variables con los datos del archivo
		this.cantidad_corredores = sc.nextInt();
		this.cat_fem = sc.nextInt();
		this.cat_masc = sc.nextInt();
		this.corredores_meta = sc.nextInt();

		// Resetear ids de las cotegorias por si se crea más de una carrera en el main
		Categoria.resetId();

		// Cargar las categorias femeninas
		for (int i = 0; i < cat_fem; i++)
			categorias.add(new Categoria('F', sc.nextInt(), sc.nextInt()));

		// Cargar categorias masculinas
		for (int i = 0; i < cat_masc; i++)
			categorias.add(new Categoria('M', sc.nextInt(), sc.nextInt()));

		// Cargar corredores
		for (int i = 0; i < cantidad_corredores; i++) {
			agregarCorredor(sc.nextInt(), sc.next().charAt(0));
		}

		// Determinar ganadores
		for (int i = 0; i < corredores_meta; i++) {
			int id_ganador = sc.nextInt();
			corredores.get(id_ganador - 1).categoria.agregarGanador(id_ganador);
		}

		// Escribir salida
		for (Categoria categoria : categorias) {
			if(categoria.getIdCat() == cat_masc && categoria.getSexo() == 'M')
				salida.write(categoria.toString());
			else
				salida.write(categoria + "\r\n");
		}

		salida.close();
	}

	private void agregarCorredor(int edad, char sexo) {
		for (Categoria categoria : categorias) {
			int min = categoria.getEdad_min();
			int max = categoria.getEdad_max();
			char sexo_cat = categoria.getSexo();

			if (edad >= min && edad <= max && sexo == sexo_cat) {
				corredores.add(new Corredor(edad, sexo, categoria));
				break;
			}
		}
	}
}