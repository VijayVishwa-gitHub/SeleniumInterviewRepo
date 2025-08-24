package TestNG;

import JavaProgramming.Array;
import io.cucumber.java.hu.Ha;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class dataProvider {


    @DataProvider(name="Logindata", parallel = true)
    public Object[][] getData(){
        return new Object[][]{
                {"student", "Password123"}, {"Anil", "Kumble123"}, {"Sachin", "Tendulkar123"}
        };

    }

    @DataProvider(name = "usingIterator")
    public Iterator<Object[]> getData2(){
        List<Object[]> credentials = new ArrayList<>();
        credentials.add(new Object[]{"student", "Password123"});
        credentials.add(new Object[]{"user2", "pwd2"});
        credentials.add(new Object[]{"user3", "pwd3"});

        return credentials.iterator(); //returning iterator instead of an array
    }

    @DataProvider(name= "usingHashmap")
    public Object[][] getData3(){

        HashMap<String, String> credentials = new HashMap<>();
        credentials.put("username", "user");
        credentials.put("password", "pwd");
        HashMap<String, String> credentials2 = new HashMap<>();
        credentials2.put("username", "user1");
        credentials2.put("password", "pwd2");
        HashMap<String, String> credentials3 = new HashMap<>();
        credentials3.put("username", "user2");
        credentials3.put("password", "pwd2");

        return new Object[][]{ {credentials}, {credentials3}, {credentials2}
        };
    }

    @Test(dataProvider = "usingIterator")
    public void getDetails(String username, String password){
        System.out.println("The name is " +username +" and password is " +password);
    }

    @Test(dataProvider = "usingHashmap")
    public void getDetail(HashMap<String, String> datas){
        System.out.print(datas.get("username") +" ");
        System.out.print(datas.get("password") +"\n");
    }
}
