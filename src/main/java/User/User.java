package User;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String id;
    private String name;
    private String password;
    private List<String> checkedOutBookIds = new ArrayList<>();

    public User() {}

    interface Importer {
        String fetchId();
        String fetchName();
        String fetchPassword();
        List<String> fetchBookIdList();
    }
    public User(Importer source) {
        id = source.fetchId();
        name = source.fetchName();
        password = source.fetchPassword();
        checkedOutBookIds = source.fetchBookIdList();
    }

    interface Exporter {
        void storeId(String id);
        void storeName(String name);
        void storePassword(String password);
        void storeBookIdList(List<String> bookIdList);
    }
    public void export(Exporter destination) {
        destination.storeId(id);
        destination.storeName(name);
        destination.storePassword(password);
        destination.storeBookIdList(checkedOutBookIds);
    }
}
