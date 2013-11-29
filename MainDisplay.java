package code;

import code.sorts.SelectionSort;
import code.sorts.Sort;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
public class MainDisplay extends JInternalFrame implements ActionListener, ChangeListener
{
  static JPanel sortPanel;
  ExecutorService executor;
  JSlider delaySlider, itemCountSlider;

  /** Button to begin initiation of selected sorting algorithms */
  JButton startButton;

  /** Button to stop execution of selected sorting algorithms */
  JButton resetButton;

  Container c = getContentPane();

  /** The initial width of the internal frame */
  private int frameWidth = 900;

  /** The initial height of the internal frame */
  private int frameHeight = 700;

  /** The number of milliseconds of delay between steps in any sorting algorithm. */
  int delay = 100;

  /** the number of items to be sorted. This number is obtained by itemCountSlider*/
  int numItems = 50;

  Font font_bold = new Font("Bold", Font.BOLD, 15);
  TitledBorder itemCount_tBorder, delay_tBorder;

  /** the values to be sorted */
  int[] values;

  static HashMap<Class, Sort> sorts = new HashMap<Class, Sort>();

  /** The maximum number of sorts to be displayed on the screen at once */
  final int MAX_SORTS = 3;

  private int currentDataMode = 1;

  /** The dataMode that represents a random data set */
  public static final int RANDOM = 1;

  /** The dataMode that represents a best-case data set */
  public static final int BEST = 2;

  /** The dataMode that represents a worst-case data set */
  public static final int WORST = 3;


  public MainDisplay()
  {
    // Initialize the internal frame
    super("Main Display", true, true, true, true);
    setSize(frameWidth, frameHeight);
    setVisible(true);
    setOpaque(true);

    // Set the layout
    c.setLayout(new FlowLayout());

    // Create and add our sliders and buttons
    initComponents();

    // Store random numbers in our array to be sorted
    initValsArr();

    sortPanel = new JPanel();
    c.add(sortPanel);

    executor = Executors.newFixedThreadPool(MAX_SORTS);

    addSort(SelectionSort.class);
  }

  /**
   * Creates and displays our sliders and buttons
   */
  void initComponents()
  {
    // itemCountSlider
    itemCount_tBorder = new TitledBorder(new LineBorder(Color.BLACK,3), "Array Length: N = " + numItems,
        TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font_bold, Color.BLACK);
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
    delay_tBorder = new TitledBorder(new LineBorder(Color.BLACK,3), "Current Delay = " + delay + "ms",
        TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font_bold, Color.BLACK);
    delaySlider = new JSlider(1, 800, delay);
    delaySlider.setBorder(delay_tBorder);
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
    switch(currentDataMode)
    {
      case RANDOM:  // Random numbers
        for (int i = 0; i < numItems; i++)
        {
          values[i] = (int)(100 * Math.random() + 1); // random number from 1-100
        }
        break;
      case BEST:  // Best Case
        for (int i = 0; i < numItems; i++)
        {
          values[i] = (int)(100 * Math.random() + 1); // random number from 1-100
        }
        // Selection sort used to sort the random values from least to greatest
        for (int j = numItems; j > 0; j--)
        {
          int maxVal = 0;
          for (int i = 0; i < j; i++)
          {
            if ( values[i] >= values[maxVal] )
              maxVal = i; // set new max position
          }
          // Swap
          int temp = values[j-1];
          values[j-1] = values[maxVal];
          values[maxVal] = temp;
        }

        break;
      case WORST: // Worst Case
        for (int i = 0; i < numItems; i++)
        {
          values[i] = (int)(100 * Math.random() + 1); // random number from 1-100
        }
        // Selection sort used to sort the random values from greatest to least
        for (int j = numItems; j > 0; j--)
        {
          int minVal = 0;
          for (int i = 0; i < j; i++)
          {
            if ( values[i] < values[minVal] )
              minVal = i; // set new min position
          }
          // Swap
          int temp = values[j-1];
          values[j-1] = values[minVal];
          values[minVal] = temp;
        }
        break;

      default:
        System.out.println("Invalid currentDataMode!");
    }
  }

  /**
  * Creates all sorts new that are to be shown.
  */
  public void updateSorts()
  {
    System.out.println("updating sorts...");
    sortPanel.removeAll();
    sortPanel.setLayout(new GridLayout(sorts.size(), 1));
    for(Sort sorter : sorts.values())
    {
      System.out.println("\t >" + sorter.toString());
      sorter.setValues(values);
      sorter.delay = delay;
      sortPanel.add(sorter);
    }
    c.validate();
  }

  public void addSort(Class clazz)
  {
    if(sorts.size() < MAX_SORTS)
    {
      System.out.println("Adding sort " + clazz.toString());
      Sort algorithm = null;
      try{
        algorithm = (Sort) clazz.getConstructor(int[].class, int.class).newInstance(values, delay);
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
    resetButtonAction();
    sorts.clear();
    updateSorts();
  }

  /**
  * Updates currentDataMode and repaints all the sorts with new data.
  * dataMode should be 1 for random, 2 for best case, and 3 for worst case.
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
   */
  public void resetButtonAction()
  {
    // Disable our resetButton and enable our startButton
    for(Sort s: sorts.values())
    {
      s.running = false;
    }

    try {
      executor.shutdown();
      executor.awaitTermination(delay*2, TimeUnit.MILLISECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    executor.shutdownNow();
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
    }

  }

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
      // Change the border title to show the new slider value
      itemCount_tBorder.setTitle("Array Length: N = " + numItems);
      initValsArr();
      updateSorts();
    }

    // Changes the delay of the sorting algorithms running
    else if (e.getSource() == delaySlider)
    {
      delay = delaySlider.getValue();
      for(Sort s: sorts.values())
      {
        s.delay = delay;
      }
      // Change the border title to show the new slider value
      delay_tBorder.setTitle("Current Delay = " + delay + "ms");
    }
    repaint();
  }
}
