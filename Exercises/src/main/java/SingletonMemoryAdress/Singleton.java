package SingletonMemoryAdress;

import org.openjdk.jol.vm.VM;

public class Singleton {
    private static Singleton instance;

    private Singleton() {
        // private constructor to prevent instantiation from outside
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void showAdress() {
        System.out.println("The memory address of this object " + VM.current().addressOf(instance));
    }
}
