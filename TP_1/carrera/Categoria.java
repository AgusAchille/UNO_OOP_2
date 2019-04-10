package carrera;
import java.util.ArrayList;

public class Categoria {
	private int id_cat;
	private static int next_id_cat_f = 1;
	private static int next_id_cat_m = 1;
	private char sexo;
	private int edad_min;
	private int edad_max;
	private int oro;
	private int plata;
	private int bronce;
	private int ganadores;
	
	public Categoria(char sexo, int edad_min, int edad_max) {
		this.sexo = sexo;
		this.edad_min = edad_min;
		this.edad_max = edad_max;
		
		if(sexo == 'F')
			this.id_cat = next_id_cat_f++;
		else
			this.id_cat = next_id_cat_m++;
		
		this.ganadores = 0;
	}
	
	public void agregarGanador(int id) {
		switch(ganadores++) {
			case 0:
				oro = id;
				break;
			case 1:
				plata = id;
				break;
			case 2:
				bronce = id;
				break;
		}
	}
	
	@Override
	public String toString() {
		return id_cat + " " + oro + " " + plata +" " + bronce;
	}
	
	public static void resetId() {
		next_id_cat_f = 1;
		next_id_cat_m = 1;
	}

	public char getSexo() {
		return sexo;
	}
	
	public int getIdCat() {
		return id_cat;
	}

	public int getEdad_min() {
		return edad_min;
	}

	public int getEdad_max() {
		return edad_max;
	}
	
}
