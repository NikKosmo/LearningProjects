package CodeWars;

import java.util.Arrays;

public class Kata {
    public static void main(String[] args) {
	System.out.println(HighAndLow("-7 1 2 3 8 4 5"));
    }
    
    public static String HighAndLow(String numbers) {
	String [] stringNum= numbers.split(" ");
	int [] intNum = new int[stringNum.length];
	for (int i = 0; i < stringNum.length; i++) {
	    intNum[i] = Integer.parseInt(stringNum[i]);
	}
	Arrays.sort(intNum);
	return intNum[0] + " " + intNum[intNum.length-1];
    }
}
