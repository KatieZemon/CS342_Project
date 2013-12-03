package code;

import code.sorts.*;
import code.KInternalFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The display that houses the charts that represent the different sorting algorithms
 * This is responsible for managing the algorithms and data being used
 */
public class MainDisplay extends KInternalFrame implements ActionListener, ChangeListener
{
  /** The panel containing the sort graphs */
  static JPanel sortPanel;

  /** A executor pool for sort threads */
  ExecutorService executor;

  /** The slider which controls the amount of delay to use in the sorts*/
  JSlider delaySlider;

  /** The slider which controls the number of elements to sort */
  JSlider itemCountSlider;

  /** The button which starts the sorting process */
  JButton startButton;

  /** The button which resets the sorts and stops the sorting process */
  JButton resetButton;

  /** The content pane */
  Container c = getContentPane();

  /** The initial width of the internal frame */
  private int frameWidth = 900;

  /** The initial height of the internal frame */
  private int frameHeight = 700;

  /** The maximum value of an element in the data array */
  public static final int MAX_VALUE = 100;

  /** The number of milliseconds of delay between steps in any sorting algorithm. */
  int delay = 100;

  /** the number of items to be sorted. This number is obtained by itemCountSlider*/
  int numItems = 50;

  /** A bold font */
  Font font_bold = new Font("Bold", Font.BOLD, 15);

  /** the values to be sorted */
  int[] values;

  /** A map between the class of the sort, and an instance of it */
  static HashMap<Class, Sort> sorts = new HashMap<Class, Sort>();

  /** The maximum number of sorts to be displayed on the screen at once */
  public static final int MAX_SORTS = 3;

  /**
   * The current data mode
   * @see #RANDOM
   * @see #BEST
   * @see #WORST
   */
  private int currentDataMode = 1;

  /** The dataMode that represents a random data set */
  public static final int RANDOM = 1;

  /** The dataMode that represents a best-case data set */
  public static final int BEST = 2;

  /** The dataMode that represents a worst-case data set */
  public static final int WORST = 3;

  /** Does what a default constructor does */
  public MainDisplay()
  {
    // Initialize the internal frame
    super("Main Display");
    setSize(frameWidth, frameHeight);
    setVisible(true);

    // Set the layout
    c.setLayout(new FlowLayout());

    // Create and add our sliders and buttons
    initComponents();

    // Store random numbers in our array to be sorted
    initValsArr();

    sortPanel = new JPanel();
    c.add(sortPanel);

    executor = Executors.newFixedThreadPool(MAX_SORTS);

  }

  /**
   * Creates and displays our sliders and buttons
   */
  void initComponents()
  {
    itemCountSlider = new JSlider(1, 800, numItems);
    itemCountSlider.setBorder(new TitledBorder(new LineBorder(Color.BLACK,3), "Array Length: N = " + numItems,
        TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font_bold, Color.BLACK));
    itemCountSlider.addChangeListener(this);
    Dimension d = itemCountSlider.getPreferredSize();
    itemCountSlider.setPreferredSize(new Dimension(d.width+60,d.height));
    itemCountSlider.setMajorTickSpacing(50);
    c.add(itemCountSlider);

    // Start Button
    startButton = new JButton("Sort");
    startButton.addActionListener(this);
    startButton.setEnabled(false);
    c.add(startButton);

    // Reset Button
    resetButton = new JButton("Reset");
    resetButton.setEnabled(false); // Initially this is disabled
    resetButton.addActionListener(this);
    c.add(resetButton);

    delaySlider = new JSlider(1, 800, delay);
    delaySlider.setBorder(new TitledBorder(new LineBorder(Color.BLACK,3), "Current Delay = " + delay + "ms",
        TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font_bold, Color.BLACK));
    delaySlider.addChangeListener(this);
    d = delaySlider.getPreferredSize();
    delaySlider.setPreferredSize(new Dimension(d.width+60,d.height));
    c.add(delaySlider);
  }


  /**
   * Initializes the size of our values array and stores these values
   * in each location of the array. Numerical values are in the range [1,100]
   * dataMode updates currentDataMode. If this is 1, random numbers are used,
   * 2 sets best case numbers (already sorted) and 3 is worst case.
   */
  void initValsArr()
  {
    values = new int[numItems];
    for (int i = 0; i < numItems; i++)
    {
      values[i] = (int)(MAX_VALUE * Math.random() + 1); // random number from 1-100
    }
    if(currentDataMode == RANDOM) return;
    Arrays.sort(values);
    if(currentDataMode == BEST) return;
    //reverse order
    int[] temp = new int[values.length];
    for(int i = 0; i < values.length; i++){
      temp[values.length - 1 - i] = values[i];
    }
    values = temp;
  }

