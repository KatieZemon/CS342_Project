package code.sorts;

/**
 * class: SelectionSort
 * desc:  This class is used for running a simulation of selection sort.
 */
public class SelectionSort extends Sort
{

  /**
   * Draws the intial graph and sets values for v and d
   * @param values: The set of data to be sorted.
   * @param delay: The delay (in milliseconds) in between each step of the algorithm
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
    for (int j = bars.length; j > 0; j--)
    {
      int max = 0;
      for (int i = 0; i < j; i++)
      {
        if(running == false)
        {
          return;
        }
        try {
          Thread.sleep(delay);
        } catch (InterruptedException e){}
        selectedBar = i;

        // New max
        if ( bars[i].height > bars[max].height )
        {
          max = i; // set new max value
        }
        repaint();
      }
      swap(max,j-1);
      // Each time we swap, we add a delay
      try {
        Thread.sleep(delay);
        Thread.sleep(delay);
      } catch (InterruptedException e){
        e.printStackTrace();
      }
      repaint();
    }
  }
}