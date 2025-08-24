package JavaProgramming;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Array {

    public static boolean canWin(int leap, int[] game) {
            return isSolvable(leap, game, 0);
        }

    private static boolean isSolvable(int leap, int[] game, int i) {
        if (i < 0 || game[i] == 1) return false;
        if (i + leap >= game.length || i == game.length - 1) return true;

        game[i] = 1; // mark as visited

        return isSolvable(leap, game, i + 1) ||
                isSolvable(leap, game, i + leap) ||
                isSolvable(leap, game, i - 1);
    }

    @Test
    public static void testing(){
        Scanner sc = new Scanner(System.in);

        //Getting number of elements
        String N = sc.nextLine();
        int numberOfElements = Integer.parseInt(N);

        //Getting array of elements
        String L = sc.nextLine();
        String[] array = L.split(" ");  //getting inform of string array and converting it to list

        List<Integer> ActualList = new ArrayList<>();
        for(int i=0;i<numberOfElements;i++){
            ActualList.add(Integer.parseInt(array[i]));
        }


        int Q = sc.nextInt();  //number of operation we are going to perform
        // inserting and removing happens here

        for(int j =0;j<Q;j++){
            String query = sc.next();
            sc.nextLine();
            String[] s2 = sc.nextLine().split(" ");


            if(query.equalsIgnoreCase("INSERT")){
                int index = Integer.parseInt(s2[0]);
                int value = Integer.parseInt(s2[1]);
                ActualList.add(index, value);}
                else if(query.equalsIgnoreCase("DELETE")){
                    int index = Integer.parseInt(s2[0]);
                ActualList.remove(index);
            }
        }
        for(int num :ActualList){
            System.out.print(num +" ");
        }
        sc.close();
    }

    public static void arrayInsideArray() {
        Scanner sc = new Scanner(System.in);
        List<List<Integer>> data = new ArrayList<>();


        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int d = sc.nextInt();
            List<Integer> line = new ArrayList<>();
            for (int j = 0; j < d; j++) {
                line.add(sc.nextInt());
            }
            data.add(line);
        }

        int q = sc.nextInt();
        for (int k = 0; k < q; k++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            if (x <= data.size() && x > 0) {
                List<Integer> line = data.get(x - 1);
                if (y <= line.size() && y > 0) {
                    System.out.println(line.get(y - 1));
                } else {
                    System.out.println("ERROR!");
                }
            } else {
                System.out.println("ERROR!");
            }
        }
        sc.close();
    }

    public static void main(String[] args) {

        testing();

//        Scanner scan = new Scanner(System.in);
//        int q = scan.nextInt();
//        while (q-- > 0) {
//            int n = scan.nextInt();
//            int leap = scan.nextInt();
//
//            int[] game = new int[n];
//            for (int i = 0; i < n; i++) {
//                game[i] = scan.nextInt();
//            }
//            int leap = 3;
//            int[] game = {0,0,1,0,0,1};
//            System.out.println( (canWin(leap, game)) ? "YES" : "NO" );
        }

       // scan.close();
    }

