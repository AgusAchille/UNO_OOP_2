package montana;

public class Carrito {
	private Posicion posicion_carrito;
	private long velocidad;
	private boolean finalizo = false;
	private int id_next_cima_valle;

	public Carrito(Posicion posicion, boolean finalizo, int velocidad) {
		this.posicion_carrito = posicion;
		this.velocidad = 0;
		this.finalizo = finalizo;
	}

	public Carrito(Posicion posicion, boolean finalizo, int velocidad, int id_next_cima_valle) {
		this.posicion_carrito = posicion;
		this.velocidad = 0;
		this.finalizo = finalizo;
		this.id_next_cima_valle = id_next_cima_valle;
	}
	
	public void incrementarIdCimaValle() {
		id_next_cima_valle++;
	}
	
	public void mover() {
		this.posicion_carrito.aumentarX(velocidad);
		this.posicion_carrito.aumentarY(velocidad);
	}
	
	public void setNextCimaValle(int id) {
		this.id_next_cima_valle = id;
	}

	public int getNextCimaValle() {
		return id_next_cima_valle;
	}

	public void setPosicion(Posicion nueva_posicion) {
		this.posicion_carrito = nueva_posicion;
	}

	public Posicion getPosicion() {
		return posicion_carrito;
	}

	public long getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(long velocidad) {
		this.velocidad = velocidad;
	}

	public boolean getFinalizo() {
		return finalizo;
	}
	
	public void setFinalizo(boolean finalizo) {
		this.finalizo = finalizo;
	}

	@Override
	public String toString() {
		return "Carrito: " + posicion_carrito + "\nVelocidad: " + velocidad + "\nFinaliz�: " + finalizo;
	}
}
