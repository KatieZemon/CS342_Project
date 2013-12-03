package code.sorts;

import java.awt.*;

/**
 * Cycle Sort.
 * Finds the position of an item, then cycles it with the item in that position
 * @author Thomas
 */
public class CycleSort extends Sort {
  public CycleSort(int[] values, int delay){
    super(values, delay, "Cycle Sort");
  }


  /**
   * Colors the bars being compared with <code>compare</code>
   * @see #compare
   */
  private int colorCompare(int a, int b){
    colorBar(a, Color.ORANGE);
    colorBar(b, Color.ORANGE);
    repaint();
    return compare(a, b);
  }

  /**
   * Finds where the value at <code>start</code> should be placed in the array
   * @param start where to start looking for the items position
   * @return where the item can be inserted
   */
  private int findItemsPosition(int start){
    int pos = start;
    for(int i = start+1; i < values.length && running; i++){
      colorBar(pos, Color.GREEN);
      if(colorCompare(i, start) < 0){
        pos++;
      }
      clearBarColors();
    }
    return pos;
  }

  /**
   * Places an item at the destination, or places it after the destination if the
   * item at <code>destion</code> is equal in value
   * @param current the items current positition
   * @param destination the starting index of where it should be placed
   */
  private void placeItem(int current, int destination){
    while(colorCompare(current, destination) == 0
        && destination < values.length
        && running){
      destination++;
      colorBar(destination, Color.GREEN);
      repaint();
    }
    System.out.println("swapping " + current + " and " + destination);
    swap(current, destination);
  }

  @Override
  /**
   * runs cycle sort
   * @see #runSort()
   */
  protected void runSort() {
    for(int cycleStart = 0; cycleStart < values.length -1 && running; cycleStart++){
      int pos = findItemsPosition(cycleStart);
      if(pos == cycleStart) continue;
      placeItem(cycleStart, pos);
      while(pos != cycleStart && running){
        pos = findItemsPosition(cycleStart);
        System.out.println("pos = " + pos);
        System.out.println("cs = " + cycleStart);
        if(pos != cycleStart) placeItem(cycleStart, pos);
      }
    }
    clearBarColors();
  }
}
