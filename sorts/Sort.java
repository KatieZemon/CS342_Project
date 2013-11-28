package code.sorts;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Holds the functionality shared by all sorting algorithms. It draws a horizontal bar graph of
 * data to be sorted and contains a function for swapping one value with another
 */
public abstract class Sort extends JPanel implements Runnable {
  protected int[] values;
  protected Rectangle[] bars; // Our rectangular bars in the chart
  protected String graphTitle;
  public volatile boolean running = false;
  public int delay;
  protected TitledBorder titleBorder;
  protected Font font_bold = new Font("Bold", Font.BOLD, 15);
  protected int panelWidth = 800;
  protected int panelHeight = 150;
  protected int selectedBar = 0; // The highlighted bar
  protected Color panelBackgroundColor = new Color(247, 247, 247);
  protected Color barOutlineColor = new Color(14, 0, 10);
  protected Color unselectedBarColor = new Color(49, 78, 139);
  protected Color selectedBarColor1 = new Color(216, 47, 154);
  protected Color selectedBarColor2 = new Color (164, 63, 197);

  protected GradientPaint selectedBarColor = new GradientPaint(75, 75, selectedBarColor1,
          95, 95, selectedBarColor2, true);

  /**
   *
   * @param values the values to sort
   * @param delay the delay per iteration of sort
   * @param sortName the name of the sort
   */
  public Sort(int[] values, Integer delay, String sortName) {
    //panelWidth = panelWidth/values.length * values.length;
    this.setPreferredSize(new Dimension(panelWidth, panelHeight));
    this.values = values;
    this.delay = delay;
    graphTitle = sortName;

    titleBorder = new TitledBorder(
        new LineBorder(Color.BLACK,3), graphTitle, TitledBorder.CENTER,
            TitledBorder.DEFAULT_POSITION, font_bold, Color.BLACK);
    this.setBorder(titleBorder);

    this.setBackground(panelBackgroundColor);

    initBars();

  }

  /**
   * Initializes the rectangles that represent the data to be sorted in the chart
   */
  private void initBars(){
    int xPos, yPos, xWidth, yHeight;
    // Initialize all bar sizes
    bars = new Rectangle[values.length];
    xWidth = (panelWidth)/values.length; // The width of each bar
    for (int i = 0; i < values.length; i++)
    {
      yHeight = values[i];
      xPos = i*xWidth + 5;
      yPos = panelHeight - yHeight - 5;
      bars[i] = new Rectangle(xPos,yPos,xWidth,yHeight);
    }
  }

  /**
   * @param values the array of integers to be sorted
   */
  public void setValues(int[] values){
    if(!running){
      this.values = values;
      if (bars.length != values.length) initBars();
    }
  }

  protected abstract void runSort();

  public void run()
  {
    running = true;
    runSort();
    running = false;
  }

  // swap two bars
  protected void swap(int left, int right)
  {
    int height = bars[right].height;
    int yPos = bars[right].y;
    bars[right].setBounds(bars[right].x, bars[left].y, bars[left].width, bars[left].height );
    bars[left].setBounds( bars[left].x, yPos, bars[left].width, height );
    repaint();
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    g2.setColor(unselectedBarColor);
    for (int i = 0; i < values.length; i++)
    {
      // Fill selected bar with selectedBarColor
      if (i == selectedBar)
      {
        g2.setPaint(selectedBarColor);
        g2.fill(bars[i]);
      }
      // Fill unselected bar with unselectedBarColor
      else
      {
        g2.setPaint(unselectedBarColor);
        g2.fill(bars[i]);
      }
      // Draw the outline
      g2.setPaint(barOutlineColor);
     g2.draw(bars[i]);
    }
  }

  @Override
  public String toString()
  {
    return graphTitle;
  }
}