package User;

import java.util.ArrayList;
import java.util.List;

public class UserRegistry {

    private final List<User> registeredUsers = new ArrayList<>();

    public UserRegistry() {}

    public void createUser(double id, String name, String password) {
        UserImporter importer = new UserImporter(id, name, password);
        User newUser = new User(importer);

        registeredUsers.add(newUser);
    }

    public void listUsers() {
        UserExporter exporter = new UserExporter();
        for (User user : registeredUsers) {
            user.export(exporter);
            System.out.println(exporter.exportData());
        }
    }
}
