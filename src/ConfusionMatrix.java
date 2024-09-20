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
        NaiveBayes objA = new NaiveBayes();
        if (objA.classify(String unclassifiedDocument) == 1 && objA.classifyFile() == 1) {
            trueNegatives ++;
        }
        return trueNegatives;
    }
    public int getTruePositives() {
        return truePositives;
    }
    public int getFalseNegatives() {
        return falseNegatives;
    }
    public int getFalsePositives() {
        return falsePositives;
    }
}
