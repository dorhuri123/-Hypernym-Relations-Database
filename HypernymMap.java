import java.util.Comparator;
import java.util.TreeMap;
import java.util.Map;
/**
 * @author Dor Huri
 * this class is for the HypernymMap whice is the map inside(the value)
 * of our originale map so we could have  for every key in the original
 * tree multiple number of value.
 */
public class HypernymMap {
    private Map<String, Integer> hypernymMap = new TreeMap<>();
    /**
     * this method is for adding to the HypernymMap.
     * @param mapVal the key of HypernymMap
     */
    public void addToMap(String mapVal) {
        /*
        using merge so every key who is already assign to key will get another
        assignment we increment this key value by 1 using reference function Integer::sum
        */
        this.hypernymMap.merge(mapVal, 1, Integer::sum);
    }
    /**
     * this method is for adding to the lemmaMap.
     * @param lemma the key of lemmaMap
     * @param size the value of the key
     */
    public void addToLemmaMap(String lemma, Integer size) {
        this.hypernymMap.put(lemma, size);
    }
    /**
     * this method is for setting the HypernymMap key and value
     * according to format of part 1 using string builder.
     * @return the HypernymMap string according to format
     */
    public String stringFormat1() {
        //sorting the HypernymMap in descending order
        this.mapSortDescending();
        StringBuilder stringBuilder = new StringBuilder();
        //iterating trow the HypernymMap key(hypernym)
        for (String key : this.hypernymMap.keySet()) {
            //creating line of string according format
            stringBuilder.append(key).append(" (").append(this.hypernymMap.get(key)).append("), ");
        }
        //if there is more then 2 characters in stringBuilder
        if (stringBuilder.length() > 2) {
            //delete the last 2 characters the extra ", "
            stringBuilder.setLength(stringBuilder.length() - 2);
        }
        return stringBuilder.toString();
    }
    /**
     * this method return the number of Hyponym that an hypernym have.
     * @return the number of Hyponym
     */
    public int numOfHyponym() {
        return this.hypernymMap.keySet().size();
    }
    /**
     * this method is for sorting the HypernymMap in descending order.
     */
    public void mapSortDescending() {
        //creating temporary map
        Map<String, Integer> integerMap = this.hypernymMap;
        //creating the sorted tree and his comparator method
        Map<String, Integer> sortedMap = new TreeMap<>(new Comparator<String>() {
            @Override
          public int compare(String key1, String key2) {
            /*
            comparing according to value of key and return result so if we get 0
            in compare we still can have duplicated value in sorted map
            */
                int compare = integerMap.get(key2).compareTo(integerMap.get(key1));
                return compare != 0 ? compare : key1.compareTo(key2);
            }
        });
        //all the map is now in sortedMap in descending order
        sortedMap.putAll(integerMap);
        //assigning hypernymMap to sortedMap
        this.hypernymMap = sortedMap;
    }
    /**
     * this method return the key of hypernymMap in set.
     * @return the key of map in a set
     */
    public java.util.Set<String> getKeySet() {
        return this.hypernymMap.keySet();
    }
    /**
     * this method return the key that is equal to the lemma.
     * @param lemma a
     * @return the key of map in a set
     */
    public String findLemmaVal(String lemma) {
        //iterating trow the HypernymMap key
        for (String key : this.getKeySet()) {
            //if lemma is one of the key
            if (lemma.equals(key)) {
                //return the key
                return key;
            }
        }
        //else return null
        return null;
    }
    /**
     * this method return the value of the key.
     * @param key the key which value is returned
     * @return the value of the key
     */
    public Integer getValueMap(String key) {
        return this.hypernymMap.get(key);
    }
}
