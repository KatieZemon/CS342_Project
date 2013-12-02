package code.sorts;

import java.awt.*;

/**
 * @author Thomas Clay
 * Date and Time: 11/27/13 11:26 PM
 */
public class MergeSort extends Sort {

  public MergeSort(int[] values, int delay){
    super(values, delay, "Merge Sort");
  }

  private void sort(){
    for(int width = 1; width < values.length; width *= 2){
      for(int i = 0; i < values.length; i = i + 2 * width){
        if(!running) return;
        int leftStart = i;
        int rightStart =  Math.min(i+width, values.length);
        int end = Math.min(width*2 + i, values.length);
        Rectangle[] sorted = merge(leftStart, rightStart, end);
        assertSorted(sorted);
        move(leftStart, end, sorted);
      }
    }
  }



  private void move(int start, int end, Rectangle[] sorted){
    for(int m = start; m < end; m++){
      if(!running) return;
      colorBar(m, Color.GREEN);
      swap(bars[m], sorted[m - start]);
    }
    clearBarColors();
  }


  private Rectangle[] merge(int leftStart, int rightStart, int end){
    int left = leftStart;
    int right = rightStart;
    Rectangle[] array = new Rectangle[end - leftStart];
    int j = 0;
    while(j < array.length){
      if(!running) return null;
      // if right is out OR left has some and left is less than right
      colorBar(left, Color.RED);
      colorBar(right,Color.RED);
      repaint();
      if(!(right < end) || (left < rightStart && compare(left, right) < 0)){
        // move left indexValue into next array position
        array[j] = new Rectangle(bars[left]);
        left++;
      }
      else{
        array[j] = new Rectangle(bars[right]);
        right++;
      }
      assert array[j] != null;
      j++;
    }
    clearBarColors();
    return array;
  }


  protected void runSort() {
    sort();
  }


}
