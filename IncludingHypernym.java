/**
 * @author Dor Huri
 * this class is for hypernym with including pattern.
 */
public class IncludingHypernym extends BaseHypernym {
    /**
     * this method is the constructor method for class IncludingHypernym.
     */
    public IncludingHypernym() {
        //calling the father constructor method
        super.setRegPattern(NP + " (, )?including " + NP + END_NP);
    }
}
