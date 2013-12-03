package code.sorts;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class is used for running a simulation of radix sort.
 */
public class RadixSort extends Sort
{

  /**
   * Draws the intial graph and sets values for v and d
   * @param values The set of data to be sorted.
   * @param delay The delay (in milliseconds) in between each step of the algorithm
   */
  public RadixSort(int[] values, int delay)
  {
    super(values, delay, "Radix Sort");
  }

  /**
   * This runs the simulation for Insertion sort
   */
  public void runSort()
  {
    Queue q = new Queue();
    Queue p[] = new Queue[10];
    int numb;
    int count = 0;

    for(int i = 0; i < values.length && running; i++)
    {
      colorBar(i, Color.GREEN);
      repaint();
      delay();
      q.enqueue(values[i]);
      count++;
    }

    for(int j = 0; j < 10 && running; j++)
    {
      for(int i = 0; i < 10 && running; i++)
        p[i] = new Queue();
      for(int i = 0; i < count && running; i++)
      {
        numb = (Integer)q.dequeue();
        p[getDigit(numb, j)].enqueue(numb);
      }
      for(int i = 0; i < 10; i++)
      {
        q.append(p[i]);
      }

      //Prepare the screen update
      ArrayList<Integer> objectValues = new ArrayList<Integer>();
      int intValues[] = new int[count];
      queueToArrayList(q, objectValues);
      int temp = objectValues.get(0);
      objectValues.remove(0);
      objectValues.add(temp);
      for(int i = 0; i < objectValues.size(); i++)
      {
        intValues[i] = objectValues.get(i);
      }
      createBars(intValues);
      delay();
      repaint();
    }
  }

  /**
   * Converts a queue to an array where the value
   * in the queue may be another queue.
   * @param q a Queue object to be converted to list
   * @param a an ArrayList to send the queue to.
   */
  private void queueToArrayList(Queue q, ArrayList a)
  {
    Cell nextVal = q.trailer();
    Cell firstVal = nextVal;
    boolean first = true;
    while(first || firstVal != nextVal)
    {
      if(first)
        first = false;
      if(nextVal.getItem() instanceof Integer)
      {
        Integer val = (Integer)nextVal.getItem();
        a.add(val);
      } else if(nextVal.getItem() instanceof Queue)
      {
        queueToArrayList((Queue)nextVal.getItem(), a);
      }
      nextVal = nextVal.getNext();
    }
  }

  /**
  * Gets the digit at a given position in an int
  * @param number the int that the value is pulled from
  * @param place the position in number that is returned
  */
  private int getDigit(int number, int place)
  {
    for(int i = 0; i < place; i++)
      number /= 10;
    return (number % 10);
  }

  /**
  * Cell class used in Queue class
  */
  private class Cell
  {
    /**Holds the item in the cell*/
    Object item;

    /**Holds the next cell*/
    Cell next;

    /**
    * Constructor for Cell class
    * @param itm item the cell holds
    * @param nxt holds the next cell
    */
    public Cell(Object itm, Cell nxt)
    {
      item = itm;
      next = nxt;
    }

    /**
    * Set method for Cell class
    * @param nxt the next cell
    */
    public void setNext(Cell nxt)
    {
      next = nxt;
    }

    /**
    * Accessor method to get the next cell
    */
    public Cell getNext()
    {
      return next;
    }

    /**
    * Accessor method for the item in the cell object
    */
    public Object getItem()
    {
      return item;
    }
  }

  /**
   * A (LIFO) Queue class for the radix sort
   */
  private class Queue
  {
    /**Holds the tail of the queue*/
    Cell tail;

    /**
    * Constructs a blank queue object
    */
    public Queue()
    {
      tail = null;
    }

    /**
    * Pushes an Object on to queue
    * @param obj object to be pushed
    */
    public void enqueue(Object obj)
    {
      if(obj == null)
        return;
      if(tail == null)
      {
        tail = new Cell(obj, null);
        tail.setNext(tail);
      }
      else
      {
        tail.setNext(new Cell(obj, tail.getNext()));
        tail = tail.getNext();
      }
    }

    /**
    * Pops an Object off the queue
    */
    public Object dequeue()
    {
      Object t;
      if(tail == null)
        return null;
      Cell ptr = tail.getNext();
      t = ptr.getItem();
      if(ptr != tail)
        tail.setNext(ptr.getNext());
      else
        tail = null;
      return t;
    }

    /**
    * Appends a queue on to the queue
    * @param q the Queue object to be pushed on to the queue
    */
    public void append(Queue q)
    {
      Cell ptr;
      if(tail == null)
        tail = q.trailer();
      else if(!q.empty())
      {
        ptr = q.trailer().getNext();
        q.trailer().setNext(tail.getNext());
        tail.setNext(ptr);
        tail = q.trailer();
      }
    }

    /**
    * Accessor method for the tail of the queue
    */
    public Cell trailer()
    {
      return tail;
    }

    /**
    * Checks to see if queue is empty
    */
    public boolean empty()
    {
      return tail == null;
    }
  }
}
