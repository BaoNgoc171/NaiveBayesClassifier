import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;

public class WordCounter {

    private final String focusWord;
    private int totalWordsInNoSpam;
    private int totalWordsInSpam;
    private int totalFocusWordsInNoSpam;
    private int totalFocusWordsInSpam;

    /* Constructor, initialize the variables
    * @param String focusWord
    */
    public WordCounter(String focusWord) {
        this.focusWord = focusWord;
        this.totalWordsInNoSpam = 0;
        this.totalWordsInSpam = 0;
        this.totalFocusWordsInNoSpam = 0;
        this.totalFocusWordsInSpam = 0;
    }

    /* Get the current focus word
    * @return focusWord
    */
    public String getFocusWord() {
        return this.focusWord;
    }

    /* When adding document sample, we need to update
    * the counting total number of words and the focus words
    * respecting spam or no spam of the document sample.
    * @param String document sample
    * */
    public void addSample(String document) {
        String documentContent = getDocumentContent(document);
        if (isSpam(document)) {
            this.totalWordsInSpam += getTotalWordCount(documentContent);
            this.totalFocusWordsInSpam += getTotalFocusWordCount(documentContent);
        } else {
            this.totalWordsInNoSpam += getTotalWordCount(documentContent);
            this.totalFocusWordsInNoSpam += getTotalFocusWordCount(documentContent);
        }
    }

    /* Get the document actual word content,
    * filter out the identifier 0/1 at the
    * beginning of the document.
    *
    * @param String document
    * @return String the actual word content
    * */
    private String getDocumentContent(String document) {
        return document.substring(2);
    }

    /* Identify if the document is spam or no spam
    * @param String document
    * @return boolean spam or no spam
    */
    public boolean isSpam(String document) {
        return document.startsWith("1");
    }

    /* Get the total number of words in the document content
    * @param String document content
    * @return int total number of words
    */
    private int getTotalWordCount(String documentContent) {
        return documentContent.split("\\s").length;
    }

    /* Get the frequency of the focus word
    * appears in the document content.
    *
    * @param String document content
    * @return int the frequency number
    * */
    private int getTotalFocusWordCount(String documentContent) {
        // Split the string by the focus word and
        // count the number of parts that were splited
        String[] parts = documentContent.split(this.focusWord, -1);
        return parts.length - 1;
    }

    public void printInfo() {
        System.out.println("Focus word: " + this.focusWord);
        System.out.println("Total number of words in SPAM: " + this.totalWordsInSpam);
        System.out.println("Total number of words in NO SPAM: " + this.totalWordsInNoSpam);
        System.out.println("Total number of FOCUS words in SPAM: " + this.totalFocusWordsInSpam);
        System.out.println("Total number of FOCUS words in NO SPAM: " + this.totalFocusWordsInNoSpam);
        System.out.println("-------------------");
    }

    /* Check if the counter is trained or not
    * @return boolean is counter trained
    * */
    public boolean isCounterTrained() {
        if (this.totalFocusWordsInNoSpam + this.totalFocusWordsInSpam > 0 ) {
            if (this.totalWordsInSpam > 0) {
                if (this.totalWordsInNoSpam > 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /* Estimates for the probability for no spam that
    * a random word from a document is the focus word.
    * @return double the probability
    * */
    public double getConditionalNoSpam() {
        // Shouldn't be called before the counter has trained
        if (!isCounterTrained()) {
            throw new IllegalStateException();
        }
        return (double) this.totalFocusWordsInNoSpam / this.totalWordsInNoSpam;
    }

    /* Estimates for the probability for spam that
     * a random word from a document is the focus word.
     * @return double the probability
     * */
    public double getConditionalSpam() {
        // Shouldn't be called before the counter has trained
        if (!isCounterTrained()) {
            throw new IllegalStateException();
        }
        return (double) this.totalFocusWordsInSpam / this.totalWordsInSpam;
    }
}
