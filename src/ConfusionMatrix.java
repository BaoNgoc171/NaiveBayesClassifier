import java.util.ArrayList;

/*This class is counting the true/false and positive/negative
*using for calculate accuracy/errors
 */
public class ConfusionMatrix {

    private int truePositives;
    private int falsePositives;
    private int trueNegatives;
    private int falseNegatives;
    /* Constructor, initialize variables
     * @param ArrayList<Integer> groundTruthList, ArrayList<Integer> classFieldList
     */

    public ConfusionMatrix(ArrayList<Integer> groundTruthList, ArrayList<Integer> classFieldList) {
        this.truePositives = 0;
        this.falsePositives = 0;
        this.trueNegatives = 0;
        this.falseNegatives = 0;

        for (int i = 0; i < groundTruthList.size(); i++) {
            if (groundTruthList.get(i).equals(classFieldList.get(i))) {
                if (groundTruthList.get(i) == 0) {
                    this.trueNegatives++;
                } else {
                    this.truePositives++;
                }
            } else {
                if (groundTruthList.get(i) == 0) {
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
