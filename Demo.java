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
  /** The JDesktopPane that manages the internal frames */
  private static final JDesktopPane DESKTOP = new JDesktopPane();

  /** A listener for menu events that trigger displays of other frames */
  private static final ActionListener DISPLAY_LISTENER = new FrameDisplayListener(DESKTOP);

  /** The main display; where the sorting panels are */
  static final MainDisplay MAIN_DISPLAY_FRAME = new MainDisplay();

  /** A listener for menu events that change the sort properties */
  static final ActionListener ALGORITHM_SELECTION_LISTENER = new AlgorithmSelectionListener(MAIN_DISPLAY_FRAME);

  /** The content pane */
  private Container c = getContentPane();




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
        if(!Demo.MAIN_DISPLAY_FRAME.isClosed())
        {
          Demo.MAIN_DISPLAY_FRAME.doDefaultCloseAction();
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
    DESKTOP.add(MAIN_DISPLAY_FRAME);
    DESKTOP.setBackground(Color.LIGHT_GRAY);
    c.add(DESKTOP);
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
    MenuBuilder.addMenuItem(aboutMenu, FrameDisplayListener.AUTHOR, DISPLAY_LISTENER, getKeyStroke('1', CTRL_MASK));
    MenuBuilder.addMenuItem(aboutMenu, FrameDisplayListener.PROBLEM, DISPLAY_LISTENER, getKeyStroke('2', CTRL_MASK));
    MenuBuilder.addMenuItem(aboutMenu, FrameDisplayListener.REFERENCE, DISPLAY_LISTENER, getKeyStroke('3', CTRL_MASK));
    MenuBuilder.addMenuItem(aboutMenu, FrameDisplayListener.HELP, DISPLAY_LISTENER, getKeyStroke('4', CTRL_MASK));
    menuBar.add(aboutMenu);

    /** The "Demo" menu */
    JMenu demoMenu = MenuBuilder.makeMenu("Demos");

    /** The Algorithms sub menu */
    JMenu algorithmsMenu = MenuBuilder.makeMenu("Algorithms");

    // Add the menu items to the Algorithms sub menu, and add the sub menu to the Demo menu
    for(String algorithmName : AlgorithmSelectionListener.sortingAlgorithms.keySet()){
      MenuBuilder.addCheckBoxMenuItem(algorithmsMenu, algorithmName, ALGORITHM_SELECTION_LISTENER, false);
    }
    MenuBuilder.addMenuItem(algorithmsMenu, AlgorithmSelectionListener.UNSELECT, ALGORITHM_SELECTION_LISTENER, null);
    demoMenu.add(algorithmsMenu);

    /** The Data Type Options sub menu */
    JMenu Data_Type_OptionsMenu = MenuBuilder.makeRadioButtonMenu("Data Type Items", ALGORITHM_SELECTION_LISTENER, 0,
            AlgorithmSelectionListener.RANDOM, AlgorithmSelectionListener.BEST, AlgorithmSelectionListener.WORST);

    // Add the Data Type Options sub menu to "Demo" menu
    demoMenu.add(Data_Type_OptionsMenu);
    // Add Demo > Main Display
    MenuBuilder.addMenuItem(demoMenu, FrameDisplayListener.MAIN, DISPLAY_LISTENER, getKeyStroke('5', CTRL_MASK));
    menuBar.add(demoMenu);

  }

}


