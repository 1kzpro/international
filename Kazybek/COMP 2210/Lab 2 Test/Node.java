public class Node {
      private Object element;
      private Node next;

      public Node(Object e) {
         element = e;
      }

      public Node(Object e, Node n) {
         element = e;
         next = n;
      }
}