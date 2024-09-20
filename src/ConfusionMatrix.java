import java.io.File;

public class ConfusionMatrix {
    private int trueNegatives;
    private int truePositives;
    private int falseNegatives;
    private int falsePositives;
    /*
    true positive -> classifier predicts 1 for document has classification 1
    false positive-> classifier predicts 1 for document has classification 0
    true negative -> classifier predicts 0 for document has classification 0
    false negative -> classifier predicts 0 for document has classification 1
     */
    public int getTrueNegatives() {
        testMatrix();
        return trueNegatives;
    }
    public int getTruePositives() {
        testMatrix();
        return truePositives;
    }
    public int getFalseNegatives() {
        testMatrix();
        return falseNegatives;
    }
    public int getFalsePositives() {
        testMatrix();
        return falsePositives;
    }
    // determine whether the case is true or false, positive or negative
    private void testMatrix() {
        NaiveBayes objA = new NaiveBayes();

        if (objA.classify() == 1) {
            if (objA.classifyFile() == 1) {
                trueNegatives ++;
            } else {
                falsePositives ++;
            }
        } else {
            if (objA.classifyFile(File input, File output) == 1) {
                falseNegatives ++;
            } else {
                falsePositives ++;
            }
        }
    }

}
