import java.util.Random;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Part1.java
 *
 * @author Kazybek Mizam (kzm0099@auburn.edu)
 * @version 03/05/2020
 */
public class Part1<T> implements RandomizedList<T> {
   /**Fields**/
   
   /** stores elements of list. */
   T[] elements;

	/** index of next insertion point; number of elements in list. */
   int rear;
   
  /** Constructs an instance of this list. */
   @SuppressWarnings("unchecked")
   public Part1(int capacity) {
      elements = (T[]) new Object[capacity];
      rear = 0;
   }

	/** Removes the specified element from this list. */
   T remove(T element) {
      return null;
   }

	/** Returns true if this list has no elements, false otherwise. */
   public boolean isEmpty() {
      return rear == 0;
   }

	/** Returns the number of elements in this list. */
   public int size() {
      return rear;
   }

	/** Returns an iterator over the elements in this list. */
   public Iterator<T> iterator() {
     
      int[] seed = randomSeed();
      
      Iterator<T> it = 
         new Iterator<T>() {
         
            private int currentIndex = 0;
         
            @Override
            public boolean hasNext() {
               if (seed.length == 0 || seed.length <= currentIndex) {
                  return false;
               }
               return currentIndex < size() && elements[seed[currentIndex]] != null;
            }
         
            @Override
            public T next() {
               if (seed.length == 0 || seed.length <= currentIndex || 
                     elements[seed[currentIndex]] == null) {
                  throw new NoSuchElementException();
               }
               return elements[seed[currentIndex++]];
            }
         
            @Override
            public void remove() {
               throw new UnsupportedOperationException();
            }
         };
      return it;
   }

	/** Adds the given element at random place in list. */
   public void add(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }
      if (isFull()) {
         resize(elements.length * 2);
      }
      elements[rear++] = element;
   }

	/** Returns the random element from the list. */
   public T sample() {
      if (!isEmpty()) {
         int index = random();
         return elements[index];
      }
      return null;
   }


	/** Removes the random element from the list. */
   public T remove() {
      if (!isEmpty()) {
         int index = random();
         T removed = elements[index];
         elements[index] = elements[--rear];
         return removed;
      }
      return null;
   }

	/** Returns true if the array is full, false otherwise. */
   private boolean isFull() {
      return rear == elements.length;
   }

	/** Resizes the array to the specified size, copying corresonding elements. */
   @SuppressWarnings("unchecked")
   void resize(int capacity) {
      assert (capacity >= rear);
      T[] newArray = (T[]) new Object[capacity];
      for (int i = 0; i < rear; i++) {
         newArray[i] = elements[i];
      }
      elements = newArray;
   }

	/** Moves elements from index..rear-1 one index to the right. */
   private void shiftRight(int index) {
      assert !isFull();
      for(int i = index; i < rear; i++) {
         T element = elements[i + 1];
         elements[i + 1] = elements[index];
         elements[index] = element;
      }
   }
   
   /** Generate random integers in range 0 to rear. */
   public int random() {
      Random rand = new Random();
      if(rear == 0 || rear == 1) {
         return 0;
      }
      int index = rand.nextInt(rear - 1);
      return index;
   }
   
   /** Generate random array *seed* for iterator. */
   private int[] randomSeed() {
      int[] seed = new int[rear];
      for(int i = 0; i < rear; i++) {
         seed[i] = i;
      }
      
      for(int i = 0; i < (int)rear / 2; i++) {
         int element = seed[i];
         int index = random();
         seed[i] = seed[index];
         seed[index] = element;
      }
      return seed;
   }
}