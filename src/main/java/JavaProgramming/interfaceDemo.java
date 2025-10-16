package JavaProgramming;




// Interface definition
    interface Member {
        // A callback method that implementing classes must define.
        void callback();

    }

    // Store class: Manages a list of registered Members and can notify them (inviteSale).
    class Store {
        // Array to hold Member objects, simulating a registration list.
        Member mem[] = new Member[100];
        int count = 0; // Counter for the number of registered members.

        // Method to register a new Member.
        void register(Member m) {
            // The image shows the counter being incremented *after* assignment.
            // Assuming post-increment based on common index usage.
            mem[count++] = m;
        }

        // Method to notify all registered members (simulate an invitation).
        void inviteSale() {
            for (int i = 0; i < count; i++) {
                // Call the callback() method on each registered member.
                mem[i].callback();
            }
        }
    }

    // Customer class: Implements the Member interface, making it registerable with the Store.
    class Customer implements Member {
        String name;

        // Constructor to set the customer's name.
        Customer(String n) {
            name = n;
        }

        // Implementation of the callback method from the Member interface.
        // This is the specific action the Customer takes when notified by the Store.
        @Override
        public void callback() {
            // s.o.p is shorthand for System.out.println
            System.out.println("OK, I will visit " + name);
        }
    }

    class Vendor implements Member {
    String name;

    // Constructor to set the customer's name.
    Vendor(String n) {
        name = n;
    }

    // Implementation of the callback method from the Member interface.
    // This is the specific action the Customer takes when notified by the Store.
    @Override
    public void callback() {
        // s.o.p is shorthand for System.out.println
        System.out.println("I will update the stock " + name);
    }
}


    // Example usage (Main class)
    public class interfaceDemo {

        public static void main(String[] args) {
            Store centralStore = new Store();

            // Create Customer objects (who are also Members).
            Customer c1 = new Customer("Alice");
            Customer c2 = new Customer("Bob");

            Vendor v1 = new Vendor("Manoj");
            Vendor v2 = new Vendor("Jagan");

            // Register the customers/members with the store.
            centralStore.register(c1);
            centralStore.register(c2);

            centralStore.register(v1);
            centralStore.register(v2);


            System.out.println("--- Store inviting Members to sale ---");
            // The Store calls inviteSale(), which triggers the callback() method
            // implemented uniquely by each Customer object.
            centralStore.inviteSale();
        }
    }

