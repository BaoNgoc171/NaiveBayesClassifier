import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;
public class WordCounter {
    private String focusWord;
    private int totalWordsInNoSpam;
    private int totalWordInSpam;
    private int totalFocusWordsInNoSpam;
    private int totalFocusWordsInSpam;
    private double probabilityNoSpam;
    private double probabilitySpam;

    /* Constructor, initialize the variables
    * @param String focusWord
    */
    public WordCounter(String focusWord) {
        this.focusWord = focusWord;
        totalWordsInNoSpam = 0;
        totalWordInSpam = 0;
        totalFocusWordsInNoSpam = 0;
        totalFocusWordsInSpam = 0;
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
            this.totalWordInSpam += getTotalWordCount(documentContent);
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
    private boolean isSpam(String document) {
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

    /*xác định wordCounter train bằng cách:
    xuất hiện ít nhất 1 lần => timesFocusSpam + timesFocusNoSpam >=1
    xuất hiện ít nhất 1 lần trong spam =>  timesFocusSpam >=1
    xuất hiện ít nhất 1 lần trong non spam => timeFocusNoSpam => 1
    => 2 and 3 holds=> 1 holds => lấy 2 và 3

     */
//    public boolean isCounterTrained() {
//        if (this.timesFocusSpam >= 1 && this.timesFocusNoSpam >= 1) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//    /*
//    giả về giá trị probabilities bằng cách lấy
//    timeFocusNoSpam / wordNoSpam => no spam
//    timeFocusSpam / wordSpam => spam
//    nếu 2 method conditional được gọi trước isCounterTrained thì throw IllegalStateException
//
//     */
//    public double getConditionalNoSpam() {
//        if (isCounterTrained()) {
//            throw new IllegalStateException();
//        }
//        this.probabilityNoSpam = (double)timesFocusNoSpam / wordNoSpam;
//        return probabilityNoSpam ;
//    }
//    public double getConditionalSpam() {
//        if (isCounterTrained()) {
//            throw new IllegalStateException();
//        }
//        this.probabilitySpam = (double)timesFocusSpam / wordSpam;
//        return this.probabilitySpam;
//    }

    public static void main(String[] args) {
        WordCounter wc = new WordCounter("good");
        System.out.println(wc.getFocusWord());
        wc.addSample("1 good bad bad bad");
        wc.addSample("0 bad good good");
        wc.addSample("0 bad good");
    }
}
