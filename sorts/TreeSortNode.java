package code.sorts;

import java.util.ArrayList;

/**
 * Tree Node class
 * A node has list of index values whose bars have equal height, and pointers to nodes of lesser and greater value
 * @author Thomas Clay
 */
public class TreeSortNode{

  /** The list of indicies with equal values */
  public ArrayList<Integer> indexValues = new ArrayList<Integer>();
  /** The node with lesser values */
  public TreeSortNode left = null;
  /** The node with greater value */
  public TreeSortNode right = null;

  /**
   * @param indexValue the first value of the Node, stored in {@link #indexValues}
   */
  TreeSortNode(int indexValue){
    this.indexValues.add(indexValue);
  }

  /**
   * @return the first index value.
   * Usually for comparison (since all heights in this should be equal)
   */
  public int getIndexValue(){
    return indexValues.get(0);
  }

  /**
   * copies this nodes values into the given array
   * @param starting the position in the given array to start
   * @param indices the array of indices to append this indexValues to;
   * @return the index after the last index written to
   */
  public int copyValues(int starting, int[] indices){
    int j;
    for (j = 0; j < indexValues.size(); j++) {
      indices[starting+j] = indexValues.get(j);
    }
    return starting + j;
  }

}