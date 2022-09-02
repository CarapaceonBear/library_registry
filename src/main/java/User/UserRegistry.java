package User;

import Utilities.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRegistry implements UserRegistrySearch, UserRegistryAddUser, UserRegistryUpdate {

    private final List<User> registeredUsers = new ArrayList<>();
    private final IdGenerator idGenerator;
    private final UserUpdater userUpdater = new UserUpdater();

    public UserRegistry(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    private void createUser(String id, String name, String password) {
        UserImporter importer = new UserImporter(id, name, password, "");
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

    public Optional<Map<String, String>> searchById(String id) {
        List<Map<String, String>> userList = listUsers();
        return userList.stream().filter(
                u -> u.get("id").equals(id))
                .findFirst();
    }

    public void updateUser(String id, Map<String, String> newDetails) {
        userUpdater.updateUser(registeredUsers, id, newDetails);
    }

//    temporary
    public void printUsers() {
        List<Map<String, String>> test = listUsers();
        for (Map<String, String> u : test) {
            System.out.println(u);
        }
    }
}
