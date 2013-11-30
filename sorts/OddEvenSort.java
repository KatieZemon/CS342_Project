package code.sorts;

import java.awt.*;

/**
 * This class is used for running a simulation of OddEven sort.
 */
public class OddEvenSort extends Sort
{
  /**
   * Constructor for OddEven sort. Draws the initial graph and sets values for v and d
   * @param v: The set of data to be sorted.
   * @param d: The delayTime (in milliseconds) in between each step of the algorithm
   */
  public OddEvenSort(int[] v, int d)
  {
    super(v, d, "OddEven Sort");
  }

  /**
   * This runs the simulation for OddEven sort
   */
  public void runSort()
  {
    int size = bars.length;
    boolean sorted = false;

    while (!sorted)
    {
      sorted = true;
      // Sort Odd Elements
      for (int i = 1; i < size-1; i += 2)
      {
        if(running == false)
        {
          return;
        }
        colorBar(i,Color.YELLOW);
        colorBar(i+1,Color.RED);
        if (compare(i,i+1) > 0)
        {
          swap(i,i+1);
          // If we had to make a swap, then our list was not sorted
          sorted = false;
        }
        clearBarColors();
      }

      // Sort Even elements
      for (int i = 0; i < size-1; i += 2)
      {
        if(running == false)
        {
          return;
        }
        colorBar(i,Color.YELLOW);
        colorBar(i+1,Color.RED);
        if (compare(i,i+1) > 0)
        {
          swap(i,i+1);
          // If we had to make a swap, then our list was not sorted
          sorted = false;
        }
        clearBarColors();
      }

    }

  }
}
