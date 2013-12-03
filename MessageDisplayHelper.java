package code;

import javax.swing.*;
import java.awt.*;

/**
 * @author Thomas
 *         Date and Time: 12/2/13 10:39 PM
 */
public class MessageDisplayHelper {

  public static void showErrorMessage(Component parent, String message){
    JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
  }
}
