package SingletonMemoryAdress;

public class SingletonDemo {
    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        System.out.println("Hashcode of instance1: " + instance1.hashCode());
        System.out.println("Hashcode of instance2: " + instance2.hashCode());

        // The hashcodes of the two instances should be the same
        if (instance1 == instance2) {
            System.out.println("Both instances refer to the same object.");
        } else {
            System.out.println("Both instances refer to different objects.");
        }

        // Call a method on the Singleton instance
        instance1.showAdress();
    }
}
