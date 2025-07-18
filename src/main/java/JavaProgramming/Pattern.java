package JavaProgramming;

import org.testng.annotations.Test;

public class Pattern {
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


    public static void main(String args[]){
            ;pattern1(5);
    }
}
