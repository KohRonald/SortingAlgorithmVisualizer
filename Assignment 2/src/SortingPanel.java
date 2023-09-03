import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
    Design explanation:
        This SortingPanel class is designed to be a constructor. MenuPanel will call this constructor class based
        on user selection. The sorting panel will display the back menu and start button, along with an explanation on how
        the algorithm which was selected does it's sort with a sorting animation. Graphic component is used to draw
        and repaint the sort onto the panel.

    Sort explanation:
        I used the timer class to execute the sort visualisation animation, the sort will continuously run as long as the
        array has not been sorted. I created a method which returns a boolean flag which checks whether the array has been sorted.
        If array has been sorted, the timer will be set to stop and the visualisation animation will be ended.

        Back menu button is disabled once start button is clicked, and will only be re-enabled after sort has
        been completed. This is to prevent repaint process from being abruptly interrupted.
*/

public class SortingPanel extends JPanel {

    private static int[] array;

    private BubbleSort bubbleSort;
    private SelectionSort selectionSort;
    private MergeSort mergeSort;
    private JButton startButton, menuScreenButton;
    private JLabel bsDescription, ssDescription, msDescription;
    private String sortChoice;
    private static boolean isRunning;
    private MenuPanel menu = new MenuPanel();

    public SortingPanel(int[] userArray, String sortChoice){
        array = userArray;
        this.sortChoice = sortChoice;

        bubbleSort = new BubbleSort(array);
        selectionSort = new SelectionSort(array);
        mergeSort = new MergeSort(array);

        this.setBackground(Color.decode("#005F73"));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbcSortPanel = new GridBagConstraints();

        //-----Back menu button-----
        String menuScreenButtonString = "<html><span style=\"font-family:SansSerif; font-weight:bold; font-size:16px;\">Back to Menu</span></html>";
        menuScreenButton = new JButton(menuScreenButtonString);
        menuScreenButton.setForeground(Color.decode("#AE2012"));
        menuScreenButton.addActionListener(new displayMenuListener());

        gbcSortPanel.gridx = 0;
        gbcSortPanel.gridy = 0;
        gbcSortPanel.anchor = GridBagConstraints.PAGE_START;
        gbcSortPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcSortPanel.insets = new Insets(15,150,10,150);
        this.add(menuScreenButton, gbcSortPanel);

        //-----Start button-----
        String startButtonString = "<html><span style=\"font-family:SansSerif; font-weight:bold; font-size:16px;\">Start</span></html>";
        startButton = new JButton(startButtonString);
        startButton.setForeground(Color.decode("#AE2012"));
        startButton.addActionListener(new startButtonListener());

        gbcSortPanel.gridx = 0;
        gbcSortPanel.gridy = 1;
        gbcSortPanel.insets = new Insets(10,150,10,150);
        this.add(startButton, gbcSortPanel);

        //-----Sort Choices-----
        gbcSortPanel.gridx = 0;
        gbcSortPanel.gridy = 2;
        gbcSortPanel.weighty = 1;
        gbcSortPanel.weightx = 1;
        gbcSortPanel.insets = new Insets(10,50,0,50);

        String bulletPoint = "<span style=\"color:white;\">&#x2022 </span>";

        switch (sortChoice){
            case "bubbleSort":
                String bsDescriptionOne = bulletPoint + "Bubble Sort grabs index i's value and compares it to the value at index i+1.<br>" + bulletPoint + "If index i value is larger than i+1's, the value will be swapped.";
                String bsNote = "<br><br>&#x2022; Negative numbers are in <span style=\"color:#AE2012;\">red</span><br>&#x2022; Indexes being compared are in <span style=\"color:yellow;\">yellow</span>";

                bsDescription = new JLabel("<html><span style=\"font-family:SansSerif; font-weight:bold; font-size:15px;\">" + bsDescriptionOne + "</span>" + "<span style=\"color:white; font-size:14px;\">" + bsNote + "</span></html>");
                bsDescription.setForeground(Color.decode("#EE9B00"));
                bsDescription.setBackground(Color.decode("#005F73"));
                bsDescription.setOpaque(true);

                this.add(bsDescription, gbcSortPanel);
                break;

            case "selectionSort":
                String ssDescriptionOne = bulletPoint + "Selection Sort grabs index 0's value and stores it into a temporary variable, labeling it as the smallest value.<br>" + bulletPoint + "Selection sort then compares it to the values in the rest of the array. If any other value is smaller, the new value will replace the previous value.<br>";
                String ssDescriptionTwo = bulletPoint + "Once all value in the index has been compared, the value in the temporary smallest value will be place on the most left position.<br>" + bulletPoint + "The value which was replace will be repositioned to where the smallest value was.<br>" + bulletPoint + "This sort is reiterated until the array has been fully sorted";
                String ssNote = "<br><br>&#x2022; Negative numbers are in <span style=\"color:#AE2012;\">red</span><br>&#x2022; Current value being compared is in <span style=\"color:green;\">green</span><br>&#x2022; Indexes being iterated to find a possible smaller value is in <span style=\"color:yellow;\">yellow</span>";

                ssDescription = new JLabel("<html><span style=\"font-family:SansSerif; font-weight:bold; font-size:14px;\">" + ssDescriptionOne + ssDescriptionTwo + "</span>" + "<span style=\"color:white; font-size:14px;\">" + ssNote + "</span></html>");
                ssDescription.setForeground(Color.decode("#EE9B00"));
                ssDescription.setBackground(Color.decode("#005F73"));
                ssDescription.setOpaque(true);

                this.add(ssDescription, gbcSortPanel);
                break;

            case "mergeSort":
                String msDescriptionOne = bulletPoint + "Merge Sort performs a divide and conquer technique to sort the array.<br>" + bulletPoint + "Divide: Merge Sort grabs all elements in the array and breaks them down into single element array.<br>";
                String msDescriptionTwo = bulletPoint + "Conquer: Merge sort will start to grab pairs of array that were divided and merge them to perform sorting. After first iteration, the result will be a new array that will be passed on to the next conquer iteration.<br>" + bulletPoint + "If there is an odd number array, the array without a pair will be passed on to the next conquer iteration.<br>";
                String msDescriptionThree = bulletPoint + "The conquer stage will be continuously performed until the array is fully sorted.";
                String msNote = "<br><br>&#x2022; Negative numbers are in <span style=\"color:#AE2012;\">red</span><br>&#x2022; Arrays being merged and sorted are in <span style=\"color:yellow;\">yellow</span>";

                msDescription = new JLabel("<html><span style=\"font-family:SansSerif; font-weight:bold; font-size:14px;\">" + msDescriptionOne + msDescriptionTwo + msDescriptionThree + "</span>" + "<span style=\"color:white; font-size:14px;\">" + msNote + "</span></html>");
                msDescription.setForeground(Color.decode("#EE9B00"));
                msDescription.setBackground(Color.decode("#005F73"));
                msDescription.setOpaque(true);

                this.add(msDescription, gbcSortPanel);
                break;
        }
    }

