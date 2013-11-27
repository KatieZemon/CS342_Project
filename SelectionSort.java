package code;

/**
 * class: SelectionSort
 * desc:  This class is used for running a simulation of selection sort.
 */
class SelectionSort extends Sort
{

  /**
   * fn:   SelectionSort
   * desc: Draws the intial graph and sets values for v and d
   * param v: The set of data to be sorted.
   * param d: The delay (in milliseconds) in between each step of the algorithm
   */
  public SelectionSort(int[] values, int delay)
  {
    super(values, delay, "Selection Sort");
  }

  /**
   * fn:   runSort
   * desc: This runs the simulation for selection sort
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
          System.out.println("new max: "+ max + " Val: " + bars[max].height);
        }
        repaint();
      }
      swap(max,j-1);
      // Each time we swap, we add a delay
      try {
        Thread.sleep(delay);
        Thread.sleep(delay);
      } catch (InterruptedException e){}
      repaint();
    }
  }
}