import java.util.Scanner;
import java.nio.file.Paths;
public class WordCounter {
    private String focus;
    private int wordNoSpam;
    private int wordSpam;
    private int timesFocusSpam;
    private int timesFocusNoSpam;
    public WordCounter(String focusWord) {
        this.focus = focusWord;

    }

    public String getFocusWord() {
        return this.focus;
    }

    public void addSample(String document) {

        try (Scanner scanner = new Scanner(Paths.get(document))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                while (scanner.hasNext()) {
                    this.wordSpam = 0;
                    this.wordNoSpam = 0;
                    this.timesFocusNoSpam = 0;
                    this.timesFocusSpam = 0;
                    if (line.contains("1")) {
                        if (line.contains(this.focus)) {
                            timesFocusSpam++;
                        }
                    } else {
                        if (line.contains(this.focus)) {
                            timesFocusNoSpam++;
                        }
                    }
                }
            }
        } catch (Exception e) {
        }


    }
}
