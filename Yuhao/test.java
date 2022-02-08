import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class test {

    public algorithm() {
        Array newArray = new Array[Scanner.in];
        int sum = 0;
        int max = 0;
        for (int i = 1; i < newArray.length; i++) {
            sum = 0;
            for (int j = 0; j < newArray.length; j++) {
                sum = sum + newArray[j];
            }
            if sum > max {
                max = sum;
            }
        }
        return max;
    }

}