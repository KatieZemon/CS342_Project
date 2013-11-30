package code.sorts;

import java.awt.*;
// TODO: Still working on this implementation
/**
 * This class is used for running a simulation of cocktail sort
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
        colorBar(i-1, Color.YELLOW);
        repaint();
        if(compare(i-1, i) > 0) //if(bars[i - 1].height > bars[i].height)
        {
          colorBar(i, Color.YELLOW);
          uncolorBar(i-1);
          swap(i - 1, i);
          newN = i;
        } //TODO consider leaving colored bars for previous maxes
        repaint();
        clearBarColors();
      }
      n = newN;
    } while(n > 0);
  }
}
