package code.sorts;

import java.awt.*;
import java.util.LinkedList;

/**
 * This class is used to implement Tom Sort
 * TomSort is a modified version of Tim Sort
 * TomSort identifies 'runs' in the array were the data is in sorted, or reverse-sorted order.
 * TomSort reverse the reverse-sorted runs until all the runs are in sorted order.  In which
 * case it performs merge sort on the given runs.
 * TomSort runs in O(n) time for a sorted array
 * TomSort runs in O(n) time for a reverse sorted array
 * For a random array, TomSort has a O(n lg(n)) runtime, as at worst it performs merge sort
 * @author Thomas Clay
 */
public class TomSort extends Sort {

  /**
   * Sets sort name to "Tom Sort" in {@link #Sort(int[], Integer, String)}
   * @param values to sort
   * @param delay in milliseconds to use during the sorting process
   */
  public TomSort(int[] values, int delay){
    super(values, delay, "Tom Sort");
  }

  /**
   * A helper class that holds two int values
   */
  class Tuple{
    int _1, _2;
    Tuple(int a, int b){
      _1 = a;
      _2 = b;
    }

    @Override
    public String toString(){
      return "(" + _1 + ", " + _2 + ")";
    }
  }

  /** The list of tuples representing the start and end of the increasing runs */
  private LinkedList<Tuple> increasingRuns;
  /** The list of tuples representing the start and end of the decreasing runs */
  private LinkedList<Tuple> decreasingRuns;
  /** The list of tuples representing the start and end of the ordered runs */
  private LinkedList<Tuple> orderedRuns;


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
   * Goes through the data, and attempts to determine if the next sequence of value is increasing or decreasing
   * After identifying the trend of the data, it adds a tuple with the starting and ending (inclusive)
   * points of the data to a tuple and stores it in the appropriate list
   */
  private void findRuns(){
    for(int i = 0; i < values.length && running; i++){
      int comparison = findTrend(i);
      if(comparison > 0){
        //start a decreasing run
        Tuple t = tallyDecreasingRun(i);
        decreasingRuns.add(t);
        i = t._2;
      }
      else if(comparison < 0){
        //start an increasing run
        Tuple t = tallyIncreasingRun(i);
        increasingRuns.add(t);
        i = t._2;
      }
      else{
        orderedRuns.add(new Tuple(i, values.length-1));
      }
    }
  }

  /**
   * Tries to identify a trend in the data
   * @param start the index to start at
   * @return
   * <ul>
   *   <li><b>positive</b> if the trend is increasing</li>
   *   <li><b>negative</b> if the trend is decreasing</li>
   *   <li><b>zero</b> if there is no trend; if the data is equal</li>
   * </ul>
   */
  private int findTrend(int start){
    if(values.length == 0 || start == values.length -1) return 0;
    for(int i = start; i < values.length -1 && running; i++){
      int comparison = colorCompare(i, i + 1);
      if(comparison != 0) {
        return comparison;
      }
    }
    return 0;
  }

  /**
   * From the start point, records all data that is decreasing in a row (run)
   * @param start where to start tallying a decreasing run
   * @return a tuple whose first value is where the run starts, and whose second is where it ends (inclusive)
   */
  private Tuple tallyDecreasingRun(int start){
    for(int i = start; i < values.length -1 && running; i++){
      colorBar(i, Color.RED);
      if(compare(i, i + 1) < 0){
        //if next value is greater, the decreasing run has broken
        return new Tuple(start, i);
      }
    }
    colorBar(values.length-1, Color.RED);
    return new Tuple(start, values.length-1);
  }

  /**
   * From the start point, records all data that is increasing in a row (run)
   * @param start where to start tallying a increasing run
   * @return a tuple whose first value is where the run starts, and whose second is where it ends (inclusive)
   */
  private Tuple tallyIncreasingRun(int start){
    for(int i = start; i < values.length -1 && running; i++){
      colorBar(i, Color.GREEN);
      if(compare(i, i + 1) > 0){
        //if next value is less, the increasing run has broken
        return new Tuple(start, i);
      }
    }
    colorBar(values.length-1, Color.BLACK);
    return new Tuple(start, values.length-1);
  }

