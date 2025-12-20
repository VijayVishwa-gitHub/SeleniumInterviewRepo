package JavaProgramming;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.experimental.theories.ParametersSuppliedBy;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.*;

import static java.util.Map.Entry.comparingByValue;

public class SimpleCodes {

    public static String name = "ALPHABOY";

    private static final Logger logger = LogManager.getLogger(SimpleCodes.class);

    @Test
    public static void removingDuplicates(){
        String input = "Programming";
        LinkedHashSet<Character> remDuplicates = new LinkedHashSet<>();
        StringBuilder sb = new StringBuilder();
        for(char c : input.toCharArray() ){
            remDuplicates.add(c);
        }


        for(char d : remDuplicates){
            sb.append(d);
        }
        System.out.print(sb);
    }

    @Test
    public static void longestPalindrome() {
        String s = "babad";

            int start = 0, end = 0;

            for (int i = 0; i < s.length(); i++) {
                int len1 = expandAroundCenter(s, i, i);       // odd length
                int len2 = expandAroundCenter(s, i, i + 1);   // even length
                int len = Math.max(len1, len2);

                if (len > end - start) {
                    start = i - (len - 1) / 2;
                    end = i + len / 2;
                }
            }

            System.out.println(s.substring(start, end + 1));
        }

        private static int expandAroundCenter(String s, int left, int right) {
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            return right - left - 1;
        }


   @Test
   public static void CTS1(){
//       Scanner sc = new Scanner(System.in);
//       String inputFromUser = sc.nextLine();

       String inputFromUser = "  Automation     enginEERing   ";

       String input1 = inputFromUser.trim().replaceAll("\\s+", " ");
       String[] Updated = input1.split(" ");


       StringBuilder sb = new StringBuilder(Updated[0]);
       sb.reverse();
       sb.append(" ");
       String secondWord = Updated[1].trim().replaceAll("[Ee]", "a");

       System.out.println(sb.append(secondWord));
   }

   @Test
   public static void CTS2(){

       String s = "aabbbcaa";

       StringBuilder sb = new StringBuilder();
       int n = s.length();
       int i = 0;
       while (i < n) {
           char c = s.charAt(i);
           int j = i + 1;
           while (j < n && s.charAt(j) == c) j++;
           sb.append(c).append(j - i);
           i = j;
       }
       //String sc = sb.toString();
       System.out.println(sb);


   }

    @Test
    @Parameters({"word"})
    public void removingSplChar(String inputString){
        //String updatedString = inputString.replaceAll("[^\\w]", ""); //inside square bracket means match any character other than alphabets
        //System.out.println(updatedString);

        StringBuilder sb = new StringBuilder();
        String output = inputString.replaceAll("[^A-Za-z0-9]", "");

        for(char c : inputString.toCharArray()){
            int ascii = (int) c;
            if((ascii>64 && ascii<91) || (ascii>94 && ascii<123)|| (ascii >= 48 && ascii <= 57)){
                sb.append(c);
            }
        }
        System.out.println(sb +" "+ output);
    }

    @Test
    @Parameters("word")
    public String StringReverse(String name) {

        String rev = "";
        for (int i = name.length() - 1; i >= 0; i--) {
            rev = rev + name.charAt(i);
        }
        logger.info(rev.toUpperCase());
        System.out.println(rev);
        return rev;

    }

    @Test
    public void Palindrome() {

        String ana = "madam";
        String message = StringReverse(ana).equalsIgnoreCase(ana) ? "Palindrome" : "Not a Palindrome";
        logger.info(message);
    }

    @Test
    public static void factorial(int i) {

        int factorialOf = i;
        int x = factorialOf;

        while ((factorialOf - 1) != 1) {
            x = x * (factorialOf - 1);
            factorialOf--;
        }
        System.out.println(x);
    }
    
