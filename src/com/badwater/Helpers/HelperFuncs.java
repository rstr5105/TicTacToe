package com.badwater.Helpers;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by irinix on 8/23/14.
 */
public class HelperFuncs {

	/**
	 * Created by irinix on 8/3/14.
	 */
	private static final Random random = new Random ();

	public static int nextIntInRange(int max, int min) {
		return random.nextInt ( ( max - min ) + 1 ) - min;
	}
	public static boolean coinFlip(float f){
		if (f <= random.nextFloat ()){
			return true;
		}
		else return false;
	}
	public static String[] toArgs(String msg) {

		for ( String s : msg.split ( " " ) ) {

		}
		return msg.split ( " " );
	}

	public static boolean boundsCheck(char[][] arr, int y, int x) {
		return x == 0 || y == 0 || x + 1 >= arr[y].length || y + 1 >= arr.length;
	}

	public static String[] prepMsgForLearner(String msg) {
		//Strip All punctuation from the string and convert it to an array, then return
		return msg.replaceAll ( "\\p{Punct}", "" ).toLowerCase ().split ( "\\s+" );
	}

	public static Tuple<Integer, Integer> convertToMove(String s) {
		String[] tmp = s.split(",");
		Tuple<Integer, Integer> retVal;
		Pattern p = Pattern.compile ( "\\d+" );
		retVal = new Tuple(  Integer.parseInt(tmp[0]) , Integer.parseInt ( tmp[1] ) );
		return retVal;
	}

}


