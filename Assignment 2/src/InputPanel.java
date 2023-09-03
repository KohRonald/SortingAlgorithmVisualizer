import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/*
    Design explanation:
        This InputPanel class is designed to be a constructor. MenuPanel will call this constructor class
        based on user selection which responsibility is to display the input panel. This class also contains
        the method to convert string input into a int[] input.

        The reason to impose a maximum array size is because anything more will exceed the width of the frame.
*/

public class InputPanel extends JPanel {

    private JLabel promptUserLabel, exampleLabel, noteLabel;
    private JButton submitInputButton;
    private JTextField userInputField;
    private int[] userArray;

    public InputPanel(ActionListener inputAction) {
        this.setBackground(Color.decode("#005F73"));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbcInputPanel = new GridBagConstraints();

        //-----Prompt label-----
        String promptString = "<html><span style=\"font-family:Verdana; font-weight:bold; font-size:15px;\">Enter an array of numbers <span style=\"color:#ffd60a;\">seperated with a comma and no white-spaces</span></span></html>";
        promptUserLabel = new JLabel(promptString);
        promptUserLabel.setForeground(Color.decode("#EE9B00"));

        gbcInputPanel.gridy = 0;
        gbcInputPanel.weightx = 1;
        gbcInputPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcInputPanel.insets = new Insets(0,50,5,50);
        this.add(promptUserLabel, gbcInputPanel);

        //-----Example label-----
        String exampleStringOne = "E.g: 1,2,3;<br>";
        String exampleStringTwo = "Values from array will be drawn as a rectangle on sort screen with values labeled in centre of rectangle";
        exampleLabel = new JLabel("<html><span style=\"font-family:SansSerif; font-weight:bold; font-size:12px;\">" + exampleStringOne + exampleStringTwo + "</span></html>");
        exampleLabel.setForeground(Color.decode("#E9D8A6"));

        gbcInputPanel.gridy = 1;
        this.add(exampleLabel, gbcInputPanel);

        //-----Note label-----
        String noteStringOne = "Note: Due to height of GUI, values from array of more than 700 or lesser than -700 will have its value labeled in a fixed position.<br>";
        String noteStringTwo = "Note: The maximum array size allowed is 25";
        noteLabel = new JLabel("<html><span style=\"font-family:SansSerif; font-weight:bold; font-size:10px;\">" + noteStringOne + noteStringTwo + "</span></html>");
        noteLabel.setForeground(Color.WHITE);

        gbcInputPanel.gridy = 2;
        this.add(noteLabel, gbcInputPanel);

        //-----JTextField box-----
        userInputField = new JTextField(30);

        gbcInputPanel.gridy = 3;
        gbcInputPanel.ipady = 10;
        gbcInputPanel.insets = new Insets(10,50,10,50);
        this.add(userInputField, gbcInputPanel);

        //-----Submit button-----
        String submitButtonString = "<html><span style=\"font-family:SansSerif; font-weight:bold; font-size:16px;\">Submit Array</span></html>";
        submitInputButton = new JButton(submitButtonString);
        submitInputButton.addActionListener(inputAction);
        submitInputButton.setForeground(Color.decode("#AE2012"));

        gbcInputPanel.gridy = 4;
        gbcInputPanel.ipady = 13;
        gbcInputPanel.insets = new Insets(10,250,0,250);
        this.add(submitInputButton, gbcInputPanel);
    }

    //--------------------String to Int[] Method--------------------
    public int[] userArray(){
        String userInputString = userInputField.getText();
        String[] arrayNumbers = userInputString.split(",");

        userArray = new int[lengthOfArray(arrayNumbers)];
        for(int i = 0; i < lengthOfArray(arrayNumbers); i++){
            userArray[i] = Integer.parseInt(arrayNumbers[i]);
        }
        return userArray;
    }

    //--------------------Helper Method--------------------
    private int lengthOfArray(String[] arrayNumbers){
        return arrayNumbers.length;
    }
}