    //------------------------------Algorithm animation methods------------------------------
    public void bubbleSortAnimate() throws Exception{

        bubbleSort.setCompareIndex(0);
        repaint();

        //Paints every 500 milliseconds
        Timer bubbleTimer = new Timer(500, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                menuScreenButton.setEnabled(false);

                //if array sorted, timer will stop
                if (isSorted() || !isRunning) {
                    //This line below returns colour of last compared rectangle to its original colour once sort has been completed
                    bubbleSort.setCompareIndex(Integer.MIN_VALUE);
                    isRunning = false;
                    menuScreenButton.setEnabled(true);
                    setDoneSortedText();
                    ((Timer)e.getSource()).stop();
                } else {
                    if (isRunning){
                        setCurrentSortingText();
                        array = bubbleSort.sortOneValue();
                    }
                }
                //Repaint is called to update the visualiser
                repaint();
            }
        });
        //Starts the timer
        bubbleTimer.start();
    }

    public void selectionSortAnimate() throws Exception{

        selectionSort.setCurrentIndex(0);
        selectionSort.setCompareIndex(1);
        selectionSort.setSmallestValueIndex(0);
        repaint();

        Timer selectionTimer  = new Timer(500, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                menuScreenButton.setEnabled(false);

                if (isSorted() || !isRunning) {
                    //This 3 lines below returns colour of rectangles to its original colour once sort has been completed
                    selectionSort.setCompareIndex(Integer.MAX_VALUE);
                    selectionSort.setCurrentIndex(Integer.MAX_VALUE);
                    selectionSort.setSmallestValueIndex(Integer.MAX_VALUE);
                    isRunning = false;
                    menuScreenButton.setEnabled(true);
                    setDoneSortedText();
                    ((Timer)e.getSource()).stop();
                }
                else {
                    if (isRunning){
                        setCurrentSortingText();
                        array = selectionSort.sortOneValue();
                    }
                }
                repaint();
            }
        });
        selectionTimer.start();
    }

    public void mergeSortAnimate() throws Exception{

        mergeSort.setCurrentIterationArrayIndex(0);
        mergeSort.setFirstIndexOfCompareArray(0);
        mergeSort.setLastIndexOfCompareArray(0);
        mergeSort.splitArrayDownIntoSingleElementsArray(array);
        repaint();

        Timer mergeTimer  = new Timer(500, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                menuScreenButton.setEnabled(false);

                if (isSorted() || !isRunning) {
                    //This 3 lines below returns colour of rectangles to its original colour once sort has been completed
                    mergeSort.setCurrentIterationArrayIndex(Integer.MAX_VALUE);
                    mergeSort.setFirstIndexOfCompareArray(Integer.MAX_VALUE);
                    mergeSort.setLastIndexOfCompareArray(Integer.MAX_VALUE);
                    isRunning = false;
                    menuScreenButton.setEnabled(true);
                    setDoneSortedText();
                    ((Timer)e.getSource()).stop();
                }
                else {
                    if (isRunning){
                        setCurrentSortingText();
                        array = mergeSort.mergeAndCompareArraysToSort();
                    }
                }
                repaint();
            }
        });
        mergeTimer.start();
    }

    //------------------------------Helper methods------------------------------
    public static boolean isSorted() {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public void setCurrentSortingText(){
        startButton.setText("<html><span style=\"font-family:SansSerif; font-weight:bold; font-size:16px;\">Running!</span></html>");
        startButton.setForeground(Color.decode("#FEFEE3"));
        startButton.setBackground(Color.decode("#AE2012"));
        startButton.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.decode("#AE2012")));
        startButton.setOpaque(true);
    }

    public void setDoneSortedText(){
        startButton.setText("<html><span style=\"font-family:SansSerif; font-weight:bold; font-size:16px;\">Done!</span></html>");
        startButton.setForeground(Color.decode("#FEFEE3"));
        startButton.setBackground(Color.decode("#4C956C"));
        startButton.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.decode("#4C956C")));
        startButton.setOpaque(true);
    }

    //------------------------------Paint Method------------------------------
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Sets color of negative and positive values
        for (int i = 0; i < array.length; i++){
            if (array[i] >= 0){
                g.setColor(Color.decode("#94D2BD"));
            } else {
                g.setColor(Color.decode("#AE2012"));
            }

            //Sets comparison color based on algorithm choice
            switch (sortChoice){

                case "bubbleSort":
                    if (i == bubbleSort.getCompareIndex() || i == (bubbleSort.getCompareIndex() + 1)){
                        g.setColor(Color.YELLOW);
                    }
                    break;

                case "selectionSort":
                    if (i == selectionSort.getCompareIndex() || i == selectionSort.getSmallestValueIndex()) {
                        g.setColor(Color.YELLOW);
                    }
                    if (i == selectionSort.getCurrentIndex()) {
                        g.setColor(Color.GREEN);
                    }
                    break;

                case "mergeSort":
                    int firstIndexOfCurrentIterationSort = mergeSort.getFirstIndexOfCompareArray();
                    int lastIndexOfCurrentIterationSort = mergeSort.getLastIndexOfCompareArray();
                    int temp = 0;

                    for(int msIndex = firstIndexOfCurrentIterationSort; msIndex < lastIndexOfCurrentIterationSort; msIndex++) {
                        temp = msIndex;
                        if(i == msIndex) {
                            g.setColor(Color.YELLOW);
                        }
                    }
                    break;
            }

            //Set location of rectangle to be drawn on panel
            g.drawRect(i*32, 800 - Math.abs(array[i]), 30, Math.abs(array[i]));
            g.fillRect(i*32, 800 - Math.abs(array[i]), 30, Math.abs(array[i]));
        }

        //Set the value of each index as string and prints in the center of rectangle
        //If value of index is more than 700 or lesser than -700, print string in a fixed position on rectangle
        for (int i = 0; i < array.length; i++){
            int y = 0;
            Font font = new Font( "SansSerif", Font.BOLD, 13 );
            FontMetrics fMetrics = g.getFontMetrics(font);
            String text = "" + array[i];
            int x = i * 32 + (30 - fMetrics.stringWidth(text)) / 2;
            if(array[i] > 700 || array[i] < -700){
                y = 430;
            }else {
                y = 800 - Math.abs(array[i]) + ((Math.abs(array[i]) - fMetrics.getHeight()) / 2) + fMetrics.getAscent();
            }

            g.setFont(font);
            g.setColor(Color.BLACK);
            g.drawString(text, x, y);
        }
    }

    //------------------------------Button listeners------------------------------
    private class displayMenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                MyFrame.setPanel(menu);
            } catch (Exception event){
                event.printStackTrace();
            }
        }
    }

    private class startButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                if (!isRunning){
                    isRunning = true;

                    //Show algorithm animation depending on user selection
                    switch (sortChoice){
                        case "bubbleSort":
                            bubbleSortAnimate();
                            break;

                        case "selectionSort":
                            selectionSortAnimate();
                            break;

                        case "mergeSort":
                            mergeSortAnimate();
                            break;
                    }
                }
            } catch (Exception event){
                event.printStackTrace();
            }
        }
    }
}