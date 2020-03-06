import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class ListFactoryTest {

   @Test public void defaultTest() {
      int numbers = ListFactory.makeDoubleEndedList();
   }
}
