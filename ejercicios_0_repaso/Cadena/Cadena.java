package Cadena;

public class Cadena {
	public static void frecuencias(String cadena) {
		int[] array = new int[256];
		int letra_cod;
		
		//recorro cada caracter de la cadena recibida
		for(int i=0; i<cadena.length(); i++) {
			//guardo el código ascii del caracter
			letra_cod = (int)cadena.charAt(i);
			
			//si el caracter es una letra aumento el contador en el array para su código
			if(Character.isLetter(cadena.charAt(i)))
				array[letra_cod]++;
		}
		
		//recorro el array y muestro solo en los que el contador es mayor 0
		for(int i=0; i<array.length; i++) {
			if(array[i]!=0) {
				System.out.println((char)i + ": " + array[i]);
			}
				
		}
		
	}
}
