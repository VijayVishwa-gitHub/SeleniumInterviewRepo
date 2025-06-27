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



    public static void main(String[] args){
        BufferAndBuilder();
//        sums dm = (a, b) ->a+b;
//        System.out.println(dm.result(2,7));


    }
}
