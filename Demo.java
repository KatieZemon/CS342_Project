package code;
/**
 * @author Tom Clay, Katie Isbell, and Westin Sykes
 * @version 1.0
 * Date: December 3, 2013
 * Class: CS342 Java GUI
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.awt.event.KeyEvent.CTRL_MASK;
import static javax.swing.KeyStroke.getKeyStroke;


public class Demo extends JApplet implements ActionListener
{
  private JDesktopPane desktop = new JDesktopPane();
  static MainDisplay mainDisplayFrame;
  private Container c = getContentPane();
  private ActionListener displayListener = new FrameDisplayListener(desktop);
  static ActionListener algorithmSelectionListener = null;

  static{
    mainDisplayFrame = new MainDisplay();
    algorithmSelectionListener = new AlgorithmSelectionListener(mainDisplayFrame);
  }

  /**
   * fn:   main function
   * desc: The main function creates a JFrame so that
   * our applet can be used as an application
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
     * If we run the demo as an application, we want the music to stop
     * immediately when we close our window. Long sound files will not stop playing
     * unless we add a custom windowClosing event like below.
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
   * fn:   init
   * desc: Initializes all of our declared private variables
   * pre:  none
   * post: one of the panels is activated and displayed to the user
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
   * fn:   createMenus
   * desc: Creates our menu bar, menus, and menu items needed by the program
   */
  public void createMenus()
  {

    // Create our menuBar
    JMenuBar menuBar = new JMenuBar();
    menuBar.setBackground(MenuBuilder.menuBackgroundColor);

    setJMenuBar(menuBar);

    //Make 'About' menu
    JMenu aboutMenu = MenuBuilder.makeMenu("About");
    MenuBuilder.addMenuItem(aboutMenu, FrameDisplayListener.AUTHOR, displayListener, getKeyStroke('1', CTRL_MASK));
    MenuBuilder.addMenuItem(aboutMenu, FrameDisplayListener.PROBLEM, displayListener, getKeyStroke('2', CTRL_MASK));
    MenuBuilder.addMenuItem(aboutMenu, FrameDisplayListener.REFERENCE, displayListener, getKeyStroke('3', CTRL_MASK));
    MenuBuilder.addMenuItem(aboutMenu, FrameDisplayListener.HELP, displayListener, getKeyStroke('4', CTRL_MASK));
    menuBar.add(aboutMenu);
    // Make 'Demo' menu
    JMenu demoMenu = MenuBuilder.makeMenu("Demos");
    // Add menu items to Demo > Algorithms menu
    JMenu algorithmsMenu = MenuBuilder.makeMenu("Algorithms");
    for(String algorithmName : AlgorithmSelectionListener.sortingAlgorithms.keySet()){
      MenuBuilder.addCheckBoxMenuItem(algorithmsMenu, algorithmName, algorithmSelectionListener, algorithmName == "Selection Sort");
    }
    MenuBuilder.addMenuItem(algorithmsMenu, AlgorithmSelectionListener.UNSELECT, algorithmSelectionListener, null);
    demoMenu.add(algorithmsMenu);
    // Add Demo > Data Type options
    demoMenu.add(MenuBuilder.makeRadioButtonMenu("Data Type Items", algorithmSelectionListener, 0,
        AlgorithmSelectionListener.RANDOM, AlgorithmSelectionListener.BEST, AlgorithmSelectionListener.WORST));
    // Add Demo > Main Display
    MenuBuilder.addMenuItem(demoMenu, FrameDisplayListener.MAIN, displayListener, getKeyStroke('5', CTRL_MASK));
    menuBar.add(demoMenu);

  }

  public void actionPerformed(ActionEvent event)
  {
  }
}


