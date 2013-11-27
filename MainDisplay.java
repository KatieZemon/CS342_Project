package code;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

public class MainDisplay extends JInternalFrame implements ActionListener, ChangeListener
{
  JPanel sortPanel;
  ExecutorService executor;
  JSlider delaySlider, itemCountSlider;
  JButton startButton, resetButton;
  Container c = getContentPane();
  //** frameWidth and frameHeight = the initial width and height of the internalframe */
  int frameWidth = 900, frameHeight = 700;
  //** delay = the number of milliseconds of delay between steps in any sorting algorithm.
  // This number is obtained from the delaySlider*/
  int delay = 100;
  //** numItems = the number of items to be sorted.
  // This number is obtained by itemCountSlider*/
  int numItems = 50;
  FlowLayout layout;
  Font font_bold = new Font("Bold", Font.BOLD, 15);
  TitledBorder itemCount_tBorder, delay_tBorder;
  LineBorder itemCount_border, delay_border;
  /** vals= the values to be sorted */
  int[] vals;
  HashMap<Class, Sort> sorts = new HashMap<Class, Sort>();
  final int sortCountMax = 3;
  GridLayout sortLayout;
  public int currentDataMode = -1;
  public static final int RANDOM = 1;
  public static final int BEST = 2;
  public static final int WORST = 3;

  /**
   * class: MainDisplay
   * desc:
   */
  public MainDisplay()
  {
    // Initialize the internal frame
    super("Main Display", true, true, true, true);
    setSize(frameWidth, frameHeight);
    setVisible(true);
    setOpaque(true);

    // Set the layout
    layout = new FlowLayout();
    c.setLayout(layout);

    // Create and add our sliders and buttons
    initComponents();

    // Store random numbers in our array to be sorted
    initValsArr(1);

    sortPanel = new JPanel();
    c.add(sortPanel);

    executor = Executors.newFixedThreadPool(3);

    addSort(SelectionSort.class);
  }

  /**
  * Creates all sorts new that are to be shown.
  */
  private void updateSorts()
  {
    System.out.println("updating sorts...");
    sortPanel.removeAll();
    sortPanel.setLayout(new GridLayout(sorts.size(), 1));
    for(Sort sorter : sorts.values())
    {
      System.out.println("\t >" + sorter.toString());
      sorter.setValues(vals);
      sorter.delay = delay;
      sortPanel.add(sorter);
    }
    c.validate();
  }

  public void addSort(Class clazz)
  {
    if(sorts.size() < sortCountMax)
    {
      System.out.println("Adding sort " + clazz.toString());
      Sort algorithm = null;
      try{
        algorithm = (Sort) clazz.getConstructor(int[].class, int.class).newInstance(vals, delay);
        sorts.put(clazz, algorithm);
      }
      catch(Exception e){
        e.printStackTrace();
      }
      System.out.println("sorts: ");
      System.out.println(sorts.keySet());
    }
    updateSorts();
  }

  /**
  * Removes a single sort from being shown.
  */
  public void removeSort(Class clazz)
  {
    sorts.remove(clazz);
    updateSorts();
  }

  /**
  * Removes all sorts.
  */
  public void removeAllSorts()
  {
    sorts.clear();
    updateSorts();
  }


  /**
   * fn: initValsArray
   * desc: Initialize the size of our values array and store values
   * in each location of the array. Numerical values are in the range [1,100]
   * dataMode updates currentDataMode. If this is 1, random numbers are used,
   * 2 sets best case numbers (already sorted) and 3 is worst case.
   */
  void initValsArr(int dataMode)
  {
    currentDataMode = dataMode;
    vals = new int[numItems];
    switch(currentDataMode)
    {
      case RANDOM:  // Random numbers
        for (int i = 0; i < numItems; i++)
        {
          vals[i] = (int)(100 * Math.random() + 1); // random number from 1-100
        }
        break;
      case BEST:  // Best Case
        for (int i = 0; i < numItems; i++)
        {
          vals[i] = i;
        }
        break;
      case WORST:
        for (int i = 0; i < numItems; i++)
        {
          vals[i] = numItems - i;
        }
        break;

      default:
        System.out.println("Invalid currentDataMode!");
    }
  }

    /**
   * fn: initValsArray
   * desc: Initialize the size of our values array and store values
   * in each location of the array. Numerical values are in the range [1,100]
   * This uses currentDataMode. If this is 1, random numbers are used,
   * 2 sets best case numbers (already sorted) and 3 is worst case.
   */
  void initValsArr()
  {
    initValsArr(currentDataMode);
  }

