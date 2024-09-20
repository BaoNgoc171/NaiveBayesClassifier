import java.io.File;
import java.io.IOException;
import java.net.PortUnreachableException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NaiveBayes {
    private List<WordCounter> wordCounters;
    private int totalNumberSpam;
    private int totalNumberNonSpam;
    private int totalDocument;
    private double spamScore;
    private double nonSpamScore;

    /*
    String [] is an array of focusWords, ghi rõ số lượng private WordCounter objects
    => each WordCounter respond 1 String from focusWords
     */
    public NaiveBayes(String[] focusWords) {
        this.totalNumberSpam = 0;
        this.totalNumberNonSpam = 0;
        this.totalDocument = 0;

        this.wordCounters = new ArrayList<>();
        for (String focusWord : focusWords) {
            wordCounters.add(new WordCounter(focusWord));
        }
    }

    /* document be processed by all WordCounter object manage by NaiveBayes object
    NaiveBayes object should have: total number of spam / non spam documents has seen
    Calculate spam or no spam score P spam (non-spam) = # spam(non-spam) document seen / # total documents seen
     */
    public void addSample(String document) {
        /*try (Scanner scanner = new Scanner(Paths.get(document))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                this.totalDocument ++;
                if (line.contains("1")) {
                    this.totalNumberSpam ++;
                } else {
                    this.totalNumberNonSpam ++;
                }
            }
        }*/
        this.spamScore = (double)this.totalNumberSpam / this.totalDocument;
        this.nonSpamScore = (double)this.totalNumberNonSpam / this.totalDocument;

    }

    /*
    Take over document without classification and determine probability between the document
    is spam or not spam
    Calculate initial document score by P both spam and not spam
    -> check each word if NaiveBayes object hold a WordCounter contain the word as focusWord
    -> If it is, calculate new spam/non-spam score by multiply each of that
    with getConditionalSpam() or NoSpam() respectively
    -> Compare whether no spam score < spam score
     */
    public boolean classify(String unclassifiedDocument) {
        this.spamScore = (double)this.totalNumberSpam / this.totalDocument;
        this.nonSpamScore = (double)this.totalNumberNonSpam / this.totalDocument;
        return true;
    }

    /*
    All the classification line in trainingFile should be processed to train NaiveBayes objects
    like addSample method
    If can not read, not exist => throws IOException. FileNotFoundException
     */
    public void trainClassifier(File trainingFile) throws IOException{}

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
