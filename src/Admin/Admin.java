package Admin;

import Interfaces.CanSignIn;

public class Admin implements CanSignIn {

    private double id;
    private String name;
    private String password;

    public Admin() {}

    interface Importer {
        double fetchId();
        String fetchName();
        String fetchPassword();
    }
    public Admin(Importer source) {
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

    public void getReport() {
        System.out.println("uses interface");
    }
}
