import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
/**
 * @author Dor Huri
 * this class is for HypernymListObject who hold the HypernymMap and hypernymList
 * that trow thus data structure we get the hypernym and their hyponym.
 */
public class HypernymListObject {
    public static final int HYPONYM_MIN = 3;
    private final Map<String, HypernymMap> hypernymMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private final ArrayList<Hypernym> hypernymList = new ArrayList<>();
    /**
     * this method is for adding hypernym to hypernymList.
     * @param hypernym the hypernym pattern
     */
    public void addHypernymObject(Hypernym hypernym) {
        hypernymList.add(hypernym);
    }
    /**
     * this method is for updating the string that been digest
     * for finding hypernym.
     * @param fileString the string of the file
     */
    public void updateHypernymsObject(String fileString) {
        for (Hypernym hypernym : this.hypernymList) {
            //setting hypernym string
            hypernym.setFileString(fileString);
        }
    }
    /**
     * this method is for getting the hypernym and their hyponym
     * from the file string for each hypernym pattern in hypernymList.
     */
    public void digestHypernym() {
        for (Hypernym hypernym : this.hypernymList) {
            //catching hypernym from file string
            hypernym.catchHypernym(this.hypernymMap);
        }
    }
    /**
     * this method is for setting the text according
     * to format of part 1 using string builder.
     * @return the text file
     */
    public String setText() {
        StringBuilder stringBuilder = new StringBuilder();
        //iterating trow the map key(hypernym)
        for (String key : this.hypernymMap.keySet()) {
            //only taking hypernym with 3 or more hyponym
            if (this.hypernymMap.get(key).numOfHyponym() >= HYPONYM_MIN) {
                //creating line of text line
                stringBuilder.append(key).append(": ");
                stringBuilder.append(this.hypernymMap.get(key).stringFormat1()).append("\n");
            }
        }
        //returning all the text of file with stringBuilder
        return stringBuilder.toString();
    }
    /**
     * this method is for finding all the lemma that are in the
     * map and returning it in a new HypernymMap .
     * @param lemma the hyponym that we search his hypernym
     * @return the lemmaTree HypernymMap
     */
    public HypernymMap findLemma(String lemma) {
        //creating the lemma map tree which we create the lemma hypernym
        HypernymMap lemmaTree = new HypernymMap();
        //iterating trow keys in HypernymMap
        for (String key : this.hypernymMap.keySet()) {
            //getting map from HypernymMap value
            HypernymMap lemmaMap = this.hypernymMap.get(key);
            //getting the lemma key if exist in lemmaMap
            String lemmaKey = lemmaMap.findLemmaVal(lemma);
            //if lemma exist in lemmaMap keys
            if (lemmaKey != null) {
                /*
                add to lemmaTree the hypernym and
                 the lemma(hyponym) value for this hypernym
                 */
                lemmaTree.addToLemmaMap(key, lemmaMap.getValueMap(lemmaKey));
            }
        }
        //sorting lemmaTree in descending order according to value
        lemmaTree.mapSortDescending();
        return lemmaTree;
    }
    /**
     * this method is for setting the text according
     * to format of part 2 using string builder.
     * @param lemmaMap the map we set text from
     * @return the lemma string to be printed
     */
    public String setLemmaText(HypernymMap lemmaMap) {
        StringBuilder stringBuilder = new StringBuilder();
        //iterating trow the lemmaMap key(hypernym)
        for (String key : lemmaMap.getKeySet()) {
            //creating line of text line
            stringBuilder.append(key).append(": ");
            stringBuilder.append("(").append(lemmaMap.getValueMap(key)).append(")\n");
        }
        //returning all the lemma string to be printed with stringBuilder
        return stringBuilder.toString();
    }
}
