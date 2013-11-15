package code;

//TODO: State code originality, individual contribution, what did you learn
import javax.swing.*;
import java.awt.*;

/**
 * class: Author
 * desc:  This creates an internal frame with information about the author
 */
public class Author extends JInternalFrame
{
  Font bold = new Font("Arial", Font.BOLD, 20);
  FontMetrics fn;
  int strwid,y,h,w;
  String str;
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
    fn = g.getFontMetrics(bold);
    g.setColor(darkGreen);
    y=115;
    w = getWidth();
    h = getHeight();
    g.setFont(bold);

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

    str= "Katie Isbell and Westin Sykes";
    strwid = fn.stringWidth(str);
    g.setColor(Color.BLUE);
    g.drawString(str, w / 2 - (strwid / 2), y);
    y += 70;
  }
}

