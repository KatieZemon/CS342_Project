package code.sorts;

import code.MainDisplay;

import java.awt.*;

/**
 * @author Thomas
 *         12/1/13 3:35 PM
 */
public class BeadSort extends Sort {

  public BeadSort(int[] values, int delay){
    super(values, delay, "Bead Sort");
  }

  private int colorCompare(int a, int b){
    colorBar(a, Color.ORANGE);
    colorBar(b, Color.ORANGE);
    repaint();
    return compare(a, b);
  }

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
