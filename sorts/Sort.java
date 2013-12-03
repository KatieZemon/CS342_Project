package code.sorts;

import code.ValueBar;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.HashMap;

/**
 * Holds the functionality shared by all sorting algorithms. It draws a horizontal bar graph of
 * data to be sorted and contains a function for swapping one indexValue with another
 */
public abstract class Sort extends JPanel implements Runnable {

  /** Array of values to be sorted */
  protected int[] values;

  /** An array of bars used in the bar graph*/
  protected ValueBar[] bars; // Our rectangular bars in the chart

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
  private int panelHeight = 150;

  /** The width of each bar in the graph */
  private int barWidth;

  /** The background color of the panel that holds the bar graph*/
  private final Color PANEL_BACKGROUND_COLOR = new Color(200, 200, 200);

  /** The color for the outline of each bar */
  protected final static Color BAR_OUTLINE_COLOR = new Color(14, 0, 10);

  /** The color used to fill all bars that are not selected*/
  protected final static Color DEFAULT_BAR_COLOR = new Color(48, 57, 114);

  /** Keeps track of colored bars */
  protected HashMap<Integer, Color> specialColoredBars = new HashMap<Integer, Color>();

  /**
   * Constructor used to create a new sorting algorithm. It initializes the
   * sort with the given values, the delay, and a sort name
   * @param values the values to sort
   * @param delay the delay in milliseconds per iteration and/or comparison of sort
   * @param sortName the name of the sort
   */
  public Sort(int[] values, Integer delay, String sortName) {
    // this.panelHeight = panelHeight
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
    this.setPreferredSize(new Dimension(PANEL_WIDTH, this.panelHeight));
    // Initialize all bar sizes
    createBars(values);
  }

  /**
   * Creates bars for integer array passed in
   */
  protected void createBars(int[] v){
    bars = new ValueBar[v.length];
    barWidth = (PANEL_WIDTH)/v.length; // The width of each bar
    for (int i = 0; i < v.length; i++)
    {
      bars[i] = new ValueBar(values[i], i*barWidth + 5, barWidth, panelHeight);
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

  /**
   * This is used in every sorting algorithm which inherits from the
   * Sort class. It runs the main loop of each sorting algorithm
   */
  protected abstract void runSort();

  /**
   * This is used to run the sorting alogorithms and keep track
   * of the fact that the sorting algorithms are running. When running=false,
   * we know that the sorting algorithms are complete
   */
  public void run()
  {
    running = true;
    runSort();
    running = false;
  }

  /**
   * sleeps for the indexValue of <code>delayTime</code>
   */
  protected void delay(){
    try {
      Thread.sleep(delayTime);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * @return a copy of the bars array
   */
  protected ValueBar[] getBarsCopy(){
    ValueBar[] barsCopy = new ValueBar[bars.length];
    for(int i = 0; i < values.length; i++){
      barsCopy[i] = new ValueBar(bars[i]);
    }
    return barsCopy;
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
    return bars[barIndex1].value - bars[barIndex2].value;
  }

  /**
   * swaps two rectangular bars in the graph
   * @param left the left bar to be swapped with the right bar
   * @param right the right bar to be swapped with the left bar
   */
  protected void swap(int left, int right)
  {
    swap(bars[left], bars[right]);
//    int height = bars[right].height;
//    int yPos = bars[right].y;
//    bars[right].setBounds(bars[right].x, bars[left].y, bars[left].width, bars[left].height );
//    bars[left].setBounds( bars[left].x, yPos, bars[left].width, height );
//    repaint();
//    delay();
  }

  /**
   * swaps the sizes of ValueBars a and b
   * @param a ValueBar "a" to be swapped with ValueBar "b"
   * @param b ValueBar "b" to be swapped with ValueBar "a"
   */
  protected void swap(ValueBar a, ValueBar b){
    int tempVal = b.value;
    int height = b.height;
    int yPos = b.y;
    b.setBounds(b.x, a.y, a.width, a.height);
    b.value = a.value;
    a.setBounds(a.x, yPos, a.width, height);
    a.value = tempVal;
    repaint();
    delay();
  }

  /**
   * @param index of bar to get value of
   * @return the value of the bar
   */
  protected int getBarValue(int index){
    return bars[index].value;
  }

  /**
   * Sets the height of the bar at the specified index
   * @param index The index value for this bar
   * @param value The new height of the bar
   */
  protected void setBarValue(int index, int value){
    int yHeight = value;
    int yPos = panelHeight - yHeight - 5;
    ValueBar original = new ValueBar(bars[index]);
    bars[index] = new ValueBar(value, original.x, original.width, panelHeight);
    repaint();
  }

  /**
   * Decrements a specific bar by a specific value
   * @param index of bar to decrement
   * @param value to decrement current bar value
   */
  protected void decrementBarValue(int index, int value){
    setBarValue(index, getBarValue(index) - value);
  }

  /**
   * Increments a specific bar by a specific value
   * @param index of bar to increment
   * @param value to increment current bar value
   */
  protected void incrementBarValue(int index, int value){
    setBarValue(index, getBarValue(index) + value);
  }

  /**
   * Sets the bar at the given index to the color provided
   * @param barIndex The index of the bar
   * @param color The color of the bar
   */
  protected void colorBar(int barIndex, Color color){
    repaint(); // Repaint the bar that was colored
    specialColoredBars.put(barIndex, color);
  }

  /**
   * Resets the color of a specific bar
   * @param barIndex index of bar to reset to default color
   */
  protected void uncolorBar(int barIndex){
    if(specialColoredBars.containsKey(barIndex)){
      specialColoredBars.remove(barIndex);
    }
  }

  /**
   * Get the color of a specific bar
   * @param barIndex index of bar to check color
   * @return the color of the given bar
   */
  protected Color getBarColor(int barIndex){
    if(specialColoredBars.containsKey(barIndex)){
      return specialColoredBars.get(barIndex);
    }
    return DEFAULT_BAR_COLOR;
  }

  /**
   * resets all bars to their default color
   */
  public void clearBarColors(){
    specialColoredBars.clear();
  }

  /**
   * Asserts that the given array is in sorted order
   * @param array
   * @throws AssertionError if array not in sorted order
   */
  protected void assertSorted(ValueBar[] array){
    int y = -1;
    for (int i = 0; i < array.length; i++) {
      int newY = array[i].value;
      if (!(y <= newY)){
        throw new AssertionError("y(" + y + ")   newY(" + newY + ")");
      };
      y = newY;
    }
  }

  /**
   * @param panelHeight sets the height of the panel
   */
  public void setPanelHeight(int panelHeight){
    this.panelHeight = panelHeight;
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
      // Print an error message if there are no bars to be displayed
      if(bars == null)
        System.err.println("Error! There are no values to be printed on this graph!");
      g2.fill(bars[i]);
      // Draw an outline for the bar under certain conditions
      if(barWidth > 3)
      {
        g2.setPaint(BAR_OUTLINE_COLOR);
        g2.draw(bars[i]);
      }
    }
  }

  /**
   * Get the title of the graph
   */
  @Override
  public String toString()
  {
    return graphTitle;
  }
}
