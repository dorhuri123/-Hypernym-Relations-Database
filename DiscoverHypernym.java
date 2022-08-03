import java.io.File;
import java.io.IOException;
/**
 * @author Dor Huri
 * this class is for finding all the Hypernym in
 * the files of a giving lemma and printing in console.
 */
public class DiscoverHypernym {
    public static final int FIRST_ARGUMENT = 0;
    public static final int SECOND_ARGUMENT = 1;
    /**
     * this main is for finding all the Hypernym in
     * the files of the lemma and print to console.
     * @param args command line arguments
     * @throws IOException throwing Exception if caught
     */
    public static void main(String[] args) throws IOException {
        //creating new data base Hypernym object
        CreateHypernymDatabase createdHypernymDatabase = new CreateHypernymDatabase();
        //getting the path directory of files
        String path = args[FIRST_ARGUMENT];
        //creating new file from file path then making a list of files in the directory
        File file = new File(path);
        File[] files = file.listFiles();
        //initialize hypernym list
        createdHypernymDatabase.initializedHypernymObjectList();
        //create hypernym and their hyponym from files
        createdHypernymDatabase.getHypernym(files);
        HypernymListObject hypernymList = createdHypernymDatabase.getHypernymList();
        //calling find lemma with the list we created and getting HypernymMap
        HypernymMap lemmaTree =  hypernymList.findLemma(args[SECOND_ARGUMENT]);
        //checking if tree is empty so no lemma found
        if (lemmaTree.getKeySet().isEmpty()) {
            System.out.println("The lemma doesn't appear in the corpus.");
            return;
        }
        //setting lemma text format in text
        String text = hypernymList.setLemmaText(lemmaTree);
        //printing to console
        System.out.println(text);
    }
}
