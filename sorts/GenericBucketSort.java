package code.sorts;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A default implementation of bucket sort
 * Uses insertion sort to sort the buckets
 * @see code.sorts.AbstractBucketSort
 * @author Thomas
 */
public class GenericBucketSort extends AbstractBucketSort<ArrayList<ArrayList<Integer>>> {

  /**
   * Constructor for generic bucket sort, where the name is "Bucket Sort"
   * @see #Sort(int[], Integer, String)
   */
  public GenericBucketSort(int[] values, int delay) {
    super(values, delay, "Bucket Sort");
  }

  @Override
  protected void initializeBuckets() {
    buckets = new ArrayList<ArrayList<Integer>>();
    for(int i = 0; i < getNumRanges(); i++){
      buckets.add(new ArrayList<Integer>(values.length / getNumRanges() + 1));
    }
  }

  @Override
  protected int getNumRanges() {
    return calcNumRanges(false);
  }

  @Override
  /**
   * Sorts buckets using insertion sort
   */
  protected void sortBuckets() {
    for(int currentMaxIndex = 0; currentMaxIndex < bars.length; currentMaxIndex++)
    {
      for (int j = currentMaxIndex; j>0; j--)
      {
        colorBar(currentMaxIndex, Color.GREEN);
        if(running == false) return;

        if(compare(j, j-1) < 0) //if(bars[selectedBar].height < bars[j].height)
        {
          colorBar(j-1, Color.RED);
          swap(j,j-1);
        }
        else
        {
          j = 0; //done
        }
        repaint();
        uncolorBar(j-1);
      }
      uncolorBar(currentMaxIndex);
      repaint();
    }
  }

  @Override
  protected Iterator<Integer> getBucketIterator(int bucketNumber){
    return buckets.get(bucketNumber).iterator();
  }

  @Override
  protected void addToBucket(int bucketNum, int barIndex) {
    buckets.get(bucketNum).add(barIndex);
  }
}
