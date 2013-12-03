package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A utility for building menus.  Colors the menus according to the
 * color scheme specified by its Color members
 * @author Thomas Clay
 */
public class MenuBuilder {

  /** The background color of the menu, and menu items */
  public static Color menuBackgroundColor = new Color(49, 78, 139);

  /** The text color of menus and menu items */
  public static Color menuTextColor = new Color(254, 253, 255);


  /**
   * Creates a colorized menu
   * @param menuName the name of the menu
   * @return the Menu created
   */
  public static JMenu makeMenu(String menuName){
    return colorize(new JMenu(menuName));
  }

  /**
   * Adds a menu item to the parentMenu with an action command of the same name
   * @param parentMenu the menu to add a menu item to
   * @param itemName the name of the menu item, and action command
   * @param actionListener the implementation of ActionListener that will react to the action command
   * @param accelerator - the KeyStroke to use as the accelerator.  Can be null
   * @return the menu item added
   */
  public static JMenuItem addMenuItem(JMenu parentMenu, String itemName, ActionListener actionListener, KeyStroke accelerator){
    JMenuItem menuItem = colorize(new JMenuItem(itemName));
    menuItem.addActionListener(actionListener);
    menuItem.setActionCommand(itemName);
    if(accelerator != null) menuItem.setAccelerator(accelerator);
    parentMenu.add(menuItem);
    return menuItem;
  }

  /**
   * Adds a JCheckBoxMenuItem to the parent menu
   * @param parentMenu the menu to add the checkbox item to
   * @param itemName the name of the item to add
   * @param actionListener the implementation of ActionListener to use
   * @param checked should this be default checked
   * @return the menu item added
   */
  public static JMenuItem addCheckBoxMenuItem(JMenu parentMenu, String itemName, ActionListener actionListener, boolean checked){
    JMenuItem menuItem = colorize(new StayOpenCheckBoxMenuItem(itemName));
    menuItem.addActionListener(actionListener);
    menuItem.setActionCommand(itemName);
    menuItem.setSelected(checked);
    parentMenu.add(menuItem);
    return menuItem;
  }

  /**
   * Creates a radio button menu with the
   * @param menuName the name of the menu
   * @param actionListener the implementation of ActionListener to use for these menu items
   * @param defaultSelction the index (starting at 0) of the default selected radio button
   * @param itemNames the list of names of options
   * @return the menu created
   */
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

  /**
   * Colorizes a menu item based on <code>menuBackgroundColor</code> and <code>menuTextColor</code>
   * @param menuItem the menu item to colorize
   * @return the colorized menu item
   */
  public static JMenuItem colorize(JMenuItem menuItem)
  {
    menuItem.setForeground(menuTextColor);
    menuItem.setBackground(menuBackgroundColor);
    return menuItem;
  }

  /**
   * Colorizes a menu item based on <code>menuBackgroundColor</code> and <code>menuTextColor</code>
   * @param menu the menu to colorize
   * @return the colorized menu
   */
  public static JMenu colorize(JMenu menu)
  {
    menu.setOpaque(true);
    menu.setForeground(menuTextColor);
    menu.setBackground(menuBackgroundColor);
    return menu;
  }
}
