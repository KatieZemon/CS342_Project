package code.sorts;

import java.awt.*;
import static java.lang.Math.random;

/**
 * This class is used for running a simulation of Clock sort.
 */
public class ClockSort extends Sort
{
  /**
   * Constructor for Clock sort. Draws the initial graph and sets values for v and d
   * @param v: The set of data to be sorted.
   * @param d: The delayTime (in milliseconds) in between each step of the algorithm
   */
  public ClockSort(int[] v, int d)
  {
    super(v, d, "Clock Sort");
  }

  /**
   * This runs the simulation for Clock sort
   */
  public void runSort()
  {
    int clock = 0;
    //Find the max value in the array
    int maxVal = 0;
    int vIndex = 0;
    for(int i: v)
    {
      if(i > maxVal)
        maxVal = i;
    }
    while(clock < maxVal)
    {
      Color color = new Color((float) random(), (float) random(), (float) random());
      //Find all values that are equal to clock and put them in their spot
      for(int i = vIndex; i < v.size(); i++)
      {
        colorBar(i, Color.RED);
        if(compare(clock, v[i]) == 0)
        {
          swap(i, vIndex);
          colorBar(vIndex, color);
          vIndex++;
        }
      }
      clock++;
    }

  }
}
