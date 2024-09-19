import java.io.File;
import java.io.IOException;
import java.net.PortUnreachableException;

public class NaiveBayes {
    /*
    String [] is an array of focusWords, ghi rõ số lượng private WordCounter objects
    => each WordCounter respond 1 String from focusWords
     */
    public NaiveBayes(String[] focusWords) {}

    /* document be processed by all WordCounter object manage by NaiveBayes object
    NaiveBayes object should have: total number of spam / non spam documents has seen
    Calculate spam or no spam score P spam (non-spam) = # spam(non-spam) document seen / # total documents seen
     */
    public void addSample(String document) {}

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
