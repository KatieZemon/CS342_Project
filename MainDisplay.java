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

public class MainDisplay extends JInternalFrame
{
  SelectionSort selectionSort;
  ExecutorService executor;
  SliderListener sListener;
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

    selectionSort = new SelectionSort(vals,delay);

    c.add(selectionSort);

    executor = Executors.newFixedThreadPool(3);

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
    sListener = new SliderListener();
    itemCount_border = new LineBorder(Color.BLACK,3);
    itemCount_tBorder = new TitledBorder(
            itemCount_border, "Array Length: N = " + numItems, TitledBorder.CENTER,
            TitledBorder.DEFAULT_POSITION, font_bold, Color.BLACK);
    itemCountSlider = new JSlider(1, 800, numItems);
    itemCountSlider.setBorder(itemCount_tBorder);
    itemCountSlider.addChangeListener(sListener);
    Dimension d = itemCountSlider.getPreferredSize();
    itemCountSlider.setPreferredSize(new Dimension(d.width+60,d.height));
    itemCountSlider.setMajorTickSpacing(50);
    c.add(itemCountSlider);

    // Start Button
    startButton = new JButton("Sort");
    startButton.addActionListener( new ButtonListener() );
    c.add(startButton);

    // Reset Button
    resetButton = new JButton("Reset");
    resetButton.setEnabled(false); // Initially this is disabled
    resetButton.addActionListener( new ButtonListener() );
    c.add(resetButton);

    // delaySlider
    delay_border = new LineBorder(Color.BLACK,3);
    delay_tBorder = new TitledBorder(
            delay_border, "Current Delay = " + delay + "ms", TitledBorder.CENTER,
            TitledBorder.DEFAULT_POSITION, font_bold, Color.BLACK);
    delaySlider = new JSlider(1, 800, delay);
    delaySlider.setBorder(delay_tBorder);
    delaySlider.addChangeListener(sListener);
    d = delaySlider.getPreferredSize();
    delaySlider.setPreferredSize(new Dimension(d.width+60,d.height));
    c.add(delaySlider);
  }

  public void doDefaultCloseAction()
  {
    executor.shutdown();
    this.dispose();
  }

  /**
   * class: ButtonListener
   * desc:  An ActionListener class for the Sort and Reset buttons.
   * If the sort button is pressed, the algorithms begin executing.
   * If the reset button is pressed, the execution stops.
   */
  class ButtonListener implements ActionListener
  {
    /**
     * fn: actionPerformed
     * desc: This function is used when selecting the sort and reset buttons to start our selected algorithms
     */
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
        executor.execute(selectionSort);

        // c.add(new selectionSort(vals));


        repaint();
      }

      // Reset Button
      else if (e.getSource() == resetButton)
      {
        // Disable our resetButton and enable our startButton
        resetButton.setEnabled(false);
        startButton.setEnabled(true);
        itemCountSlider.setEnabled(true);
        selectionSort.running = false;
        executor.shutdown();
      }
      repaint();
    }
  }


  /**
   * class: SliderListener
   * desc:  A ChangeListener class for our sliders
   */
  class SliderListener implements ChangeListener
  {
    /**
     * fn: stateChanged
     * desc: This function is used when changing the itemCountSlider or delaySlider and updates the
     * variables storing both the numItems and the delay
     */
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
      }

      // Delay Slider
      else if (e.getSource() == delaySlider)
      {
        delay = delaySlider.getValue();
        selectionSort.delay = delay;

        // Change the border
        delay_tBorder = new TitledBorder(
                delay_border, "Current Delay = " + delay + "ms", TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION, font_bold, Color.BLACK);
        delaySlider.setBorder(delay_tBorder);
      }
      repaint();
    }
  }

}
