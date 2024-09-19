import java.io.File;
import java.io.IOException;
import java.net.PortUnreachableException;

public class NaiveBayes {
    public NaiveBayes(String[] focusWords) {}
    public void addSample(String document) {}
    public boolean classify(String unclassifiedDocument) {
        return true;
    }

    public void trainClassifier(File trainingFile) throws IOException{}
    public void classifyFile(File input, File output) throws IOException{}

    public ConfusionMatrix computeAccuracy(File testdata) throws IOException{
        return new ConfusionMatrix();
    }
}
