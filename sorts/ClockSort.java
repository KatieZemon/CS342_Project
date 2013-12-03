package code.sorts;

import code.MainDisplay;

import java.awt.*;
import static java.lang.Math.random;

/**
 * This class is used for running a simulation of Clock sort.
 */
public class ClockSort extends Sort
{
  /**
   * Constructor for Clock sort. Draws the initial graph and sets values for v and d
   * @param v: The set of data to be sorted.
   * @param d: The delayTime (in milliseconds) in between each step of the algorithm
   */
  public ClockSort(int[] v, int d)
  {
    super(v, d, "Clock Sort");
  }

  /**
   * @param height of the bar
   * @return a color based on the height of the bar
   */
  private Color getColorByValue(int height){
    return new Color(0, 100, height * 2);
  }

  /**
   * This runs the simulation for Clock sort
   */
  public void runSort()
  {
    //Find the max value in the array
    int currentIndex = 0;

    for(int clock = 0; clock <= MainDisplay.MAX_VALUE && running; clock++)
    {
      Color color = getColorByValue(clock);
      //Find all values that are equal to clock and put them in their spot
      for(int i = currentIndex; i < values.length && running; i++)
      {
        colorBar(i, color);
        repaint();
        delay();
        uncolorBar(i);
        if(clock == getBarValue(i))
        {
          swap(i, currentIndex);
          colorBar(currentIndex, color);
          currentIndex++;
        }
      }
    }

  }
}