  /**
   * Creates all sorts new that are to be shown.
   */
  public void updateSorts()
  {
    sortPanel.removeAll();
    sortPanel.setLayout(new GridLayout(sorts.size(), 1));
    if(sorts.size() > 0)
    {
      int sortHeight = (getHeight() - 100) / sorts.size();
      for(Sort sorter : sorts.values())
      {
        sorter.setPanelHeight(sortHeight);
        sorter.setValues(values);
        sorter.delayTime = delay;
        sortPanel.add(sorter);
      }
    }
    c.validate();
  }

  /**
   * Logic for adding a sort to the display.  Does not allow the number of
   * sorts to exceed {@link #MAX_SORTS}
   * @param clazz Class of the sort to add to the display
   */
  public void addSort(Class clazz)
  {
    if(sorts.size() < MAX_SORTS)
    {
      startButton.setEnabled(true);
      Sort algorithm;
      try{
        algorithm = (Sort) clazz.getConstructor(int[].class, int.class).newInstance(values, delay);
        sorts.put(clazz, algorithm);
      }
      catch(Exception e){
        e.printStackTrace();
      }
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
    resetButtonAction();
    sorts.clear();
    updateSorts();
  }

  /**
   * Updates currentDataMode and repaints all the sorts with new data.
   * Use:
   * <ul>
   *   <li><code>MainDisplay.BEST</code> for best case</li>
   *   <li><code>MainDisplay.WORST</code> for worst case</li>
   *   <li><code>MainDisplay.RANDOM</code> for random (average case)</li>
   * </ul>
   */
  void updateDataDistribution(int dataMode)
  {
    currentDataMode = dataMode;
    initValsArr();
    resetButtonAction();
    updateSorts();
  }

  /**
   * Stops execution of all sorting algorithms when the program is closed
   */
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
   * Stops execution of all threads (ie. stops all running sorting algorithms)
   * and resets the data in the algorithm
   */
  public void resetButtonAction(){
    resetButtonAction(5, delay*2);
  }

  /**
   * Attempts to stop the sorts threads, and reinitialize them for a new sorting run
   * @param retryCount the number of tries to try to reset/stop the sorts
   * @param waitTime the time it should give the threads to finish
   */
  public void resetButtonAction(int retryCount, int waitTime)
  {
    if(retryCount < 0) return;
    // Disable our resetButton and enable our startButton
    for(Sort s: sorts.values())
    {
      s.running = false;
      s.clearBarColors();
    }
    try {
      executor.shutdown();
      executor.awaitTermination(waitTime, TimeUnit.MILLISECONDS);
      executor.shutdownNow();
      Thread.sleep(waitTime);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    if(executor.isTerminated()){
      resetButton.setEnabled(false);
      startButton.setEnabled(true);
      itemCountSlider.setEnabled(true);
      initValsArr();
      updateSorts();
      c.validate();
      executor = Executors.newFixedThreadPool(3);
      repaint();
    }
    else{
      System.err.println("Problem terminating threads");
      resetButtonAction(retryCount - 1, waitTime);
    }
  }

  /**
   * Launches the sorting algorithms
   */
  private void startButtonAction(){
    // Disable the start button and enabled our reset button
    startButton.setEnabled(false);
    resetButton.setEnabled(true);
    itemCountSlider.setEnabled(false);

    // Begin execution of our algorithms
    initValsArr(); // Reinitialize our array of values in case the ItemCountSlider changed
    for(Sort s: sorts.values())
    {
      executor.execute(s);
    }
    repaint();
  }

  /**
   * This calls the startButtonAction() when the start button is pressed
   * or it calls the resetButtonAction() when the reset button is pressed
   */
  public void actionPerformed(ActionEvent e)
  {
    // Sort button
    if (e.getSource() == startButton)
    {
      startButtonAction();
    }
    // Reset Button
    else if (e.getSource() == resetButton)
    {
      resetButtonAction();
    }
    else
    {
      System.err.println("Action not implemented for " + e.getSource());
    }
  }

  /**
   * Function automatically called when the item count slider or delay slider is moved
   */
  public void stateChanged(ChangeEvent e)
  {
    // Changes the number of items to be sorted and updates the sorting algorithms
    if (e.getSource() == itemCountSlider)
    {
      numItems = itemCountSlider.getValue();
      // Change the border title to show the new slider indexValue
      ((TitledBorder)itemCountSlider.getBorder()).setTitle("Array Length: N = " + numItems);
      initValsArr();
      updateSorts();
    }

    // Changes the delay of the sorting algorithms running
    else if (e.getSource() == delaySlider)
    {
      delay = delaySlider.getValue();
      for(Sort s: sorts.values())
      {
        s.delayTime = delay;
      }
      // Change the border title to show the new slider indexValue
      ((TitledBorder)delaySlider.getBorder()).setTitle("Current Delay = " + delay + "ms");
    }
    repaint();
  }
}
