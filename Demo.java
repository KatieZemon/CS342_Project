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
  private JMenuBar menuBar;
  private JMenu aboutMenu, demoMenu, algorithmsMenu, dataMenu;
  private JMenuItem authorMenuItem, problemMenuItem, referencesMenuItem, helpMenuItem, unselectMenuItem, mainDisplayItem;
  JCheckBoxMenuItem bubbleMenuItem, insertionMenuItem, selectionMenuItem, quicksortMenuItem,
          heapsortMenuItem, shellsortMenuItem;
  JRadioButtonMenuItem randomCaseMenuItem, bestCaseMenuItem, worstCaseMenuItem;
  //ButtonGroup
  private JDesktopPane desktop = new JDesktopPane();
  private static JInternalFrame authorFrame, problemFrame, referencesFrame, helpFrame, mainDisplayFrame;
  private Container c = getContentPane();
  Color menuBackgroundColor = new Color(49, 78, 139);
  Color menuTextColor = new Color(254, 253, 255);

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

    authorFrame = new Author();
    problemFrame = new Problem();
    referencesFrame = new References();
    helpFrame = new Help();
    mainDisplayFrame = new MainDisplay();

    desktop.add(authorFrame);
    authorFrame.dispose();
    desktop.add(problemFrame);
    problemFrame.dispose();
    desktop.add(referencesFrame);
    referencesFrame.dispose();
    desktop.add(helpFrame);
    helpFrame.dispose();
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
    menuBar = new JMenuBar();
    menuBar.setBackground(menuBackgroundColor);

    setJMenuBar(menuBar);

    aboutMenu = new JMenu("About");
    aboutMenu.setForeground(menuTextColor);
    aboutMenu.setBackground(menuBackgroundColor);
    menuBar.add(aboutMenu);

    demoMenu = new JMenu("Demos");
    demoMenu.setForeground(menuTextColor);
    demoMenu.setBackground(menuBackgroundColor);
    menuBar.add(demoMenu);

    algorithmsMenu = new JMenu("Algorithms");
    algorithmsMenu.setOpaque(true); // This is required for setting the background color of submenus
    algorithmsMenu.setBackground(menuBackgroundColor);
    algorithmsMenu.setForeground(menuTextColor);
    demoMenu.add(algorithmsMenu);

    dataMenu = new JMenu("Data Type Items");
    dataMenu.setOpaque(true); // This is required for setting the background color of submenus
    dataMenu.setForeground(menuTextColor);
    dataMenu.setBackground(menuBackgroundColor);
    demoMenu.add(dataMenu);

    // Add menu items to About Menu
    authorMenuItem = new JMenuItem("Author");
    authorMenuItem.addActionListener(this);
    authorMenuItem.setBackground(menuBackgroundColor);
    authorMenuItem.setForeground(menuTextColor);
    aboutMenu.add(authorMenuItem);

    problemMenuItem = new JMenuItem("Problem Description");
    problemMenuItem.addActionListener(this);
    problemMenuItem.setBackground(menuBackgroundColor);
    problemMenuItem.setForeground(menuTextColor);
    aboutMenu.add(problemMenuItem);

    referencesMenuItem = new JMenuItem("References");
    referencesMenuItem.addActionListener(this);
    referencesMenuItem.setBackground(menuBackgroundColor);
    referencesMenuItem.setForeground(menuTextColor);
    aboutMenu.add(referencesMenuItem);

    helpMenuItem = new JMenuItem("Help");
    helpMenuItem.addActionListener(this);
    helpMenuItem.setBackground(menuBackgroundColor);
    helpMenuItem.setForeground(menuTextColor);
    aboutMenu.add(helpMenuItem);

    // Add menu items to Demo > Algorithms menu
    bubbleMenuItem = new JCheckBoxMenuItem("Bubble");
    bubbleMenuItem.addActionListener(this);
    bubbleMenuItem.setForeground(menuTextColor);
    bubbleMenuItem.setBackground(menuBackgroundColor);
    algorithmsMenu.add(bubbleMenuItem);

    insertionMenuItem = new JCheckBoxMenuItem("Insertion");
    insertionMenuItem.addActionListener(this);
    insertionMenuItem.setForeground(menuTextColor);
    insertionMenuItem.setBackground(menuBackgroundColor);
    algorithmsMenu.add(insertionMenuItem);

    selectionMenuItem = new JCheckBoxMenuItem("Selection");
    selectionMenuItem.setState(true);
    selectionMenuItem.addActionListener(this);
    selectionMenuItem.setForeground(menuTextColor);
    selectionMenuItem.setBackground(menuBackgroundColor);
    algorithmsMenu.add(selectionMenuItem);

    quicksortMenuItem = new JCheckBoxMenuItem("Quicksort");
    quicksortMenuItem.addActionListener(this);
    quicksortMenuItem.setForeground(menuTextColor);
    quicksortMenuItem.setBackground(menuBackgroundColor);
    algorithmsMenu.add(quicksortMenuItem);

    heapsortMenuItem = new JCheckBoxMenuItem("Heapsort");
    heapsortMenuItem.addActionListener(this);
    heapsortMenuItem.setForeground(menuTextColor);
    heapsortMenuItem.setBackground(menuBackgroundColor);
    algorithmsMenu.add(heapsortMenuItem);

    shellsortMenuItem = new JCheckBoxMenuItem("Shellsort");
    shellsortMenuItem.addActionListener(this);
    shellsortMenuItem.setForeground(menuTextColor);
    shellsortMenuItem.setBackground(menuBackgroundColor);
    algorithmsMenu.add(shellsortMenuItem);

    unselectMenuItem = new JMenuItem("Unselect");
    unselectMenuItem.addActionListener(this);
    unselectMenuItem.setForeground(menuTextColor);
    unselectMenuItem.setBackground(menuBackgroundColor);
    algorithmsMenu.add(unselectMenuItem);

    // Add menu items to Demo > DataTypeItems menu
    randomCaseMenuItem = new JRadioButtonMenuItem("Random");
    randomCaseMenuItem.setForeground(menuTextColor);
    randomCaseMenuItem.setBackground(menuBackgroundColor);
    dataMenu.add(randomCaseMenuItem);

    bestCaseMenuItem = new JRadioButtonMenuItem("Best");
    bestCaseMenuItem.setForeground(menuTextColor);
    bestCaseMenuItem.setBackground(menuBackgroundColor);
    dataMenu.add(bestCaseMenuItem);

    worstCaseMenuItem = new JRadioButtonMenuItem("Worst");
    worstCaseMenuItem.setForeground(menuTextColor);
    worstCaseMenuItem.setBackground(menuBackgroundColor);
    dataMenu.add(worstCaseMenuItem);

    // Add mainDisplay
    mainDisplayItem = new JMenuItem("Main Display");
    mainDisplayItem.addActionListener(this);
    mainDisplayItem.setForeground(menuTextColor);
    mainDisplayItem.setBackground(menuBackgroundColor);
    demoMenu.add(mainDisplayItem);

    // Add shortcuts to About menu items
    authorMenuItem.setAccelerator(getKeyStroke('1', CTRL_MASK));
    problemMenuItem.setAccelerator(getKeyStroke('2', CTRL_MASK));
    referencesMenuItem.setAccelerator(getKeyStroke('3', CTRL_MASK));
    helpMenuItem.setAccelerator(getKeyStroke('4', CTRL_MASK));
  }

  public void actionPerformed(ActionEvent event)
  {
    if (event.getSource() == authorMenuItem)
    {
      if (authorFrame.isClosed())
      {
        authorFrame = new Author();
        desktop.add(authorFrame);
        authorFrame.toFront();
        authorFrame.setLocation(0,0);
      }
    }
    else if (event.getSource() == problemMenuItem)
    {
      if (problemFrame.isClosed())
      {
        problemFrame = new Problem();
        desktop.add(problemFrame);
        problemFrame.toFront();
        problemFrame.setLocation(25,25);
      }
    }
    else if (event.getSource() == referencesMenuItem)
    {
      if (referencesFrame.isClosed())
      {
        referencesFrame = new References();
        desktop.add(referencesFrame);
        referencesFrame.toFront();
        referencesFrame.setLocation(50,50);
      }
    }
    else if (event.getSource() == helpMenuItem)
    {
      if (helpFrame.isClosed())
      {
        helpFrame = new Help();
        desktop.add(helpFrame);
        helpFrame.toFront();
        helpFrame.setLocation(75, 75);
      }
    }
    else if (event.getSource() == mainDisplayItem)
    {
      if (mainDisplayFrame.isClosed())
      {
        mainDisplayFrame = new MainDisplay();
        desktop.add(mainDisplayFrame);
        mainDisplayFrame.toFront();
        mainDisplayFrame.setLocation(100, 100);
      }
    }
    else if (event.getSource() == unselectMenuItem)
    {
      ((MainDisplay)mainDisplayFrame).removeAllSorts();
    }
    else if (event.getSource() == selectionMenuItem)
    {
      if(mainDisplayFrame != null && !mainDisplayFrame.isClosed())
      {
        if(selectionMenuItem.getState())
        {
          ((MainDisplay)mainDisplayFrame).addSort("SelectionSort");
        }
        else
        {
          ((MainDisplay)mainDisplayFrame).removeSort("SelectionSort");
        }
      }
    }
    else if (event.getSource() == bubbleMenuItem)
    {
      if(mainDisplayFrame != null && !mainDisplayFrame.isClosed())
      {
        if(bubbleMenuItem.getState())
        {
          ((MainDisplay)mainDisplayFrame).addSort("BubbleSort");
        }
        else
        {
          ((MainDisplay)mainDisplayFrame).removeSort("BubbleSort");
        }
      }
    }
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

    /**
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
}


