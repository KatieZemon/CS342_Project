package code;

//TODO: We need algorithm descriptions and run times for all algorithms used
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JInternalFrame;
import java.awt.Color;
import java.awt.Font;

/**
 * class: Help
 * desc:  This class contains instructions for how to use the program,
 * and it contains descriptions for algorithms.
 */
public class Help extends KInternalFrame
{
  /** Help class variables:
   * FRAME_WIDTH - The width of the internalFrame
   * FRAME_HEIGHT - The height of the internalFrame
   * ta - The text area holding the instructions for how to use the program
   * TA_WIDTH - The width of the text area
   * TA_HEIGHT - The height of the text area
   * helpInstructions - A string for holding the instructions for how to use the program
   * font_bold - The font type for our text area
   * scrollPane - The scrollPane the holds the text area
   */
  private final int FRAME_WIDTH = 600, FRAME_HEIGHT = 400;
  private JTextArea ta;
  private final int TA_WIDTH = 22, TA_HEIGHT = 40;
  private String helpInstructions, algorithmDescriptions;
  private Font font_bold;
  private JScrollPane scrollPane;


  /**
   * fn:   Help Default Constructor
   * desc: Creates our internal frame and displays a text area of instructions for how to
   * use the program
   */
  public Help()
  {
    super("Help");
    setSize(FRAME_WIDTH, FRAME_HEIGHT);

    // Initialize font properties
    font_bold = new Font("Bold", Font.BOLD, 16);

    // Write the problem description
    helpInstructions = "********************Instructions for program usage:********************\n\n" +
            "How to open the internal frames within our program:\n" +
            "This is done by either selecting the appropriate menu item from the menu bar or choosing one " +
            "of the following hotkeys: \n" +
            "Ctrl+1: Author\n" +
            "Ctrl+2: Problem Description\n" +
            "Ctrl+3: References\n" +
            "Ctrl+4: Help\n" +
            "Ctrl+5: Main Display\n\n" +

            "How to run a sorting algorithm: \n" +
            "1. Select Main Display to view the main display for running sorting algorithms.\n"+

            "2. Select up to three sorting algorithms from the check boxes appearing in the " +
            "Demos>Algorithms sub menu. This will add panels each containing bar graphs of data to be sorted. Each graph " +
            "will have a titled border specifying the sorting algorithm that will be used to sort the data. In order " +
            "to remove these panels, uncheck its associated sorting algorithm or choose Demos>Algorithms to unselect " +
            "all of the sorting algorithms and remove their panels from the mainDisplay internalFrame.\n" +

            "3. Once the panels have been added to the screen, select either best case, worst case, or random case " +
            "for sorting the data. This is done by selecting one of the options within the Demos>Data Type Items " +
            "submenu.\n" +

            "4. Change the Array Length slider to alter the number of items to be sorted in each algorithm\n" +

            "5. Change the Current Delay slider to alter the delay in milliseconds for each step in every sorting " +
            "algorithm.\n"+

            "6. Press the Sort button to begin initiation of the selected sorting algorithms. " +
            "While each graph is being sorted, the delay can be changed to slow down or speed up the " +
            "sorting process of each algorithm.\n" +

            "7. Press the reset button to stop the execution of the sorting algorithms.\n\n\n" +

            "********************* Algorithm Descriptions *********************\n" +
            // The following are the required sorting algorithms
            "1. Bubble Sort\n\n" +

            "2. Insertion Sort:\n" +
            "Worst case: O(n^2)\n" +
            "Best case: O(n) - Given an already-sorted array\n" +
            "Average case:\n\n" +

            "3. Selection Sort:\n" +
            "This algorithm iterates through an entire list of data while searching for the greatest item in that set. The " +
            " greatest item is swapped with the last item in that list. The algorithm starts again from the beginning " +
            "searching for the greatest item in the list until it reaches the next to last item in the list and swaps " +
            "its new greatest item with that item. The algorithm continues to search for the new greatest items in a smaller " +
            "and smaller list of unsorted data until that small list of data is size 1. " +
            "Worst case: O(n^2)\n" +
            "Best case: O(n^2)\n" +
            "Average case: O(n^2)\n" +

            "4. Quick Sort:\n" +
            "Worst case: O(n^2)" +
            "Best case: O(nlogn)" +
            "Average case: O(nlogn)" +

            "5. Heap Sort:\n\n" +

            "6. Shell Sort:\n\n" +

            // The following are sorting algorithms for extra credit
            "7. Merge Sort:\n" +
            "Recursively sort\n" +
            "Worst case: O(nlogn)\n" +
            "Best case: O(nlogn))\n" +
            "Average case: O(nlogn)\n\n" +

            "8. Bucket Sort:\n\n" +
            "9. Bogo Sort:\n\n" +
            "10. Comb Sort:\n\n" +
            "11. Distribution Sort:\n\n" +
            "12. Counting Sort:\n\n" +
            "13. Radix Sort:\n\n"+
            "14. Gnome Sort:\n\n" +
            "15. ...";


    // Initialize our text area
    ta = new JTextArea(helpInstructions, TA_WIDTH, TA_HEIGHT);
    ta.setFont(font_bold);
    ta.setEnabled(false);
    ta.setDisabledTextColor(new Color(131, 28, 40));
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