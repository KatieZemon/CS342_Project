package code.sorts;

import java.awt.*;
/**
 * This class is used for running a simulation of cocktail sort.
 * Cocktail Sort is also called bidirectional bubble sort, shaker sort,
 * shuffle sort, ripple sort, shuttle sort, or happy hour sort.
 * It is both stable and a comparison sorting algorithm. It is a variation of
 * Bubble Sort but it differs in that it sorts in both directions. (source from Wikipedia).
 */
public class CocktailSort extends Sort
{
  /**
   * Constructor for Cocktail sort. Draws the initial graph and sets values for v and d
   * @param v: The set of data to be sorted.
   * @param d: The delayTime (in milliseconds) in between each step of the algorithm
   */
  public CocktailSort(int[] v, int d)
  {
    super(v, d, "Cocktail Sort");
  }

  /**
   * This runs the simulation for Cocktail sort
   */
  public void runSort()
  {
    int left = 0;
    int right = bars.length - 1;
    boolean swapped = true;

    // Sort the elements until no swaps are performed
    while (swapped)
    {
      if(running == false)
      {
        return;
      }
      swapped = false;
      // Read from left to right
      for(int i = left; i < right; i++)
      {
        if(running == false)
        {
          return;
        }
        colorBar(i,Color.YELLOW);
        colorBar(i+1, Color.RED);
        if ( compare(i,i+1) > 0)
        {
          swap(i, i+1);
          swapped = true;
        }
        clearBarColors();
      }
      if (swapped)
      {
        swapped = false;
        right--;  // elements after right are in correct order

        // Read from right to left
        for (int i = right; i >= left; i--)
        {
          if(running == false)
          {
            return;
          }
          colorBar(i,Color.YELLOW);
          colorBar(i+1, Color.RED);
          if (compare(i, i+1) > 0)
          {
            swap(i, i+1);
            swapped = true;
          }
          clearBarColors();
        } // End For
      } // End If
      left++;
    } // End While
  }
}
