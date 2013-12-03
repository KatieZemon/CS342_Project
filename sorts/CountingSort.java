package code.sorts;

import code.MainDisplay;

import java.awt.*;
import java.util.HashMap;

/**
 * Preforms counting sort
 * @author Thomas
 */
public class CountingSort extends Sort {

  /** stores the number of instances of a value */
  private HashMap<Integer, Integer> counts;

  /**
   * Sets sort name to "Counting Sort" in {@link #Sort(int[], Integer, String)}
   * @param values to sort
   * @param delay to use during sort (milliseconds)
   */
  public CountingSort(int[] values, int delay){
    super(values, delay, "Counting Sort");
  }

  /**
   * Used iteratively to count the occurrences of a bar's height
   * @param index of the bar to count
   */
  private void count(int index){
    int value = bars[index].height;
    if(counts.containsKey(value)){
      int occurrence = counts.get(value) + 1;
      colorBar(index, getColorByOccurrence(occurrence));
      counts.put(value, occurrence);
    }
    else{
      colorBar(index, getColorByOccurrence(1));
      counts.put(value, 1);
    }
    delay();
  }

  /**
   * @param value the height of the bar to get the number of occurences
   * @return the number of occurrences for the given value
   */
  private int getCount(int value){
    if(counts.containsKey(value)){
      return counts.get(value);
    }
    return 0;
  }

  /**
   * @param occurrence the number of times this bars height has occurred
   * @return a color based on how the number of occurrences
   */
  private Color getColorByOccurrence(int occurrence){
    int x = Math.min(255, occurrence * 20);
    return new Color(x,x,x);
  }

  /**
   * @param height of the bar
   * @return a color based on the height of the bar
   */
  private Color getColorByHeight(int height){
    return new Color(0, 100, height * 2);
  }

  /**
   * Runs counting sort
   */
  @Override
  protected void runSort() {
    counts = new HashMap<Integer, Integer>(MainDisplay.MAX_VALUE);

    //count occurrences of bar's height
    for (int i = 0; i < values.length && running; i++) {
      count(i);
    }

    //calculate starting index
    HashMap<Integer, Integer> positions = new HashMap<Integer, Integer>(values.length);
    int total = 0;
    for (int value = 0; value <= MainDisplay.MAX_VALUE; value++){
      positions.put(value, total);
      total += getCount(value);
    }

    //find correct position for value
    Rectangle[] sortedArray = new Rectangle[values.length];
    for (int i = 0; i < values.length && running; i++){
      int value = bars[i].height;
      int correctPosition = positions.get(value);
      sortedArray[correctPosition] = new Rectangle(bars[i]);
      colorBar(i, getColorByHeight(bars[i].height));
      positions.put(value, correctPosition + 1); //increment position
      repaint();
      delay();
    }

    for (int i = 0; i < values.length && running; i++){
      colorBar(i, getColorByHeight(sortedArray[i].height));
      swap(bars[i], sortedArray[i]);
    }

  }
}
