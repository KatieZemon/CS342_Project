package code.sorts;

import java.awt.*;

/**
 * This class is used to implement stooge sort
 * @author Thomas Clay
 */
public class StoogeSort extends Sort {

  /**
   * Sets name to "Stooge Sort" in {@link code.sorts.Sort#Sort(int[], int, String)}
   * @param values to sort
   * @param delay to use during sorting
   */
  public StoogeSort(int[] values, int delay){
    super(values, delay, "Stooge Sort");
  }

  /**
   * Adds color to the {@link #compare(int, int)} process
   * @see #compare(int, int)
   */
  private int colorCompare(int a, int b){
    colorBar(a, Color.ORANGE);
    colorBar(b, Color.ORANGE);
    repaint();
    return compare(a, b);
  }

  /**
   * Runs Stooge Sort between the given indices
   * @param start the starting point to run Stooge Sort
   * @param end the ending point of Stooge Sort
   */
  private void stoogeSort(int start, int end){
    if(!running) return;
    if(colorCompare(start, end) > 0){
      colorBar(start, Color.RED);
      colorBar(end, Color.RED);
      swap(start, end);
    }
    clearBarColors();
    if((end - start + 1) >= 3){
      int third = (end - start +1) / 3;
      stoogeSort(start, end - third); //initial 2/3 of list
      stoogeSort(start + third, end); //final 2/3 of list
      stoogeSort(start, end - third); //initial 2/3 again
    }

  }

  /** Runs Stooge Sort */
  @Override
  protected void runSort() {
    stoogeSort(0, values.length-1);
    clearBarColors();
  }
}
