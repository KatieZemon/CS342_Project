package code.sorts;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 11/30/13
 * Time: 8:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeapSort extends Sort {

  int heapSize;

  public HeapSort(int[] values, int delay){
    super(values, delay, "Heap Sort");
  }

  void heapify(){
    int start = (heapSize - 2) /2;
    while(start >= 0){
      siftDown(start);
      start--;
    }
  }

  private void siftDown(int start){
    int root = start;
    while(root * 2 + 1 < heapSize){ //while the root has at least one child
      int child = root * 2 + 1; //left child
      int swap = root;
      if(colorCompare(swap, child) < 0){
        //check if root is smaller than left child
        swap = child;
      }
      if(child + 1 < heapSize && colorCompare(swap, child + 1) < 0){
        swap = child + 1;
      }
      if(swap != root){
        swap(root, swap);
        root = swap;
      }
      else{
        return;
      }
      clearBarColors();
    }
  }

  private int colorCompare(int a, int b){
    colorBar(a, Color.RED);
    colorBar(b, Color.ORANGE);
    return compare(a, b);
  }


  @Override
  protected void runSort() {
    heapSize = values.length;
    heapify();
    while(heapSize > 0){
      swap(heapSize-1, 0);
      heapSize--;
      siftDown(0);
    }
  }
}
