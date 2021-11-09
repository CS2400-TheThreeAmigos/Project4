import java.util.Arrays;
/**
   A class that implements the ADT maxheap by using an array.
 
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public final class MaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T>
{
   public int[] heap;                   // Array of heap entries; ignore heap[0]
   private int lastIndex;               // Index of last entry and number of entries
   private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
   public int swapsForSM = 0;
   public int swapsForOM = 0;

   
   public MaxHeap()
   {
      this(DEFAULT_CAPACITY); // Call next constructor
   } // end default constructor
   
   public MaxHeap(int initialCapacity)
   {
      // Is initialCapacity too small?
      if (initialCapacity < DEFAULT_CAPACITY)
         initialCapacity = DEFAULT_CAPACITY;
      //else            // Is initialCapacity too big?
         //checkCapacity(initialCapacity);
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      int[] tempHeap = (int[])new Comparable[initialCapacity + 1];
      heap = tempHeap;
      lastIndex = 0;
      integrityOK = true;
   } // end constructor


   public void sequentialInsertions(int[] items)
   {
      this.add(null);
      for(int i : items)
      {
         this.add(i);
      }
   }

   public void optimalMethod(int[] items)
   {
      for(int i=1; i<=items.length; i++ )
      {
         heap[i] = items[i];
      }
      this.reheap(1);
   }

   public void add(int newEntry)
   {
      //checkIntegrity();        // Ensure initialization of data fields
      int newIndex = lastIndex + 1;
      int parentIndex = newIndex / 2;
      while ( (parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0)
      {
         heap[newIndex] = heap[parentIndex];
         newIndex = parentIndex;
         parentIndex = newIndex / 2;
         swapsForSM++;
      } // end while

      heap[newIndex] = newEntry;
      lastIndex++;
      //ensureCapacity();
   } // end add

   public int removeMax()
   {
      //checkIntegrity();             // Ensure initialization of data fields
      int root = null;
   
      if (!isEmpty())
      {
         root = heap[1];              // Return value
         heap[1] = heap[lastIndex];   // Form a semiheap
         lastIndex--;                 // Decrease size
         reheap(1);                   // Transform to a heap
      } // end if
   
      return root;
   } // end removeMax

   public int getMax()
   {
		//checkIntegrity();
      int root = null;
      if (!isEmpty())
         root = heap[1];
      return root;
   } // end getMax

   public boolean isEmpty()
   {
      return lastIndex < 1;
   } // end isEmpty

   public int getSize()
   {
      return lastIndex;
   } // end getSize

   public void clear()
   {
		//checkIntegrity();
      while (lastIndex > -1)
      {
         heap[lastIndex] = null;
         lastIndex--;
      } // end while
      lastIndex = 0;
   } // end clear
   
// Private methods
   private void reheap(int rootIndex)
   {
      boolean done = false;
      int orphan = heap[rootIndex];
      int leftChildIndex = 2 * rootIndex;

      while (!done && (leftChildIndex <= lastIndex) )
      {
         int largerChildIndex = leftChildIndex; // Assume larger
         int rightChildIndex = leftChildIndex + 1;

         if ( (rightChildIndex <= lastIndex) &&
               heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
         {
            largerChildIndex = rightChildIndex;
            //swapsForOM++;
         } // end if

         if (orphan.compareTo(heap[largerChildIndex]) < 0)
         {
            heap[rootIndex] = heap[largerChildIndex];
            rootIndex = largerChildIndex;
            leftChildIndex = 2 * rootIndex;
            swapsForOM++;
         }
         else
            done = true;
      } // end while

      heap[rootIndex] = orphan;
   } // end reheap

} // end MaxHeap
