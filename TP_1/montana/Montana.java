package montana;

import java.io.*;
import java.util.*;

public class Montana {
	private int cant_cimas_valles;
	ArrayList<Posicion> cimas_valles = new ArrayList<Posicion>();
	Carrito carrito;

	public Montana(String ruta_archivo) throws IOException {
		File file = new File(ruta_archivo);
		Scanner sc = new Scanner(file);
		String ruta_salida = ruta_archivo.substring(0, ruta_archivo.length() - 2) + "out";
		FileWriter salida = new FileWriter(ruta_salida);

		// Cargo la cantidad de cimas y valles totales
		this.cant_cimas_valles = sc.nextInt();

		// Cargo la primer cima en la posicion x=0
		cimas_valles.add(new Posicion(0, sc.nextInt()));

		// Cargo las cimas y valles con sus coordenadas X, Y
		for (int i = 1; i < cant_cimas_valles; i++) {
			long altura_anterior = cimas_valles.get(i - 1).getPosY();
			long x_anterior = cimas_valles.get(i - 1).getPosX();
			int altura_nueva = sc.nextInt();
			long distancia = Math.abs(altura_anterior - altura_nueva);

			cimas_valles.add(new Posicion(x_anterior + distancia, altura_nueva));
		}

		posicionarCarrito(sc.nextInt());

		//System.out.println(carrito);
		
		while(!carrito.getFinalizo()) {
			desplazarCarrito();
		}

		//System.out.println("");
		//System.out.println(carrito);
		
		sc.close();
		salida.write(carrito.getPosicion().getPosX() + " " + carrito.getPosicion().getPosY());
		salida.close();
	}

	private void desplazarCarrito() {
		
		Posicion next_cima_valle = null;
		long siguiente_ascenso;
		
		try {//error en caso de que el carrito pueda ascender la �ltima cima de la monta�a.
			next_cima_valle = cimas_valles.get(carrito.getNextCimaValle());
		} catch (Exception e) {
			error8(1);
		}

		// El carrito se encuentra por descender, se calcula la velocidad que va a tener
		// en el siguiente valle.
		carrito.setVelocidad(carrito.getVelocidad() + next_cima_valle.getPosX() - carrito.getPosicion().getPosX() - 1);

		carrito.setPosicion(next_cima_valle);
		carrito.incrementarIdCimaValle();

		//System.out.println("\nEl carrito se movi� a:");
		//System.out.println(carrito);

		try {//error en caso de que el carrito llegue a un valle en el cual se termine la monta�a.
			next_cima_valle = cimas_valles.get(carrito.getNextCimaValle());
		} catch (Exception e) {
			error8(2);
		}

		//System.out.println("Siguiente cima: " + next_cima_valle);

		siguiente_ascenso = next_cima_valle.getPosY() - carrito.getPosicion().getPosY();

		//System.out.println("Siguiente ascenso: " + siguiente_ascenso);
		//Si la velocidad no le alcanza para completar el siguiente ascenso se hace un movimiento final
		if (carrito.getVelocidad() < siguiente_ascenso) {
			carrito.mover();
			carrito.setVelocidad(0);
			carrito.setFinalizo(true);
		}
		else {
			carrito.setVelocidad(carrito.getVelocidad() - siguiente_ascenso);
			carrito.setPosicion(next_cima_valle);
			carrito.incrementarIdCimaValle();
		}
	}
	
	private void posicionarCarrito(int posX) {
		Posicion punto_cercano_anterior = null;
		int punto_id = 0;

		// Caso que haya una sola cima y ningun valle.
		if (cant_cimas_valles == 1)
			this.carrito = new Carrito(new Posicion(0, cimas_valles.get(0).getPosY()), true, 0);
		else {
			// Averiguar cual es la cima o valle m�s cercana anterior
			for (int i = 0; i < cant_cimas_valles; i++) {
				if (posX >= cimas_valles.get(i).getPosX()) {
					punto_cercano_anterior = cimas_valles.get(i);
					punto_id = i;// guardamos su posicion para luego saber si era una cima o valle
				} else
					break;
			}

			long posY_cima_valle = punto_cercano_anterior.getPosY();

			// distancia en x entre el carrito y la cima o valle
			long diferencia = posX - punto_cercano_anterior.getPosX();

			long posY;

			// si su punto m�s cercano anterior es un valle o si se encuentra sobre la
			// �ltima cima
			if (punto_id % 2 == 1 || punto_id == cant_cimas_valles - 1) {
				posY = posY_cima_valle + diferencia;
				this.carrito = new Carrito(new Posicion(posX, posY), true, 0);
			} else {// caso contrario se encuentra sobre una cima o en descenso
				posY = posY_cima_valle - diferencia;
				this.carrito = new Carrito(new Posicion(posX, posY), false, 50, punto_id + 1);
			}
		}
	}
	
	public void error8(int num) {
		System.out.println("\n\n-----------------------------------------------------------");
		System.out.println("Conflicto con la ley n�mero 8.\nCorregir archivo de entrada.");
		
		if(num==1)
			System.out.println("\r\nEl carrito pudo ascender la �ltima cima de la monta�a.");
		else
			System.out.println("\r\nEl carrito lleg� hasta un valle en el cual termina la monta�a.");
		
		System.out.println("\r\nLEY 8");
		System.out.println("Un carrito siempre encontrar� una monta�a lo\r\n" + 
				"suficientemente alta que no pueda ascender completamente.");
		
		System.exit(-1);
	}
}
