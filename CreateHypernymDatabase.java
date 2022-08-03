import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Files;
/**
 * @author Dor Huri
 * this class is for creating hypernym data base from files that their
 * path is sent to us as argument and export new file(or overwrigt if exist)
 * of hypernym and their hyponym in insensitive order.
 */
public class CreateHypernymDatabase {
    public static final int FIRST_ARGUMENT = 0;
    public static final int SECOND_ARGUMENT = 1;
    private final HypernymListObject hypernymList = new HypernymListObject(); //list of hypernym
    /**
     * this main is for creating and exporting the
     * hypernym data base.
     * @param args command line arguments
     * @throws IOException throwing Exception if caught
     */
    public static void main(String[] args) throws IOException {
        //getting the path directory of files
        String path = args[FIRST_ARGUMENT];
        CreateHypernymDatabase createdHypernymDatabase = new CreateHypernymDatabase();
        //creating new file from file path then making a list of files in the directory
        File file = new File(path);
        File[] files = file.listFiles();
        //initialize hypernym list
        createdHypernymDatabase.initializedHypernymObjectList();
        if (files != null) {
            //create hypernym and their hyponym from files
            createdHypernymDatabase.getHypernym(files);
        }
        //export hypernym data base to file according to path in argument receive
        createdHypernymDatabase.exportToFile(args[SECOND_ARGUMENT]);
    }
    /**
     * this method get list of files and get the hypernym and their
     * hyponym according to the pattern that in hypernymList.
     * @param fileList a list of files in path
     * @throws IOException throwing Exception if caught
     */
    public void getHypernym(File[] fileList) throws IOException {
        //iterating trow the files in fileList
        for (File file:fileList) {
            //getting specific file path
            String path = file.getAbsolutePath();
            String fileString = Files.readString(Path.of(path));
            //update file string of every hypernym pattern in hypernymList
            this.hypernymList.updateHypernymsObject(fileString);
            //digest file and get his hypernym and their hyponym
            this.hypernymList.digestHypernym();
        }
    }
    /**
     * this method get the path we create the file in and write the
     * string we get from getHypernym method and export to file.
     * @param filePath the path we create the file in
     * @throws IOException throwing Exception if caught
     */
    public void exportToFile(String filePath) throws IOException {
        //setting the text that get exported to file
        String textFileToExport = this.hypernymList.setText();
        //creating FileWriter with the path we create the file in
        FileWriter fileWriter = new FileWriter(filePath);
        //writing file string, and closing when done
        fileWriter.write(textFileToExport);
        fileWriter.close();
    }
    /**
     * this method initialize hypernym list with the 5 hypernym pattern.
     */
    public void initializedHypernymObjectList() {
        //initializing list by adding the 5 hypernym pattern
        this.hypernymList.addHypernymObject(new SuchAsHypernym());
        this.hypernymList.addHypernymObject(new WhichIsHypernym());
        this.hypernymList.addHypernymObject(new EspeciallyHypernym());
        this.hypernymList.addHypernymObject(new AsHypernym());
        this.hypernymList.addHypernymObject(new IncludingHypernym());
    }
    /**
     * this method return the hypernymList.
     * @return the hypernymList
     */
    public HypernymListObject getHypernymList() {
        return hypernymList;
    }
}
