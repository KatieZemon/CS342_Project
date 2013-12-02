package code.sorts;

import java.awt.*;
import java.util.HashMap;

/**
 * This class is used to run tree sort
 * @author Thomas Clay
 * Date: 11/30/13 Time: 6:50 PM
 */
public class TreeSort extends Sort {

  private TreeSortNode root;

  public TreeSort(int[] values, int delay){
    super(values, delay, "Binary Tree Sort");
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

  //TODO add a color compare

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
    Color c = new Color(0,0,0);
    colorBar(0, c);
    root = new TreeSortNode(0);
    for(int i = 1; i < bars.length; i++){
      if(!running) return;
      insert(root, i, c);
    }
    int[] sortedIndices = new int[values.length];
    in_order(root, 0, sortedIndices);
    reorder(sortedIndices);
  }
}
