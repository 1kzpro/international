public class referenceTest {
   public static void main(String []args) {
      String a = "Yuhao";
      String b = "Kazybek";
      
      a = b; // a = "Kazybek"
      
      b = "Yuhao";
      
      System.out.println(a + " " + b); // Kazybek Yuhao
   }
}