package code.sorts;

import java.awt.*;

/**
 * Preforms heap sort
 * @author  Thomas
 */
public class HeapSort extends Sort {

  /** The size of the heap */
  private int heapSize;

  /**
   * Sort constructor.  Sets name ot "Heap Sort"
   * @param values the values to be sroted
   * @param delay the delay to use in the sort
   * @see code.sorts.Sort#Sort(int[], int, String)
   */
  public HeapSort(int[] values, int delay){
    super(values, delay, "Heap Sort");
  }

  /**
   * Ensure the heap property of the first <code>heapSize</code> elements is perserved
   */
  void heapify(){
    int start = (heapSize - 2) /2;
    while(start >= 0){
      siftDown(start);
      start--;
    }
  }

  /**
   * Used in {@link #heapify()} to perserve the heap property.
   * Moves elements of a lesser value to a heap further down
   * @param startingIndex the index to sift down
   */
  private void siftDown(int startingIndex){
    int rootIndex = startingIndex;
    while(rootIndex * 2 + 1 < heapSize){ //while the root has at least one child
      if(!running) return;
      int childIndex = rootIndex * 2 + 1; //left child
      int indexToSwap = rootIndex;
      if(colorCompare(indexToSwap, childIndex) < 0){
        //check if root is smaller than left child
        indexToSwap = childIndex;
      }
      if(childIndex + 1 < heapSize && colorCompare(indexToSwap, childIndex + 1) < 0){
        indexToSwap = childIndex + 1;
      }
      if(indexToSwap != rootIndex){
        swap(rootIndex, indexToSwap);
        rootIndex = indexToSwap;
      }
      else{
        return;
      }
      clearBarColors();
    }
  }

  /**
   * Adds color to the {@link #compare(int, int)} process
   * @see #compare(int, int)
   */
  private int colorCompare(int a, int b){
    colorBar(a, Color.RED);
    colorBar(b, Color.ORANGE);
    return compare(a, b);
  }


  /** runs heap sort */
  @Override
  protected void runSort() {
    heapSize = values.length;
    heapify();
    while(heapSize > 0){
      if(!running) return;
      swap(heapSize-1, 0);
      heapSize--;
      siftDown(0);
    }
  }
}
