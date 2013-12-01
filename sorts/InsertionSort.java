package code.sorts;

import java.awt.*;

/**
 * Runs a simulation of Insertion Sort
 */
public class InsertionSort extends Sort
{
  /**
   * Draws the intial graph and sets values for v and d
   * @param values: The set of data to be sorted.
   * @param delay: The delay (in milliseconds) in between each step of the algorithm
   */
  public InsertionSort(int[] values, int delay)
  {
    super(values, delay, "Insertion Sort");
  }


  /**
   * This runs the simulation for Insertion sort
   */
  public void runSort()
  {
    for(int currentMaxIndex = 0; currentMaxIndex < bars.length; currentMaxIndex++)
    {
      for (int j = currentMaxIndex; j>0; j--)
      {
        colorBar(currentMaxIndex, Color.GREEN);
        if(running == false) return;

        if(compare(j, j-1) < 0) //if(bars[selectedBar].height < bars[j].height)
        {
          colorBar(j-1, Color.RED);
          swap(j,j-1);
        }
        else
        {
          j = 0; //done
        }
        repaint();
        uncolorBar(j-1);
      }
      uncolorBar(currentMaxIndex);
      repaint();
    }
    clearBarColors();
  }
}