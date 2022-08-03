import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author Dor Huri
 * this class is for hypernym with which is pattern.
 */
public class WhichIsHypernym extends BaseHypernym {
    /**
     * this method is the constructor method for class WhichIsHypernym.
     */
    public WhichIsHypernym() {
        //calling the father constructor method
        super.setRegPattern(NP + " (, )?which is ((an example|a kind|a class)? of )?" + NP);
    }
    @Override
    public void catchHypernym(Map<String, HypernymMap> hypernymMap) {
        //compiling pattern and match in file string
        Pattern pattern = Pattern.compile(this.getRegPattern());
        Matcher matcher = pattern.matcher(this.getFileString());
        //compile pattern of np
        Pattern npPattern = Pattern.compile(NP);
        Matcher endSubMatch;
        //hypernymKeyMap for inserting to hypernymMap
        HypernymMap hypernymKeyMap;
        //while there is a match in regexMatcher
        while (matcher.find()) {
            //assigning the pattern we catch in textCatch
            String textCatch = matcher.group();
            //creating matcher for sub string
            endSubMatch = npPattern.matcher(textCatch);
            //finding the next pattern according npPattern
            endSubMatch.find();
            //according to which is pattern this is the hyponym
            String value = endSubMatch.group(GROUP_CATCH);
            //finding the next pattern according npPattern
            endSubMatch.find();
            //according which is pattern this is the hypernym
            String key = endSubMatch.group(GROUP_CATCH);
            //if the key is already exist in hypernymMap
            if (hypernymMap.containsKey(key)) {
                //hypernymKeyMap get assigned with hypernymMap value
                hypernymKeyMap = hypernymMap.get(key);
                //adding the value in hypernymKeyMap
                hypernymKeyMap.addToMap(value);
            } else {
                //create new HypernymMap
                hypernymKeyMap = new HypernymMap();
                //adding the value in hypernymKeyMap
                hypernymKeyMap.addToMap(value);
                //assign in map the new key
                hypernymMap.put(key, hypernymKeyMap);
            }
        }

    }

}