    @Test
    public static int factorial2(int x) {
        if(x==0 || x==1) {
            return 1;
        }

       return x * factorial2(x-1);}

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
    public void longestSequence()   {

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
         int maximum = Collections.max(newInput.values());  //int maximum = Collections.max(mapName.values());
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

    @Test
    public static void largestSubArray(){

        int[] arr = {1,2,3,4,5,6};
        int k =8;
        int max = 0;
        List<List<Integer>> finalList = new ArrayList<>();

        for(int i=0; i<arr.length; i++){
            List<Integer> grouping = new ArrayList<>();
            int sum =arr[i];
            grouping.add(arr[i]);
            for(int j=1;j<=arr.length; j++){
                if(sum<=k){
                    sum = sum + arr[j];
                    if(sum<=k){
                        grouping.add(arr[j]);
                    }
                }
                else{
                    finalList.add(grouping);
                    break;
                }
            }
        }
        List<Integer> largest = null;
        for(List<Integer> y : finalList){
            if(y.size()>max){
                max = y.size();
                largest = y;
            }
        }
        System.out.println(largest);
    }

    @Test
    public static void mostRepeating(){

        String sentence = "Java is great, and Java is powerful! Python is also great. Java and Python are popular.";

        String updatedSentence = sentence.replaceAll("[^a-zA-Z ]", "");

        // splitedSentence =  new String[sentence.length()];

        String[] splitedSentence = updatedSentence.split(" ");

        HashMap<String, Integer> sortMap = new HashMap<>();

        for(String x : splitedSentence){
        sortMap.put(x, sortMap.getOrDefault(x ,0)+1);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(sortMap.entrySet());

        //list.sort(Map.Entry.<String, Integer>comparingByValue().reversed());
        list.sort(Map.Entry.comparingByValue());

        Map<String, Integer> finalMap = new LinkedHashMap<>();

        for(Map.Entry<String, Integer> entry : list){
            finalMap.put(entry.getKey(), entry.getValue());
        }


        System.out.println(finalMap);

    }

    @Test
    public static void vowels(){
        String word = "wElcome";
        String vowel = "aeiouAEIOU";
        int count = 0;
        for(int i=0; i<word.length();i++){
           // if(word.charAt(i) == 'a'||word.charAt(i)=='i'||word.charAt(i)=='e'||word.charAt(i)=='o'||word.charAt(i)=='u'){

            //System.out.println(vowel.indexOf("a"));
             if(vowel.indexOf(word.charAt(i))!= -1){
                System.out.println("Yes");
                count++;
            }
        }
        System.out.println(count);
    }//indexOf mtd returns -1 if char not found in the string

    @Test
    public static void arrMethod(){
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int lenArr = arr.length;
        int[] arrSmall = new int[lenArr/2];
        Integer[] arrBig = new Integer[lenArr / 2 + 1];
        int smallCount=0;
        int bigCount = 0;

        for(int i =0; i<lenArr;i++){
            if(i<lenArr/2){
                arrSmall[smallCount] = arr[i];
                smallCount++;
            }
            else {
                arrBig[bigCount] = arr[i];
                bigCount++;
            }
        }
        Arrays.sort(arrBig, Collections.reverseOrder());

        smallCount =0;
        bigCount=0;

        for(int m=0; m<lenArr;m++){
                arr[m] = (m % 2 == 0) ? arrBig[smallCount++] : arrSmall[bigCount++];
        }

        for(int l : arr){
            System.out.println(l);
        }


    }

    @Test
    public static void treeMap(){
        TreeMap<String, Integer> sortingKeys = new TreeMap<String, Integer>();

        sortingKeys.put("Vijay", 3);
        sortingKeys.put("Robin", 2);
        sortingKeys.put("Kathir",1);

        for(Map.Entry<String, Integer> sorts : sortingKeys.entrySet()){
            System.out.println(sorts.getKey() +" "+ sorts.getValue());
        }


    }

    @Test
    public static void secondLargest(int[] arr) {
        for(int i=0; i<arr.length-1;i++) {
            for (int k = i+1; k < arr.length; k++) {
                if(arr[k]>arr[i]){
                    arr[i] = arr[i] + arr[k];
                    arr[k] = arr[i] - arr[k];
                    arr[i] = arr[i] - arr[k];
                }
            }
        }
        System.out.println(arr[1]);

    }

    @Test
    public static void anagram(){
        String[] arr = {"eat", "tea", "ate", "ran", "nar", "anr"};
        Map<String, List<String>> finalList = new HashMap<String, List<String>>();

        for(String x : arr){

            char[] current = x.toCharArray();
            Arrays.sort(current);
            String sortKey = new String(current);

            finalList.computeIfAbsent(sortKey, k ->new ArrayList<>()).add(x);

        }
        List<List<String>> result = new ArrayList<>(finalList.values());
        for (List<String> group : result) {
            System.out.println(group);
        }

    }

    public static void countNumbers(int[] arr) {
        List<Integer> numberCount = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (!numberCount.contains(arr[i])) {
                numberCount.add(arr[i]);
                count = 0; // reset count for each new number

                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[i] == arr[j]) {
                        count++;
                    }
                }
                numberCount.add(count + 1); // +1 to include the first occurrence
            }
        }

        for (int k : numberCount) {
            System.out.println(k);
        }
    }


    public static void main(String[] args){

        //SimpleCodes sc = new SimpleCodes();
        //System.out.println("Factorial of number" + factorial2(5));
        //sc.removingSplChar("A!B#C$D%E^");
        //CTS2();
        factorial(7);
//        int[] arr = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4, 5,5,5,5};
//        countNumbers(arr);




    }
}




