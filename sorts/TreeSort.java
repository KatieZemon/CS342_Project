package code.sorts;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 11/30/13
 * Time: 6:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class TreeSort extends Sort {

  //TODO fix bug where it only runs once

  /**
   * Tree Node class
   */
  private class Node{

    public ArrayList<Integer> indexValues = new ArrayList<Integer>();
    public Node left = null;
    public Node right = null;

    Node(int indexValue){
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

  private Node root;

  public TreeSort(int[] values, int delay){
    super(values, delay, "Binary Tree Sort");
    colorBar(0, new Color(0,0,0));
    root = new Node(0);
  }

  private void insert(Node node, int indexValue, Color color){
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

  private void insertLeft(Node parent, int indexValue, Color color){
    Color nextColor = getNextColor(color, true);
    if(parent.left == null){
      parent.left = new Node(indexValue);
      colorBar(indexValue, nextColor);
      repaint();
      delay();
    }
    else{
      insert(parent.left, indexValue, nextColor);
    }
  }

  private void insertRight(Node parent, int indexValue, Color color){
    Color nextColor = getNextColor(color, false);
    if(parent.right == null){
      parent.right = new Node(indexValue);
      colorBar(indexValue, nextColor);
      repaint();
      delay();
    }
    else{
      insert(parent.right, indexValue, nextColor);
    }
  }

  private int in_order(Node node, int firstIndex, int[] sortedIndices){
    if(node == null){
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

  /**
   * Reorders the bars
   * @param orderedIndices
   */
  private void reorder(int[] orderedIndices){
    Rectangle[] barsCopy = new Rectangle[bars.length];
    for(int i = 0; i < barsCopy.length; i++){
      barsCopy[i] = new Rectangle(bars[i]);
    }
    HashMap<Integer, Color> barColorsCopy = new HashMap<Integer, Color>(specialColoredBars);
    for(int i = 0; i < values.length; i++){
      if(!running) return;
      colorBar(i, barColorsCopy.get(orderedIndices[i]));
      swap(bars[i], barsCopy[orderedIndices[i]]);
    }
  }


  @Override
  protected void runSort() {
    for(int i = 1; i < bars.length; i++){
      if(!running) return;
      insert(root, i, new Color(0,0,0));
    }
    int[] sortedIndices = new int[values.length];
    in_order(root, 0, sortedIndices);
    reorder(sortedIndices);
  }
}
