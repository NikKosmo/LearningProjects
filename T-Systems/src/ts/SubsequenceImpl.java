package ts;

/*this class lets you check, if 
 * one subsequence contains another subsequence 
 */
import java.util.Arrays;
import java.util.List;

public class SubsequenceImpl implements Subsequence {

    public static void main(String[] args) {
	
	Subsequence s = new SubsequenceImpl();
	boolean b = s.find(Arrays.asList("A", "B", "C", "D"), 
		Arrays.asList("BD", "A", "ABC", "B", "M", "D", "M", "C", "DC", "D"));
	System.out.println(b);

    }

    @Override
    public boolean find(List x, List y) {

	// check if List y contains set of elements from List x
	// this check can be removed, rightOrderCheck works fine by itself
	if (y.containsAll(x)) {
	    // check if they in the right order
	    return rightOrderCheck(x, y, 0);
	} else {
	    return false;
	}
    }

    //checks if there is sequence from List x in List y 
    private boolean rightOrderCheck(List x, List y, int pos) {
	
	//if last element is reached, then we can make List x from List y
	//this check prevents IndexOutOfBoundsException
	if (pos >= x.size() - 1)
	    return true;
	
	if (y.indexOf(x.get(pos)) != -1 
		//check if elements in may be placed in right order
		&& y.indexOf(x.get(pos)) < y.lastIndexOf(x.get(pos + 1))) {
	    //if so, create new smaller list and perform rightOrderCheck on it
	    return rightOrderCheck(x, y.subList(y.indexOf(x.get(pos)) + 1, y.size()), pos + 1);
	} else {
	    return false;
	}
    }
}