   /**
  * fn: updateDataDistribution
  * desc: Updates currentSortMode and repaints all the sorts with new data.
  * dataMode should be 1 for random, 2 for best case, and 3 for worst case.
  */
  void updateDataDistribution(int dataMode)
  {
    initValsArr(dataMode);
    resetButtonAction();
    updateSorts();
  }

  /**
   * fn: initComponents
   * desc: Creates and displays our sliders and buttons
   */
  void initComponents()
  {
    // itemCountSlider
    itemCount_border = new LineBorder(Color.BLACK,3);
    itemCount_tBorder = new TitledBorder(
            itemCount_border, "Array Length: N = " + numItems, TitledBorder.CENTER,
            TitledBorder.DEFAULT_POSITION, font_bold, Color.BLACK);
    itemCountSlider = new JSlider(1, 800, numItems);
    itemCountSlider.setBorder(itemCount_tBorder);
    itemCountSlider.addChangeListener(this);
    Dimension d = itemCountSlider.getPreferredSize();
    itemCountSlider.setPreferredSize(new Dimension(d.width+60,d.height));
    itemCountSlider.setMajorTickSpacing(50);
    c.add(itemCountSlider);

    // Start Button
    startButton = new JButton("Sort");
    startButton.addActionListener(this);
    c.add(startButton);

    // Reset Button
    resetButton = new JButton("Reset");
    resetButton.setEnabled(false); // Initially this is disabled
    resetButton.addActionListener(this);
    c.add(resetButton);

    // delaySlider
    delay_border = new LineBorder(Color.BLACK,3);
    delay_tBorder = new TitledBorder(
            delay_border, "Current Delay = " + delay + "ms", TitledBorder.CENTER,
            TitledBorder.DEFAULT_POSITION, font_bold, Color.BLACK);
    delaySlider = new JSlider(1, 800, delay);
    delaySlider.setBorder(delay_tBorder);
    delaySlider.addChangeListener(this);
    d = delaySlider.getPreferredSize();
    delaySlider.setPreferredSize(new Dimension(d.width+60,d.height));
    c.add(delaySlider);
  }

  public void doDefaultCloseAction()
  {
    for(Sort s: sorts.values())
    {
      s.running = false;
    }
    executor.shutdown();
    this.dispose();
  }

  /**
   * fn: resetButtonAction
   * desc: Resets all sorts
   */
  public void resetButtonAction()
  {
    // Disable our resetButton and enable our startButton
    resetButton.setEnabled(false);
    startButton.setEnabled(true);
    itemCountSlider.setEnabled(true);
    for(Sort s: sorts.values())
    {
      s.running = false;
    }
    executor.shutdown();
    updateSorts();
    c.validate();
    executor = Executors.newFixedThreadPool(3);
    repaint();
  }

  public void actionPerformed(ActionEvent e)
  {
    // Sort button
    if (e.getSource() == startButton)
    {
      // Disable the start button and enabled our reset button
      startButton.setEnabled(false);
      resetButton.setEnabled(true);
      itemCountSlider.setEnabled(false);

      // Begin execution of our algorithms
      initValsArr(); // Reinitialize our array of values in case the ItemCountSlider changed
      // Executor exe = Executors.newCachedThreadPool();
      for(Sort s: sorts.values())
      {
        executor.execute(s);
      }
      repaint();
    }

    // Reset Button
    else if (e.getSource() == resetButton)
    {
      resetButtonAction();
    }
  }

  public void stateChanged(ChangeEvent e)
  {
    // Item Count Slider
    if (e.getSource() == itemCountSlider)
    {
      numItems = itemCountSlider.getValue();

      // Change the border
      itemCount_tBorder = new TitledBorder(
              itemCount_border, "Array Length: N = " + numItems, TitledBorder.CENTER,
              TitledBorder.DEFAULT_POSITION, font_bold, Color.BLACK);
      itemCountSlider.setBorder(itemCount_tBorder);
      initValsArr();
      updateSorts();
    }

    // Delay Slider
    else if (e.getSource() == delaySlider)
    {
      delay = delaySlider.getValue();
      for(Sort s: sorts.values())
      {
        s.delay = delay;
      }
      // Change the border
      delay_tBorder = new TitledBorder(
              delay_border, "Current Delay = " + delay + "ms", TitledBorder.CENTER,
              TitledBorder.DEFAULT_POSITION, font_bold, Color.BLACK);
      delaySlider.setBorder(delay_tBorder);
    }
    repaint();
  }
}
