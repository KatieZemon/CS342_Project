package code.sorts;

/**
 * @author Thomas Clay
 * Date and Time: 11/27/13 11:26 PM
 */
public class MergeSort extends Sort {

  MergeSort(int[] values, int delay){
    super(values, delay, "Merge Sort");
  }

  private void sort(){
    // Make successively longer sorted runs of length 2, 4, 8, 16... until whole array is sorted.
    for (int width = 1; width < values.length; width = 2 * width)
    {
      // Array A is full of runs of length width.
      for (int i = 0; i < values.length; i = i + 2 * width)
      {
          /* Merge two runs: A[i:i+width-1] and A[i+width:i+2*width-1] to B[]
           * or copy A[i:n-1] to B[] ( if(i+width >= n) ) */
//        merge(A, i, min(i+width, n), min(i+2*width, n), B);
      }

      /* Now work array B is full of runs of length 2*width. */
      /* Copy array B to array A for next iteration. */
      /* A more efficient implementation would swap the roles of A and B */
//      CopyArray(A, B, n);
      /* Now array A is full of runs of length 2*width. */
    }
  }

  private void merge(int A[], int iLeft, int iRight, int iEnd, int B[])
  {
    int i0 = iLeft;
    int i1 = iRight;
    int j;

  /* While there are elements in the left or right lists */
    for (j = iLeft; j < iEnd; j++)
    {
      /* If left list head exists and is <= existing right list head */
      if (i0 < iRight && (i1 >= iEnd || A[i0] <= A[i1]))
      {
        B[j] = A[i0];
        i0 = i0 + 1;
      }
      else
      {
        B[j] = A[i1];
        i1 = i1 + 1;
      }
    }
  }

  protected void runSort() {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
