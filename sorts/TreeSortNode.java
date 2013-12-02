package code.sorts;

import java.util.ArrayList;

/**
 * Tree Node class
 * @author Thomas Clau
 * 12/1/13 7:33 PM
 */
public class TreeSortNode{

  public ArrayList<Integer> indexValues = new ArrayList<Integer>();
  public TreeSortNode left = null;
  public TreeSortNode right = null;

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
   * @param indices the array of indicies to append this indexValues to;
   * @return the next index //TODO reword
   */
  public int copyValues(int starting, int[] indices){
    int j;
    for (j = 0; j < indexValues.size(); j++) {
      indices[starting+j] = indexValues.get(j);
    }
    return starting + j;
  }

}