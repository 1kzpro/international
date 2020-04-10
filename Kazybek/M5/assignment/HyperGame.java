import java.util.TreeSet;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
/**
 * Implements WordSearchGame interface
 *
 * @author Kazybek Mizam (kzm0099@auburn.edu)
 * @version 04/09/2020
 * 
 */
 
public class HyperGame implements WordSearchGame {
   ////////////
   ///FIELDS///
   ////////////
   TreeSet<String> words;
   String board[];
   SortedSet<String> validWords = new TreeSet<String>();
   
   /////////////////
   ///Constructor///
   /////////////////
   public HyperGame() {
      words = new TreeSet<String>();
   }
   
   /**
    * Loads the lexicon into a data structure for later use. 
    * 
    * @param fileName A string containing the name of the file to be opened.
    * @throws IllegalArgumentException if fileName is null
    * @throws IllegalArgumentException if fileName cannot be opened.
    */
   public void loadLexicon(String fileName) throws IllegalArgumentException {
      if (fileName == null) {
         throw new IllegalArgumentException();
      }
      
      try {
         File wordsFile = new File(fileName);
         Scanner sc = new Scanner(wordsFile);
         while (sc.hasNextLine()) {
           String data = sc.nextLine();
           Scanner lineScanner = new Scanner(data);
           lineScanner.useDelimiter(" ");
           words.add(lineScanner.next().toUpperCase());
         }
         int size = words.size();
         System.out.println(size);
      }
      catch (FileNotFoundException e) {
         throw new IllegalArgumentException();
      }
   }
   
   /**
    * Stores the incoming array of Strings in a data structure that will make
    * it convenient to find words.
    * 
    * @param letterArray This array of length N^2 stores the contents of the
    *     game board in row-major order. Thus, index 0 stores the contents of board
    *     position (0,0) and index length-1 stores the contents of board position
    *     (N-1,N-1). Note that the board must be square and that the strings inside
    *     may be longer than one character.
    * @throws IllegalArgumentException if letterArray is null, or is not
    *     square.
    */
   public void setBoard(String[] letterArray) {
      if (letterArray == null) {
         throw new IllegalArgumentException();
      }
      
      int dimension = letterArray.length;
      
      if (Math.sqrt(dimension) != (int)Math.sqrt(dimension)) {
         throw new IllegalArgumentException();
      }
      
      // When everything passes
      board = new String[dimension]; //instantiating
      
      for (int i = 0; i < dimension; i++) {
         board[i] = letterArray[i];
      }
   }   
   
   /**
    * Creates a String representation of the board, suitable for printing to
    *   standard out. Note that this method can always be called since
    *   implementing classes should have a default board.
    */
   public String getBoard() {
      if (board.length != 0) {
         String theBoard = "";
         int dimension = (int)Math.sqrt(board.length + 1);
         for (int i = 0; i < board.length; i++) {
            theBoard += board[i];
            if (i < board.length - 1) {
               if ((i + 1) % dimension == 0) {
                  theBoard += "\n";
               }
               else {
                  theBoard += ", ";
               }
            }
         }
         return theBoard;
      }
      return "";
   }
   
   /**
    * Retrieves all valid words on the game board, according to the stated game
    * rules.
    * 
    * @param minimumWordLength The minimum allowed length (i.e., number of
    *     characters) for any word found on the board.
    * @return java.util.SortedSet which contains all the words of minimum length
    *     found on the game board and in the lexicon.
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public SortedSet<String> getAllValidWords(int minimumWordLength) {
      if (words.isEmpty()) {
         throw new IllegalStateException();
      }
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      
      validWords = new TreeSet<String>();
      
      for (int i = 0; i < board.length; i++) {
         ArrayList<Integer> visited = new ArrayList<Integer>();
         digDeepDip(i, visited, minimumWordLength);
      }
      
      return validWords;
   }
   
  /**
   * Computes the cummulative score for the scorable words in the given set.
   * To be scorable, a word must (1) have at least the minimum number of characters,
   * (2) be in the lexicon, and (3) be on the board. Each scorable word is
   * awarded one point for the minimum number of characters, and one point for 
   * each character beyond the minimum number.
   *
   * @param words The set of words that are to be scored.
   * @param minimumWordLength The minimum number of characters required per word
   * @return the cummulative score of all scorable words in the set
   * @throws IllegalArgumentException if minimumWordLength < 1
   * @throws IllegalStateException if loadLexicon has not been called.
   */  
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      if (words.isEmpty()) {
         throw new IllegalStateException();
      }
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      
      int score = 0;
      
      Iterator it = words.iterator();
      
      while(it.hasNext()) {
         String word = (String)it.next();
         
         if (word.length() >= minimumWordLength && words.contains(word.toUpperCase()) && !isOnBoard(word.toUpperCase()).isEmpty()) {
            score += 1 + word.length() - minimumWordLength;
         }
      }
      
