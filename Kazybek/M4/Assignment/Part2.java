import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Part1.java
 *
 * @author Kazybek Mizam (kzm0099@auburn.edu)
 * @version 03/05/2020
 */
public class Part2<T> implements DoubleEndedList<T> {

   /** Field */
   private Node<T> front;
   private Node<T> rear;
   private int size;
   
   /**
    * Defines a doubly-linked node.
    * Type Object is used instead of a generic type to make
    * this example cleaner and easier to read. If this class
    * were to be used in the context of implementing a collection,
    * generic types should be used.
    */
   private class Node<T> {
      private T element;
      private Node<T> next;
      private Node<T> prev;
      
      public Node(T e) {
         element = e;
      }
   }
   
   /**
   private Node<T> locate(T element) { 
      Node<T> n = front;
      while (n != null) {
         if (n.element.equals(element)) 
            return n;
         n = n.next; }
      return null;
   }
    
   public boolean contains(T element) {
      return locate(element) != null;
   }*/
   
   /**
    * Returns the number of elements in this list.
    */
   public int size() {
      return size;
   }
 
   /**
    * Returns true if this list contains no elements, false otherwise.
    */
   public boolean isEmpty(){
      return size == 0;
   }
   
   /**
    * Creates and returns an iterator over the elements of this list.
    */
   public Iterator<T> iterator(){
      Iterator<T> it = 
         new Iterator<T>() {
            Node<T> current = front;
            public boolean hasNext() 
            {
               return current.next != null;
            }
            public void remove() { 
               throw new UnsupportedOperationException();
            }
            public T next() { 
               if (current == null) {
                  throw new NoSuchElementException();
               }
               T result = current.element; 
               current = current.next; 
               return result;
            }
         };
      return it;
   }
   
   /**
    * Adds element to the front of the list. If element is null,
    * this method throws an IllegalArgumentException.
    */
   public void addFirst(T element){
      if(element == null) {
         throw new IllegalArgumentException();
      }
      Node<T> n = new Node<T>(element);
      if (front == null || rear == null) {
         front = n;
         rear = n;
      }
      else {
         n.next = front;
         front = n;
      }
      size++;
   }
   
   /**
    * Adds element to the end of the list. If element is null,
    * this method throws an IllegalArgumentException.
    */
   public void addLast(T element){
      if(element == null) {
         throw new IllegalArgumentException();
      }
      Node<T> n = new Node<T>(element);
      if (front == null || rear == null) {
         front = n;
         rear = n;
      }
      else {
         n.prev = rear; 
         rear = n;
      }
      size++;
   }
      
   /**
    * Delete and return the element at the front of the list.
    * If the list is empty, this method returns null.
    */
   public T removeFirst() {
      if(isEmpty()) {
         return null;
      }
      T removed = front.element;
      if(front.next != null) {
         front = front.next;
      }
      front.prev = null;
      size--;
      return removed;
   }
   
   /**
    * Delete and return the element at the end of the list.
    * If the list is empty, this method returns null.
    */
   public T removeLast(){
      if(isEmpty()) {
         return null;
      }
      T removed = rear.element;
      if(rear.prev != null) {
         rear = rear.prev;
      }
      rear.next = null;
      size--;
      return removed;
   }
}