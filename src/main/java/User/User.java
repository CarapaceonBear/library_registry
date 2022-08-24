package User;

import Interfaces.CanSignIn;
import java.util.ArrayList;
import java.util.List;

public class User implements CanSignIn {

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

    private final CanSignIn delegate = new CanSignIn.Implementation();
    public void signIn() { delegate.signIn(); }
    public void signOut() { delegate.signOut(); }

    public void checkOutBook() {
        System.out.println("uses interface");
    }
    public void returnBook() {
        System.out.println("uses interface");
    }
}
