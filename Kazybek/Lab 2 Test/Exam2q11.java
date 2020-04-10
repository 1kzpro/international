public class Exam2q11 {
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
   
   public static void main(String[] args) {
      Exam2q11 client = new Exam2q11();
      client.example();
   }
   
   public void example() {
      Node n = null;
      for(int i = 0; i < 5; i++) {
         n = new Node(i, n);
      }
      Node m = n.next.next;
      n = null;
   }
}