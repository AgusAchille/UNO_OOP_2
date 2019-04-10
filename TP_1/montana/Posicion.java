package montana;

public class Posicion {
	private long posX;
	private long posY;

	public Posicion(long posX, long posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public void aumentarX(long distancia) {
		this.posX += distancia;
	}
	
	public void aumentarY(long distancia) {
		this.posY += distancia;
	}
	
	public long getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public long getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	@Override
	public String toString() {
		return "X:" + posX + " Y:" + posY;
	}
}
