package code.sorts;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is used to implement trucket sort
 * Trucket sort is a hybrid algorithm between tree sort and bucket sort.
 * Items are divided into buckets, where each bucket is a binary tree.
 * When retrieved, the items are in-order, and are copied to the original array
 * @author Thomas
 */
public class TrucketSort extends AbstractBucketSort<TreeSortNode[]> {

  /**
   * Sets sort name to "Trucket Sort" in {@link #Sort(int[], Integer, String)}
   * @param values to sort
   * @param delay in milliseconds to use during the sorting process
   */
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

  /**
   * Inserts into the tree a node with the given indexValue
   * @param node the starting node to insert the value
   * @param indexValue the index of the value to insert
   * @param color the color the inserted bar should be
   */
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

  /**
   * @param parent the parent node
   * @param indexValue the value of the node to be inserted
   * @param color the base color of the bar being inserted
   */
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

  /**
   * @param parent the parent node
   * @param indexValue the value of the node to be inserted
   * @param color the base color of the bar being inserted
   */
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

  /**
   * Recursively gets the ordered values of the given node and its children
   * @param node the root or parent node
   * @param firstIndex the position in the <code>sortedIndices</code> to place the next item
   * @param sortedIndices an array to put the sorted indices
   * @return the next starting position for insertion into <code>sortedIndices</code>
   */
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
