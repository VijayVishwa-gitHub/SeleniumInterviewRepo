package JavaProgramming;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LearningArray {

    @Test
    public void reverseArray(){
        int[] list1 = {1,2,3,4,5,6};
        int[] list2=new int[list1.length];
        int j =0;
        for(int i=list1.length-1;i>=0;i--){
            if(j<list1.length){
                list2[j] = list1[i];
                j++;
            }
        }
        for(int k : list2){
            System.out.print(k+" ");
        }

    }
    
    @Test
    public void sortArray(){
        int[] array1 = {1,-4,6,-1, 4, -3};
        int arrayLength = array1.length;
        for(int i=0; i< arrayLength; i++){
            for(int j=0;j<arrayLength-1;j++){
                int temp=0;
                if(array1[j]>array1[j+1]){
                    temp = array1[j];
                    array1[j] = array1[j+1];
                    array1[j+1] = temp;
                }
            }
            
        }
        for(int arr : array1){
        System.out.print(arr+",");}
    }

    @Test
    public void post_nega()  {
        int i = 0, j = 0, k = 0;
        int[] array1 = {1,-2,4,5,-5,-8};
        int[] result = new int[array1.length];
        Arrays.sort(array1);
        List<Integer> positiveNumber = new ArrayList<>();
        List<Integer> negativeNumber = new ArrayList<>();
      for (int num : array1){
          if(num>0) positiveNumber.add(num);
          else negativeNumber.add(num);
        }
      while(i<positiveNumber.size() && j<negativeNumber.size()){
          result[k++] = negativeNumber.get(j++);
          result[k++] = positiveNumber.get(i++);
      }
        while (i < negativeNumber.size()) {
            result[k++] = negativeNumber.get(i++);
        }
        while (j < positiveNumber.size()) {
            result[k++] = positiveNumber.get(j++);
        }

        System.out.println(Arrays.toString(result));

        }


    @Test
    public static int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // Base case: sum = 0 occurs once
        int count = 0;
        int sum = 0;

        for (int num : nums) {
            sum += num;

            // Check if sum - k exists in the map
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }

            // Add current sum to map
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 0, 4, 3};
        int k = 3;
        System.out.println("Number of subarrays: " + subarraySum(nums, k)); // Output: 2
    }

}



