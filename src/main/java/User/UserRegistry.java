package User;

import Utilities.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRegistry implements UserRegistrySearch, UserRegistryAddUser {

    private final List<User> registeredUsers = new ArrayList<>();
    private final IdGenerator idGenerator;

    public UserRegistry(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    private void createUser(double id, String name, String password) {
        UserImporter importer = new UserImporter(id, name, password);
        User newUser = new User(importer);

        registeredUsers.add(newUser);
    }

    public void addUser(Map<String, String> user) {
        createUser(idGenerator.generateNewId(), user.get("name"), user.get("password"));
    }

    private List<Map<String, String>> listUsers() {
        List<Map<String, String>> exportedUsers = new ArrayList<>();
        for (User user : registeredUsers) {
            UserExporter exporter = new UserExporter();
            user.export(exporter);
            exportedUsers.add(exporter.exportData());
        }
        return exportedUsers;
    }

    public Optional<Map<String, String>> searchUsers(Map<String, String> userQuery) {
        List<Map<String, String>> userList = listUsers();
        return userList.stream().filter(
                u -> u.get("name").equals(userQuery.get("name"))
                        && u.get("password").equals(userQuery.get("password")))
                .findFirst();
    }
}
