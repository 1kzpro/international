import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.util.Iterator;
import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Your Name (you@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2019-03-29
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

   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   private HashSet<String> lexicon;
   private TreeSet<String> wordlist;
   private String[] temp;
   public Doublets(InputStream in) {
      try {
         //////////////////////////////////////
         // INSTANTIATE lexicon OBJECT HERE  //
         //////////////////////////////////////
         lexicon = new HashSet<String>();
         wordlist = new TreeSet<String>();
         Scanner s =
            new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
            /////////////////////////////////////////////////////////////
            // INSERT CODE HERE TO APPROPRIATELY STORE str IN lexicon. //
            /////////////////////////////////////////////////////////////
            str = str.toUpperCase();
            lexicon.add(str);
            wordlist.add(str);
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
   public int getHammingDistance(String str1, String str2) {
      if (str1.length() != str2.length()) {
      
         return -1;
      
      }
   
      str1 = str1.toUpperCase();
   
      str2 = str2.toUpperCase();
   
      //count differences
   
      int a = 0;
   
      for (int n = 0; n < str1.length(); n++) {
      
         if (str1.charAt(n) != str2.charAt(n)) {
         
            a++;
         
         }
      
      }
   
      return a;
   }
   public List<String> getMinLadder(String start, String end) {
      int a = level(start, end);
      List<String> ele = new ArrayList<String>();
      ele.add(end);
      int i = 0;
      while(temp[i] != null && a > 1) {
         String word = temp[i];
         ele.add(word);
         if (isWordLadder(ele) == false) { 
            ele.remove(word);
         }
         else {
            i = -1;
            a--;
         }
         i++;
      }
      Object[] obj = ele.toArray();
      List<String> returnList = new ArrayList<String>();
      for (int p = obj.length - 1; p >= 0; p--) {
         returnList.add(String.valueOf(obj[p]));
      } 
      return returnList;
   }
   public boolean isWord(String str) {
      if (wordlist.contains(str.toUpperCase())) {
         return true;
      }
      else {
         return false;
      }
   }
   public List<String> getNeighbors(String word) {
      // int i = 0;
      // char aword = new char[word.length()];
      // Scanner scan = new Scanner(word);
      // while(scan.hasNext()) {
         // aword[i] = scan.nextChar();
         // i++;
      // }
      List<String> series = new ArrayList<String>();
      char[] chararray = word.toCharArray(); 
      for (int j = 0; j < chararray.length; j++) {
         char ori = chararray[j];
         for (char c = 'a'; c < 'z'; c++) { 
            chararray[j] = c;
            String newword = String.valueOf(chararray);
            if (getHammingDistance(word, newword) == 1 && lexicon.contains(newword.toUpperCase())) {
               series.add(newword);
            }
         }
         chararray[j] = ori;
      }
      return series;
            
   }
   public int getWordCount() {
      return wordlist.size();
   }
   private int level(String str1, String str2) {
      if (str1.length() == str2.length() /** isWord(str1) == true &&*/ /*isWord(str2) == true*/) {
         Deque<String> queue = new LinkedList();
         temp = new String[lexicon.size()];
         queue.offer(str1);
         int level = 1;
         int q = 0;
         while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
               String curr = queue.poll();
               temp[q] = curr;
               q++;
               char[] chararray = curr.toCharArray();
               for (int j = 0; j < chararray.length; j++) {
                  char orginal = chararray[j];
                  for (char c = 'a'; c < 'z'; c++) {
                     if (chararray[j] == c) 
                        continue;
                     chararray[j] = c;
                     String newword = String.valueOf(chararray);
                     if (newword.equals(str2)) {
                        return level + 1;
                     }
                     if (lexicon.contains(newword.toUpperCase())) {
                        queue.offer(newword);
                        lexicon.remove(newword.toUpperCase());
                     }
                  }
                  chararray[j] = orginal;
               }
            }
            level++;
         }
         return -1;
      }
      else {
         return -1;
      }
   }
   public boolean isWordLadder(List<String> element) {
      String a;
   
      String b;
   
      if (element.isEmpty()) {
      
         return false;
      
      }
   
      for (int c = 0; c < element.size()-1; c++) {
      
         a = element.get(c);
      
         b = element.get(c+1);
      
         if (isWord(a) == false && isWord(b) == false) {
          
            return false;
          
         } 
      
         if (getHammingDistance(a, b) != 1) {
         
            return false;
         
         }
      
      }
   
      return true;
   }
}

