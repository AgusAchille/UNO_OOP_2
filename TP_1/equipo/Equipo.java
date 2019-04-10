package equipo;

public class Equipo implements Comparable<Equipo>{
	String respuesta;
	int integrantes;
	int afinidad;
	
	public Equipo(String respuesta, int integrantes, int afinidad) {
		this.respuesta = respuesta;
		this.integrantes = integrantes;
		this.afinidad = afinidad;
	}

	@Override
	public String toString() {
		return "Respuesta: " + respuesta + "\nIntegrantes:" + integrantes + "\nAfinidad:" + afinidad;
	}

	public String output() {
		return afinidad + "\r\n" + respuesta;
	}
	
	@Override
	public int compareTo(Equipo otro_equipo) {
		return this.afinidad - otro_equipo.afinidad;
	}
	
	
	
}
