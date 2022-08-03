/**
 * @author Dor Huri
 * this class is for hypernym with as pattern.
 */
public class AsHypernym extends BaseHypernym {
    /**
     * this method is the constructor method for class AsHypernym.
     */
    public AsHypernym() {
        //calling the father constructor method
        super.setRegPattern("such " + NP + " as " + NP + END_NP);
    }
}
