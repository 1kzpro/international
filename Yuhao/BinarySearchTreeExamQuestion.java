import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Exam question on binary search trees.
 *
 * @author YOUR NAME (YOU@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2020-04-21
 */
public class BinarySearchTreeExamQuestion {

   /*

   >>>>>>>>>>>>>>>>>> INSTRUCTIONS <<<<<<<<<<<<<<<<

   1.  Read through this entire class to make sure you understand the context.
       A class named BinarySearchTree is provided for you below. It has an add
       method for adding new values to the tree, as well as other utility methods.
       These methods must not be changed. It also provides a max method that
       you must complete. Note that linked nodes are used to implement the
       BinarySearchTree class. Note also that the data type allowed in the
       BinarySearchTree is constrained to be a class that implements the Comparable
       interface and thus has a natural total order defined.

   2.  A sample main method is provided to illustrate the intended effect of
       building a simple binary search tree and then finding the largest value
       using the max method.

   3.  Once you have read through all the provided source code, your task is
       to provide a correct implementation of the max method, which appears as
       the first method of the BinarySearchTree class, just after the fields
       are defined.

   4.  Your grade will be determined by the percentage of test cases that your
       code passes.

    */


   /** Provides an example. */
   public static void main(String[] args) {
      BinarySearchTree<Integer> iBst = new BinarySearchTree<>();
      iBst.add(10);
      iBst.add(12);
      iBst.add(8);
      iBst.add(2);
      iBst.add(6);
      iBst.add(4);
      Integer largestInteger = iBst.max();
      // The following statement should print 12.
      System.out.println(largestInteger);
   
      BinarySearchTree<String> sBst = new BinarySearchTree<>();
      sBst.add("W");
      sBst.add("A");
      sBst.add("R");
      sBst.add("E");
      sBst.add("A");
      sBst.add("G");
      sBst.add("L");
      sBst.add("E");
      String largestString = sBst.max();
      // The following statement should print W.
      System.out.println(largestString);
   }



   /** Defines a binary search tree. */
   static class BinarySearchTree<T extends Comparable<T>> implements Iterable<T> {
   
      // the root of this binary search tree
      private Node root;
   
      // the number of nodes in this binary search tree
      private int size;
   
      /** Defines the node structure for this binary search tree. */
      private class Node {
         T element;
         Node left;
         Node right;
      
         /** Constructs a node containing the given element. */
         public Node(T elem) {
            element = elem;
         }
      }
   
   
      /* >>>>>>>>>>>>>>>>>> YOUR WORK STARTS HERE <<<<<<<<<<<<<<<< */
   
   
      //////////////////////////////////////////////////////////////////
      // I M P L E M E N T   T H E   M A X   M E T H O D   B E L O W  //
      //////////////////////////////////////////////////////////////////
   
      /**
       * Returns the largest element in this binary search tree. If this
       * tree is empty, this method returns null.
       */
      public T max() {
         if (isEmpty()) {
            return null;
         }
         
         Node t = root;
         T maxElement = root.element;
         
         while(t != null) {
            if (t.element.compareTo(maxElement) > 0) {
               maxElement = t.element;
            }
            t = t.right;
         }
         
         return maxElement;        
      }
   
   
      /* >>>>>>>>>>>>>>>>>> YOUR WORK ENDS HERE <<<<<<<<<<<<<<<< */
   
   
   
      ////////////////////////////////////////////////////////////////////
      //  D O   N O T   M O D I F Y   B E L O W   T H I S   P O I N T   //
      ////////////////////////////////////////////////////////////////////
   
   
   
      ////////////////////
      // M E T R I C S  //
      ////////////////////
   
      /**
       * Returns the number of elements in this bst.
       */
      public int size() {
         return size;
      }
   
      /**
       * Returns true if this bst is empty, false otherwise.
       */
      public boolean isEmpty() {
         return size == 0;
      }
   
      /**
       * Returns the height of this bst.
       */
      public int height() {
         return height(root);
      }
   
      /**
       * Returns the height of node n in this bst.
       */
      private int height(Node n) {
         if (n == null) {
            return 0;
         }
         int leftHeight = height(n.left);
         int rightHeight = height(n.right);
         return 1 + Math.max(leftHeight, rightHeight);
      }
   
   
      ////////////////////////////////////
      // A D D I N G   E L E M E N T S  //
      ////////////////////////////////////
   
      /**
       * Ensures this bst contains the specified element. Uses an iterative implementation.
       */
      public void add(T element) {
         // special case if empty
         if (root == null) {
            root = new Node(element);
            size++;
            return;
         }
      
         // find where this element should be in the tree
         Node n = root;
         Node parent = null;
         int cmp = 0;
         while (n != null) {
            parent = n;
            cmp = element.compareTo(parent.element);
            if (cmp == 0) {
               // don't add a duplicate
               return;
            } else if (cmp < 0) {
               n = n.left;
            } else {
               n = n.right;
            }
         }
      
         // add element to the appropriate empty subtree of parent
         if (cmp < 0) {
            parent.left = new Node(element);
         } else {
            parent.right = new Node(element);
         }
         size++;
      }
   
      /**
       * Ensures this bst contains the specified element. Calls a recursive method.
       */
      public void put(T element) {
         root = put(element, root);
      }
   
      /**
       * Ensures this bst contains the specified element. Uses a recursive implementation.
       */
      private Node put(T element, Node n) {
         if (n == null) {
            size++;
            return new Node(element);
         }
         int cmp = element.compareTo(n.element);
         if (cmp < 0) {
            n.left = put(element, n.left);
         } else if (cmp > 0) {
            n.right = put(element, n.right);
         }
         return n;
      }
   
   
      //////////////////////
      // T O S T R I N G  //
      //////////////////////
   
      /**
       * Returns a string representation of the elements in this bst listed in
       * ascending natural order.
       */
      @Override
      public String toString() {
         return inorderList(root).toString();
      }
   
      /**
       * Returns a List containing the elements of this bst in ascending natural order.
       */
      private List<T> inorderList(Node n) {
         List<T> list = new ArrayList<>();
         buildInorderList(root, list);
         return list;
      }
   
      /**
       * Builds list from the elements of this bst in ascending natural order.
       */
      private void buildInorderList(Node n, List<T> list) {
         if (n != null) {
            buildInorderList(n.left, list);
            list.add(n.element);
            buildInorderList(n.right, list);
         }
      }
   
   
      ////////////////////////
      // I T E R A T I O N  //
      ////////////////////////
   
      /**
       * Provides an iterator over the elements in this bst. Elements will be
       * returned in ascending natural order.
       */
      @Override
      public Iterator<T> iterator() {
         return inorderList(root).iterator();
      }
   
   }

}
