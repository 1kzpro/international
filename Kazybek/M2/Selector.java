import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  YOUR NAME (you@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version TODAY
 *
 */
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
   private Selector() { }


   /**
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null){
         throw new IllegalArgumentException();
      }
      if(coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      Iterator<T>	iter = coll.iterator();
      T min = iter.next();
      for(T item: coll) {
         if(comp.compare(item, min) < 0) {
            min = item;
         }
      }
      return min;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null){
         throw new IllegalArgumentException();
      }
      if(coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      Iterator<T>	iter = coll.iterator();
      T max = iter.next();
      for(T item: coll) {
         if(comp.compare(item, max) > 0) {
            max = item;
         }
      }
      return max;
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
      if (coll == null || comp == null){
         throw new IllegalArgumentException();
      }
      if(coll.isEmpty() || k > coll.size() || k < 1) {
         throw new NoSuchElementException();
      }
      
      T lastMin = min(coll, comp);
      T max = max(coll, comp);
      Collection<T> list = range(coll, lastMin, max, comp);
      
      if (k == 1) {
         return lastMin;
      }
      else {
         list.remove(lastMin);
         for(int i = 0; i < k - 1; i++){
            T newMin = min(list, comp);
            if(lastMin == newMin) {
               k = k + 1;
            }
            lastMin = newMin;
            if (lastMin == max && i+1 < k) {
               throw new NoSuchElementException();
            }
            list.remove(lastMin);
         }
      }
      return lastMin;
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      if (coll == null || comp == null){
         throw new IllegalArgumentException();
      }
      if(coll.isEmpty() || k > coll.size() || k < 1) {
         throw new NoSuchElementException();
      }
      
      T lastMax = max(coll, comp);
      T min = min(coll, comp);
      Collection<T> list = range(coll, min, lastMax, comp);
      
      if (k == 1) {
         return lastMax;
      }
      else {
         list.remove(lastMax);
         for(int i = 0; i < k - 1; i++){
            T newMax = max(list, comp);
            if(lastMax == newMax) {
               k = k + 1;
            }
            lastMax = newMax;
            if (lastMax == min && i+1 < k) {
               throw new NoSuchElementException();
            }
            list.remove(lastMax);
         }
      }
      return lastMax;
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      if (coll == null || comp == null){
         throw new IllegalArgumentException();
      }
      if(coll.isEmpty()) {
         throw new NoSuchElementException();
      }
   
      Collection<T> list = new ArrayList<T>();
      for(T item: coll) {
         if(comp.compare(item, high) <= 0 && comp.compare(item, low) >= 0) {
            list.add(item);
         }
      }
      if (list.isEmpty()) {
         throw new NoSuchElementException();
      }
      return list;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null){
         throw new IllegalArgumentException();
      }
      if(coll.isEmpty() || comp.compare(key, max(coll, comp)) >= 0) {
         throw new NoSuchElementException();
      }
      Collection<T> list = range(coll, key, max(coll, comp), comp);
      
      return min(list, comp);
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null){
         throw new IllegalArgumentException();
      }
      if(coll.isEmpty() || comp.compare(key, min(coll, comp)) <= 0) {
         throw new NoSuchElementException();
      }
      Collection<T> list = range(coll, min(coll, comp), key, comp);
      
      return max(list, comp);
   }

}
