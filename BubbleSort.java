package code;

/**
 * class: BubbleSort
 * desc:  This class is used for running a simulation of bubble sort.
 */
class BubbleSort extends Sort
{
  /**
   * fn:   BubbleSort
   * desc: Constructor for bubble sort. Draws the intial graph and sets values for v and d
   * param v: The set of data to be sorted.
   * param d: The delay (in milliseconds) in between each step of the algorithm
   */
  public BubbleSort(int[] v, int d)
  {
    super(v, d, "Bubble Sort");
  }

  /**
   * fn:   runSort
   * desc: This runs the simulation for bubble sort
   */
  public void runSort()
  {
    int n = bars.length;
    do
    {
      int newN = 0;
      for(int i = 1; i < n; i++)
      {
        if(running == false)
        {
          return;
        }
        try {
          Thread.sleep(delay);
        } catch (InterruptedException e){}
        selectedBar = i;
        if(bars[i - 1].height > bars[i].height)
        {
          swap(i-1, i);
          repaint();
          newN = i;
        }

      }
      n = newN;
    } while(n != 0);
  }
}
