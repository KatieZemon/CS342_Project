package code;

import code.sorts.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * This class is an implementation of ActionListener whose domain of responsibility is
 * configuring a MainDisplay object with the correct algorithms and data sets
 * to use for its sorting demo
 *
 * @author Thomas Clay
 * Date: 11/26/13
 * Time: 7:51 PM
 */
public class AlgorithmSelectionListener implements ActionListener {
  /** String representation for use of random numbers in the data to be sorted */
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
    //NEW SORTS TO BE ADDED HERE
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
    // If command deals with data or delay:
    else {
      switch(command){
        case RANDOM: mainDisplay.updateDataDistribution(mainDisplay.RANDOM);
          break;
        case BEST: mainDisplay.updateDataDistribution(mainDisplay.BEST);
          break;
        case WORST: mainDisplay.updateDataDistribution(mainDisplay.WORST);
          break;
        case UNSELECT:
          mainDisplay.removeAllSorts();
          JMenuItem unselect = (JMenuItem)e.getSource();
          JPopupMenu menu = (JPopupMenu)unselect.getParent();
          for(Component component: menu.getComponents()){
            System.out.println("looking at item of class " + component.getClass());
            if(component.getClass() == JCheckBoxMenuItem.class){
              JCheckBoxMenuItem item = (JCheckBoxMenuItem) component;
              System.out.println("Unselecting checkbox");
              item.setSelected(false);
            }
          }
         break;
        default:
          System.err.println("Could not understand menu command");
      }
    }
  }
}