  /**
   * Reverse a decreasing run by swapping values until it is in order
   * @param t the tuple representing the limits of the run
   */
  private void reverseRun(Tuple t){
    int start = t._1;
    int end = t._2;
    while(start <= end){
      colorBar(start, Color.LIGHT_GRAY);
      colorBar(end, Color.LIGHT_GRAY);
      swap(start, end);
      start++;
      end--;
    }
  }

  /**
   * Merges a list of runs together, so they create a sorted run
   * @param runs a list of runs to merge
   */
  private void merge(LinkedList<Tuple> runs){
    if(runs.size() <= 1) return;
    System.out.println("Merging " + runs.size() + " runs...");
    LinkedList<Tuple> nextRuns = new LinkedList<Tuple>();
    while(runs.size() > 1){
      nextRuns.add(mergeRuns(runs.pollFirst(), runs.pollFirst()));
    }
    if(runs.size() % 2 == 1){
      nextRuns.addLast(mergeRuns(nextRuns.pollLast(), runs.pollFirst()));
    }
    merge(nextRuns);
  }

  /**
   * Merges two runs together
   * @param a the tuple representing the left run to merege
   * @param b the tuple representing the right run to merge
   * @return a tuple representing the merged run
   */
  private Tuple mergeRuns(Tuple a, Tuple b){
    if(a._2 + 1 != b._1){
      throw new IllegalArgumentException("Tuples were not consecutive: a = " + a + " b = " + b);
    }
    System.out.println("Merging " + a + " and " + b);
    Rectangle[] array = merge2(a._1, b._1, b._2 +1);
    move(a._1, b._2 +1, array);
    return new Tuple(a._1, b._2);
  }

  /**
   * copies values from the given array into the <code>bars</code> array
   * @param start index of <code>bars</code> to put the first value
   * @param end index of <code>bars</code> to stop at
   * @param sorted a sorted array
   */
  private void move(int start, int end, Rectangle[] sorted){
    for(int m = start; m < end; m++){
      if(!running) return;
      colorBar(m, Color.CYAN);
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
  private Rectangle[] merge2(int leftStart, int rightStart, int end){
    int left = leftStart;
    int right = rightStart;
    Rectangle[] array = new Rectangle[end - leftStart];
    int j = 0;
    while(j < array.length){
      if(!running) return null;
      //if right is out OR left has some and left is less than right
      colorBar(left, Color.RED);
      colorBar(right,Color.RED);
      repaint();
      if(!(right < end) || (left < rightStart && compare(left, right) < 0)){
        //move left indexValue into next array position
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


  /**
   * Takes {@link #decreasingRuns} and {@link #increasingRuns} and puts them in order into {@link #orderedRuns}
   */
  private void orderRuns(){
    Tuple last = orderedRuns.pollLast();
    assert orderedRuns.size() == 0;
    while(!(increasingRuns.isEmpty() && decreasingRuns.isEmpty()) && running){
      if(decreasingRuns.isEmpty() || (!increasingRuns.isEmpty() && increasingRuns.peekFirst()._1 < decreasingRuns.peekFirst()._1)){
        Tuple t = increasingRuns.pollFirst();
        orderedRuns.addLast(t);
      }
      else{
        Tuple t = decreasingRuns.pollFirst();
        orderedRuns.addLast(t);
      }
    }
    if(last != null) {
      orderedRuns.addLast(last);
    }
  }

  /**
   * Runs TomSort
   */
  @Override
  protected void runSort() {
    increasingRuns = new LinkedList<Tuple>();
    decreasingRuns = new LinkedList<Tuple>();
    orderedRuns = new LinkedList<Tuple>();
    findRuns();
    for(Tuple t: decreasingRuns){
      reverseRun(t);
    }
    orderRuns();
    merge(orderedRuns);
  }
}
