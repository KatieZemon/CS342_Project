package code.sorts;

import java.awt.*;

/**
 * This class is used for running a simulation of Gnome sort.
 * Gnome sort is a sorting algorithm that works similar
 * to insertion sort but moves elements to their proper place
 * by doing a series of swaps between adjacent elements (like
 * Bubble Sort). It is based on the fact that performing a swap
 * can introduce a new pair of elements that are out of order.
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
    int size = bars.length; // The length of the unsorted list
    int pos = 1;

    while (pos < size)
    {
      if(running == false)
      {
        return;
      }

      colorBar(pos-1,Color.YELLOW);
      colorBar(pos,Color.RED);
      if ( compare(pos,pos-1) >= 0)
      {
        pos++;
        clearBarColors();
        colorBar(pos-1,Color.YELLOW);
        colorBar(pos,Color.RED);
      }

      else
      {
        colorBar(pos,Color.YELLOW);
        colorBar(pos-1,Color.RED);
        swap(pos,pos-1);
        if (pos > 1)
          pos--;
      }
      clearBarColors();
    }



  }
}
