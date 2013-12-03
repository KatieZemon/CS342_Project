package code.sorts;

import code.MainDisplay;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Preforms basic bucket sorting
 * Divides array into buckets.  Each bucket holds a range of values
 * Sorting of these values depend on implementation of this class
 * @author Thomas
 */
public abstract class AbstractBucketSort<T> extends Sort {

  protected T buckets;

  /**
   * @param values to be sorted
   * @param delay to use during sort
   * @param name of the bucket sort variant
   * @see #Sort(int[], int, String)
   */
  public AbstractBucketSort(int[] values, int delay, String name){
    super(values, delay, name);
  }

  /**
   * @param bucketNum the number of the bucket to get the color for
   * @return the color a bar in that bucket should be
   */
  protected Color getColorByBucket(int bucketNum){
    int r = 100 * bucketNum * bucketNum;
    int g = 200 * bucketNum + bucketNum * bucketNum;
    int b = values.length * bucketNum * 130;
    return new Color(r % 255, g % 255, b % 255);
  }

  /**
   * @return an array that represents the lower value limit of each bucket
   */
  private int[] getBucketRanges(){
    int numBuckets = getNumRanges();
    int[] ranges = new int[numBuckets];
    int num = MainDisplay.MAX_VALUE / numBuckets;
    ranges[0] = 0;
    for(int i = 0; i < numBuckets; i++){
      ranges[i] = i * num;
    }
    return ranges;
  }

  /**
   * Code that must initialize <code>buckets</code>
   */
  protected abstract void initializeBuckets();

  /**
   * Default utility function to be used in {@link #getNumRanges()} function
   * @param ensureEven <code>true</code> if the number of ranges should be divisible by two
   * @return the number of ranges (and buckets) to use
   */
  protected int calcNumRanges(boolean ensureEven){
    int num = new Long(Math.round( Math.log(values.length) * Math.log10(values.length) )).intValue();
    return ensureEven? num : num + (num % 2);
  }

  /**
   * @return the number of ranges to use
   */
  protected abstract int getNumRanges();

  /**
   * @return preform the sort operation on the bars array after they have been made
   * contiguous using {@link #organizeBarsByBuckets()}
   */
  protected abstract void sortBuckets();

  /**
   * Add a bar to a bucket
   * @param bucketNum which bucket the bar should be added to
   * @param barIndex the index of the bar in the <code>bars</code> array
   */
  protected abstract void addToBucket(int bucketNum, int barIndex);

  /**
   * @param bucketNumber the index of the bucket whose iterator you're requesting
   * @return the iterator for the request bucket
   */
  protected abstract Iterator<Integer> getBucketIterator(int bucketNumber);

  /**
   * This will make each bucket item's contiguous in the graph according to it's iterator
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


  /**
   * Run the implemented bucket sort
   */
  @Override
  protected void runSort() {
    int[] ranges = getBucketRanges();
    initializeBuckets();
    for(int i = 0; i < values.length && running; i++){
      for(int r = ranges.length -1; r >= 0 && running; r--){
        if(getBarValue(i) > ranges[r]){
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
