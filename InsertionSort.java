package code;

/**
 * class: InsertionSort
 * desc:  This class is used for running a simulation of Insertion sort.
 */
class InsertionSort extends Sort
{
  /**
   * fn:   InsertionSort
   * desc: Draws the intial graph and sets values for v and d
   * param v: The set of data to be sorted.
   * param d: The delay (in milliseconds) in between each step of the algorithm
   */
  public InsertionSort(int[] v, int d)
  {
    super(v, d, "Insertion Sort");
  }

  /**
   * fn:   runSort
   * desc: This runs the simulation for Insertion sort
   */
  public void runSort()
  {
    for(int i = 0; i < bars.length; i++)
    {
      selectedBar = i;
      for (int j = i-1; j>=0; j--)
      {
        if(bars[selectedBar].height < bars[j].height)
        {
          swap(selectedBar,j);
          selectedBar -= 1;
          // Each time we swap, there will be a delay
          try {
            Thread.sleep(delay);
            Thread.sleep(delay);
          } catch (InterruptedException e){}

        }
        else
        {
          j = 0; //done
        }
        repaint();
      }
      try {
        Thread.sleep(delay);
      } catch (InterruptedException e){}
      repaint();
    }
  }
}