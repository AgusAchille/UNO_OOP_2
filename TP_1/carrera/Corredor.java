package carrera;
public class Corredor {
	private static int next_id = 1;
	private char sexo;
	private int edad;
	private int id_corredor;
	Categoria categoria;
	
	public Corredor(int edad, char sexo, Categoria categoria) {		
		this.edad = edad;
		this.sexo = sexo;
		this.categoria = categoria;
		this.id_corredor = next_id++;
	}

	public char getSexo() {
		return sexo;
	}

	public int getEdad() {
		return edad;
	}

	public int getId() {
		return id_corredor;
	}
	
	@Override
	public String toString() {
		return id_corredor + " " + sexo + " " + edad + " " + categoria;
	}
}
