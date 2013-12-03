package code;

import java.awt.*;

/**
 * A class that uses rectangles to represent int values
 * @author Thomas
 *         Date and Time: 12/3/13 12:43 AM
 */
public class ValueBar extends Rectangle {

  /** the value this bar represents */
  public int value;

  /**
   *
   * @param value that the rectangle represents
   * @param xPos the x position of the bar
   * @param barWidth the width of the bar
   * @param panelHeight the height of the containing panel
   */
  public ValueBar(int value, int xPos, int barWidth, int panelHeight){
    this.value = value;
    int yHeight = new Long(Math.round(((float)value / 100) * (panelHeight - 30))).intValue();
    int yPos = panelHeight - yHeight - 5;
    setBounds(xPos ,yPos,barWidth,yHeight);
  }

  /**
   * copy constructor
   * @param bar to base itself off of
   */
  public ValueBar(ValueBar bar){
    this.value = bar.value;
    setBounds(bar.x, bar.y, bar.width, bar.height);
  }


}
