package code;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implementation of ActionListener that listens for commands to display
 * internal frames on the given {@link JDesktopPane}
 * @author Thomas
 */
public class FrameDisplayListener implements ActionListener
{

  /** String of the Author frame's title and action command*/
  public static final String AUTHOR = "Author";

  /** String of the Problem Statement frame's title and action command */
  public static final String PROBLEM = "Problem Statement";

  /** String of the References frame's title and action command */
  public static final String REFERENCE = "References";

  /** String of the Help frame's title and action command */
  public static final String HELP = "Help";

  /** String of the Main frame's title and action command */
  public static final String MAIN = "Main Display";


  /** Author frame instance */
  private Author authorFrame = new Author();
  /** Problem Statement frame instance */
  private Problem problemFrame = new Problem();
  /** References frame instance */
  private References referencesFrame = new References();
  /** Help frame instance */
  private Help helpFrame = new Help();
  /** Desktop pane */
  private JDesktopPane desktop;

  /**
   * @param desktopPane the pane to show the internal frames in
   */
  FrameDisplayListener(JDesktopPane desktopPane)
  {
    desktop = desktopPane;
    desktop.add(authorFrame);
    desktop.add(problemFrame);
    desktop.add(referencesFrame);
    desktop.add(helpFrame);
  }


  /**
   * Handles menu events to display frames
   * @param e
   * @see #actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    if (command == AUTHOR)
      showFrame(authorFrame, 10, 10);
    else if (command == PROBLEM)
      showFrame(problemFrame, 35, 35);
    else if (command == REFERENCE)
      showFrame(referencesFrame, 60, 60);
    else if (command == HELP)
      showFrame(helpFrame, 85, 85);
    else if (command == MAIN)
      showFrame(Demo.MAIN_DISPLAY_FRAME, 1, 1);
    else
      System.err.print("FrameDisplayListener could not understand command");
  }

  /**
   * Adds a frame to the <code>desktop</code> to display, and brings to front
   *
   * @param iframe internal frame to show
   * @param x x position of frame
   * @param y y position of frame
   */
  public void showFrame(JInternalFrame iframe, int x, int y)
  {
    iframe.validate();
    iframe.show();
    iframe.setLocation(x, y);
  }
}
