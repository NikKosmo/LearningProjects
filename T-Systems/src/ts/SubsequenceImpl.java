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

	    return rightOrderCheck(x, y, 0);

    }
    
    //TO DO:
    //Check List x from both sides

    //checks if there is sequence from List x in List y 
    private boolean rightOrderCheck(List x, List y, int positionInListX) {
	
	//if last element is reached, then we can make List x from List y
	//this check prevents IndexOutOfBoundsException
	if (positionInListX >= x.size() - 1)
	    return true;
	
	if (y.indexOf(x.get(positionInListX)) != -1 
		//check if elements in may be placed in right order
		&& y.indexOf(x.get(positionInListX)) < y.lastIndexOf(x.get(positionInListX + 1))) {
	    //if so, create new smaller list and perform rightOrderCheck on it
	    return rightOrderCheck(x, y.subList(y.indexOf(x.get(positionInListX)) + 1, y.size()), positionInListX + 1);
	} else {
	    return false;
	}
    }
}
