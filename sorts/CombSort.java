package code.sorts;

import java.awt.*;

/**
 * This class is used for running a simulation of comb sort.
 * Comb sort is a sorting algorithm that is faster than bubble sort but is implemented
 * in a similar fashion. Comb sort is different than bubble sort in that it initializes
 * a value "gap" to be usually 1.3 * the number of elements in the list to be sorted.
 * The algorithm compares element i with element i+gap until i+gap reaches the end of
 * the array and performs swaps where needed. It reduces the gap value for each iteration
 * and stops until the gap is equal to 1 and no swaps have been performed.
 *
 * The main reason this algorithm is faster than bubble sort is because it eliminates
 * "turtles", or small values near the end of the list. These small values are what
 * slow down bubble sort tremendously.
 */
public class CombSort extends Sort
{
  /**
   * Constructor for comb sort. Draws the initial graph and sets values for v and d
   * @param v: The set of data to be sorted.
   * @param d: The delayTime (in milliseconds) in between each step of the algorithm
   */
  public CombSort(int[] v, int d)
  {
    super(v, d, "Comb Sort");
  }

  /**
   * This runs the simulation for comb sort
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
      // Update the gap value for the next comb
      if (gap > 1)
      {
        gap = (int) (gap/1.3);
      }
      swapped = false;
      for (int i = 0; i + gap < bars.length; i++)
      {
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
