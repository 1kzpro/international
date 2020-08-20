class SinglyLinked<T extends Comparable<T>> {

        /** A reference to the first node in the chain. */
        Node front;

        /** Defines a node class. */
        class Node {
            T element;
            Node next;

            public Node(T elmt, Node nxt) {
                element = elmt;
                next = nxt;
            }
        }

        /** Constructs an empty chain. */
        public SinglyLinked() {
            front = null;
        }

        /** Adds a node with the given value to the front of the chain. */
        public void add(T value) {
            front = new Node(value, front);
        }


        /* >>>>>>>>>>>>>>>>>> YOUR WORK GOES BELOW <<<<<<<<<<<<<<<< */


        ///////////////////////////////////////////////////
        // YOU MUST COMPLETE THE BODY OF THE MAX METHOD. //
        ///////////////////////////////////////////////////
        /**
         * Returns the maximum element in the chain. If the chain is empty
         * (front is null), the max method should return null.
         */
        public T max() {
            if(front == null) {
               return null;
            }
        
            Node n = front.next;
            T maxElement = front.element;
            while(n != null) {
                  maxElement = n.element.compareTo(maxElement) > 0 ? n.element : maxElement;
                  n = n.next;
            }
            return maxElement;
        }
}