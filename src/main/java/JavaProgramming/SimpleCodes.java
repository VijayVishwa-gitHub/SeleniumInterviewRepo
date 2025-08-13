package JavaProgramming;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SimpleCodes {

    public static String name = "ALPHABOY";

    private static final Logger logger = LogManager.getLogger(SimpleCodes.class);

    @Test(priority = 1)
    public String StringReverse(String name) {
        String rev = "";
        for (int i = name.length() - 1; i >= 0; i--) {
            rev = rev + name.charAt(i);
        }
        logger.info(rev.toUpperCase());
        return rev;
    }

    @Test
    public void Palindrome() {

        String ana = "madam";
        String message = StringReverse(ana).equalsIgnoreCase(ana) ? "Palindrome" : "Not a Palindrome";
        logger.info(message);
    }

    @Test
    public void factorial() {
        int factorialOf = 11;
        int x = factorialOf;
        while ((factorialOf - 1) != 1) {
            x = x * (factorialOf - 1);
            factorialOf--;
        }
        System.out.println(x);
    }

    @Test
    public void revStringArray() {
        String a = " my name is Vijay ";
        List<String> d = new ArrayList<>();


        for (String x : a.trim().split(" ")) {
            if (!x.equals("")) {
                d.add(x);
            }
        }

        for (int i = 0; i < d.size(); i++) {
            if (i % 2 != 0) {
                d.set(i, new StringBuilder(d.get(i)).reverse().toString());
            }
        }

        d.forEach(System.out::println);


    }
}




