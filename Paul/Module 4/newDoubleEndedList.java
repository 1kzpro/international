import java.util.Iterator;
import java.util.NoSuchElementException;
  
/**
 * @author   Haegang Yang (hzy0052@auburn.edu)
 * @version  2020-03-15
 */
public class newDoubleEndedList<T> implements DoubleEndedList<T> {
   Node first;
   int last;
   Node end;
   
   public newDoubleEndedList() 
   {
      first = null;
      last = 0;
      end = null;
   }

   
   private class Node 
   {
      T element;
      Node after = null;
      Node before = null;
      
      public Node(T e) 
      {
         
         element = e;
      }
         
      public Node(T e, Node a) 
      {
         element = e;
         after = a;
      }
      
      public Node(T e, Node a, Node b) 
      {
         element = e;
         after = a;
         before = b;
      }
      
      public int length(Node n) 
      {
         Node a = n;
         int len = 0;
         while (a != null) 
         {
            len++;
            a = a.after;
         }
         return len;
      }
   
   }
   
      
   public int size() 
   {
      return last;
   }
 
   
   public boolean isEmpty() 
   {
      return last == 0;
   }
   
   
   public Iterator<T> iterator() 
   {
      return new LinkedIterator();
   }

   
   private class LinkedIterator implements Iterator<T> 
   {
      private Node now = first;
      
      public boolean hasNext() 
      {
         return now != null;
      }
      
      public T next() 
      {
         if (!hasNext()) 
         {
            throw new NoSuchElementException();
         }
         
         T result = now.element;
         
         now = now.after;
         
         return result;
      }
      
   }

   
   public void addFirst(T element) 
   {
      if (element == null) 
      {
         throw new IllegalArgumentException();
      }
      
      Node a = new Node(element);
      
      if (first == null || end == null) 
      {
         first = a;
         end = a;
      }
      
      else 
      {
         first.before = a;
         a.after = first;
         first = a;
      }
      last++;
      
   }

   public void addLast(T element) 
   {
      if (element == null) 
      {
         throw new IllegalArgumentException();
      }
      Node a = new Node(element);
      if (first == null || end == null) 
      {
         first = a;
         end = first;
      }
      else 
      {
         a.before = end;
         end.after = a;
         end = a;
      }
      last++;
   }

      
   public T removeFirst() 
   {
      if (isEmpty()) 
      {
         return null;
      }
      T removed = first.element; 
      
      if (last == 1) 
      {
         first = null;
         end = null;
      }
      else 
      {
         first = first.after;
         first.before = null;
      }
      
      last--;
      
      return removed;
   }

   
   public T removeLast() 
   {
      if (isEmpty()) 
      {
         return null;
      }
      T removed = end.element; 
      
      if (last == 1) 
      {
         first = null;
         end = null;
      }
      else 
      {
         end = end.before;
         end.after = null;
      }
      
      last--;
      
      return removed;
   }
   
        
      
   
}