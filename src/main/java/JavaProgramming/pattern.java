package JavaProgramming;

import org.testng.annotations.Test;

public class pattern {
    @Test
    public static void pattern1(int n){
//        for(int i=n; i>=1;i--){
//            for (int s = 1; s <= n - i; s++) {   // space part
//                System.out.print(" ");
//            }
//          for(int j=1;j<=i;j++){
//              System.out.print(j+" ");
//          }
//          System.out.println();
//        }
        for(int i=0; i<=n;i++){
            for (int s = 1; s <= n - i; s++) {   // space part
                System.out.print(" ");
            }
            for(int j=1;j<=i;j++){
                System.out.print(j+" ");
            }
            System.out.println();
        }

    }


    @Test
    public static void pattern2(String s){

        s = s.replaceAll("0","");

        int i =0;
        int m  = s.length();
        long result = 0;

        while (i < m && s.charAt(i) == ' ') {   //this is how you IGNORE whitespaces
            i++;
        }

        s = s.trim().replaceAll("\\s+", "");  //assign it while replacing
        int sign =1;

        while(s.charAt(i)=='+' || s.charAt(i)=='-'){
            sign = (s.charAt(i)=='-')?-1:1;
            i++;
        }
        while(i<m && Character.isDigit(s.charAt(i))){
            int digit2 = Integer.parseInt(String.valueOf(s.charAt(i)));  // here we have done the same thing using parseInt
            int digit = s.charAt(i) - '0';   // here we are substract the ascii value i.e., 51(3) - 48(0)
            result = result*10 + digit2;
            i++;
        }
        System.out.println(sign * result);
    }


    public static void main(String[] args){
            //pattern1(5);
            pattern2("    0-1");
    }
}
