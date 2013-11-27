package code;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * User: Thomas
 * Date: 11/26/13
 * Time: 7:51 PM
 */
public class AlgorithmSelectionListener implements ActionListener {

  public static final String RANDOM = "Random";
  public static final String WORST = "Worst";
  public static final String BEST = "Best";
  public static final String UNSELECT = "Unselect";

  MainDisplay mainDisplay;

  static HashMap<String, Class> sortingAlgorithms = new HashMap<String, Class>();
  static{
    sortingAlgorithms.put("Insertion Sort", InsertionSort.class);
    sortingAlgorithms.put("Selection Sort", SelectionSort.class);
  }

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
        mainDisplay.sorts.remove(clazz);
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
      }
    }
  }
}
