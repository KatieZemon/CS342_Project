package code;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: Thomas
 * Date: 11/26/13
 * Time: 7:51 PM
 */
public class FrameDisplayListener implements ActionListener {

  public static final String AUTHOR = "Author";
  public static final String PROBLEM = "Problem Statement";
  public static final String REFERENCE = "References";
  public static final String HELP = "Help";

  private Author authorFrame = new Author();
  private Problem problemFrame = new Problem();
  private References referencesFrame = new References();
  private Help helpFrame = new Help();
  private JDesktopPane desktop;

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

  public void addFrame(JInternalFrame iframe, int x, int y){
    //consider adding if closed check
    desktop.add(iframe);
    iframe.setVisible(true);
    iframe.toFront();
    iframe.setLocation(x, y);
  }
}
