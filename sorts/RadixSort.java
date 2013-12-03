package code.sorts;

import java.awt.*;
import java.util.ArrayList;

/**
 * class: RadixSort
 * desc:  This class is used for running a simulation of radix sort.
 */
public class RadixSort extends Sort
{

  /**
   * Draws the intial graph and sets values for v and d
   * @param values: The set of data to be sorted.
   * @param delay: The delay (in milliseconds) in between each step of the algorithm
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


    System.out.println("Starting radix!!!!!!!!!!!!!!!\n");

    for(int i: values)
    {
      q.enqueue(new Integer(i));
      System.out.println(": " + i);
      count++;
    }

    for(int j = 0; j < 10; j++)
    {
      if(running == false)
      {
        return;
      }
      System.out.println("~");
      for(int i = 0; i < 10; i++)
        p[i] = new Queue();
      for(int i = 0; i < count; i++)
      {
        numb = (Integer)q.dequeue();
        System.out.println(numb);
        p[getDigit(numb, j)].enqueue(numb);
      }
      for(int i = 0; i < 10; i++)
      {
        System.out.println("_");
        q.append(p[i]);
      }
      System.out.println("preparing update");

      //Prepare the screen update
      ArrayList<Integer> objectValues = new ArrayList<Integer>();
      int intValues[] = new int[count];
      System.out.println("starting qtal");
      queueToArrayList(q, objectValues);
      System.out.println("Finished qtal");
      // for(int i = 0; i < count; i++)
      for(int i = 0; i < objectValues.size(); i++)
      {
        intValues[i] = (int)objectValues.get(i);
      }
      System.out.println("finished list to array");
      createBars(intValues);
      System.out.println("Bars created");
      delay();
      System.out.println("delayed");
      repaint();
    }
    System.out.println("Finished");

    // Integer n;
    // while((n = q.dequeue()) != null)
    // {
    //   System.out.println("~" + n);
    // }
  }

  /**
   * Converts a queue to an array where the value
   * in the queue may be another queue
   */
  private void queueToArrayList(Queue q, ArrayList a)
  {
    Cell nextVal = q.trailer();
    Cell firstVal = nextVal;
    boolean first = true;
    while(first == true || firstVal != nextVal)
    {
      if(first)
        first = false;
      System.out.println(".");
      if(nextVal.getItem() instanceof Integer)
      {
        Integer val = new Integer((Integer)nextVal.getItem());
        System.out.println("int found " + val);
        a.add(val);
      } else if(nextVal.getItem() instanceof Queue)
      {
        System.out.println("queue found");
        queueToArrayList((Queue)nextVal.getItem(), a);
      } else
      {
        System.out.println("Error in queueToArrayList!");
      }
      nextVal = nextVal.getNext();
    }
  }

  private int getDigit(int number, int place)
  {
    for(int i = 0; i < place; i++)
      number /= 10;
    return (number % 10);
  }

  private class Cell
  {
    Object item;
    Cell next;

    public Cell(Object itm, Cell nxt)
    {
      item = itm;
      next = nxt;
    }

    public void setNext(Cell nxt)
    {
      next = nxt;
    }

    public Cell getNext()
    {
      return next;
    }

    public Object getItem()
    {
      return item;
    }
  }

  private class Queue
  {
    Cell tail;

    public Queue()
    {
      tail = null;
    }

    // public Queue(Queue q)////////NOT SURE ABOUT THIS
    // {
    //   Queue q2 = new Queue();
    //   Object qCell = q.dequeue();/////USE GETTAIL AND GO FROM THERE!!!!!!!!!
    //   while(qCell != null)
    //   {
    //     q2.enqueue(qCell);
    //     enqueue(qCell);//////////////THIS IS PROBABLY WRONG!!! PROBABLY BUILDING IT BACKWARDS. MAYBE SHOULD JUST CONSTRUCT IT FROM CELLS
    //     qCell = q.dequeue();
    //   }
    //   //Fix q
    //   qCell = q2.dequeue();
    //   while(qCell != null)
    //   {
    //     q.enqueue(q2.dequeue());
    //     qCell = q2.dequeue();
    //   }
    // }

    public void enqueue(Object obj)
    {
      Cell h;
      if(obj == null)
        return;
      if(tail == null)
      {
        tail = new Cell(obj, null);
        tail.setNext(tail);/////////////////////
        // tail.setNext(null);
      }
      else
      {
        tail.setNext(new Cell(obj, tail.getNext()));
        tail = tail.getNext();
      }
    }

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


      // Object t;
      // if(tail == null)
      //   return null;
      // Cell ptr = tail.getNext();
      // if(ptr != null)
      // {
      //   t = ptr.getItem();
      //   if(ptr != tail)
      //     tail.setNext(ptr.getNext());
      //   else
      //     tail = null;
      //   return t;
      // }
      // else
      // {
      //   return tail.getItem();
      // }
    }

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

    public Cell trailer()
    {
      return tail;
    }

    public boolean empty()
    {
      return tail == null;
    }
  }
}
