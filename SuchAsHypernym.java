/**
 * @author Dor Huri
 * this class is for hypernym with such as pattern.
 */
public class SuchAsHypernym extends BaseHypernym {
    /**
     * this method is the constructor method for class SuchAsHypernym.
     */
    public SuchAsHypernym() {
        //calling the father constructor method
        super.setRegPattern(NP + " (, )?such as " + NP + END_NP);
    }
}