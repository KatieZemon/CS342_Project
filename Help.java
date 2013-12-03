package code;


import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JInternalFrame;
import java.awt.Color;
import java.awt.Font;

/**
 * This class contains instructions for how to use the program,
 * and it contains descriptions for the algorithms.
 */
public class Help extends KInternalFrame
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

  /** String containing the list of instructions for how to use the program and the
   * descriptions about each algorithm used*/
  private String helpMessage;

  /** The font type for our text area */
  private Font font_bold;

  /** The scrollPane the holds the text area */
  private JScrollPane scrollPane;

  /**
   * Help Default Constructor that creates the internal frame and displays a text area of instructions for how to
   * use the program
   */
  public Help()
  {
    super("Help");

    /**
     * Sets the frame size and width
     * @param FRAME_WIDTH the width of the frame
     *  @param FRAME_HEIGHT the height of the frame
     */
    setSize(FRAME_WIDTH, FRAME_HEIGHT);

    // Initialize font properties
    font_bold = new Font("Bold", Font.BOLD, 16);

    // Write the problem description
    helpMessage = "********************Instructions for program usage:********************\n\n" +
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

            "1. Bead Sort:\n" +
            "The idea is based on an abacus.  If you had rows of beads on parallel columns, and allowed the beads to " +
            "fall, then the result rows would be sorted.\n\n" +

            "2. Bubble Sort:\n" +
            "Bubble sort works by starting at the beginning of an array and working upwards swapping" +
            " two neighboring numbers if the left if greater than the right. This sort works slowly at O(n2)" +
            " time but requiring little code and O(n) memory usage.\n\n" +

            "3. Bucket Sort:\n" +
            "Moves values into buckets of certain ranges.  Pulls values out of the buckets and places them into the " +
            "array.  Since each bucket has values strictly larger than the previous, insertion sort preforms in about " +
            "O(n) time. \n\n" +
            
            "4. Clock Sort:\n" +
            "Iterates through all possible values starting from the lowest. " +
            "The current iterations value is called the clock value. " +
            "Starting with the 0th index, it swaps any value that matches the clock " +
            "then increments the current index.\n\n" +

            "5. Cocktail Sort:\n" +
            "Cocktail Sort is also called bidirectional bubble sort, shaker sort,\n" +
            " * shuffle sort, ripple sort, shuttle sort, or happy hour sort.\n" +
            " * It is both stable and a comparison sorting algorithm. It is a variation of\n" +
            " * Bubble Sort but it differs in that it sorts in both directions. (source from Wikipedia)\n\n." +

            "6. Comb Sort:\n" +
            " Is a sorting algorithim that is faster than bubble sort, but is implemented ina similar fashion. " +
            " Comb sort is different than bubble sort in that it initializes an index value gap to be usually 1/3 " +
            " of the number of elements to be sorted.  The algorithm the compares elements i and i+gap until " +
            " i+gap reaches the end of the array, and preforms swaps where needed.  It reduces the gap index value " +
            " for each iteration and stops until the gap is equal to one and no swaps have been performed. \n\n" +

            "7. Counting Sort:\n" +
            "This counts the numbers of times an element value occurs.  Then figures out where each value should be in the " +
            "array by using the number of occurrences of that item.  Then it places items in the correct order\n\n" +

            "8. Cycle Sort:\n" +
            "Looks at each item to find where it should be in the array.  Once it finds it's position " +
            "it swaps items, then attempts to find the swapped items position in the array.  If the swapped item " +
            "belongs where it was placed, it goes to the next item in the array.\n\n  " +

            "9. Gnome Sort:\n" +
            "Gnome sort is a sorting algorithm that works similar\n" +
            " * to insertion sort but moves elements to their proper place\n" +
            " * by doing a series of swaps between adjacent elements (like\n" +
            " * Bubble Sort). It is based on the fact that performing a swap\n" +
            " * can introduce a new pair of elements that are out of order.\n\n" +

            "10. Heap Sort:\n" +
            "Moves items in array until the heap property applies. " +
            "The heap proprety ensures that the children (2 * index + 1 and 2 * index + 2) " +
            "and children of those children have a value less than thier parents.  " +
            "using this proprety, it moves the root (which has the largest value) to the " +
            "end of the array, and decreases the heap size by one.\n\n" +

            "11. Insertion Sort:\n" +
            "Worst case: O(n^2)\n" +
            "Best case: O(n) - Given an already-sorted array\n" +
            "Average case:\n\n" +

            "12. Merge Sort:\n" +
            "Starts of by ensuring every w items in the array are sorted. " +
            "w doubles every time the previous w items have been sorted and merged. " +
            "The merging process takes two consecutive runs of sorted data, and creates " +
            "a larger run of sorted data.\n" +
            "Worst case: O(nlogn)\n" +
            "Best case: O(nlogn))\n" +
            "Average case: O(nlogn)\n\n" +

            "13. Odd-even Sort:\n" +
            "Odd-even sort is a variation of bubble sort which compares all odd,even index pairs of elements instead" +
            "of all adjacent elements. This sorting algorithm is also known as brick sort and it was" +
            "originally developed for use on parallel processors. (Wikipedia).\n\n  " +

            "14. Quick Sort:\n" +
            "The Quick Sort algorithm uses a divide and conquer approach to sorting a list of values. " +
            "It partitions the list into two lists using a pivot and left and right index. It moves the left index " +
            " closer to the pivot until one of the values to the left of the pivot is greater than the pivot. " +
            "It then moves the right index closer to the pivot until it finds a value less than the pivot, and then " +
            "the left and right bars are swapped. The left and right indices move closer to the pivot (each not passing " +
            "the pivot location until both left and right indices are at the pivot location. At this point the pivot " +
            "is sorted. The algorithm then runs on the left half of the list of data and then the right half until " +
            "the entire list of data is sorted.\n" +
            "The worst case run time of quick sort is O(n^2)\n" +
            "The best case is O(nlogn)\n" +
            "The average case: O(nlogn)\n\n" +

            "15. Radix Sort:\n" +
            "Radix sort is different than most sorts in that it uses no comparisons. It works by grouping same " +
            "numbers for each index in the integers to be sorted. This method allows for O(n) worst case sorting " +
            "times when the data set allows for this sorting algorithm.\n\n" +

            "16. Selection Sort:\n" +
            "This algorithm iterates through an entire list of data while searching for the greatest item in that set. The " +
            " greatest item is swapped with the last item in that list. The algorithm starts again from the beginning " +
            "searching for the greatest item in the list until it reaches the next to last item in the list and swaps " +
            "its new greatest item with that item. The algorithm continues to search for the new greatest items in a smaller " +
            "and smaller list of unsorted data until that small list of data is size 1. \n\n" +

            "17. Shell Sort:\n" +
            "A lot like comb sort, except it uses insertion sort as a sub sequence instead of bubble sort" +

            "18. Stooge Sort:\n" +
            "A stupid sorting that unsorts 1/3 of its list while sorting 2/3 of some side.  " +
            "Preforms worse than bubble sort but still gets the job done, somehow.\n\n" +

            "19. Tom Sort:\n" +
            "Similar to TimSort in that it looks for runs -- elements in the array that are already in sorted, or reverse-" +
            "sorted order.  It then reverses the reverse-sorted runs so that all the runs are in sorted order.  It then " +
            "preforms merge sort on the sorted runs.\n\n" +

            "20. Tree Sort:\n" +
            "Puts each value of an array into a binary tree, where left children are less than the root value, and " +
            "right children contain values that are greater than the root value.  Preforms an in-order iteration to " +
            "retrieve the values in sorted order.\n\n" +

            "21. Trucket Sort:\n" +
            "A variation of bucket sort that places values in a certain range into a respective tree.  " +
            "Then when the buckets are copied into the array they are in sorted order. " +
            "\n\n";

    // Initialize our text area
    ta = new JTextArea(helpMessage, TA_WIDTH, TA_HEIGHT);
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