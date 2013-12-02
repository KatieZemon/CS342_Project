package code;

import code.sorts.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * The class configures the MainDisplay object with the correct algorithms and datasets
 */
public class AlgorithmSelectionListener implements ActionListener
{
  /** String representation of using random numbers as the data to be sorted */
  public static final String RANDOM = "Random";
  /** String representation for use of worst-case numbers in the data to be sorted */
  public static final String WORST = "Worst";
  /** String representation for use of best-case numbers in the data to be sorted */
  public static final String BEST = "Best";
  /** String representation for action command to unselect all algorithms to be sorted */
  public static final String UNSELECT = "Unselect";

  private MainDisplay mainDisplay;

  /** A map between the name of the sort and the sorts Class */
  static HashMap<String, Class> sortingAlgorithms = new HashMap<String, Class>();
  static{ //initialize the static hash map of sorts
    sortingAlgorithms.put("Insertion Sort", InsertionSort.class);
    sortingAlgorithms.put("Selection Sort", SelectionSort.class);
    sortingAlgorithms.put("Bubble Sort", BubbleSort.class);
    sortingAlgorithms.put("Merge Sort", MergeSort.class);
    sortingAlgorithms.put("Quick Sort", QuickSort.class);
    sortingAlgorithms.put("Comb Sort", CombSort.class);
    sortingAlgorithms.put("Cocktail Sort", CocktailSort.class);
    sortingAlgorithms.put("Gnome Sort", GnomeSort.class);
    sortingAlgorithms.put("Odd-even Sort", OddEvenSort.class);
    sortingAlgorithms.put("Binary Tree Sort", TreeSort.class);
    sortingAlgorithms.put("Heap Sort", HeapSort.class);
    sortingAlgorithms.put("Shell Sort", ShellSort.class);
    sortingAlgorithms.put("Stooge Sort", StoogeSort.class);
    sortingAlgorithms.put("Cycle Sort", CycleSort.class);
    sortingAlgorithms.put("Tom Sort", TomSort.class);
    sortingAlgorithms.put("Counting Sort", CountingSort.class);
    sortingAlgorithms.put("Bead Sort", BeadSort.class);
    sortingAlgorithms.put("Bucket Sort", GenericBucketSort.class);
    // TODO: Add new sorts
  }

  /**
   *
   * @param mainDisplay the display to modify when events happen
   */
  AlgorithmSelectionListener(MainDisplay mainDisplay){
    this.mainDisplay = mainDisplay;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    // If command deals with algorithm selection:
    if(sortingAlgorithms.containsKey(command)){
      mainDisplay.resetButtonAction();
      JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();
      Class clazz = sortingAlgorithms.get(command);
      if(menuItem.getState())
      {
        mainDisplay.addSort(clazz);
      }
      else if(!menuItem.getState() && mainDisplay.sorts.containsKey(clazz)){
        mainDisplay.removeSort(clazz);
      }
    }
    // The action we are performing is to choose a Random, Best, or Worst case scenario for our algorithm
    else
    {
      mainDisplay.resetButtonAction();
      if (command.equals(RANDOM))
      {
        mainDisplay.updateDataDistribution(mainDisplay.RANDOM);
      }
      else if (command.equals(BEST))
      {
        mainDisplay.updateDataDistribution(mainDisplay.BEST);
      }
      else if (command.equals(WORST))
      {
        mainDisplay.updateDataDistribution(mainDisplay.WORST);
      }
      else if (command.equals(UNSELECT))
      {
        mainDisplay.removeAllSorts();
        JMenuItem unselect = (JMenuItem) e.getSource();
        JPopupMenu menu = (JPopupMenu) unselect.getParent();
        for (Component component : menu.getComponents())
        {
          System.out.println("looking at item of class " + component.getClass());
          if (component.getClass() == JCheckBoxMenuItem.class)
          {
            JCheckBoxMenuItem item = (JCheckBoxMenuItem) component;
            item.setSelected(false);
          }
        }
      } else
        System.err.println("Could not understand menu command");
    }
  }
}
