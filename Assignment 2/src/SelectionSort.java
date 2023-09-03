
/*
    Design explanation:
        This SelectionSort class is designed to be called when the user selects the selection sort button.
        The sorting logic is defined here which sorts one number at a time, along with its getter and
        setters methods.
        Sorting is done this way for the timer method in sorting panel to repaint each sorting steps.
*/

public class SelectionSort {

    private int currentIndex;
    private int comparedIndex;
    private int smallestValueIndex;
    private int arrayLength;
    private int[] array;
    private boolean findingSmallestNum;

    public SelectionSort(int[] userArray) {
        array = userArray;
        arrayLength = array.length;
        currentIndex = Integer.MAX_VALUE;
        comparedIndex = Integer.MAX_VALUE;
        smallestValueIndex = Integer.MAX_VALUE;
        findingSmallestNum = false;
    }

    public int[] sortOneValue() {

        if (findingSmallestNum == false) {
            findingSmallestNum = true;
            smallestValueIndex = currentIndex;
        }
        if (array[comparedIndex] < array[smallestValueIndex]) {
            smallestValueIndex = comparedIndex;
        }
        comparedIndex++;
        if (comparedIndex >= arrayLength) {
            findingSmallestNum = false;
            swap(array, smallestValueIndex, currentIndex);
            currentIndex++;
            comparedIndex = currentIndex + 1;
        }
        return array;
    }

    //-----Helper method-----

    public void swap(int array[], int smallestIndex, int currentIndex) {
        int storeTempValue = array[smallestIndex];
        array[smallestIndex] = array[currentIndex];
        array[currentIndex] = storeTempValue;
    }

    //-----Getter and Setter Methods-----
    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCompareIndex(int comparedIndex) {
        this.comparedIndex = comparedIndex;
    }

    public int getCompareIndex() {
        return comparedIndex;
    }

    public void setSmallestValueIndex(int smallestValueIndex) {
        this.smallestValueIndex = smallestValueIndex;
    }

    public int getSmallestValueIndex() {
        return smallestValueIndex;
    }
}