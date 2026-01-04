package JavaProgramming;


public class OrderDemo {

    // 1. Static variable
    static int staticVar = print("1. Static variable initialized");

    // 2. Static block
    static {
        print("2. Static block executed");
    }

    // 3. Instance variable
    int instanceVar = print("4. Instance variable initialized");

    // 4. Constructor
    OrderDemo() {
        print("5. Constructor executed");
    }

    static int print(String msg) {
        System.out.println(msg);
        return 0;
    }

    public static void main(String[] args) {
        print("Class loaded, main starts");
        new OrderDemo();
    }
}
