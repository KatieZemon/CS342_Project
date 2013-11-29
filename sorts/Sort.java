package code.sorts;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.HashMap;

/**
 * Holds the functionality shared by all sorting algorithms. It draws a horizontal bar graph of
 * data to be sorted and contains a function for swapping one value with another
 */
public abstract class Sort extends JPanel implements Runnable {

  /** Array of values to be sorted */
  protected int[] values;

  /** An array of bars used in the bar graph*/
  protected Rectangle[] bars; // Our rectangular bars in the chart

  /** The title of the graph which displays the name of the sorting algorithm used */
  protected String graphTitle;

  /** Determines whether the algorithm is running*/
  public volatile boolean running = false;

  /** The time (in milliseconds) for a delay of a comparison for any sorting algorithm*/
  public int delayTime;

  /** Displays the name of the sorting algorithm used */
  private TitledBorder titleBorder;

  /** Font used in the titledborder */
  private final Font TITLE_FONT = new Font("Bold", Font.BOLD, 15);

  /** The width of the panel holding the bar graph*/
  private final int PANEL_WIDTH = 800;

  /** The height of the panel holding the bar graph */
  private final int PANEL_HEIGHT = 150;

  /** An integer value representing the highlighted bar*/
//  private int selectedBar = 0;

  /** The width of each bar in the graph */
  private int barWidth;

  /** The background color of the panel that holds the bar graph*/
  private final Color PANEL_BACKGROUND_COLOR = new Color(200, 200, 200);

  /** The color for the outline of each bar */
  protected final static Color BAR_OUTLINE_COLOR = new Color(14, 0, 10);

  /** The color used to fill all bars that are not selected*/
  protected final static Color DEFAULT_BAR_COLOR = new Color(48, 57, 114);

  /** One of two colors for the selected bar */
  protected final static Color SELECTED_BAR_COLOR1 = new Color(255, 226, 41);

  /** One of two colors for the selected bar */
  protected final static Color SELECTED_BAR_COLOR2 = new Color (204, 166, 61);

  /** The fill Color (gradient of two colors) for the selected bar.*/
//  protected GradientPaint selectedBarColor = new GradientPaint(75, 75, SELECTED_BAR_COLOR1,
//          95, 95, SELECTED_BAR_COLOR2, true);

  /** Keeps track of colored bars */
  private HashMap<Integer, Color> specialColoredBars = new HashMap<Integer, Color>();

  /**
   *
   * @param values the values to sort
   * @param delay the delay per iteration of sort
   * @param sortName the name of the sort
   */
  public Sort(int[] values, Integer delay, String sortName) {
    this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    this.values = values;
    this.delayTime = delay;
    graphTitle = sortName;
    titleBorder = new TitledBorder(
        new LineBorder(Color.BLACK,3), graphTitle, TitledBorder.CENTER,
            TitledBorder.DEFAULT_POSITION, TITLE_FONT, Color.BLACK);
    this.setBorder(titleBorder);
    this.setBackground(PANEL_BACKGROUND_COLOR);
    initBars();
  }

  /**
   * Initializes the rectangles that represent the data to be sorted in the chart
   */
  private void initBars(){
    int xPos, yPos, yHeight;
    // Initialize all bar sizes
    bars = new Rectangle[values.length];
    barWidth = (PANEL_WIDTH)/values.length; // The width of each bar
    for (int i = 0; i < values.length; i++)
    {
      yHeight = values[i];
      xPos = i*barWidth + 5;
      yPos = PANEL_HEIGHT - yHeight - 5;
      bars[i] = new Rectangle(xPos,yPos,barWidth,yHeight);
    }
  }

  /**
   * @param values the array of integers to be sorted
   */
  public void setValues(int[] values){
    if(!running){
      this.values = values;
      initBars();
    }
  }

  protected abstract void runSort();

  public void run()
  {
    running = true;
    runSort();
    running = false;
  }

  /**
   * sleeps for the value of <code>delayTime</code>
   */
  protected void delay(){
    try {
      Thread.sleep(delayTime);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Compares two bars by their index (zero-based)
   * @param barIndex1 index of first bar
   * @param barIndex2 index of second bar
   * @return
   * <ul>
   *   <li><b>positive</b> int if first is <b>greater than</b> second</li>
   *   <li><b>negative</b> int if first is <b>less than</b> second</li>
   *   <li><b>zero</b> int if first is <b>equal to</b> second</li>
   * </ul>
   */
  protected int compare(int barIndex1, int barIndex2){
    delay();
    return bars[barIndex1].height - bars[barIndex2].height;
  }

  /**
   * swaps two rectangular bars in the graph
   * @param left the left bar to be swapped with the right bar
   * @param right the right bar to be swapped with the left bar
   */
  protected void swap(int left, int right)
  {
    int height = bars[right].height;
    int yPos = bars[right].y;
    bars[right].setBounds(bars[right].x, bars[left].y, bars[left].width, bars[left].height );
    bars[left].setBounds( bars[left].x, yPos, bars[left].width, height );
    repaint();
    delay();
  }


  protected void colorBar(int barIndex, Color color){
    //TODO consider having a repaint method here, maybe just repaint the one bar
    specialColoredBars.put(barIndex, color);
  }

  protected void uncolorBar(int barIndex){
    if(specialColoredBars.containsKey(barIndex)){
      specialColoredBars.remove(barIndex);
    }
    //OR specialColoredBars.put(barIndex, DEFAULT_BAR_COLOR);
  }

  protected Color getBarColor(int barIndex){
    if(specialColoredBars.containsKey(barIndex)){
      return specialColoredBars.get(barIndex);
    }
    return DEFAULT_BAR_COLOR;
  }

  public void clearBarColors(){
    specialColoredBars.clear();
  }




  /**
   * Draws the bars on the screen
   */
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    for (int i = 0; i < values.length; i++){
      g2.setColor(getBarColor(i));
      g2.fill(bars[i]);
      // Draw an outline for the bar under certain conditions
//      if ( barWidth > 1 || (i != selectedBar && barWidth < 2))
      if(barWidth > 3)
      {
        g2.setPaint(BAR_OUTLINE_COLOR);
        g2.draw(bars[i]);
      }
    }
  }

  @Override
  public String toString()
  {
    return graphTitle;
  }
}