package code;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

// I used this reference here:
// http://jlipton.web.wesleyan.edu/courses/fall2012/lecs/barGraph/arrayVersion/ArrayBarGraph.java

public abstract class Sort extends JPanel implements Runnable {
  protected int[] values;
  protected Rectangle[] bars; // Our rectangular bars in the chart
  protected String graphTitle;
  protected boolean running = false;
  protected int xPos, yPos, xWidth, yHeight;
  protected int delay;
  protected LineBorder lb;
  protected TitledBorder tb;
  protected Font font_bold = new Font("Bold", Font.BOLD, 15);
  protected int panelWidth = 800;
  protected int panelHeight = 150;
  protected int selectedBar = 0; // The highlighted bar
  protected Color panelBackgroundColor = new Color(255, 255, 255);
  protected Color unselectedBarColor = new Color(31, 50, 131);
  protected Color selectedBarColor = new Color(31, 50, 131);
  protected Color poptartPurple = new Color(175, 116, 192);
  protected Color pink = new Color(221, 161, 182);
  protected GradientPaint rainbowPoptart = new GradientPaint(75, 75, poptartPurple,
          95, 95, pink, true);

  /**
   * class: SelectionSort
   * desc:  implements selection sort sorting algorithm on a set of data
   * param v: The set of data to be sorted. The set of data to be sorted is always
   * randomely assorted
   */
  public Sort(int[] v, int d) {
    panelWidth = panelWidth/v.length * v.length;
    this.setPreferredSize(new Dimension(panelWidth, panelHeight));
    values = v;
    delay = d;
    bars = new Rectangle[values.length];
    graphTitle = "Selection Sort";


    lb = new LineBorder(Color.BLACK,3);
    tb = new TitledBorder(
            lb, graphTitle, TitledBorder.CENTER,
            TitledBorder.DEFAULT_POSITION, font_bold, Color.BLACK);
    this.setBorder(tb);

    this.setBackground(panelBackgroundColor);

    // Initialize all bar sizes
    xWidth = (panelWidth)/values.length; // The width of each bar
    for (int i = 0; i < values.length; i++)
    {
      yHeight = values[i];
      xPos = i*xWidth + 5;
      yPos = panelHeight - yHeight - 5;
      bars[i] = new Rectangle(xPos,yPos,xWidth,yHeight);
    }
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    g2.setColor(unselectedBarColor);
    for (int i = 0; i < values.length; i++)
    {
      g2.fill(bars[i]); // Fill the rectangle
      if (i == selectedBar)
      {
        g2.setPaint(rainbowPoptart);
        g2.draw(bars[i]);
        g2.fill(bars[i]);
        g2.setColor(unselectedBarColor);
      }
      else
      {
        g2.draw(bars[i]);
      }
    }
  }

  protected abstract void runSort();

  // I'm not so sure this passes as selection sort, but it's a good test run!
  public void run()
  {
    running = true;
    runSort();
    running = false;
  }

  public void stop()
  {
    running = false;
  }

  public void setDelay(int d)
  {
    delay = d;
  }

  // swap two bars
  protected void swap(int left, int right)
  {
    int height = bars[right].height;
    int yPos = bars[right].y;
    bars[right].setBounds(bars[right].x, bars[left].y, bars[left].width, bars[left].height );
    bars[left].setBounds( bars[left].x, yPos, bars[left].width, height );
  }


}
