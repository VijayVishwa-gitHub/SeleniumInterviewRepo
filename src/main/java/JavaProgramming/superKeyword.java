package JavaProgramming;

public class superKeyword {

        public static class car{

            public void runs(){
                System.out.println("The car runs");
            }
            public car(){
                System.out.println("Inside parent class");
            }
        }

        public static class ship extends car{

            public ship(){
                super();
                System.out.println("Inside child class");
            }
            public void runs(){
                super.runs();
                System.out.println("The ship sails");
            }
        }

        public static void main(String[] args) {
            ship s = new ship();
            s.runs();
        }
    }

