package JavaProgramming;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.*;

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

    @Test
    public void longestSequence(){
        int[] input = {100, 1, 200, 3, 4, 100, 5};
        HashSet<Integer> output = new HashSet<>();
        for(int x : input){
            output.add(x);
        }
        List<Integer> bestSequence = new ArrayList<>();
        int bestLength = 0;

        for(int y : output){
            if(!output.contains(y-1)){

                List<Integer> currentSequence = new ArrayList<>();
                int currentNumber = y;

                while(output.contains(currentNumber)){
                    currentSequence.add(currentNumber);
                    currentNumber++;
                }

                if(currentSequence.size()>bestLength){
                    bestLength = currentSequence.size();
                    bestSequence = currentSequence;
                }
            }
        }
        for(int z : bestSequence) {
            System.out.println(z);

        }

    }

    @Test
    public void findMostRepeated(){

        //String and Hashmap --> put and get mtd, map.getOrDefault(ch,0)+1) 2) entry set for looping 3 ) collections.max func
        String input = "y name is Vjaat";

        String input2 = input.toLowerCase().replaceAll(" ", "");
        System.out.println(input2);
        HashMap<Character, Integer> newInput = new HashMap<>();
        for(int i=0; i<input2.length();i++){
            newInput.put(input2.charAt(i), newInput.getOrDefault(input2.charAt(i),0) + 1);
        }
        int maximum = Collections.max(newInput.values());
        int sum = 0;
        for(Map.Entry<Character, Integer> entry : newInput.entrySet()){
            sum =sum + entry.getValue();
            if(entry.getValue()==maximum) {
                System.out.println(entry.getKey());
            }
        }
        System.out.println(sum);
    }

    @Test
    public static void reverse() {
        int x = 1534236469;
        long reverse =0;
        while(x!=0){
            int digit = x%10;
            reverse = reverse*10 +digit;
            x = x/10;

        }
       System.out.println(reverse);

    }
}




