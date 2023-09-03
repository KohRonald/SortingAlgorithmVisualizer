import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

/*
    Design explanation:
        This MenuPanel class is designed to display the sort options for selection as well as to call the
        input and sorting panel. Depending on the user selection, the input and sorting panel will be called accordingly.
        The code here checks whether the user has inputted the array correctly and then displays the sort.
*/

public class MenuPanel extends JPanel {

    private JLabel welcomeLabel, introLabel;
    private JButton bubbleSortButton, selectionSortButton, mergeSortButton;

    private SortingPanel sortPanel;

    private InputPanel inputBubble = new InputPanel(new displayBubbleSortPanel());
    private InputPanel inputSelection = new InputPanel(new displaySelectionSortPanel());
    private InputPanel inputMerge = new InputPanel(new displayMergeSortPanel());

    public MenuPanel(){
        this.setBackground(Color.decode("#005F73"));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbcMenuPanel = new GridBagConstraints();

        //-----Welcome label-----
        String welcomeString = "<html><span style=\"font-family:Verdana; font-weight:bold; font-size:30px;\">Learn Sorting Algorithm!</span></html>";
        welcomeLabel = new JLabel(welcomeString);
        welcomeLabel.setForeground(Color.decode("#EE9B00"));

        gbcMenuPanel.gridy = 0;
        this.add(welcomeLabel, gbcMenuPanel);

        //-----Intro label-----
        String introString ="<html><span style=\"font-family:SansSerif; font-size:13px;\">Click one of the algorithm to start</span></html>";
        introLabel = new JLabel(introString);
        introLabel.setForeground(Color.decode("#E9D8A6"));

        gbcMenuPanel.gridy = 1;
        gbcMenuPanel.insets = new Insets(10,0,50,0);
        this.add(introLabel, gbcMenuPanel);

        //-----Bubble sort button-----
        String bubbleButtonString = "<html><span style=\"font-family:SansSerif; font-weight:bold; font-size:16px;\">Bubble Sort</span></html>";
        bubbleSortButton = new JButton(bubbleButtonString);
        bubbleSortButton.setPreferredSize(new Dimension(250, 50));
        bubbleSortButton.setForeground(Color.decode("#AE2012"));

        gbcMenuPanel.gridy = 2;
        gbcMenuPanel.insets= new Insets(2,0,2,0);
        bubbleSortButton.addActionListener(new algorithmSelectorListener());
        this.add(bubbleSortButton, gbcMenuPanel);

        //-----Selection sort button-----
        String selectionButtonString = "<html><span style=\"font-family:SansSerif; font-weight:bold; font-size:16px;\">Selection Sort</span></html>";
        selectionSortButton = new JButton(selectionButtonString);
        selectionSortButton.setPreferredSize(new Dimension(250, 50));
        selectionSortButton.setForeground(Color.decode("#AE2012"));

        gbcMenuPanel.gridy = 3;
        selectionSortButton.addActionListener(new algorithmSelectorListener());
        this.add(selectionSortButton, gbcMenuPanel);

        //-----Merge sort button-----
        String mergeButtonString = "<html><span style=\"font-family:SansSerif; font-weight:bold; font-size:16px;\">Merge Sort</span></html>";
        mergeSortButton = new JButton(mergeButtonString);
        mergeSortButton.setPreferredSize(new Dimension(250, 50));
        mergeSortButton.setForeground(Color.decode("#AE2012"));

        gbcMenuPanel.gridy = 4;
        mergeSortButton.addActionListener(new algorithmSelectorListener());
        this.add(mergeSortButton, gbcMenuPanel);
    }

    //--------------------Menu listener--------------------
    private class algorithmSelectorListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){

            if(checkButtonClickIs(bubbleSortButton, e)){
                MyFrame.setPanel(inputBubble);
            }
            else if(checkButtonClickIs(selectionSortButton, e)){
                MyFrame.setPanel(inputSelection);
            }
            else if(checkButtonClickIs(mergeSortButton, e)){
                MyFrame.setPanel(inputMerge);
            }
        }
    }

    //--------------------Input Button Listener--------------------
    private class displayBubbleSortPanel implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                int[] userArray = inputBubble.userArray();

                if(checkIfArrayLengthIsMoreThan25(userArray)){
                    showMessageDialog(null, arrayLengthExceededMessage(userArray));
                } else {
                    passArrayAndDisplayPanel(userArray, "bubbleSort");
                }
            }
            catch (Exception event){
                event.printStackTrace();
                showMessageDialog(null, arrayInputIncorrectlyMessage());
            }
        }
    }
    private class displaySelectionSortPanel implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){

            try{
                int[] userArray = inputSelection.userArray();
                if(checkIfArrayLengthIsMoreThan25(userArray)){
                    showMessageDialog(null, arrayLengthExceededMessage(userArray));
                } else {
                    passArrayAndDisplayPanel(userArray, "selectionSort");
                }
            }
            catch (Exception event){
                event.printStackTrace();
                showMessageDialog(null, arrayInputIncorrectlyMessage());
            }
        }
    }

    private class displayMergeSortPanel implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){

            try{
                int[] userArray = inputMerge.userArray();

                if(checkIfArrayLengthIsMoreThan25(userArray)){
                    showMessageDialog(null, arrayLengthExceededMessage(userArray));
                } else {
                    passArrayAndDisplayPanel(userArray, "mergeSort");
                }
            }
            catch (Exception event){
                event.printStackTrace();
                showMessageDialog(null, arrayInputIncorrectlyMessage());
            }
        }
    }

    //--------------------Helper Methods--------------------
    private boolean checkIfArrayLengthIsMoreThan25(int[] userArray){
        if(userArray.length > 25){
            return true;
        } else{
            return false;
        }
    }

    private boolean checkButtonClickIs(JButton button, ActionEvent e){
        if(e.getSource() == button){
            return true;
        } else{
            return false;
        }
    }

    private void passArrayAndDisplayPanel(int[] userArray, String sortChoice){
        sortPanel = new SortingPanel(userArray, sortChoice);
        MyFrame.setPanel(sortPanel);
    }

    private String arrayLengthExceededMessage(int[] userArray){
        return "You have inserted " + userArray.length + " numbers, only a maximum number of 25 is allowed";
    }

    private String arrayInputIncorrectlyMessage(){
        return "Try again, make sure you input numbers correctly";
    }
}