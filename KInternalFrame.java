package code;

import javax.swing.*;

/**
 * A JInternalFrame that has a default close action of {@link #HIDE_ON_CLOSE}
 * @author Thomas Clay
 */
public class KInternalFrame extends JInternalFrame {

  /**
   * @param name of the frame
   */
  KInternalFrame(String name){
    super(name, true, true, true, true);
    setOpaque(true);
    setVisible(false);
    setDefaultCloseOperation(HIDE_ON_CLOSE);
  }
}
