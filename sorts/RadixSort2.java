package code.sorts;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class is used for running a simulation of radix sort.
 */
public class RadixSort2 extends Sort
{

  /**
   * Draws the intial graph and sets values for v and d
   * @param values: The set of data to be sorted.
   * @param delay: The delay (in milliseconds) in between each step of the algorithm
   */
  public RadixSort2(int[] values, int delay)
  {
    super(values, delay, "Radix Sort");
  }

  private Color getColorByDigit(int digit){
    int r = digit * digit * digit * digit;
    int g = digit * (digit + 1)* (digit + 2);
    int b = digit * 2 * digit * 3;
    return new Color(r%255, g%255, b%255);
  }


  /**
   * This runs the simulation for Insertion sort
   */
  public void runSort()
  {
    Queue q = new Queue();
    Queue p[] = new Queue[10];
    int index;
    int count = 0;

    for(int i = 0; i < values.length && running; i++)
    {
      colorBar(i, Color.GREEN);
      repaint();
      delay();
      q.enqueue(i);
      count++;
    }

    for(int j = 0; j < 10 && running; j++)
    {
      for(int i = 0; i < 10 && running; i++)
        p[i] = new Queue();
      for(int i = 0; i < count && running; i++)
      {
        index = (Integer)q.dequeue();
        int digit = getDigit(values[index], j);
        colorBar(index, getColorByDigit(digit));
        p[digit].enqueue(index);
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
   * @param q: a Queue object to be converted to list
   * @param a: an ArrayList to send the queue to.
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
      if(nextVal.getData() instanceof Integer)
      {
        Integer val = (Integer)nextVal.getData();
        a.add(val);
      } else if(nextVal.getData() instanceof Queue)
      {
        queueToArrayList((Queue)nextVal.getData(), a);
      }
      nextVal = nextVal.getNext();
    }
  }

  /**
  * Gets the digit at a given position in an int
  * @param number: the int that the value is pulled from
  * @param place: the position in number that is returned
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
    Object data;

    /**Holds the next cell*/
    Cell next;

    /**
    * Constructor for Cell class
    * @param data the cell holds
    * @param next holds the next cell
    */
    public Cell(Object data, Cell next)
    {
      this.data = data;
      this.next = next;
    }

    /**
    * Set method for Cell class
    * @param next: the next cell
    */
    public void setNext(Cell next)
    {
      this.next = next;
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
    public Object getData()
    {
      return data;
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
    * @param index of bar to pushed
    */
    public void enqueue(int index)
    {
      if(tail == null)
      {
        tail = new Cell(index, null);
        tail.setNext(tail);
      }
      else
      {
        tail.setNext(new Cell(index, tail.getNext()));
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
      t = ptr.getData();
      if(ptr != tail)
        tail.setNext(ptr.getNext());
      else
        tail = null;
      return t;
    }

    /**
    * Appends a queue on to the queue
    * @param q: the Queue object to be pushed on to the queue
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
