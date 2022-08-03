import java.util.Map;
/**
 * @author Dor Huri
 * this interface is for all kind of the Hypernym that diffrence
 * in their pattern so we could catch all Hypernym pattern.
 */
public interface Hypernym {
    /**
     * this method catch all the Hypernym pattern in the file.
     * @param map the map which hold the Hypernym
     */
    void catchHypernym(Map<String, HypernymMap> map);
    /**
     * this method set the string of the file in fileString filed.
     * @param fileString the string of the file
     */
    void setFileString(String fileString);
}
