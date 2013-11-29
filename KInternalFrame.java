package code;

import javax.swing.*;

/**
 * @author Thomas
 *         Date and Time: 11/29/13 2:02 PM
 */
public class KInternalFrame extends JInternalFrame {

  KInternalFrame(String name){
    super(name, true, true, true, true);
    setOpaque(true);
    setVisible(false);
    setDefaultCloseOperation(HIDE_ON_CLOSE);
  }
}
