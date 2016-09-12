package ts;

import java.util.Arrays;

public class Tester {

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	Tester tester = new Tester();
	tester.testSubsequenceImpl();

    }
    private void testSubsequenceImpl() {
	Subsequence s = new SubsequenceImpl();
	
	Integer[] array1 = new Integer[4];
	Integer[] array2 = new Integer[20];
	for (int i = 0; i < array1.length; i++) {
	    array1[i] = ((int) (Math.random() * 10));
	    System.out.print(array1[i] + " ");
	}
	System.out.println("");
	for (int i = 0; i < array2.length; i++) {
	    array2[i] = ((int) (Math.random() * 10));
	    System.out.print(array2[i] + " ");
	}
	System.out.println("\n" + s.find(Arrays.asList(array1), Arrays.asList(array2)));
    }

}
