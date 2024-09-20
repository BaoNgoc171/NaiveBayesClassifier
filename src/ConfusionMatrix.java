import java.lang.reflect.Array;
import java.util.ArrayList;

public class ConfusionMatrix {

    private int truePositives;
    private int falsePositives;
    private int trueNegatives;
    private int falseNegatives;

    public ConfusionMatrix(ArrayList<Integer> groundtruthList, ArrayList<Integer> classifiedlist) {
        this.truePositives = 0;
        this.falsePositives = 0;
        this.trueNegatives = 0;
        this.falseNegatives = 0;

        for (int i = 0; i < groundtruthList.size(); i++) {
            if (groundtruthList.get(i) == classifiedlist.get(i)) {
                if (groundtruthList.get(i) == 0) {
                    this.trueNegatives++;
                } else {
                    this.truePositives++;
                }
            } else {
                if (groundtruthList.get(i) == 0) {
                    this.falsePositives++;
                } else {
                    this.falseNegatives++;
                }
            }
        }
    }

    public int getTrueNegatives() {
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
