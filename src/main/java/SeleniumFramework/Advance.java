package SeleniumFramework;

import java.util.HashMap;
import java.util.Map;

public class Advance {

    public static Map<String, Integer> monthMapping() {
        Map<String, Integer> getMonth = new HashMap<>();
        String[] months = {"Jan", "Feb", "Mar", "Apl"};

        for (int i = 0; i < months.length; i++) {
            getMonth.put(months[i], i + 1);
        }
        return getMonth;
    }


    public static void main(String[] args) {
        Map<String, Integer> monthOrder = monthMapping();
        System.out.println(monthOrder.get("Jan"));


    }

}