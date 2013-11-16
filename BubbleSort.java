package code;

import java.awt.*;

//I used wikipedia as a source

class BubbleSort extends Sort
{
  /**
  * Constructor for bubble sort. Should be passed an array of values and the delay
  */
  public BubbleSort(int[] v, int d)
  {
    super(v, d, "Bubble Sort");
  }

  /**
  * Sort function for the BubbleSort class
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
