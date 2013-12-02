package code.sorts;

import code.MainDisplay;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Thomas
 *         12/1/13 4:35 PM
 */
public abstract class AbstractBucketSort<T> extends Sort {

  protected T buckets;

  public AbstractBucketSort(int[] values, int delay, String name){
    super(values, delay, name);
  }

  protected Color getColorByBucket(int bucketNum){
    int r = 100 * bucketNum * bucketNum;
    int g = 200 * bucketNum + bucketNum * bucketNum;
    int b = values.length * bucketNum * 130;
    return new Color(r % 255, g % 255, b % 255);
  }

  private int[] getBucketRanges(){
    int numBuckets = getNumRanges();
//    System.out.println("num buckets = " + numBuckets);
    int[] ranges = new int[numBuckets];
    int num = MainDisplay.MAX_VALUE / numBuckets;
//    System.out.println("bucket range = " + num);
    ranges[0] = 0;
    for(int i = 0; i < numBuckets; i++){
//      System.out.println("ranges[" + i + "] = " + i * num);
      ranges[i] = i * num;
    }
    return ranges;
  }

  protected abstract void initializeBuckets();

  protected int calcNumRanges(boolean ensureEven){
    int num = new Long(Math.round( Math.log(values.length) * Math.log10(values.length) )).intValue();
    return ensureEven? num : num + (num % 2);
  }

  protected abstract int getNumRanges();

  /**
   * @return preform the sort operation on the bars array using the buckets
   */
  protected abstract void sortBuckets();

  protected abstract void addToBucket(int bucketNum, int barIndex);

  protected abstract Iterator<Integer> getBucketIterator(int bucketNumber);

  /**
   * This will make each bucket item's contiguous according to it's iterator
   */
  protected void organizeBarsByBuckets(){
    Rectangle[] barsCopy = getBarsCopy();
    HashMap<Integer, Color> barColorsCopy = new HashMap<Integer, Color>(specialColoredBars);
    int i = 0;
    for(int b = 0; b < getNumRanges(); b++){
      Iterator<Integer> itr = getBucketIterator(b);
      while(itr.hasNext()){
        int next = itr.next();
        colorBar(i, barColorsCopy.get(next));
        swap(bars[i], barsCopy[next]);
        i++;
      }
    }
  }


  @Override
  protected void runSort() {
    int[] ranges = getBucketRanges();
    initializeBuckets();
    for(int i = 0; i < values.length && running; i++){
      for(int r = ranges.length -1; r >= 0 && running; r--){
        if(getBarValue(i) > ranges[r]){
//          System.out.println("bar[" + i + "] = " + getBarValue(i) + " > ranges[" + r + "] = " + ranges[r]);
          colorBar(i, getColorByBucket(r));
          addToBucket(r, i);
          repaint();
          break;
        }
      }
      delay();
    }
    organizeBarsByBuckets();
    sortBuckets();
  }
}
