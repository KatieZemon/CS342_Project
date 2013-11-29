package code.sorts;

import java.awt.*;

/**
 * class: BubbleSort
 * desc:  This class is used for running a simulation of bubble sort.
 */
public class BubbleSort extends Sort
{
  /**
   * fn:   BubbleSort
   * desc: Constructor for bubble sort. Draws the intial graph and sets values for v and d
   * param v: The set of data to be sorted.
   * param d: The delayTime (in milliseconds) in between each step of the algorithm
   */
  public BubbleSort(int[] v, int d)
  {
    super(v, d, "Bubble Sort");
  }

  /**
   * fn:   runSort
   * desc: This runs the simulation for bubble sort
   */
  public void runSort()
  {
    int n = bars.length;
    do
    {
      int newN = 0;
      for(int i = 1; i < n; i++)
      {
        if(running == false)
        {
          return;
        }
        colorBar(i-1, Color.YELLOW);
        repaint();
        if(compare(i-1, i) > 0) //if(bars[i - 1].height > bars[i].height)
        {
          colorBar(i, Color.YELLOW);
          uncolorBar(i-1);
          swap(i - 1, i);
          newN = i;
        } //TODO consider leaving colored bars for previous maxes
        repaint();
        clearBarColors();
      }
      n = newN;
    } while(n > 0);
  }
}
