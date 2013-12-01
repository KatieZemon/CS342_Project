package code.sorts;

import java.awt.*;

/**
 * @author Thomas
 *         11/30/13 11:34 PM
 */
public class StoogeSort extends Sort {

  public StoogeSort(int[] values, int delay){
    super(values, delay, "Stooge Sort");
  }

  private int colorCompare(int a, int b){
    colorBar(a, Color.ORANGE);
    colorBar(b, Color.ORANGE);
    repaint();
    return compare(a, b);
  }

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

  @Override
  protected void runSort() {
    stoogeSort(0, values.length-1);
    clearBarColors();
  }
}
