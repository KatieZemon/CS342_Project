package code.sorts;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Thomas
 *         12/1/13 7:31 PM
 */
public class TrucketSort extends AbstractBucketSort<TreeSortNode[]> {


  public TrucketSort(int[] values, int delay) {
    super(values, delay, "Trucket Sort");
  }

  @Override
  protected void initializeBuckets() {
    buckets = new TreeSortNode[getNumRanges()];
  }

  @Override
  protected int getNumRanges() {
    Long num = new Long( Math.round(Math.log(values.length) / Math.log(2)) );
    return num.intValue();
  }

  @Override
  protected void sortBuckets() {
    // buckets should already be sorted when orderBarsByBuckets gets called
    return;
  }

  @Override
  protected void addToBucket(int bucketNum, int barIndex) {
    if(buckets[bucketNum] == null){
      buckets[bucketNum] = new TreeSortNode(barIndex);
    }
    else{
      insert(buckets[bucketNum], barIndex, getColorByBucket(bucketNum));
    }
  }

  @Override
  protected Iterator<Integer> getBucketIterator(int bucketNumber) {
    int[] array = new int[values.length];
    int length = in_order(buckets[bucketNumber], 0, array);
    ArrayList<Integer> list = new ArrayList<Integer>(length);
    for(int i = 0; i < length && running; i++){
      list.add(array[i]);
    }
    return list.iterator();
  }

  private void insert(TreeSortNode node, int indexValue, Color color){
    if(!running) return;
    if(compare(node.getIndexValue(), indexValue) > 0){
      insertLeft(node, indexValue, color);
    }
    else if(compare(node.getIndexValue(), indexValue) < 0){
      insertRight(node, indexValue, color);
    }
    else{
      //nodes equal
      node.indexValues.add(indexValue);
      colorBar(indexValue, color);
      repaint();
      delay();
    }
  }

  private void insertLeft(TreeSortNode parent, int indexValue, Color color){
    Color nextColor = getNextColor(color, true);
    if(parent.left == null){
      parent.left = new TreeSortNode(indexValue);
      colorBar(indexValue, nextColor);
      repaint();
      delay();
    }
    else{
      insert(parent.left, indexValue, nextColor);
    }
  }

  private void insertRight(TreeSortNode parent, int indexValue, Color color){
    Color nextColor = getNextColor(color, false);
    if(parent.right == null){
      parent.right = new TreeSortNode(indexValue);
      colorBar(indexValue, nextColor);
      repaint();
      delay();
    }
    else{
      insert(parent.right, indexValue, nextColor);
    }
  }

  private int in_order(TreeSortNode node, int firstIndex, int[] sortedIndices){
    if(node == null || !running){
      return firstIndex;
    }
    firstIndex = in_order(node.left, firstIndex, sortedIndices);
    firstIndex = node.copyValues(firstIndex, sortedIndices);
    firstIndex = in_order(node.right, firstIndex, sortedIndices);
    return firstIndex;
  }


  /**
   * The color of the next bar.  At first it is black.
   * As the next node is moved right, the red increases
   * If the next node is moved left, the green increases
   * Every depth increases the blue
   */
  private Color getNextColor(Color current, boolean goLeft){
    int r = current.getRed();
    int g = current.getGreen();
    int b = current.getBlue();
    if(goLeft) {
      g += 10;
    }
    else {
      r += 10;
    }
    b += 10;
    r %= 255;
    b %= 255;
    g %= 255;
    return new Color(r, g, b);
  }


}
