package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 11/26/13
 * Time: 6:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class MenuBuilder {

  public static Color menuBackgroundColor = new Color(49, 78, 139);
  public static Color menuTextColor = new Color(254, 253, 255);


  public static JMenu makeMenu(String menuName){
    return colorize(new JMenu(menuName));
  }

  public static JMenu addSubMenu(JMenu parentMenu, String subMenuName){
    JMenu subMenu = colorize(new JMenu(subMenuName));
    parentMenu.add(subMenu);
    return subMenu;
  }


  /**
   *
   * @param parentMenu
   * @param itemName
   * @param actionListener
   * @param accelerator - can be null
   * @return
   */
  public static JMenuItem addMenuItem(JMenu parentMenu, String itemName, ActionListener actionListener, KeyStroke accelerator){
    JMenuItem menuItem = colorize(new JMenuItem(itemName));
    menuItem.addActionListener(actionListener);
    menuItem.setActionCommand(itemName);
    if(accelerator != null) menuItem.setAccelerator(accelerator);
    parentMenu.add(menuItem);
    return menuItem;
  }

  public static JMenuItem addCheckBoxMenuItem(JMenu parentMenu, String itemName, ActionListener actionListener, boolean checked){
    JMenuItem menuItem = colorize(new JCheckBoxMenuItem(itemName));
    menuItem.addActionListener(actionListener);
    menuItem.setActionCommand(itemName);
    menuItem.setSelected(checked);
    parentMenu.add(menuItem);
    return menuItem;
  }

  public static JMenu makeRadioButtonMenu(String menuName, ActionListener actionListener, int defaultSelction, String... itemNames){
    JMenu menu = colorize(new JMenu(menuName));
    ButtonGroup group = new ButtonGroup();
    for(String name: itemNames){
      JRadioButtonMenuItem rbi = new JRadioButtonMenuItem(name);
      colorize(rbi);
      rbi.addActionListener(actionListener);
      rbi.setActionCommand(name);
      if(defaultSelction == 0){
        rbi.setSelected(true);
      }
      defaultSelction--;
      group.add(rbi);
      menu.add(rbi);
    }
    return menu;
  }

  public static JMenuItem colorize(JMenuItem menuItem)
  {
    menuItem.setForeground(menuTextColor);
    menuItem.setBackground(menuBackgroundColor);
    return menuItem;
  }

  public static JMenu colorize(JMenu menu)
  {
    menu.setOpaque(true);
    menu.setForeground(menuTextColor);
    menu.setBackground(menuBackgroundColor);
    return menu;
  }
}
