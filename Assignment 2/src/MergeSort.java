import java.util.ArrayList;

/*
    Design explanation:
        This MergeSort class is designed to be called when the user selects the merge sort button.

        Following divide and conquer;

        Divide:
        The splitArrayDownIntoSingleElementsArray function is created to recursively split the user array
        down into single element array and store them into an Arraylist. This function is only run once
        upon user starting merge sort.

        Conquer:
        The mergeAndCompareArraysToSort function is created to grab the arrays at index i and i+1 from arraylist
        to perform merging and sorting. After this is done, the new result array will replace the array at index i
        that was grabbed while deleting the array at index i+1 which was used to merge.

        In order to paint the rectangles on the sort panel, the findFirstAndLastIndexOfArrayBeingComparedCurrently
        function is used to grab the current iterated array first index and last index that is to be sorted. A for loop is then
        used on the SortingPanel class to grab all values in between the first and last index, which will be used to paint
        the rectangles.

        Sorting is done this way for the timer method in sorting panel to repaint each sorting steps.
*/

public class MergeSort {

    private ArrayList<int[]> splitArray = new ArrayList<>();
    private int[] array;
    private int[] resultArray;
    private int arrayLength;
    private int currentIterationArrayIndex;
    private int totalLengthOfArray;
    private int currentIterationArraySize;
    private int lastIndexOfCompareArray;
    private int firstIndexOfCompareArray;
    private boolean initialArrayIsOdd;
    private boolean checkCurrentIteration = false;
    private boolean runOnceOnEachIteration = false;

    public MergeSort(int[] userArray) {
        array = userArray;
        totalLengthOfArray = userArray.length;
        currentIterationArrayIndex = Integer.MAX_VALUE;
        firstIndexOfCompareArray = Integer.MAX_VALUE;
        lastIndexOfCompareArray = Integer.MAX_VALUE;
    }

    public ArrayList<int[]> splitArrayDownIntoSingleElementsArray(int[] array) {

        arrayLength = array.length;

        //Stops splitting once array contains only one element
        if (arrayLength == 1) {
            return splitArray;
        }

        int middlePosition = (int) Math.floor(arrayLength / 2);

        int[] leftSplit = new int[middlePosition];
        int[] rightSplit = new int[arrayLength - middlePosition];

        System.arraycopy(array, 0, leftSplit, 0, leftSplit.length);
        System.arraycopy(array, leftSplit.length, rightSplit, 0, rightSplit.length);

        //Add to arraylist once it has been split into single element
        if (leftSplit.length == 1) {
            splitArray.add(leftSplit);
        }
        if (rightSplit.length == 1) {
            splitArray.add(rightSplit);
        }

        //Recursive to continue splitting array
        splitArrayDownIntoSingleElementsArray(leftSplit);
        splitArrayDownIntoSingleElementsArray(rightSplit);

        return splitArray;
    }

    public int[] mergeAndCompareArraysToSort() {

        int[] tempArray;
        int[] currentIterationResult;

        //Checks if array size of current iteration is odd
        if (!checkCurrentIteration) {
            if (splitArray.size() % 2 != 0) {
                checkCurrentIteration = true;
                initialArrayIsOdd = true;
                currentIterationArraySize = splitArray.size();
            }
            firstIndexOfCompareArray = 0;
            lastIndexOfCompareArray = 0;
            checkCurrentIteration = true;
        }

        tempArray = mergeCurrentIndexArrayAndNextArray();

        //This line is performed to indicate the rectangles to be painted
        findFirstAndLastIndexOfArrayBeingComparedCurrently(tempArray);

        tempArray = sortMergeArray(tempArray);

        //Stops and return result of current array when array size is the same as user's entered array
        if (tempArray.length == totalLengthOfArray) {
            return tempArray;
        }

        //Replace the array at the iterated position with the new merge and sorted array
        //Remove the array at the iterated position + 1 that was used to merge
        if (currentIterationArraySize - currentIterationArrayIndex != 1) {
            splitArray.set(currentIterationArrayIndex, tempArray);
            splitArray.remove(currentIterationArrayIndex + 1);
        }
        currentIterationArrayIndex++;
        currentIterationArraySize = splitArray.size();

        currentIterationResult = convertArrayListToIntArray(splitArray);

        //Reset the function once current iteration array has fully completed merge and sort
        if (currentIterationArrayIndex == splitArray.size()) {
            currentIterationArrayIndex = 0;
            currentIterationArraySize = 0;
            checkCurrentIteration = false;
            runOnceOnEachIteration = false;
        }

        return currentIterationResult;
    }

