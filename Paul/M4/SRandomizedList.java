import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * RandomizedList.java. Describes the abstract behavior of a
 * randomized list collection; that is, a list with order defined as "random
 * order." The order described by a radomized list is "random" in the sense
 * that the element accessed by either the sample or remove method is selected
 * uniformly at random from the current elements in the list. In addition, an
 * iterator on a randomized list will sequentially access each element in some
 * uniformly random sequence. Simultaneous iterators on the same randomized
 * list are independent of each other. That is, they will with high probability
 * have different iteration sequences.
 * 
 * @author MingJun Yuan(mzy0030@auburn.edu)
 * @version  03/13/2020
 */
public class SRandomizedList<T> implements RandomizedList<T>{
   private T[] elements;
   private int size;
   private static final int DEFAULT_CAPACITY = 5;
   
   public SRandomizedList() {
      this(DEFAULT_CAPACITY);
   }
   
   @SuppressWarnings("unchecked") 
   public SRandomizedList(int s) { 
      elements = (T[]) new Object[s];
      size = 0;
   }
   
   public int size() {
      return size;
   }
   
   public boolean isEmpty() {
      return size == 0;
   }


   /**
    * Adds the specified element to this list. If the element is null, this
    * method throws an IllegalArgumentException.
    */
   public void add(T element) { 
      if (element == null) {
         throw new IllegalArgumentException();
      }
      
      if (size == elements.length) {
         resize(elements.length * 2);
      }
      elements[size] = element;
      size++;
   }


  
   /**
    * Selects and removes an element selected uniformly at random from the
    * elements currently in the list. If the list is empty this method returns
    * null.
    */
   public T remove() {
      if (this.isEmpty()) {
         return null;
      }
      Random rand = new Random();
      int value = rand.nextInt(size);
      T removed = elements[value]; 
      elements[value] = null;   
      if (value != (size - 1)) {      
         elements[value] = elements[size - 1];
         elements[size - 1] = null;
      }
   
      size --;   
      if (size > 0 && size < elements.length / 4) {
         resize(elements.length / 2);
      }
      return removed;
   }

   
   /**
    * Selects but does not remove an element selected uniformly at random from
    * the elements currently in the list. If the list is empty this method
    * return null.
    */
   public T sample() {
      if (this.isEmpty()) {
         return null;
      }
      Random rand = new Random();
      int value = rand.nextInt(size);
      return elements[value];
   }
   
   public Iterator<T> iterator() {
      return new ArrayIterator(elements, size);
   }
   
   /**
   * resize(), Resizes an array
   */  
   private void resize(int s) {
      T[] a = (T[]) new Object[s];
      for (int i = 0; i < size(); i ++) {
         a[i] = elements[i];
      }
      elements = a;
   }
   
   public class ArrayIterator<T> implements Iterator<T> {
      private T[] items;
      private int length; 
   
      public ArrayIterator(T[] elements, int sizeIn) {
         items = elements;
         length = sizeIn;
      }

      public boolean hasNext() {
         return (length > 0);
      }
      
      /**
      * Remove(), for our purposes throws UnsupportedOperationException (since we
        already have a remove function in the List).
      */
      public void remove() {
         throw new UnsupportedOperationException();
      }
      
      /**
      * Returns the next item in the list, or throws a NoSuchElementException if 
        there are no more elements.
      */
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         Random rand = new Random();
         int value = rand.nextInt(length);
         T next = items[value]; 
         
         if (value != (length - 1)) {
            items[value] = items[length - 1];
            items[length - 1] = next;
         }
         length--;
         return next;
      
      }
   
   }

   
}