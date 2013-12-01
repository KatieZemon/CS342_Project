package code.sorts;

import java.awt.*;

/**
 * @author Thomas
 *         12/1/13 12:22 AM
 */
public class CycleSort extends Sort {
  public CycleSort(int[] values, int delay){
    super(values, delay, "Cycle Sort");
  }


  private int colorCompare(int a, int b){
    colorBar(a, Color.ORANGE);
    colorBar(b, Color.ORANGE);
    repaint();
    return compare(a, b);
  }

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