    //-----Helper Methods-----
    public int[] convertArrayListToIntArray(ArrayList<int[]> splitArray) {
        resultArray = new int[totalLengthOfArray];
        int j = 0;

        for (int i = 0; i < splitArray.size(); i++) {
            int sizeOfSplit = splitArray.get(i).length;

            if (splitArray.get(i).length == sizeOfSplit) {
                addToIntArray(j, sizeOfSplit, i, splitArray);
                j += sizeOfSplit;
            }
        }
        return resultArray;
    }

    public int[] addToIntArray(int intArrayIndex, int splitArraySize, int currentArrayIndex, ArrayList<int[]> splitArray) {
        int counter = 0;
        while (counter < splitArraySize) {
            resultArray[intArrayIndex + counter] = splitArray.get(currentArrayIndex)[counter];
            counter++;
        }
        return resultArray;
    }

    public int[] mergeCurrentIndexArrayAndNextArray() {

        int[] tempArray;
        int[] firstSetOfMergeArray;
        int[] secondSetOfMergeArray;

        //Run if current iteration array is odd
        if (initialArrayIsOdd) {
            if (currentIterationArraySize - currentIterationArrayIndex == 1) {
                initialArrayIsOdd = false;

                firstSetOfMergeArray = splitArray.get(0);
                tempArray = new int[firstSetOfMergeArray.length];
                System.arraycopy(firstSetOfMergeArray, 0, tempArray, 0, firstSetOfMergeArray.length);
                return tempArray;

            } else {
                firstSetOfMergeArray = splitArray.get(currentIterationArrayIndex);
                secondSetOfMergeArray = splitArray.get(currentIterationArrayIndex + 1);
            }
        }

        //Run if current iteration array is not odd
        else {
            firstSetOfMergeArray = splitArray.get(currentIterationArrayIndex);
            secondSetOfMergeArray = splitArray.get(currentIterationArrayIndex + 1);
        }

        //Create temp array to store the arrays at index i and i+1 of splitArray
        int tempArraySize = firstSetOfMergeArray.length + secondSetOfMergeArray.length;
        tempArray = new int[tempArraySize];
        System.arraycopy(firstSetOfMergeArray, 0, tempArray, 0, firstSetOfMergeArray.length);
        System.arraycopy(secondSetOfMergeArray, 0, tempArray, firstSetOfMergeArray.length, secondSetOfMergeArray.length);

        return tempArray;
    }

    public int[] sortMergeArray(int[] tempArray) {
        int temp = 0;

        for (int i = 0; i < tempArray.length; i++) {
            for (int j = i + 1; j < tempArray.length; j++) {
                if (tempArray[i] > tempArray[j]) {
                    temp = tempArray[i];
                    tempArray[i] = tempArray[j];
                    tempArray[j] = temp;
                }
            }
        }
        return tempArray;
    }

    public void findFirstAndLastIndexOfArrayBeingComparedCurrently(int[] tempArray) {
        int lastIndexOfArray = lastIndexOfCompareArray;
        lastIndexOfCompareArray += tempArray.length;

        if (lastIndexOfCompareArray > totalLengthOfArray) {
            lastIndexOfCompareArray = totalLengthOfArray;
        }
        if (!runOnceOnEachIteration) {
            firstIndexOfCompareArray = 0;
            runOnceOnEachIteration = true;
        } else {
            firstIndexOfCompareArray = lastIndexOfArray;
        }
    }

    //-----Getter and Setter Methods-----
    public void setCurrentIterationArrayIndex(int currentIterationArrayIndex) {
        this.currentIterationArrayIndex = currentIterationArrayIndex;
    }

    public int getFirstIndexOfCompareArray() {
        return firstIndexOfCompareArray;
    }

    public void setFirstIndexOfCompareArray(int firstIndexOfCompareArray) {
        this.firstIndexOfCompareArray = firstIndexOfCompareArray;
    }

    public int getLastIndexOfCompareArray() {
        return lastIndexOfCompareArray;
    }

    public void setLastIndexOfCompareArray(int lastIndexOfCompareArray) {
        this.lastIndexOfCompareArray = lastIndexOfCompareArray;
    }
}