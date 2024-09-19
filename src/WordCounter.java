import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;
public class WordCounter {
    private String focus;
    private int wordNoSpam;
    private int wordSpam;
    private int timesFocusSpam;
    private int timesFocusNoSpam;
    private double probabilityNoSpam;
    private double probabilitySpam;
    //xác định focusWord, ko return j cả
    public WordCounter(String focusWord) {
        this.focus = focusWord;

    }

    //trả lại focusWord mỗi khi gọi
    public String getFocusWord() {
        return this.focus;
    }

    /* xác định spam (1 hay 0); số lần focusWord xuất hiện ở spam and non spam
     số lần focusWord xuất hiện
     lọc spam hay non spam, xác định bộ đếm int,đọc từng line và mỗi line lọc từng từ
     và ghi vào bộ đếm

     */
    public void addSample(String document) throws IOException {
        try (Scanner scanner = new Scanner(Paths.get(document))) {
            this.wordSpam = 0;
            this.wordNoSpam = 0;
            this.timesFocusNoSpam = 0;
            this.timesFocusSpam = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                    if (line.contains("1")) {
                        while (scanner.hasNext()) {
                            wordSpam ++;
                            if (line.contains(this.focus)) {
                                timesFocusSpam++;
                            }
                        }
                    } else {
                        while (scanner.hasNext()) {
                            wordNoSpam ++;
                            if (line.contains(this.focus)) {
                                timesFocusNoSpam++;
                            }
                        }
                    }
            }
        }
    }


    /*xác định wordCounter train bằng cách:
    xuất hiện ít nhất 1 lần => timesFocusSpam + timesFocusNoSpam >=1
    xuất hiện ít nhất 1 lần trong spam =>  timesFocusSpam >=1
    xuất hiện ít nhất 1 lần trong non spam => timeFocusNoSpam => 1
    => 2 and 3 holds=> 1 holds => lấy 2 và 3

     */
    public boolean isCounterTrained() {
        if (this.timesFocusSpam >= 1 && this.timesFocusNoSpam >= 1) {
            return true;
        } else {
            return false;
        }
    }
    /*
    giả về giá trị probabilities bằng cách lấy
    timeFocusNoSpam / wordNoSpam => no spam
    timeFocusSpam / wordSpam => spam
    nếu 2 method conditional được gọi trước isCounterTrained thì throw IllegalStateException

     */
    public double getConditionalNoSpam() {
        if (isCounterTrained()) {
            throw new IllegalStateException();
        }
        this.probabilityNoSpam = (double)timesFocusNoSpam / wordNoSpam;
        return probabilityNoSpam ;
    }
    public double getConditionalSpam() {
        if (isCounterTrained()) {
            throw new IllegalStateException();
        }
        this.probabilitySpam = (double)timesFocusSpam / wordSpam;
        return this.probabilitySpam;
    }

    public static void main(String[] args) throws IOException {
        WordCounter wc = new WordCounter("good");
        System.out.println(wc.getFocusWord());
        wc.addSample("1 good bad bad bad");
        wc.addSample("0 bad good good");
        wc.addSample("0 bad good");
    }
}
