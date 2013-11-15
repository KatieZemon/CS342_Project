package code;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
  ArrayList<String> sortTypeList = new ArrayList<String>();
  ArrayList<Sort> sortList = new ArrayList<Sort>();
  final int sortCountMax = 3;
  GridLayout sortLayout;

  /**
   * class: MainDisplay
   * desc:
   */
  public MainDisplay()
  {
    // Initialize the internal frame
    super("Main Display", true, true, true, true);
    setSize(frameWidth,frameHeight);
    setVisible(true);
    setOpaque(true);

    // Set the layout
    layout = new FlowLayout();
    c.setLayout(layout);

    // Create and add our sliders and buttons
    initComponents();

    // Store random numbers in our array to be sorted
    initValsArr();

    sortPanel = new JPanel();
    c.add(sortPanel);

    executor = Executors.newFixedThreadPool(3);

    addSort("SelectionSort");
  }

  /**
  * Creates all sorts new that are to be shown.
  */
  private void updateSorts()
  {
    sortPanel.removeAll();
    sortLayout = new GridLayout(sortTypeList.size(), 1);
    sortPanel.setLayout(sortLayout);
    sortList = new ArrayList<Sort>();
    Sort tmpSort = null;
    for(String s: sortTypeList)
    {
      if(s.equals("SelectionSort"))
      {
        tmpSort = new SelectionSort(vals, delay);
      }
      else if(s.equals("BubbleSort"))
      {
        tmpSort = new BubbleSort(vals, delay);
      }
      else
      {
        System.out.println("Invalid sort type!");
        return;
      }
      if(tmpSort != null)
      {
        sortList.add(tmpSort);
        sortPanel.add(tmpSort);
      }
    }
    c.validate();
  }

  public void addSort(String className)
  {
    if(sortTypeList.size() < sortCountMax)
    {
      sortTypeList.add(className);
    }
    updateSorts();
  }

  /**
  * Removes all sorts and sets only the ones in the ArrayList.
  * This can be used for initializing the Demo frame
  */
  public void setSorts(ArrayList<String> classNames)
  {
    removeAllSorts();
    for(String s: classNames)
    {
      addSort(s);
    }
  }

  /**
  * Removes a single sort from being shown.
  */
  public void removeSort(String className)
  {
    if(sortTypeList.contains(className))
    {
      sortTypeList.remove(className);
    }
    updateSorts();
  }

  /**
  * Removes all sorts.
  */
  public void removeAllSorts()
  {
    for(String s: sortTypeList)
    {
      removeSort(s);
    }
  }


  /**
   * fn: initValsArray
   * desc: Initialize the size of our values array and store random values
   * in each location of the array. Numerical values are in the range [1,100]
   */
  void initValsArr()
  {
    vals = new int[numItems];
    for (int i = 0; i < numItems; i++)
    {
      vals[i] = (int)(100 * Math.random() + 1); // random number from 1-100
    }
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
    executor.shutdown();
    this.dispose();
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
      //  exe.execute(selectionSort);
      for(Sort s: sortList)
      {
        executor.execute(s);
      }
      repaint();
    }

    // Reset Button
    else if (e.getSource() == resetButton)
    {
      // Disable our resetButton and enable our startButton
      resetButton.setEnabled(false);
      startButton.setEnabled(true);
      itemCountSlider.setEnabled(true);
      for(Sort s: sortList)
      {
        s.running = false;
      }
      executor.shutdown();
      updateSorts();
      c.validate();
      executor = Executors.newFixedThreadPool(3);
    }
    repaint();
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
      for(Sort s: sortList)
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
