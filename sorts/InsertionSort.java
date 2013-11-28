package code.sorts;

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
    for(int i = 0; i < bars.length; i++)
    {

      selectedBar = i;
      for (int j = i-1; j>=0; j--)
      {
        if(running == false)
        {
          return;
        }
        if(bars[selectedBar].height < bars[j].height)
        {
          swap(selectedBar,j);
          selectedBar -= 1;
          // Each time we swap, there will be a delay
          try {
            Thread.sleep(delay);
            Thread.sleep(delay);
          } catch (InterruptedException e){
            e.printStackTrace();
          }

        }
        else
        {
          j = 0; //done
        }
        repaint();
      }
      try {
        Thread.sleep(delay);
      } catch (InterruptedException e){
        e.printStackTrace();
      }
      repaint();
    }
  }
}