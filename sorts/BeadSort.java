package code.sorts;

import java.awt.*;

/**
 * Preforms Bead Sort
 * @author Thomas Clay
 */
public class BeadSort extends Sort {

  /**
   * Sets sort name to "Bead Sort"
   * @param values to be sorted
   * @param delay to use during sort
   * @see code.sorts.Sort#Sort(int[], int, String)
   */
  public BeadSort(int[] values, int delay){
    super(values, delay, "Bead Sort");
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
   * Runs Bead Sort
   */
  @Override
  protected void runSort() {

    for(int i = values.length -1; i >= 0 && running; i--){
      for(int j = i; j >= 0 && running; j--){
        int difference = colorCompare(j, i);
        if(difference > 0) {
          colorBar(i, Color.CYAN);
          colorBar(j, Color.GREEN);
          incrementBarValue(i, difference);
          decrementBarValue(j, difference);
        }
        clearBarColors();
      }
    }
  }
}