      return score;
   }
   
   /**
    * Determines if the given word is in the lexicon.
    * 
    * @param wordToCheck The word to validate
    * @return true if wordToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidWord(String wordToCheck) {
      if (words.isEmpty()) {
         throw new IllegalStateException();
      }
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      
      if (words.contains(wordToCheck.toUpperCase())) {
         return true;
      }
      
      return false;
   }
   
   /**
    * Determines if there is at least one word in the lexicon with the 
    * given prefix.
    * 
    * @param prefixToCheck The prefix to validate
    * @return true if prefixToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if prefixToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidPrefix(String prefixToCheck) {
      if (words.isEmpty()) {
         throw new IllegalStateException();
      }
      if (prefixToCheck == null) {
         throw new IllegalArgumentException();
      }
      
      String floor = words.floor(prefixToCheck.toUpperCase());
      String ceiling = words.ceiling(prefixToCheck.toUpperCase());
      
      if(floor == null) {
         floor = prefixToCheck;
      }
      if (ceiling == null) {
         ceiling = prefixToCheck;
      }
      
      if (!floor.equalsIgnoreCase(ceiling)) {
         SortedSet<String> similarWords = words.subSet(floor, true, ceiling, true);
         for(String word: similarWords) {
            if (prefixToCheck.length() < word.length() && 
                prefixToCheck.equalsIgnoreCase(word.substring(0, prefixToCheck.length()))) {
                  return true;
            }
         }
      }
      else if(prefixToCheck.equalsIgnoreCase(ceiling.substring(0, prefixToCheck.length()))) {
         return true;
      }
      
      return false;
   }
   
   /**
    * Determines if the given word is in on the game board. If so, it returns
    * the path that makes up the word.
    * @param wordToCheck The word to validate
    * @return java.util.List containing java.lang.Integer objects with  the path
    *     that makes up the word on the game board. If word is not on the game
    *     board, return an empty list. Positions on the board are numbered from zero
    *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
    *     board, the upper left position is numbered 0 and the lower right position
    *     is numbered N^2 - 1.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public List<Integer> isOnBoard(String wordToCheck) {
      if (words.isEmpty()) {
         throw new IllegalStateException();
      }
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
   
      List<Integer> positions = new ArrayList<Integer>();
      
      for (int i = 0; i < board.length; i++) {
         if (isValidPrefixToWord(board[i], wordToCheck)) {
            ArrayList<Integer> visited = new ArrayList<Integer>();
            visited = digDeepDipOn(i, visited, wordToCheck);
            
            String word = "";
            for(int position: visited) {
               word += board[position];
            }
            if (word.equalsIgnoreCase(wordToCheck)) {
               positions = visited;
               return positions;
            }
         }
      }
      return positions;
   }

   private ArrayList<Integer> createNextAddresses(int currentAddress, ArrayList<Integer> visited) {
         ArrayList<Integer> nextPossibleAddresses = new ArrayList<Integer>();
         int dimension = (int)Math.sqrt(board.length);
         
         int currentAdressRow = currentAddress / dimension;
         int currentAdressColumn = currentAddress % dimension;
         
         int rowStart = -1;
         int rowStop = 1;
         int columnStart = -1;
         int columnStop = 1;
         
         if(currentAdressRow - 1 < 0) {
            rowStart = 0;
         }
         if(currentAdressRow + 1 == dimension) {
            rowStop = 0;
         }
         if(currentAdressColumn - 1 < 0) {
            columnStart = 0;
         }
         if(currentAdressColumn + 1 == dimension) {
            columnStop = 0;
         }
         
         for(int i = rowStart; i <= rowStop; i++) {
            for(int j = columnStart; j <= columnStop; j++) {
               int possibleAddress = currentAddress + i * dimension + j;
               if(!visited.contains(possibleAddress) && possibleAddress >= 0
                  && possibleAddress < board.length) {
                  nextPossibleAddresses.add(possibleAddress);
               }
            }
         }
         
         return nextPossibleAddresses;
      }
      
      private void digDeepDip(int address, ArrayList<Integer> visited,
                              int minimumWordLength) {     
         visited.add(address);
         
         String word = "";
         for(int position: visited) {
            word += board[position];
         }
         
         if(word.length() >= minimumWordLength && isValidWord(word) && !isOnBoard(word).isEmpty()) {
               validWords.add(word);
         }
         
         if (isValidPrefix(word)) {
            ArrayList<Integer> nextAddresses = createNextAddresses(address, visited);
            
            for (Integer nextAddress: nextAddresses) {
               digDeepDip(nextAddress, visited, minimumWordLength);
               visited.remove(nextAddress);
            }
         }
      }
      
   private ArrayList<Integer> digDeepDipOn(int address, ArrayList<Integer> visited, String wordToCheck) {
      visited.add(address);
      
      String word = "";
      for(int position: visited) {
         word += board[position];
      }
      
      if (word.equalsIgnoreCase(wordToCheck) || !isValidPrefixToWord(word, wordToCheck)) {
         return visited;
      }
            
      ArrayList<Integer> nextAddresses = createNextAddresses(address, visited);
            
      Iterator it = nextAddresses.iterator();
            
      while (it.hasNext()) {
         Integer nextAddress = (Integer)it.next();
         visited = digDeepDipOn(nextAddress, visited, wordToCheck);
         word = "";
         for(int position: visited) {
            word += board[position];
         }
         if (word.equalsIgnoreCase(wordToCheck)) {
            return visited;
         }
         visited.remove(nextAddress);
      }
      return visited;
   }
   
   public boolean isValidPrefixToWord(String prefixToCheck, String word) {
      if (words.isEmpty()) {
         throw new IllegalStateException();
      }
      if (prefixToCheck == null) {
         return false;
      }
      
      if (prefixToCheck.length() <= word.length() &&
          prefixToCheck.equalsIgnoreCase
          (word.substring(0, prefixToCheck.length()))) {
          return true;
      }
      
      return false;
   }
}