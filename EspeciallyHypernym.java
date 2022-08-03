/**
 * @author Dor Huri
 * this class is for hypernym with especially pattern.
 */
public class EspeciallyHypernym extends BaseHypernym {
    /**
     * this method is the constructor method for class EspeciallyHypernym.
     */
    public EspeciallyHypernym() {
        //calling the father constructor method
        super.setRegPattern(NP + " (, )?especially " + NP + END_NP);
    }
}
