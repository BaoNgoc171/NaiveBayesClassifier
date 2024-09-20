import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NaiveBayes {

    private ArrayList<WordCounter> wordCounters;
    private int totalDocumentSeen;
    private int totalNoSpamDocuments;
    private int totalSpamDocuments;
    private double noSpamScore;
    private double spamScore;

    /* Contructor, initialize variables
    * @param String[] fiocus words
    * */
    public NaiveBayes(String[] focusWords) {
        this.wordCounters = new ArrayList<WordCounter>();
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
            for (WordCounter wc : this.wordCounters) {
                if (wc.getFocusWord().equals(word)) {
                    this.spamScore *= wc.getConditionalSpam();
                    this.noSpamScore *= wc.getConditionalSpam();
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

    /*
    All the classification line in trainingFile should be processed to train NaiveBayes objects
    like addSample method
    If can not read, not exist => throws IOException. FileNotFoundException
     */
    public void trainClassifier(File trainingFile) throws IOException{
        BufferedReader line = null;
        try {
            line = new BufferedReader(new FileReader(trainingFile));
            String fileContent;
            while ((fileContent = line.readLine()) != null) {
                addSample(fileContent);
            }
        }
        catch (IOException e) {
        }
    }



    /*
    File input contain document without classification
    Process the input file to spam(1) or not spam(0)
    then the file output contain only classified document 1 or 0
     */
    public void classifyFile(File input, File output) throws IOException{}

    /*
    Same as trainClassifier method, which return an object of type ConfusionMatrix
    with 4 accuracy measure in testdata file can be obtained
     */
    public ConfusionMatrix computeAccuracy(File testdata) throws IOException{
        return new ConfusionMatrix();
    }
}
