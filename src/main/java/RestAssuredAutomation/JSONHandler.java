package RestAssuredAutomation;

import io.restassured.path.json.JsonPath;


import java.util.Stack;

public class JSONHandler {

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            // Push opening brackets
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            // Handle closing brackets
            else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) return false;

                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                    return false;
                }
            }
        }

        // If stack is empty, all brackets matched
        return stack.isEmpty();
    }
    public static void main(String[] args){
        isValid("{[()]}");
       JsonPath js = new JsonPath(JSONValidation.payload());
//        String bookName = js.get("books.Book[2]");
//        int count = js.get("books.size()");
//        System.out.println(bookName +", " +count);
    }
}
