import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.SortedSet;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Kazybek Mizam (you@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2019-03-29 DH
 * @version 2020-04-10 KM
 */
public class Doublets implements WordLadderGame {

    // The word list used to validate words.
    // Must be instantiated and populated in the constructor.
    /////////////////////////////////////////////////////////////////////////////
    // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
    // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS     //
    // PURPOSE OF YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES    //
    // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash      //
    // table with chaining).
    /////////////////////////////////////////////////////////////////////////////
   TreeSet<String> lexicon;

    /**
     * Instantiates a new instance of Doublets with the lexicon populated with
     * the strings in the provided InputStream. The InputStream can be formatted
     * in different ways as long as the first string on each line is a word to be
     * stored in the lexicon.
     */
   public Doublets(InputStream in) {
      try {
            //////////////////////////////////////
            // INSTANTIATE lexicon OBJECT HERE  //
            //////////////////////////////////////
         lexicon = new TreeSet<String>();
         Scanner s =
                new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
                /////////////////////////////////////////////////////////////
                // INSERT CODE HERE TO APPROPRIATELY STORE str IN lexicon. //
                /////////////////////////////////////////////////////////////
            lexicon.add(str.toUpperCase());
            s.nextLine();
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }


    //////////////////////////////////////////////////////////////
    // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
    //////////////////////////////////////////////////////////////
   /**
     * Returns the Hamming distance between two strings, str1 and str2. The
     * Hamming distance between two strings of equal length is defined as the
     * number of positions at which the corresponding symbols are different. The
     * Hamming distance is undefined if the strings have different length, and
     * this method returns -1 in that case. See the following link for
     * reference: https://en.wikipedia.org/wiki/Hamming_distance
     *
     * @param  str1 the first string
     * @param  str2 the second string
     * @return      the Hamming distance between str1 and str2 if they are the
     *                  same length, -1 otherwise
     */
   public int getHammingDistance(String str1, String str2) {
      if (str1.length() != str2.length()) {
         return -1;
      }
      
      int dist_counter = 0;
      for(int i = 0; i < str1.length(); i++) {
         if (str1.charAt(i) != str2.charAt(i)) {
            dist_counter += 1;
         }
      }
      return dist_counter;
   }


   /**
    * Returns a minimum-length word ladder from start to end. If multiple
    * minimum-length word ladders exist, no guarantee is made regarding which
    * one is returned. If no word ladder exists, this method returns an empty
    * list.
    *
    * Breadth-first search must be used in all implementing classes.
    *
    * @param  start  the starting word
    * @param  end    the ending word
    * @return        a minimum length word ladder from start to end
    */
   public List<String> getMinLadder(String start, String end) {
      List<String> ladder = new ArrayList<String>();
      
      if (!(isWord(start) && isWord(end)) ||
            getHammingDistance(start, end) == -1) {
         return ladder;
      }
      
      TreeSet<String> visited = new TreeSet<String>();
      Deque<Node> queue = new ArrayDeque<>();
      queue.addLast(new Node(start, null));
      
      visited.add(start);
      
      while (!queue.isEmpty()) {
         Node n = queue.removeFirst();
         String word = n.word;
         
         if (word.equalsIgnoreCase(end)) {
            Node p = n;
            while(p != null) {
               ladder.add(0, p.word);
               p = p.predecessor;
            }
            return ladder;
         }
         
         for (String neighbor : getNeighbors(word)) {
            if (!(visited.contains(neighbor))) {
               visited.add(neighbor);
               queue.addLast(new Node(neighbor, n));
            }
         }
      }
      
      return ladder;
   }
    
    /**
     * Constructs a node for linking positions together.
     */
   private class Node {
      String word;
      Node predecessor;
   
      public Node(String p, Node pred) {
         word = p;
         predecessor = pred;
      }
   }


    /**
     * Returns all the words that have a Hamming distance of one relative to the
     * given word.
     *
     * @param  word the given word
     * @return      the neighbors of the given word
     */
   public List<String> getNeighbors(String word) {
      List<String> neighbors = new ArrayList<String>();
      
      if (word.isEmpty()) {
         return neighbors;
      }
   
      for(int i = 0; i < word.length(); i++) {
         for(char letter = 'a'; letter <= 'z'; letter++) {
            char[] wordArray = word.toCharArray();
            wordArray[i] = letter;
            String neighbor = new String(wordArray);
            if (word.charAt(i) != letter && isWord(neighbor)) {
               neighbors.add(neighbor);
            }
         }
      }
      
      return neighbors;
   }


    /**
     * Returns the total number of words in the current lexicon.
     *
     * @return number of words in the lexicon
     */
   public int getWordCount() {
      return lexicon.size();
   }


    /**
     * Checks to see if the given string is a word.
     *
     * @param  str the string to check
     * @return     true if str is a word, false otherwise
     */
   public boolean isWord(String str) {
      return lexicon.contains(str.toUpperCase()) ? true : false;
   }


    /**
     * Checks to see if the given sequence of strings is a valid word ladder.
     *
     * @param  sequence the given sequence of strings
     * @return          true if the given sequence is a valid word ladder,
     *                       false otherwise
     */
   public boolean isWordLadder(List<String> sequence) {
      if (sequence.isEmpty()) {
         return false;
      }
      
      if (sequence.size() == 1) {
         if (isWord(sequence.get(0))) {
            return true;
         }
         return false;
      }
      
      for (int i = 1; i < sequence.size(); i++) {
         String startWord = sequence.get(i - 1);
         String endWord = sequence.get(i);
         if (!(isWord(startWord) && isWord(endWord))) {
            return false;
         }
         if (getHammingDistance(startWord, endWord) != 1) {
            return false;
         }
      }
      return true;
   }
}

