package code;
/**
 * @author Tom Clay, Katie Isbell, and Westin Sykes
 * @version 1.0
 * Date: December 3, 2013
 * Class: CS342 Java GUI
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.awt.event.KeyEvent.CTRL_MASK;
import static javax.swing.KeyStroke.getKeyStroke;

/**
 * This is the main class to run the entire program
 */
public class Demo extends JApplet
{
  private JDesktopPane desktop = new JDesktopPane();
  static MainDisplay mainDisplayFrame;
  private Container c = getContentPane();
  private ActionListener displayListener = new FrameDisplayListener(desktop);
  static ActionListener algorithmSelectionListener = null;

  static
  {
    mainDisplayFrame = new MainDisplay();
    algorithmSelectionListener = new AlgorithmSelectionListener(mainDisplayFrame);
  }

  /**
   * Creates a JFrame so that the applet can be used as an application
   */
  public static void main(String[] args)
  {
    JFrame mainFrame = new JFrame();
    Demo myDemo = new Demo();
    mainFrame.getContentPane().add(myDemo);
    myDemo.init();
    mainFrame.setTitle("Demo");
    mainFrame.setSize(1000, 900);

    /*
     * Close the main display if the window closes
     */
    mainFrame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) {
        if(!Demo.mainDisplayFrame.isClosed())
        {
          Demo.mainDisplayFrame.doDefaultCloseAction();
        }
      }
    });

    mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    mainFrame.setVisible(true);
  }



  /**
   * Initializes all of our declared private variables
   */
  public void init()
  {
    c.setBackground(Color.WHITE);
    createMenus();
    desktop.add(mainDisplayFrame);


    desktop.setBackground(Color.LIGHT_GRAY);
    c.add(desktop);
  }

  /**
   * Creates our menu bar, menus, and menu items needed by the program
   */
  public void createMenus()
  {
    // Create our menuBar
    JMenuBar menuBar = new JMenuBar();
    menuBar.setBackground(MenuBuilder.menuBackgroundColor);

    /** The menu bar */
    setJMenuBar(menuBar);

    /** The "About" menu */
    JMenu aboutMenu = MenuBuilder.makeMenu("About");

    // Add About menu items, and, add the about menu to the menu bar
    MenuBuilder.addMenuItem(aboutMenu, FrameDisplayListener.AUTHOR, displayListener, getKeyStroke('1', CTRL_MASK));
    MenuBuilder.addMenuItem(aboutMenu, FrameDisplayListener.PROBLEM, displayListener, getKeyStroke('2', CTRL_MASK));
    MenuBuilder.addMenuItem(aboutMenu, FrameDisplayListener.REFERENCE, displayListener, getKeyStroke('3', CTRL_MASK));
    MenuBuilder.addMenuItem(aboutMenu, FrameDisplayListener.HELP, displayListener, getKeyStroke('4', CTRL_MASK));
    menuBar.add(aboutMenu);

    /** The "Demo" menu */
    JMenu demoMenu = MenuBuilder.makeMenu("Demos");

    /** The Algorithms sub menu */
    JMenu algorithmsMenu = MenuBuilder.makeMenu("Algorithms");

    // Add the menu items to the Algorithms sub menu, and add the sub menu to the Demo menu
    for(String algorithmName : AlgorithmSelectionListener.sortingAlgorithms.keySet()){
      MenuBuilder.addCheckBoxMenuItem(algorithmsMenu, algorithmName, algorithmSelectionListener, false);
    }
    MenuBuilder.addMenuItem(algorithmsMenu, AlgorithmSelectionListener.UNSELECT, algorithmSelectionListener, null);
    demoMenu.add(algorithmsMenu);

    /** The Data Type Options sub menu */
    JMenu Data_Type_OptionsMenu = MenuBuilder.makeRadioButtonMenu("Data Type Items", algorithmSelectionListener, 0,
            AlgorithmSelectionListener.RANDOM, AlgorithmSelectionListener.BEST, AlgorithmSelectionListener.WORST);

    // Add the Data Type Options sub menu to "Demo" menu
    demoMenu.add(Data_Type_OptionsMenu);
    // Add Demo > Main Display
    MenuBuilder.addMenuItem(demoMenu, FrameDisplayListener.MAIN, displayListener, getKeyStroke('5', CTRL_MASK));
    menuBar.add(demoMenu);

  }

}


