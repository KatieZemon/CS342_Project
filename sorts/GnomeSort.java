package code.sorts;

import java.awt.*;

/**
 * This class is used for running a simulation of Gnome sort.
 */
public class GnomeSort extends Sort
{
  /**
   * Constructor for Gnome sort. Draws the initial graph and sets values for v and d
   * @param v: The set of data to be sorted.
   * @param d: The delayTime (in milliseconds) in between each step of the algorithm
   */
  public GnomeSort(int[] v, int d)
  {
    super(v, d, "Gnome Sort");
  }

  /**
   * This runs the simulation for Gnome sort
   */
  public void runSort()
  {
    int gap = bars.length;
    boolean swapped = true;

    // Sort elements until we have searched through the entire
    // list with a gap == 1 and with no swap performed
    while (gap > 1 || swapped)
    {
      if(running == false)
      {
        return;
      }
      // Update the gap value for the next Gnome
      if (gap > 1)
      {
        gap = (int) (gap/1.3);
      }
      swapped = false;
      for (int i = 0; i + gap < bars.length; i++)
      {
        if(running == false)
        {
          return;
        }
        colorBar(i,Color.YELLOW);
        colorBar(i+gap,Color.RED);
        if ( compare(i,i+gap) > 0)
        {
          swap(i, i+gap);
          swapped = true;
        }
        clearBarColors();
      }
    }

  }
}
