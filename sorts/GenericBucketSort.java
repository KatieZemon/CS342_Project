package code.sorts;

import code.MainDisplay;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Thomas
 *         12/1/13 5:37 PM
 */
public class GenericBucketSort extends AbstractBucketSort<ArrayList<ArrayList<Integer>>> {

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
    System.out.println("sorting buckets...");

    Rectangle[] barsCopy = new Rectangle[bars.length];
    for(int i = 0; i < values.length; i++){
      barsCopy[i] = new Rectangle(bars[i]);
    }


    int i = 0;
    for(int b = 0; b < buckets.size() && running; b++){
      ArrayList<Integer> bucket = buckets.get(b);
      for(int j = 0; j < bucket.size() && running; j++){
        colorBar(i, getColorByBucket(b));
        swap(bars[i], barsCopy[bucket.get(j)]);
        i++;
      }
    }

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
  protected void addToBucket(int bucketNum, int barIndex) {
    buckets.get(bucketNum).add(barIndex);
  }
}
