package code.sorts;

import java.awt.*;

/**
 * This class is used for running a simulation of bubble sort.
 */
public class BubbleSort extends Sort
{
  /**
   * Constructor for bubble sort. Draws the initial graph and sets values for v and d
   * @param v The set of data to be sorted.
   * @param d The delayTime (in milliseconds) in between each step of the algorithm
   */
  public BubbleSort(int[] v, int d)
  {
    super(v, d, "Bubble Sort");
  }

  /**
   * This runs the simulation for bubble sort
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
        colorBar(i, Color.RED);
        colorBar(i-1, Color.YELLOW);
        if(compare(i-1, i) > 0)
        {
          swap(i - 1, i);
          colorBar(i, Color.YELLOW);
          colorBar(i-1, Color.RED);
          newN = i;
        } //TODO consider leaving colored bars for previous maxes
        repaint();
        clearBarColors();
      }
      n = newN;
    } while(n > 0);
  }
}
