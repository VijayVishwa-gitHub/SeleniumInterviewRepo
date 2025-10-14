package JavaProgramming;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class cts {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String inputFromUser = sc.nextLine();

        String input1 = inputFromUser.trim().replaceAll("\\s+", " ");
        String[] Updated = input1.split(" ");


        StringBuilder sb = new StringBuilder(Updated[0]);
        sb.reverse();

        String secondWord = Updated[1].trim().replaceAll("[Ee]", "a");

        System.out.println(sb +" " +secondWord);



}
}
