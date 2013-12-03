package code;

import javax.swing.*;
import java.awt.*;

/**
 * Assists with displaying messages to the user
 * @author Thomas
 */
public class MessageDisplayHelper {

  /**
   * Shows the user a pop-up error message
   * @param parent component who produced the error message
   * @param message the message to display
   */
  public static void showErrorMessage(Component parent, String message){
    JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
  }
}
