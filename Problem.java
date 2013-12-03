package code;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JInternalFrame;
import java.awt.Color;
import java.awt.Font;

/**
 * This creates an internal frame holding our problem description
 */
public class Problem extends KInternalFrame
{
  /** Width of the internal frame */
  private final int FRAME_WIDTH = 600;
  /** Height of the internal frame */
  private final int FRAME_HEIGHT = 400;
  /** Text area holding the problem description*/
  private JTextArea ta;
  /** Text area width */
  private final int TA_WIDTH = 22;
  /** Text area height */
  private final int TA_HEIGHT = 40;


  /**
   * Creates our panel and initializes its contents
   */
  public Problem()
  {
    super("Problem Description");
    setSize(FRAME_WIDTH, FRAME_HEIGHT);

    // Initialize font properties
    Font font_bold = new Font("Bold", Font.BOLD, 16);

    // Write the problem description
    String problemDescription =
            "The main objective for this project was to simulate multiple sorting algorithms.\n" +
                    "The program will have a menubar with an About menu and a Demos menu.\n\n" +
                    "The About menu has the following menuItems:\n" +
                      "1. Author: This opens a JInternalFrame with description about the authors\n" +
                      "2. Problem Description: This opens a JInternalFrame describing the problem\n" +
                      "3. References: This opens a JInternalFrame containing references to all resources used\n"+
                      "4. Help: This opens a JInternalFrame containing instructions on how to run the program\n\n" +
                    "The Demos menu has the following:\n"+
                      "1. Algorithms: A submenu which contains JCheckBox menu items for each sorting algorithm and an " +
                      "Unselect menuItem to unselect all menuItems. This submenu will only allow you to check three sorting " +
                      "algorithms at a time since there can only be three sorting algorithms displayed and running on the screen " +
                      "at once.\n" +
                      "2. Data Type Items: A submenu which contains JRadioButton menu items to allow the user to select " +
                      "from random, best, and worst case scenarios for all algorithms to run.\n" +
                      "3. Main Display: A menu item that opens a JInternalFrame which is used to view the algorithms running " +
                      "next to each other simultaneously.\n\n" +

                    "The MainDisplay JInternalFrame contains an Array Length slider for changing the number " +
                    "of items to be sorted, a sort button to initiate the sorting algorithms, a reset button to stop " +
                    "execution of the sorting algorithms, and a Current Delay slider to change the delay of each algorithm. " +
                    "The Array Length slider and Delay slider each have titled borders describing what they are and displaying " +
                    "Their current value. As the user moves each slider, the value displayed in the title also changes. " +
                    "When the user selects an algorithm from the Demos menu, panels appear with graphs of data, each graph " +
                    "representing a list to be sorted and each panel having a titled border displaying the name of the " +
                    "sorting algorithm that will be used on that graph. When the user presses the Reset button, " +
                    "execution of all of the algorithms will stop. When the user unchecks one of the algorithms in the " +
                    "Demos>Algorithms check box list, the corresponding panel for that algorithm will be removed from " +
                    "the Main Display JInternalFrame.\n\n" +

                    "The required sorting algorithms to be implemented are as follows: \n" +
                    "Bubble Sort, Insertion Sort, Selection Sort, Quicksort, Heapsort, and Shellsort\n" +
                    "Any other algorithms were created for extra credit.\n\n" +

                    "Along with the project code, a powerpoint presentation and users manual was created for " +
                    "a demo and presentation.\n";

    // Initialize our text area
    ta = new JTextArea(problemDescription, TA_WIDTH, TA_HEIGHT);
    ta.setFont(font_bold);
    ta.setEnabled(false);
    ta.setDisabledTextColor(new Color(31, 50, 131));
    ta.setBackground(new Color(245, 243, 184));
    ta.setLineWrap(true); // avoid scrolling left and right
    ta.setWrapStyleWord(true);


    // Add the scrollPane to our panel
    add(new JScrollPane(ta));

    repaint();
  }
}


