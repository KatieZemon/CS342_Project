package code;

import java.awt.*;

class SelectionSort extends Sort
{
  public SelectionSort(int[] v, int d)
  {
    super(v, d);
  }

  public void runSort()
  {
    for (int j = values.length; j > 0; j--)
    {
      int max = 0;
      for (int i = 0; i < j; i++)
      {
        if(!running)
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
          System.out.println("new max: "+ mx + " Val: " + bars[max].height);
        }
        repaint();
      }
      swap(max,j-1);
      repaint();
    }
  }
}
