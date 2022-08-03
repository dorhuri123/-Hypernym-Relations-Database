import java.util.regex.Pattern;
import java.util.Map;
import java.util.regex.Matcher;
/**
 * @author Dor Huri
 * this class implement Hypernym interface and all the Hypernym pattern inherit from him
 * so we find all the Hypernym and their Hyponym according to the regex pattern.
 */
public class BaseHypernym implements Hypernym {
    public static final int GROUP_CATCH = 3; //num of group that get catch
    private   String fileString; // the string of the file
    private String regPattern; //the pattern of Hypernym
    protected static final String NP = "((<np>)([^<>]+)(</np>))"; //the np regex pattern

    //the end np regex pattern
    protected static final String END_NP = "( (, )?(<np>)([^<>]+)(</np>))*(( ,)? (and|or) (<np>)([^<>]+)(</np>))?";
    @Override
    public void catchHypernym(Map<String, HypernymMap> hypernymMap) {
        //compiling pattern and match in file string
        Pattern regexPattern = Pattern.compile(this.regPattern);
        Matcher regexMatcher = regexPattern.matcher(this.fileString);
        //compile pattern of np
        Pattern npPattern = Pattern.compile(NP);
        Matcher regexSubMatch;
        //hypernymKeyMap for inserting to hypernymMap
        HypernymMap hypernymKeyMap;
        //while there is a match in regexMatcher
        while (regexMatcher.find()) {
            //assigning the pattern we catch in textCatch
            String textCatch = regexMatcher.group();
            //creating matcher for sub string
            regexSubMatch = npPattern.matcher(textCatch);
            //finding the next pattern according npPattern
            regexSubMatch.find();
            //finding the first np in the string we catch
            String key = regexSubMatch.group(GROUP_CATCH);
            //if the key is already exist in hypernymMap
            if (hypernymMap.containsKey(key)) {
                //hypernymKeyMap get assigned with hypernymMap value
                hypernymKeyMap = hypernymMap.get(key);
            } else {
                //create new HypernymMap
                hypernymKeyMap = new HypernymMap();
                //assign in map the new key
                hypernymMap.put(key, hypernymKeyMap);
            }
            //while regexSubMatch has a match
            while (regexSubMatch.find()) {
                //getting the value of the key
                String value = regexSubMatch.group(GROUP_CATCH);
                //adding the value in hypernymKeyMap
                hypernymKeyMap.addToMap(value);
            }
        }
    }
    @Override
    public void setFileString(String fileString1) {
        //setting file string
        this.fileString = fileString1;
    }
    /**
     * this method return the file string of the Hypernym.
     * @return the file string of the Hypernym
     */
    protected String getFileString() {
        return this.fileString;
    }
    /**
     * this method return the regex expression of the Hypernym.
     * @return the regex expression of the Hypernym
     */
    protected String getRegPattern() {
        return this.regPattern;
    }
    /**
     * this method set the string of the regex expression of the Hypernym.
     * @param regPattern1 the string of the regex expression
     */
    protected void setRegPattern(String regPattern1) {
        this.regPattern = regPattern1;
    }
}
