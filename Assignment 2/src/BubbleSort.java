
/*
    Design explanation:
        This BubbleSort class is designed to be called when the user selects the bubble sort button.
        The sorting logic is defined here which sorts one number at a time, along with its getter and
        setters class to set and find which number is currently being compared.
        Sorting is done this way for the timer method in sorting panel to repaint each sorting steps.
*/

public class BubbleSort {

    private int[] bubbleSortArray;
    private int currentIndex, comparedIndex;

    public BubbleSort(int[] userArray) {
        bubbleSortArray = userArray;
        currentIndex = 0;
        comparedIndex = Integer.MAX_VALUE;
    }

    public int[] sortOneValue() {

        if (bubbleSortArray[comparedIndex] > bubbleSortArray[comparedIndex + 1]) {
            int storeTempValue = bubbleSortArray[comparedIndex];
            bubbleSortArray[comparedIndex] = bubbleSortArray[comparedIndex + 1];
            bubbleSortArray[comparedIndex + 1] = storeTempValue;
        }
        if ((comparedIndex + 1) >= (bubbleSortArray.length - currentIndex - 1)) {
            currentIndex++;
            comparedIndex = 0;
        }
        else {
            comparedIndex++;
        }
        return bubbleSortArray;
    }

    //-----Getter and Setter Methods-----
    public void setCompareIndex(int comparedIndex) {
        this.comparedIndex = comparedIndex;
    }

    public int getCompareIndex() {
        return comparedIndex;
    }
}