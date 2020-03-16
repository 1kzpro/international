import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;

/**
 * @author   Haegang Yang (hzy0052@auburn.edu)
 * @version  2020-03-15
 */
public class newRandomizedList<T> implements RandomizedList<T> {
   T[] elements;
   int last;
   
 
   @SuppressWarnings("unchecked")
   public newRandomizedList(int s) 
   {
      elements = (T[]) new Object[s];
      last = 0;
   }
	
   public int size() 
   {
      return last;
   }
   
   public boolean isEmpty() 
   {
      return last == 0;
   }


   public void add(T element) 
   {
      if (element == null) 
      {
         throw new IllegalArgumentException();
      }
      if (last == elements.length) 
      {
         resize(elements.length * 2);
      }
      elements[last] = element;
      last++;
   }

  	
   public T sample() 
   {
      if (!isEmpty()) 
      {
         int i = random();
         return elements[i];
      }
      return null;
   }
   
   
   public T remove() 
   {
      if (!isEmpty()) 
      {
         int i = random();
         T removed = elements[i];
         elements[i] = elements[--last];
         return removed;
      }
      return null;
   }

   public void resize(int until) 
   {
      T[] a = (T[]) new Object[until];
      for (int i = 0; i < last; i++) 
      {
         a[i] = elements[i];
      }
      elements = a;
   }
   
   public int random() 
   {
      Random a = new Random();
      if(last == 0 || last == 1) 
      {
         return 0;
      }
      int i = a.nextInt(last - 1);
      return i;
   }


   public Iterator<T> iterator() 
   {
      return new ArrayIterator(elements, last);
   }

  
   public class ArrayIterator<T> implements Iterator<T> 
   {
      int size; 
      T[] items;
      
   
      public ArrayIterator(T[] itemsIn, int sizeIn) 
      {
         items = itemsIn;
         size = sizeIn;
      }
   
               
              
      public boolean hasNext() 
      {
         return (size > 0);
      }
   
      
      public T next() 
      {
         if (!hasNext()) 
         {
            throw new NoSuchElementException();
         }
         
         Random a = new Random();
         int i = a.nextInt(size);
         T next = items[i]; 
         
         if (i != (size - 1)) 
         {
            items[i] = items[size - 1];
            items[size - 1] = next;
         }
         size--;
         return next;
      
      }
      
      public void remove() 
      {
         throw new UnsupportedOperationException();
      }
   
   
   }
}