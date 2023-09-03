import javax.swing.*;
import java.awt.*;

/*
    Design explanation:
        I decided to split the different codes to different classes, this is done to facilitate
        a smoother debugging experience, give each class its own responsibility and to provide a clearer
        readability to others who view this project.

        Throughout the project, I used GridBagConstraints to place components as it provides more flexibility
*/

public class MyFrame extends JFrame {

    private static JFrame mainFrame = new JFrame();

    private MenuPanel menu = new MenuPanel();

    MyFrame() {
        mainFrame.setTitle("Sorting Algorithm Visualiser");
        mainFrame.getContentPane().setPreferredSize(new Dimension(800,800));
        mainFrame.getContentPane().add(menu);
        mainFrame.pack();
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    //Setter method, allowing other panels to be set onto frame
    public static void setPanel(JPanel panel){
        mainFrame.setContentPane(panel);
        mainFrame.validate();
    }
}
