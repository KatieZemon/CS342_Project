package code.sorts;

import java.awt.*;
import java.util.Arrays;

/**
 * @author Thomas Clay
 * Date and Time: 11/27/13 11:26 PM
 */
public class MergeSort extends Sort {

  public MergeSort(int[] values, int delay){
    super(values, delay, "Merge Sort");
  }

  private void sort2(){
    for(int width = 1; width < values.length; width *= 2){
//      System.out.println("width = " + width);
      for(int i = 0; i < values.length; i = i + 2 * width){
        if(!running) return;
        int leftStart = i;
        int rightStart =  Math.min(i+width, values.length);
        int end = Math.min(width*2 + i, values.length);
//        System.out.println("leftStart = " + leftStart);
//        System.out.println("rightStart = " + rightStart);
//        System.out.println("end = " + end);
        Rectangle[] sorted = merge2(leftStart, rightStart, end);
        assertSorted(sorted);
        move2(leftStart, end, sorted);
      }
    }
  }

  private void assertSorted(Rectangle[] array){
    int y = -1;
    for (int i = 0; i < array.length; i++) {
      int newY = array[i].height;
      if (!(y <= newY)){
        throw new AssertionError("y(" + y + ")   newY(" + newY + ")");
      };
      y = newY;
    }
  }

  private void move2(int start, int end, Rectangle[] sorted){
    for(int m = start; m < end; m++){
//      System.out.println("bar[" + m + "] = sorted[" + (m - start) + "]");
      colorBar(m, Color.GREEN);
      swap(bars[m], sorted[m - start]);
    }
    clearBarColors();
  }


  private Rectangle[] merge2(int leftStart, int rightStart, int end){
    int left = leftStart;
    int right = rightStart;
    Rectangle[] array = new Rectangle[end - leftStart];
    int j = 0;
    while(j < array.length){
//      System.out.println("j = " + j);
      //if right is out OR left has some and left is less than right
      colorBar(left, Color.RED);
      colorBar(right,Color.RED);
      repaint();
      if(!(right < end) || (left < rightStart && compare(left, right) < 0)){
        //move left value into next array position
//        System.out.println("moving left");
        array[j] = new Rectangle(bars[left]);
        left++;
      }
      else{
//        System.out.println("moving right");
        array[j] = new Rectangle(bars[right]);
        right++;
      }
      assert array[j] != null;
      j++;
    }
    clearBarColors();
    return array;
  }

  protected int colorCompare(int barIndex1, int barIndex2){
    colorBar(barIndex1, Color.RED);
    colorBar(barIndex2, Color.RED);
    repaint();
    uncolorBar(barIndex1);
    uncolorBar(barIndex2);
    return compare(barIndex1, barIndex2);
  }


  protected void runSort() {
    sort2();
  }


}
