package code;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;

/**
 * This class contains information about the authors of the program, including
 * each author's contribution to the project
 */
public class Author extends KInternalFrame
{
  /** The width of the internalFrame */
  private final int FRAME_WIDTH = 600;

  /** The height of the internalFrame */
  private final int FRAME_HEIGHT = 400;

  /** The text area holding the instructions for how to use the program */
  private JTextArea ta;

  /** The width of the text area */
  private final int TA_WIDTH = 22;

  /** The height of the text area */
  private final int TA_HEIGHT = 40;

  /** String containing the list of references used for creating this program*/
  private String authorString;

  /** The font type for our text area */
  private Font font_bold;

  /** The scrollPane the holds the text area */
  private JScrollPane scrollPane;

  /**
   * Default Constructor. This creates the author
   * internal frame and initializes its contents
   */
  public Author()
  {
    super("Author");
    setSize(FRAME_WIDTH,FRAME_HEIGHT);

    // Initialize font properties
    font_bold = new Font("Bold", Font.BOLD, 16);

    // Write the problem description
    authorString = "CS342 Final Project\n\n" +
            "Professor: Dr. Chaman Sabharwal\n\n" +
            "Created by Tom Clay, Katie Isbell, and Westin Sykes\n\n" +
            "About the authors:\n" +

            // TOM CLAY
            "TOM CLAY\n" +
            "Contact:\n" +
            "      Phone: 816-547-5127\n" +
            "      Email:  thomas.clay@mst.edu\n" +
            "Contributions:\n" +
            "      -Created AlgorithmSelectionListener.java, FrameDisplayListener.java, and MenuBuilder.java  in order to improve the organization of our code for the greater goal of making the program more portable and easier to read.  \n" +
            "      - StayOpenCheckBoxMenu so our check box menus could stay open while we are selecting algorithms.\n" +
            "      -Implemented the following sorting algorithms:\n" +
            "            -merge sort\n" +
            "            -binary tree sort\n" +
            "            -cycle sort\n" +
            "            -stooge sort\n" +
            "            -shell sort\n" +
            "            -heap sort\n" +
            "            -bucket sort\n" +
            "            -trucket sort\n" +
            "            -Tom sort\n" +
            "            -bead sort\n" +
            "            -counting sort\n" +
            "Originality\n" +
            "      -Reference number 4 under the list of references was used in order to create StayOpenCheckBoxMenu.\n" +
            "      -TomSort and TrucketSort are originally created by me\n" +
            "Learned:\n" +
            "      -A lot of the different algorithms I implemented, excluding heap, binary tree, and merge.\n" +

            // Katie Isbell
            "KATIE ISBELL\n" +
            "Contact:\n" +
            "      Phone: 251-709-0425\n" +
            "      Email:  kjihdc@mst.edu\n" +
            "Contributions:\n" +
            "      -Set up the program with a menu bar and all needed menus and menu items.\n" +
            "      -Created Author.java, Problem.java, References.java, and Help.java.\n" +
            "      -Initialized MainDisplay.java with the both sliders and buttons\n" +
            "      -Wrote all of the initial functionality for the rectangular bar graph. This included creating a list of random numbers to be sorted, displaying the graph using those random values, writing a function to iterate through the graph, and writing a function to swap two rectangles.\n" +
            "      -Wrote the functionality to display a list of presorted data when Best Case scenario is chosen, and wrote the functionality to display data sorted from greatest to least when Worst Case scenario is chosen\n" +
            "      -Implemented the following sorting algorithms:\n" +
            "            -selection sort\n" +
            "            -insertion sort\n" +
            "            -quick sort\n" +
            "            -comb sort\n" +
            "            -cocktail sort\n" +
            "            -odd-even sort\n" +
            "            -gnome sort\n" +
            "      -Wrote the User’s Manual except for information about the other authors\n" +
            "Originality:\n" +
            "      Wikipedia was referenced in order to recall how each of these algorithms work\n" +
            "      Dr. Chaman Sabharwal’s notes about creating Rectangular bars and writing multithreaded programs" +
            " were referenced\n" +
            "Learned:\n" +
            "      -Learned how to write multi-threaded code in Java\n" +
            "      -Refreshed my knowledge of all the sorting algorithms used in this program\n" +
            "      -Became more comfortable with using Github\n" +
            "      -Became familiar with the Trello application that was used for organizing the team’s checklist\n" +
            "\n\n\n" +

            // Westin Sykes
            "WESTIN SYKES" +
            "Contact:\n" +
            "      Phone: 870-404-1413\n" +
            "      Email: jws279@mst.edu\n" +
            "Contributions:\n" +
            "      -Shaped MainDisplay.java to work dynamically with sorts and made it to be easy to add new sorting algorithms.\n" +
            "      -Wrote instructions to add new sorts to code.\n" +
            "      -Implemented bubble sort and radix sort\n" +
            "      -Implemented ability to vertically scale sort panels\n" +
            "Originality:\n" +
            "      -Used Wikipedia for remembering how each sorting algorithm worked.\n" +
            "      -Referenced the following website to figure out how the Radix algorithm worked: " +
            "http://gauss.ececs.uc.edu/Courses/C321/html/radix.java.html\n" +
            "Learned:\n" +
            "      How Radix sort works\n" +
            "      Java threads experience\n";

    // Initialize our text area
    ta = new JTextArea(authorString, TA_WIDTH, TA_HEIGHT);
    ta.setFont(font_bold);
    ta.setEnabled(false);
    ta.setDisabledTextColor(new Color(11, 94, 31));
    ta.setBackground(new Color(244, 245, 195));
    ta.setLineWrap(true); // avoid scrolling left and right
    ta.setWrapStyleWord(true);

    // Add our text area to a scroll pane so that the user can scroll through text in
    // case our list of instructions becomes long
    scrollPane = new JScrollPane(ta);

    // Add the scrollPane to our panel
    this.add(scrollPane);

    repaint();
  }
}
