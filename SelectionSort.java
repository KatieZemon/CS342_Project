package code;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

// I used this reference here:
// http://jlipton.web.wesleyan.edu/courses/fall2012/lecs/barGraph/arrayVersion/ArrayBarGraph.java

public class SelectionSort extends JPanel implements Runnable {
  private int[] values;
  private Rectangle[] bars; // Our rectangular bars in the chart
  private String graphTitle;
  int xPos, yPos, xWidth, yHeight;
  int delay;
  LineBorder lb;
  TitledBorder tb;
  Font font_bold = new Font("Bold", Font.BOLD, 15);
  int panelWidth = 800;
  int panelHeight = 150;
  int selectedBar = 0; // The highlighted bar
  Color panelBackgroundColor = new Color(255, 255, 255);
  Color unselectedBarColor = new Color(31, 50, 131);
  Color selectedBarColor = new Color(31, 50, 131);
  Color poptartPurple = new Color(175, 116, 192);
  Color pink = new Color(221, 161, 182);
  GradientPaint rainbowPoptart = new GradientPaint(75, 75, poptartPurple,
          95, 95, pink, true);

  /**
   * class: SelectionSort
   * desc:  implements selection sort sorting algorithm on a set of data
   * param v: The set of data to be sorted. The set of data to be sorted is always
   * randomely assorted
   */
  public SelectionSort(int[] v, int d) {
    panelWidth = panelWidth/v.length * v.length;
    this.setPreferredSize(new Dimension(panelWidth, panelHeight));
    values = v;
    delay = d;
    bars = new Rectangle[values.length];
    graphTitle = "Selection Sort";

    addBorder();

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

  public void addBorder()
  {
    lb = new LineBorder(Color.BLACK,3);
    tb = new TitledBorder(
            lb, graphTitle, TitledBorder.CENTER,
            TitledBorder.DEFAULT_POSITION, font_bold, Color.BLACK);
    this.setBorder(tb);
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

  // I'm not so sure this passes as selection sort, but it's a good test run!
  public void run()
  {
    for (int j = values.length; j > 0; j--)
    {
      int max = 0;
      for (int i = 0; i < j; i++)
      {
        try {
          Thread.sleep(delay);
        } catch (InterruptedException e){}
        selectedBar = i;

        // New max
        if ( bars[i].height > bars[max].height )
        {
          max = i; // set new max value
          System.out.println("new max: "+ max + " Val: " + bars[max].height);
        }
        repaint();
      }
      swap(max,j-1);
      repaint();
    }
  }

  // swap two bars
  public void swap(int left, int right)
  {
    int height = bars[right].height;
    int yPos = bars[right].y;
    bars[right].setBounds(bars[right].x, bars[left].y, bars[left].width, bars[left].height );
    bars[left].setBounds( bars[left].x, yPos, bars[left].width, height );
  }


}
