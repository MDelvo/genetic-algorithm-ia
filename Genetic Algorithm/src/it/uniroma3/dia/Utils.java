package it.uniroma3.dia;

public class Utils {
	
	public static void randomizeArray(Object[] array)
	{
		for (int i=0; i<array.length; i++) {
		    int randomPosition = (int)(Math.random()*array.length);
		    Object temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}
	}
}
