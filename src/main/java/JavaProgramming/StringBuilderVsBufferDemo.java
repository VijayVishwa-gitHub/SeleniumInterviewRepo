public class StringBuilderVsBufferDemo {

    // 1. Basic Append Comparison
    public void basicAppendDemo() {
        StringBuilder sb = new StringBuilder("Hello");
        StringBuffer sf = new StringBuffer("Hello");

        sb.append(" World");
        sf.append(" World");

        System.out.println("StringBuilder result: " + sb);
        System.out.println("StringBuffer result: " + sf);
    }

    // 2. Thread Safety Demonstration
    public void threadSafetyDemo() {
        StringBuilder sb = new StringBuilder();
        StringBuffer sf = new StringBuffer();

        Runnable builderTask = () -> {
            for (int i = 0; i < 1000; i++) {
                sb.append("A");
            }
        };

        Runnable bufferTask = () -> {
            for (int i = 0; i < 1000; i++) {
                sf.append("B");
            }
        };

        Thread t1 = new Thread(builderTask);
        Thread t2 = new Thread(builderTask);
        Thread t3 = new Thread(bufferTask);
        Thread t4 = new Thread(bufferTask);

        t1.start(); t2.start(); t3.start(); t4.start();

        try {
            t1.join(); t2.join(); t3.join(); t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("StringBuilder length (non-thread-safe): " + sb.length());
        System.out.println("StringBuffer length (thread-safe): " + sf.length());
    }

    // 3. Performance Benchmark
    public void performanceDemo() {
        long startTime, endTime;

        StringBuilder sb = new StringBuilder();
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            sb.append("X");
        }
        endTime = System.nanoTime();
        System.out.println("StringBuilder time: " + (endTime - startTime) + " ns");

        StringBuffer sf = new StringBuffer();
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            sf.append("X");
        }
        endTime = System.nanoTime();
        System.out.println("StringBuffer time: " + (endTime - startTime) + " ns");
    }

    // 4. Reverse Operation
    public void reverseDemo() {
        StringBuilder sb = new StringBuilder("Interview");
        StringBuffer sf = new StringBuffer("Interview");

        System.out.println("StringBuilder reversed: " + sb.reverse());
        System.out.println("StringBuffer reversed: " + sf.reverse());
    }

    // 5. Capacity and Mutability
    public void capacityDemo() {
        StringBuilder sb = new StringBuilder();
        StringBuffer sf = new StringBuffer();

        System.out.println("Initial StringBuilder capacity: " + sb.capacity());
        System.out.println("Initial StringBuffer capacity: " + sf.capacity());

        sb.append("Java");
        sf.append("Java");

        System.out.println("After append - StringBuilder capacity: " + sb.capacity());
        System.out.println("After append - StringBuffer capacity: " + sf.capacity());
    }

    // Main method to run all demos
    public static void main(String[] args) {
        StringBuilderVsBufferDemo demo = new StringBuilderVsBufferDemo();

        System.out.println("=== Basic Append Demo ===");
        demo.basicAppendDemo();

        System.out.println("\n=== Thread Safety Demo ===");
        demo.threadSafetyDemo();

        System.out.println("\n=== Performance Demo ===");
        demo.performanceDemo();

        System.out.println("\n=== Reverse Demo ===");
        demo.reverseDemo();

        System.out.println("\n=== Capacity Demo ===");
        demo.capacityDemo();
    }
}