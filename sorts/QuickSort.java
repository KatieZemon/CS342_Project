package code.sorts;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This implements the quicksort algorithm, which uses a divide and conquer approach
 * for sorting a list of values.
 */
public class QuickSort extends Sort
{
  /** The color of the left element in the array */
  Color leftColor = Color.GREEN;

  /** The color of the pivot element in the array */
  Color pivotColor = Color.YELLOW;

  /** The color of the right element in the array */
  Color rightColor = Color.RED;

  /**
   * Constructor for quick sort. Draws the initial graph and sets values for v and d
   * @param v: The set of data to be sorted.
   * @param d: The delay (in milliseconds) in between each step of the algorithm
   */
  public QuickSort(int[] v, int d)
  {
    super(v, d, "Quick Sort");
  }

  /**
   * This calls the recursive quicksort method to implement quicksort on the values array
   */
  public void runSort()
  {
    int n = bars.length;
    quickSort(0, n-1, n);
  }

  /**
  * This is a recursive implementation of quick sort for sorting the values array.
   * This algorithm selects a pivot point at a random index in our list. It creates a list of elements
   * which are smaller than the pivot and places that list to the left of our pivot, and then it creates
   * a list of elements which are larger than the pivot and places that list to the right of our pivot.
   * The algorithm is recursively called on each sublist until all possible sublists have been sorted.
   * @left The starting index of the elements to the left of our pivot
   * @right The last index of all elements that will be to the right of our pivot
   */
  public void quickSort(int left, int right, int length)
  {
    // A list of one element is already sorted
    if (length <= 1)
    {
      return;
    }

    /** List of unsorted values smaller than the pivot */
    List<Integer> leftList = new ArrayList();

    /** List of unsorted values larger than pivot */
    ArrayList<Integer> rightList = new ArrayList();

    /** Index of our pivot. The pivot is chosen at a random location within our partitioned list */
    int pivot = left + (int)(Math.random() * (right - left + 1));

    colorBar(left, leftColor);
    colorBar(right, rightColor);
    colorBar(pivot, pivotColor);

    while (left < right)
    {
      if(running == false)
      {
        uncolorBar(pivot);
        uncolorBar(left);
        uncolorBar(right);
        return;
      }

      // Left height less than pivot height
      while ( left < pivot && compare(left, pivot) <= 0 )
      {
        leftList.add(left);
        uncolorBar(left);
        left++;
        if (left != pivot)
          colorBar(left, leftColor);

        if(running == false)
        {
          uncolorBar(pivot);
          uncolorBar(left);
          uncolorBar(right);
          return;
        }
      }
      // Right height greater than pivot height
      while ( right > pivot && compare(right, pivot) >= 0)
      {
        System.out.println(" Right"  + right + "pivot" + pivot);
        rightList.add(right);
        uncolorBar(right);
        right--;
        if (right != pivot)
          colorBar(right, rightColor);

        if(running == false)
        {
          uncolorBar(pivot);
          uncolorBar(left);
          uncolorBar(right);
          return;
        }
      }

      // Perform a swap
      if (left != right)
      {
        // Update our pivot index if the pivot is going to be swapped
        if (left == pivot)
        {
          pivot = right;
          colorBar(pivot, pivotColor);
          colorBar(left, leftColor);
        }
        else if (right == pivot)
        {
          pivot = left;
          colorBar(pivot, pivotColor);
          colorBar(right, rightColor);
        }

        swap(left, right);


        if (left != pivot)
        {
          uncolorBar(left);
          leftList.add(left);
          left++;
          if (left != pivot)
            colorBar(left,leftColor);
        }
        else
        {
          colorBar(left, pivotColor);
        }

        if (right != pivot)
        {
          uncolorBar(right);
          rightList.add(right);
          right--;
          if (right != pivot)
            colorBar(right, rightColor);
        }
        else
        {
          colorBar(right, pivotColor);
        }
      }
    }
    uncolorBar(left);
    uncolorBar(right);
    uncolorBar(pivot);

    if (leftList.size() >= 1)
      quickSort(leftList.get(0), leftList.get(leftList.size()-1), leftList.size());
    if (rightList.size() >= 1)
      quickSort(rightList.get(rightList.size()-1), rightList.get(0), rightList.size());
  }
}
