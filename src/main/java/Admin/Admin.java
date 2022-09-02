package Admin;

public class Admin {

    private String id;
    private String name;
    private String password;

    public Admin() {}

    interface Importer {
        String fetchId();
        String fetchName();
        String fetchPassword();
    }
    public Admin(Importer source) {
        id = source.fetchId();
        name = source.fetchName();
        password = source.fetchPassword();
    }

    interface Exporter {
        void storeId(String id);
        void storeName(String name);
        void storePassword(String password);
    }
    public void export(Exporter destination) {
        destination.storeId(id);
        destination.storeName(name);
        destination.storePassword(password);
    }

    public void getReport() {
        System.out.println("uses interface");
    }
}
