package code.sorts;

import java.awt.*;
import java.util.LinkedList;

/**
 * @author Thomas
 *         12/1/13 1:41 AM
 */
public class TomSort extends Sort {
  public TomSort(int[] values, int delay){
    super(values, delay, "Tom Sort");
  }

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

  private LinkedList<Tuple> increasingRuns, decreasingRuns, orderedRuns;


  private int colorCompare(int a, int b){
    colorBar(a, Color.ORANGE);
    colorBar(b, Color.ORANGE);
    repaint();
    return compare(a, b);
  }

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
        System.out.println("Equality detected");
        orderedRuns.add(new Tuple(i, values.length-1));
      }
    }
  }

  private int findTrend(int start){
    if(values.length == 0) return 0;
    for(int i = start; i < values.length -1 && running; i++){
      int comparison = colorCompare(i, i + 1);
//      System.out.println("comp = " + comparison);
      if(comparison != 0) {
//        System.out.println("returning " + comparison);
        return comparison;
      }
    }
    return 0;
  }

  private Tuple tallyDecreasingRun(int start){
//    System.out.println("Trying for increasing run starting at " + start);
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

  private Tuple tallyIncreasingRun(int start){
//    System.out.println("Trying for decreasing run starting at " + start);
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

  private void merge(LinkedList<Tuple> runs){
    if(runs.size() <= 1) return;
    System.out.println("Merging " + runs.size() + " runs...");
    LinkedList<Tuple> nextRuns = new LinkedList<Tuple>();
    while(runs.size() > 1){
      nextRuns.add(mergeRuns(runs.pollFirst(), runs.pollFirst()));
    }
    if(runs.size() % 2 == 1) nextRuns.add(runs.pollFirst());
    merge(nextRuns);
  }

  private Tuple mergeRuns(Tuple a, Tuple b){
    if(a._2 + 1 != b._1){
      throw new IllegalArgumentException("Tuples were not consecutive: a = " + a + " b = " + b);
    }
    System.out.println("Merging " + a + " and " + b);
    Rectangle[] array = merge2(a._1, b._1, b._2 +1);
    move(a._1, b._2 +1, array);
    return new Tuple(a._1, b._2);
  }

  private void move(int start, int end, Rectangle[] sorted){
    for(int m = start; m < end; m++){
      if(!running) return;
      colorBar(m, Color.CYAN);
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


  private void orderRuns(){
    System.out.println("Ordering Runs...");
    if(orderedRuns.size() > 0) orderedRuns.pollLast();
    assert orderedRuns.size() == 0;
    while(!(increasingRuns.isEmpty() && decreasingRuns.isEmpty()) && running){
      if(decreasingRuns.isEmpty() || (!increasingRuns.isEmpty() && increasingRuns.peekFirst()._1 < decreasingRuns.peekFirst()._1)){
        Tuple t = increasingRuns.pollFirst();
        System.out.println("adding " + t + " from increasing");
        orderedRuns.addLast(t);
      }
      else{
        Tuple t = decreasingRuns.pollFirst();
        System.out.println("adding " + t + " from decreasing");
        orderedRuns.addLast(t);
      }
    }
  }

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
