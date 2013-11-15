package code;

import javax.swing.*;
import java.awt.*;

/**
 * class: Author
 * desc:  This creates an internal frame with information about the author
 */
public class Author extends JInternalFrame
{
  /**
   * font_bold- The font style for printing all of our strings
   * fn - our font metrics
   * str- the string that is typed
   * strwid- keeps track of the width of our string
   * y- used for positioning text on the screen (y-axis)
   * w- used for storing the width of our string
   * h- used to store the height of our string
  */
  private Font font_bold = new Font("Arial", Font.BOLD, 20);
  private FontMetrics fn;
  private String str;
  private int strwid, y, h, w;
  private int frameWidth = 500, frameHeight = 500;

  /**
   * name: Default constructor
   */
  public Author()
  {
    super("Author",true,true,true,true);
    setOpaque(true);
    setVisible(true);
    setSize(frameWidth,frameHeight);
    setBackground(new Color(244, 245, 195));
  }

  /**
   * Author and credits info
   */
  public void paint(Graphics g)
  {
    super.paint(g);
    Color darkGreen =  new Color(18, 135, 26);
    Color darkRed = new Color(180, 31, 31);
    fn = g.getFontMetrics(font_bold);
    g.setColor(darkGreen);
    y=115;
    w = getWidth();
    h = getHeight();
    g.setFont(font_bold);

    str= "Demonstration of FS213 CS342 Project for";
    strwid = fn.stringWidth(str);
    g.drawString(str, w/2-(strwid/2),y);
    y += 20;

    str="Java, GUI and Visualization";
    strwid = fn.stringWidth(str);
    g.drawString(str, w/2-(strwid/2),y);
    y += 70;

    str= "Professor:";
    strwid = fn.stringWidth(str);
    g.drawString(str, w/2-(strwid/2), y);
    y += 20;

    str= "Dr. Chaman L. Sabharwal";
    strwid = fn.stringWidth( str );
    g.setColor(Color.BLUE);
    g.drawString(str, w/2-(strwid/2), y);
    y += 70;

    str= " Presented by ";
    strwid = fn.stringWidth(str);
    g.setColor(darkGreen);
    g.drawString(str, w/2-(strwid/2), y);
    y += 20;

    str= "Tom Clay, Katie Isbell, and Westin Sykes";
    strwid = fn.stringWidth(str);
    g.setColor(Color.BLUE);
    g.drawString(str, w / 2 - (strwid / 2), y);
    y += 70;
  }
}

