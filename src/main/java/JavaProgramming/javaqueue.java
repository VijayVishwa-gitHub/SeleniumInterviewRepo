package JavaProgramming;

import java.util.*;

public class javaqueue {

    /**
     * 1. Dequeue is type of array that allow insertion and deletion from both the ends
     * 2. It is an alternate for Queue (First in First Out) and Stack (Last in First Out)
     * 3. If we want to an array to act both queue and stack we use this.
     */

    public void testingDequeue(){
        ArrayDeque<Integer> first = new ArrayDeque<>();
        first.offerFirst(1);     //add from first
        first.offerFirst(2);
        first.addFirst(3);
        first.addFirst(4);
        first.offerLast(11);      //add from last
        first.addLast(12);
        first.addLast(13);
        first.offerLast(14);
        System.out.println("Fetching from first" +first.peekFirst());      //get first
        System.out.println("Fetching from last:" +first.getLast());       //get last
        first.pollLast();      //removes element from last
        first.removeFirst();
        first.pollFirst();     //removes element from first
        first.removeLast();

        first.forEach((x)->System.out.println(x));




    }

    public static void nonRepeatingCharacter(){
        String word = "RRRSSSTTTOOOWWWCC MG";
        LinkedHashMap<String, Integer> test = new LinkedHashMap<>();
        String[] wordArray = word.split("");
        for(String ch : wordArray) {
            test.put(ch, (test.getOrDefault(ch, 0) + 1));
        }
        test.forEach((x,y)->System.out.println(x +" "+ y));

        boolean found = false;
        for(Map.Entry<String, Integer> maps : test.entrySet()){
            if(maps.getValue() == 1 && !maps.getKey().equals(" ")){   //gets keys value and checks the space in the string
                System.out.println(maps.getKey());
                found =  true;
                break;
            }
        }
        if(!found){
            System.out.println("no");
        }

    }

    public static void anagrams(){

        /**
         * Create a map of String and List<String>
         * Open a loop the array of MainList
         * Inside the loop spilt the work into character Array [.toCharArray()]
         * Then sort the array based on alphabets > Convert array back to word new String(ArrayName)
         * Pass the word as key to Map and keep adding word in the List<String></String>
         */

        String[] list1 = {"listen", "silent", "enlist", "hello", "ohlle", "world"};
        Map<String, List<String>> map = new HashMap<>();
        for(String s : list1){
            char[] letter = s.toCharArray();
            Arrays.sort(letter);
            String key = new String(letter);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);  
        }
        for (List<String> group : map.values()) {
            System.out.println(group);
        }

    }

    public static void Array3(){

    /**
     * Input: arr = [9, 7, 5, 3], k = 6
     * Output: true
     * Explanation: Pairs (9,3) and (7,5) → both sums are 12 → divisible by 6
     * Input: arr = [1, 2, 3, 4, 5, 6], k = 7
     * Output: true
     * Explanation: (1,6), (2,5), (3,4)
     * Input: arr = [1, 2, 3, 4, 5], k = 2
     * Output: false → one element will be left unpaired
     */

    int[] list1 = { 1, 2, 3, 4, 5, 6};
    List<int[]> finals = new ArrayList<>();
    int k = 7;
    for(int i = 0; i<list1.length -1; i++) {  //0, 1, 2
       for (int j = i+1; j < list1.length; j++) {  //1, 2
            int l = list1[i] + list1[j];
            if (l % k == 0) {
                finals.add(new int[]{list1[i], list1[j]});}}}
    finals.forEach(s ->System.out.println(Arrays.toString(s)));
    }

    interface sums{
        int result(int x, int y);
    }

    static void BufferAndBuilder(){
        String a = "Manoj";
        StringBuffer b = new StringBuffer("Manoj");
        StringBuilder c = new StringBuilder("Manoj");

        if(a.contentEquals(b) && a.contentEquals(c)){
            System.out.println(STR."All string values are equal [\{a}, \{b}, \{c}]");
        }
        else{
            System.out.println(STR."String values are not equal \{a} \{b} \{c}");
        }
   }

    static void moveZeros(){
        int[] actualArray = {0,12, 33, 7, 0, 14};
        Arrays.sort(actualArray);
        for(int i=0;i<actualArray.length-1;i++){
            for(int j=0; j<actualArray.length-i-1;j++){
                if(actualArray[j] < actualArray[j+1]){
                    int tempt = actualArray[j];
                    actualArray[j]=actualArray[j+1];
                    actualArray[j+1] = tempt;
                    }
                }
            }
        for(int x : actualArray)
        {System.out.println(x);
        }

    }

    static void firstReapeating() {
        int[] lis = {1,2, 8, 4, 6,5, 5,7,7, 2};
        LinkedHashMap<Integer, Integer> mapping = new LinkedHashMap<>();
        for(int i : lis) {
            mapping.put(i, (mapping.getOrDefault(i,0)+1));
        }
        for(Map.Entry<Integer, Integer> entry : mapping.entrySet()){
            if(entry.getValue()>1){
                System.out.println(entry.getKey());
                break;
            }
        }
        int i ;
        int count =0;
        for(i=0; i<lis.length-1;i++){
            for(int j=i+1; j<lis.length;j++){
                if(lis[i]==lis[j]){
                    count++;
                    break;
                }
            }
        }
        if(count>1){
            System.out.println(lis[i]);
        }
    }

    static int[] revWithoutDuplicates(int[] arr, int k){

                Map<Integer, Integer> freq = new HashMap<>();
                List<Integer> uniqueIndexes = new ArrayList<>();
                List<Integer> uniqueValues = new ArrayList<>();

                // Count frequency and collect unique elements
                for (int i = 0; i < arr.length; i++) {
                    int val = arr[i];
                    freq.put(val, freq.getOrDefault(val, 0) + 1);
                }
                for(Map.Entry <Integer,Integer> entry : freq.entrySet()){
                    System.out.println(entry.getKey() +" "+entry.getValue() );
                }

                // Collect indexes and values of unique elements
                for (int i = 0; i < arr.length; i++) {
                    if (freq.get(arr[i]) == 1) {
                        uniqueIndexes.add(i);
                        uniqueValues.add(arr[i]);
                    }
                }
                System.out.println("*************************************************");
                for(int s=0;s<5;s++){
                    System.out.println(uniqueIndexes.get(s)+ " "+uniqueValues.get(s));
                }
                System.out.println("*************************************************");


                int n = uniqueValues.size();
                if (n == 0) return arr;

                // Normalize rotation (handles k > n and negative k)



                // Rotate and reinsert
                for (int i = 0; i < n; i++) {
                    int rotatedIndex = (i + k) % n;
                    arr[uniqueIndexes.get(i)] = uniqueValues.get(rotatedIndex);
                }

                return arr;
            }


    static void reverseArray(int[] arr, int start, int end) {
            while (start < end) {

                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start++;
                end--;
            }

        }

    public static void main(String[] args){
        int k =3;
        int[] arr = {11, 12, 13, 16, 15, 16, 17};
        System.out.println(Arrays.toString(revWithoutDuplicates(arr, k)));


//            int n = arr.length;
//            k = ((k % n) + n) % n;    // 1%5 = 1 and -1%5=4
//            reverseArray(arr,0, k-1);
//            reverseArray(arr, k, arr.length-1);
//            reverseArray(arr, 0, arr.length-1);
//            for(int j : arr){
//                System.out.println(j);
//            }

        }


    }

