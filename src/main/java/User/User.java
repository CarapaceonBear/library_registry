package User;

import java.util.ArrayList;
import java.util.List;

public class User {

    private double id;
    private String name;
    private String password;
    private List<String> checkedOutBookIds = new ArrayList<>();

    public User() {}

    interface Importer {
        double fetchId();
        String fetchName();
        String fetchPassword();
    }
    public User(Importer source) {
        id = source.fetchId();
        name = source.fetchName();
        password = source.fetchPassword();
    }

    interface Exporter {
        void storeId(double id);
        void storeName(String name);
        void storePassword(String password);
    }
    public void export(Exporter destination) {
        destination.storeId(id);
        destination.storeName(name);
        destination.storePassword(password);
    }

    public void checkOutBook() {
        System.out.println("uses interface");
    }
    public void returnBook() {
        System.out.println("uses interface");
    }
}
