package SeleniumFramework;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Advance {

    public static Map<String, Integer> monthMapping() {
        Map<String, Integer> getMonth = new HashMap<>();
        String[] months = {"Jan", "Feb", "Mar", "Apl"};

        for (int i = 0; i < months.length; i++) {
            getMonth.put(months[i], i + 1);
        }
        return getMonth;
    }

    public static List<String> filterValidEmails(List<String> emails) {
        List<String> validEmails = new ArrayList<>();
        String regex = "^[\\w.]+@[A-Za-z0-9]+\\.[A-Za-z]{2,4}$";
        Pattern pattern = Pattern.compile(regex);

        for (String email : emails) {
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                validEmails.add(email);
            }
        }
        return validEmails;
    }

    public static void javaRegex() {
        Pattern pattern = Pattern.compile("a{3}");
        Matcher matcher = pattern.matcher("aaaba");

        while (matcher.find()) {
            System.out.println("Match: " + matcher.group());
        }
    }

    public static void javaRegex2() {
        String input = "Contact us at support@example.com or sales.team@domain.co.in or visit site. Also mail x_y.z@demo.io";
        String ph = "63 69079587";
        //String ph = String.valueOf(phoneNumber);

        //String regex = "^[\\w.]+@[A-Za-z0-9]{2,7}+.[a-z]{2}";
        String regex = "^[\\d]{10}";
        Pattern p = Pattern.compile(regex);
        //Matcher match = p.matcher(ph);


        String[] inputArray = input.split(" ");

            Matcher match = p.matcher(ph);
            if(match.find()){
                System.out.println(ph);
            }

    }


    public static void main(String[] args) {
//        Map<String, Integer> monthOrder = monthMapping();
//        System.out.println(monthOrder.get("Jan"));
//        List<String> emails = Arrays.asList(
//                "test.user@example.com",
//                "invalid-email@.com",
//                "user123@domain.in",
//                "another.user@domain123.org",
//                "wrong@domain.c"
//        );
//        List<String> result = Advance.filterValidEmails(emails);
//        System.out.println("Valid Emails: " + result);
//    }
javaRegex2();
    }
}