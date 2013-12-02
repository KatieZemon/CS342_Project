package code;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JInternalFrame;
import java.awt.Color;
import java.awt.Font;

/**
 * This class contains a list of references used for creating the program
 */
public class References extends KInternalFrame
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
  private String referencesString;

  /** The font type for our text area */
  private Font font_bold;

  /** The scrollPane the holds the text area */
  private JScrollPane scrollPane;

  /**
   * References Default Constructor. This creates the references
   * internal frame and initializes its contents
   */
  public References()
  {
    super("References");
    setSize(FRAME_WIDTH,FRAME_HEIGHT);

    // Initialize font properties
    font_bold = new Font("Bold", Font.BOLD, 16);

    // Write the problem description
    referencesString = "References:\n" +
            "1. The problem description for this assignment is a rewritten version of what was originally designed by " +
            "Dr.Chaman Sabharwal from Missour S&T.\n\n\n" +

            "2. An example taken from this website was used for creating the bar graph:"+
            "http://jlipton.web.wesleyan.edu/courses/fall2012/lecs/barGraph/arrayVersion/ArrayBarGraph.java\n\n" +
            "From the example, this section of code was borrowed, modified, and used in our project:\n" +
            "Graphics2D g2 = (Graphics2D) g;\n" +
            "int barwidth = WIDTH/arr.length;\n" +
            "for(int i=0;i<arr.length;i++)\n" +
            "    {\n" +
            "      Rectangle r = new Rectangle(i*barwidth,HEIGHT - arr[i],barwidth,arr[i]);\n" +
            "      g2.setColor(Color.RED);\n" +
            "      g2.fill(r);\n" +
            "      g2.setColor(Color.BLACK);\n" +
            "      g2.draw(r);\n" +
            "    }\n\n\n" +


            "3. Wikipedia was used for checking our understanding of sorting algorithms. In particular," +
            " the following websites were referenced for gaining knowledge about each algorithm: " +
            "http://en.wikipedia.org/wiki/Bead_Sort \n" +
            "http://en.wikipedia.org/wiki/Bubble_sort \n" +
            "http://en.wikipedia.org/wiki/Cocktail_sort \n" +
            "http://en.wikipedia.org/wiki/Comb_sort \n" +
            "http://en.wikipedia.org/wiki/Counting_sort \n" +
            "http://en.wikipedia.org/wiki/Cycle_sort \n" +
            "http://en.wikipedia.org/wiki/Bucket_sort \n" +
            "http://en.wikipedia.org/wiki/Gnome_sort \n" +
            "http://en.wikipedia.org/wiki/Heap_sort \n" +
            "http://en.wikipedia.org/wiki/Insertion_sort \n" +
            "http://en.wikipedia.org/wiki/Merge_sort \n" +
            "http://en.wikipedia.org/wiki/Odd_even_sort \n" +
            "http://en.wikipedia.org/wiki/Quick_sort \n" +
            "http://en.wikipedia.org/wiki/Selection_sort \n" +
            "http://en.wikipedia.org/wiki/Shell_sort \n" +
            "http://en.wikipedia.org/wiki/Stooge_sort \n" +
            "http://en.wikipedia.org/wiki/Tree_sort \n" +
            "http://en.wikipedia.org/wiki/Radix_sort \n\n\n" +

            "4. The following sites were referenced in order to create the check box menu item that is able" +
            "to stay open even after the user selects one of the items. \n" +
            "http://www.coderanch.com/t/497325/GUI/java/popup-clicking-JCheckBoxMenuItem \n" +
            "http://tips4java.wordpress.com/2010/09/12/keeping-menus-open/ \n\n";

    // Initialize our text area
    ta = new JTextArea(referencesString, TA_WIDTH, TA_HEIGHT);
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
