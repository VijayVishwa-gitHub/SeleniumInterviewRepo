package JavaProgramming;
import java.util.*;
import org.testng.annotations.Test;
import java.util.stream.*;

class Main {

    static List<String> removeDuplicates(List<String> employees){

            List<String> list1 = new ArrayList<>();
            Set<String> IDs = new HashSet<>();
            for(String x : employees){
                String[] parts = x.split(" ");
                String id = parts[0];

                if(!IDs.contains(id)){
                    IDs.add(id);
                    list1.add(x);
                }
            }return list1;


        }

    public static class Employee {
        private int id;
        private String name;
        private String department;
        private double salary;

        public Employee(int id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        // Getters
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return id + " | " + name + " | " + department + " | ₹" + salary;
        }


    }

    public static void sortMapByValue() {
            // Hardcoded input map
            Map<Integer, String> map = new HashMap<>();
            map.put(101, "Orange");
            map.put(104, "Apple");
            map.put(102, "Banana");
            map.put(103, "Grapes");

            // Sort by values and collect into LinkedHashMap
            Map<Integer, String> sortedMap = map.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue()) // Ascending order
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new
                    ));

            // Print sorted map
            System.out.println("Sorted by values:");
            sortedMap.forEach((k, v) -> System.out.println(k + " => " + v));
        }

    @Test
    public static void sumFirstAndLast(int number) {
        int last = number % 10;
        int first =0;
        while (number >=10){
            number= number /10;
            first = number;

        }
        System.out.println(first + last);
    }

    @Test
    public static void sorting(){
        int[] arr = {1,1, 2, 2, 3, 3};

        for(int i =0; i<arr.length-1;i++){

            for(int j=i+1;j<arr.length;j++){

                if(arr[i]<arr[j]){
                    arr[j] = arr[j]+arr[i];
                    arr[i] = arr[j]-arr[i];
                    arr[j] = arr[j]-arr[i];
                }
                if(arr[i]==arr[j]){
                    arr[j]=0;
                }
            }

        }
        for (int num : arr) {
            System.out.print(num + " ");
        }


        System.out.println(arr[1]);

    }

    public static void main() {
            sorting();
            //sumFirstAndLast(9234);

            List<Employee> employees = Arrays.asList(
                    new Employee(101, "Vijay", "QA", 65000),
                    new Employee(102, "Meena", "Development", 85000),
                    new Employee(103, "Arjun", "QA", 62000),
                    new Employee(104, "Divya", "HR", 55000),
                    new Employee(105, "Karthik", "Development", 92000),
                    new Employee(106, "Sneha", "QA", 70000),
                    new Employee(107, "Ravi", "HR", 58000),
                    new Employee(108, "Priya", "Development", 88000),
                    new Employee(109, "Manoj", "QA", 67000)
            );

            List<Employee> hrList = employees.stream().filter(e ->e.getDepartment().equalsIgnoreCase("qa") && !e.getName().equalsIgnoreCase("Divya")).toList();
            hrList.forEach(System.out::println);

//            for (Employee employee : hrList) {
//                System.out.println(employee);
//            }


            List<String> employees1 = Arrays.asList(
                    "1234 John HR",
                    "1942 Gaurav QA",
                    "1324 Mike Development",
                    "1324 Mike Development",
                    "1942 Kin Development",
                    "1112 Kin Development"
            );

            List<String> filtered = removeDuplicates(employees1);

//            for (String record : filtered) {
//                System.out.println(record);
//            }

        }
    }

