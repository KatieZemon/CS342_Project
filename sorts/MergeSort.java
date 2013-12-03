package code.sorts;

import code.ValueBar;

import java.awt.*;

/**
 * @author Thomas Clay
 * Date and Time: 11/27/13 11:26 PM
 */
public class MergeSort extends Sort {

  /**
   * Constructor for merge sort.
   * @param values to be sorted
   * @param delay to be used during sort
   * @see #Sort(int[], Integer, String)
   */
  public MergeSort(int[] values, int delay){
    super(values, delay, "Merge Sort");
  }

  /**
   * Runs merge sort
   */
  private void sort(){
    //set size of 'run' to merge; 1,2,4,8...
    for(int width = 1; width < values.length && running; width *= 2){
      //iterator through array by given width
      for(int i = 0; i < values.length && running; i = i + 2 * width){
        int leftStart = i;
        int rightStart =  Math.min(i+width, values.length); //ensures the right index is not greater than array length
        int end = Math.min(width*2 + i, values.length); //ensures the end index is not greater than array length
        ValueBar[] sorted = merge(leftStart, rightStart, end);
        assertSorted(sorted);
        move(leftStart, end, sorted);
      }
    }
  }

  /**
   * Copies sorted values into the charts bar array
   * @param start the first index to copy sorted values into
   * @param end the last+1 index to copy the sorted values into
   * @param sorted the sorted array of rectangles
   */
  private void move(int start, int end, ValueBar[] sorted){
    for(int m = start; m < end; m++){
      if(!running) return;
      colorBar(m, Color.GREEN);
      swap(bars[m], sorted[m - start]);
    }
    clearBarColors();
  }


  /**
   * Merges two runs of data into one sorted piece of data
   * @param leftStart the left starting index
   * @param rightStart the right starting index
   * @param end the ending index (+1 of last value to merge)
   * @return a sorted array of rectangles
   */
  private ValueBar[] merge(int leftStart, int rightStart, int end){
    int left = leftStart;
    int right = rightStart;
    ValueBar[] array = new ValueBar[end - leftStart];
    int j = 0;
    while(j < array.length){
      if(!running) return null;
      // if right is out OR left has some and left is less than right
      colorBar(left, Color.RED);
      colorBar(right,Color.RED);
      repaint();
      if(!(right < end) || (left < rightStart && compare(left, right) < 0)){
        // move left indexValue into next array position
        array[j] = new ValueBar(bars[left]);
        left++;
      }
      else{
        array[j] = new ValueBar(bars[right]);
        right++;
      }
      assert array[j] != null;
      j++;
    }
    clearBarColors();
    return array;
  }


  /**
   * Runs merge sort
   */
  @Override
  protected void runSort() {
    sort();
  }


}
