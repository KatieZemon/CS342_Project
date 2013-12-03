package code.sorts;

import java.awt.*;

/**
 * This class is used for running a simulation of selection sort.
 * Selection sort iterates through an entire list of data while searching for the greatest item in that set. The
 * greatest item is swapped with the last item in that list. The algorithm starts again from the beginning
 * searching for the greatest item in the list until it reaches the next to last item in the list and swaps
 * its new greatest item with that item. The algorithm continues to search for the new greatest items in a smaller
 * and smaller list of unsorted data until that small list of data is size 1.
 */
public class SelectionSort extends Sort
{

  /**
   * Draws the intial graph and sets values for v and d
   * @param values The set of data to be sorted.
   * @param delay The delay (in milliseconds) in between each step of the algorithm
   */
  public SelectionSort(int[] values, int delay)
  {
    super(values, delay, "Selection Sort");
  }

  /**
   * This runs the simulation for Insertion sort
   */
  public void runSort()
  {
    for (int rightLimit = bars.length; rightLimit > 0; rightLimit--)
    {
      int max = 0;
      for (int i = 0; i < rightLimit; i++)
      {
        if(running == false)
        {
          uncolorBar(max);
          return;
        }
        colorBar(i, Color.YELLOW);

        // New max
        if(compare(i,max) > 0)
        {
          uncolorBar(max);
          max = i; // set new max indexValue
          colorBar(max, Color.GREEN);
        }
        else{
          uncolorBar(i);
        }
        repaint();
      }
      swap(max,rightLimit-1);
      repaint();
      clearBarColors();
    }
    clearBarColors();
  }
}