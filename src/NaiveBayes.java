import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class NaiveBayes {

    private ArrayList<WordCounter> wordCounters;
    private int totalDocumentSeen;
    private int totalNoSpamDocuments;
    private int totalSpamDocuments;
    private double noSpamScore;
    private double spamScore;

    /* Constructor, initialize variables
    * @param String[] focus words
    */
    public NaiveBayes(String[] focusWords) {
        this.wordCounters = new ArrayList<>();
        for (String focusWord : focusWords) {
            WordCounter wc = new WordCounter(focusWord);
            this.wordCounters.add(wc);
        }
        this.totalDocumentSeen = 0;
        this.totalNoSpamDocuments = 0;
        this.totalSpamDocuments = 0;
        this.noSpamScore = 0;
        this.spamScore = 0;
    }

    /* When adding document sample, the class update
    * word counters and keep track of the total number
    * of spam and no spam documents.
    *
    * @param String document
    * */
    public void addSample(String document) {
        for (WordCounter wc : wordCounters) {
            wc.addSample(document);
            if (wc.isSpam(document)) {
                this.totalSpamDocuments++;
            } else {
                this.totalNoSpamDocuments++;
            }
            this.totalDocumentSeen++;
        }
        computeNoSpamScore();
        computeSpamScore();
    }

    public boolean classify(String unclassifiedDocument) {
        String[] words = unclassifiedDocument.split(" ");
        for (String word : words) {
            computeSpamScore();
            computeNoSpamScore();
            for (WordCounter wc : this.wordCounters) {
                if (wc.getFocusWord().equals(word)) {
                    this.spamScore *= wc.getConditionalSpam();
                    this.noSpamScore *= wc.getConditionalNoSpam();
                }
            }
        }
        return this.noSpamScore < this.spamScore;
    }

    private void computeSpamScore() {
        this.spamScore = (double) this.totalSpamDocuments / this.totalDocumentSeen;
    }

    private void computeNoSpamScore() {
        this.noSpamScore = (double) this.totalNoSpamDocuments / this.totalDocumentSeen;
    }

    /* Read the file and process line by line
    * @param File training file
    * */
    public void trainClassifier(File trainingFile) throws IOException{
        try (Scanner scanner = new Scanner(trainingFile)) {
            while (scanner.hasNextLine()) {
                String document = scanner.nextLine();
                addSample(document);
            }
        }
    }

    /* Read the file and write the classify
    * results line by line to the output file
    * @param File input file
    * @param File output file
    * */
    public void classifyFile(File input, File output) throws IOException{
        // Read the input and classify
        ArrayList<Boolean> results = new ArrayList<>();
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                String document = scanner.nextLine();
                results.add(classify(document));
            }
        }

        // Write the results in output file
        try (FileWriter fw = new FileWriter(output)) {
            for (Boolean result : results) {
                if (result) {
                    fw.write("1");
                    fw.write("\n");
                } else {
                    fw.write("0");
                    fw.write("\n");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String [] words = {"good", "bad"};
        NaiveBayes nb = new NaiveBayes(words);
        nb.trainClassifier(new File("trainData.txt"));
        nb.classifyFile(new File("newData.txt"), new File("classifications.txt"));
    }

    public ConfusionMatrix computeAccuracy(File testData) throws IOException{
        ArrayList<Integer> groundTruthList = new ArrayList<>();

        // Read the input and classify
        ArrayList<Boolean> results = new ArrayList<>();
        try (Scanner scanner = new Scanner(testData)) {
            while (scanner.hasNextLine()) {
                String document = scanner.nextLine();
                // Save the ground truth
                groundTruthList.add((int) document.charAt(0));
                // Strip the identifier at the beginning of each line
                String unclassifiedDocument = document.substring(2);
                results.add(classify(unclassifiedDocument));
            }
        }

        // Convert results boolean to integer
        ArrayList<Integer> classFieldList = new ArrayList<>();
        for (Boolean result : results) {
            if (result) {
                classFieldList.add(1);
            } else {
                classFieldList.add(0);
            }
        }

        // Construct confusion matrix
        return new ConfusionMatrix(groundTruthList, classFieldList);
    }
}
