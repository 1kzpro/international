import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
/**
 * MarkovModel.java Creates an order K Markov model of the supplied source
 * text. The value of K determines the size of the "kgrams" used to generate
 * the model. A kgram is a sequence of k consecutive characters in the source
 * text.
 *
 * @author     Your Name (you@auburn.edu)
 * @author     Dean Hendrix (dh@auburn.edu)
 * @version    2018-04-17
 *
 */
public class MarkovModel {

   // Map of <kgram, chars following> pairs that stores the Markov model.
   private HashMap<String, String> model;
   private TreeSet<String> temp;
   private char[][] d2;
   private HashMap<String, Integer> frequent;
   private int pjx;
   // add other fields as you need them ...


   /**
    * Reads the contents of the file sourceText into a string, then calls
    * buildModel to construct the order K model.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, File sourceText) {
      model = new HashMap<>();
      try {
         String text = new Scanner(sourceText).useDelimiter("\\Z").next();
         buildModel(K, text);
      }
      catch (IOException e) {
         System.out.println("Error loading source text: " + e);
      }
   }


   /**
    * Calls buildModel to construct the order K model of the string sourceText.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, String sourceText) {
      model = new HashMap<>();
      buildModel(K, sourceText);
   }


   /**
    * Builds an order K Markov model of the string sourceText.
    */
   private void buildModel(int K, String sourceText) {
      temp = new TreeSet<String>();
      char[] chararray = sourceText.toCharArray();
      int a = 0;
      int b = 0;
      String kgram = "";
      for (int j = 0; j < chararray.length; j++) {
         kgram += chararray[j];
         if (kgram.length() == K) {
            if (temp.contains(kgram)) {
               kgram = "";
               a++;
               j = a - 1;
               continue;
            }
            temp.add(kgram);
            b++;
            kgram = "";
            a++;
            j = a - 1;
         }
      }
      pjx = sourceText.length();
      Iterator ite = temp.iterator();
      d2 = new char[pjx][pjx];
      int row = 0;
      while(ite.hasNext()){ 
         int num2 = 0;
         int num3 = 0; 
         int column = 0;
         String kex = (String)ite.next();
         String cons = "";
         for (int num = 0; num < chararray.length; num++) {
            cons += chararray[num];
            num2 = num;
            if (cons.length() == K && num2 + 1< chararray.length) {
               if (cons.equals(kex)) {
                  d2[row][column] = chararray[num2 + 1];
                  column++;
               }
               cons = "";
               num3++;
               num = num3 - 1;
            
            }
            continue;
         }
         row++;
      }
   }


   /** Returns the first kgram found in the source text. */
   public String getFirstKgram() {
      String key = "";
      Iterator ite = temp.iterator();
      while(ite.hasNext()){ 
         key = (String)ite.next(); 
         break;
      }
      return key;
   }


   /** Returns a kgram chosen at random from the source text. */
   public String getRandomKgram() {
      Iterator ite = temp.iterator();
      String[] temp1 = new String[temp.size()];
      int i = 0;
      while(ite.hasNext()){  
         temp1[i] = (String)ite.next();
         i++;
      }
      Random r = new Random();
      int a = r.nextInt(temp1.length);
   
      return temp1[a];
   }


   /**
    * Returns the set of kgrams in the source text.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   public Set<String> getAllKgrams() {
      Iterator ite = temp.iterator();
      String[] temp1 = new String[pjx];
      int i = 0;
      while(ite.hasNext()){  
         temp1[i] = (String)ite.next();
         i++;
      }
      String neig = "";
      for (int row = 0; row < i; row++) {
         for (int column = 0; column < d2.length; column++) {
            if (d2[row][column] != '\0') {
               neig += d2[row][column];
            }
         }
         model.put(temp1[row], neig);
         neig = "";
      }
     
      return model.keySet();
   }


   /**
    * Returns a single character that follows the given kgram in the source
    * text. This method selects the character according to the probability
    * distribution of all characters that follow the given kgram in the source
    * text.
    */
   public char getNextChar(String kgram) {
      Random a = new Random();
      String b = "";
      int num = 0;
      for (String word: model.keySet()) {
         if (word.equals(kgram)) {
            b = model.get(kgram);
            int num1 = word.length();
            if (num1 > 0) {
               num = a.nextInt() + 1;
            }
         }
      }
      if (!b.equals("")) {
         return b.charAt(num - 1);
      }
      return '\u0000';
   
      // frequent = new HashMap();
   //       char letter = 'a';
   //       String a = model.get(kgram);
   //       char[] b = a.toCharArray();
   //       int q = 0;
   //       while (q < b.length) {
   //          int count = 0;
   //          Iterator ite = temp.iterator();
   //          while(ite.hasNext()){  
   //             String word = (String)ite.next();
   //             if (word.contains(String.valueOf(b[q]))) {
   //                count++;
   //             }
   //          }
   //          // Integer count2 = new Integer(count);
   //          String b1 = String.valueOf(b[q]);
   //          frequent.put(b1, count);
   //          q++;
   //       }
   //       List sorted = new ArrayList(frequent.values());
   //       Collections.reverse(sorted);
   //       for (int v = 0; v < b.length; v++) {
   //          if (frequent.get(String.valueOf(b[v])) == sorted.get(0)) {
   //             letter = b[v];
   //          }
   //       }
   //       return letter;
   }


   /**
    * Returns a string representation of the model.
    * This is not part of the provided shell for the assignment.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   @Override
    public String toString() {
      return model.toString();
   }

}
