package code;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implementation of ActionListener that listens for commands to display
 * internal frames on the given {@link JDesktopPane}
 * User: Thomas
 * Date: 11/26/13 7:51 PM
 */
public class FrameDisplayListener implements ActionListener {

  /** String of the Author frame's title and action command*/
  public static final String AUTHOR = "Author";
  /** String of the Problem Statement frame's title and action command */
  public static final String PROBLEM = "Problem Statement";
  /** String of the References frame's title and action command */
  public static final String REFERENCE = "References";
  /** String of the Help frame's title and action command */
  public static final String HELP = "Help";

  private Author authorFrame = new Author();
  private Problem problemFrame = new Problem();
  private References referencesFrame = new References();
  private Help helpFrame = new Help();
  private JDesktopPane desktop;

  /**
   * @param desktopPane the pane to show the internal frames in
   */
  FrameDisplayListener(JDesktopPane desktopPane){
    desktop = desktopPane;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    switch (command){
      case AUTHOR: addFrame(authorFrame, 0, 0);
        break;
      case PROBLEM: addFrame(problemFrame, 25, 25);
        break;
      case REFERENCE: addFrame(referencesFrame, 50, 50);
        break;
      case HELP: addFrame(helpFrame, 75, 75);
        break;
      default: System.err.print("FrameDisplayListener could not udnerstand command");
    }
  }

  /**
   * Adds a frame to the <code>desktop</code> to display, and brings to front
   * @param iframe internal frame to show
   * @param x x position of frame
   * @param y y position of frame
   */
  public void addFrame(JInternalFrame iframe, int x, int y){
    //consider adding if closed check
    desktop.add(iframe);
    iframe.setVisible(true);
    iframe.toFront();
    iframe.setLocation(x, y);
  }
}
