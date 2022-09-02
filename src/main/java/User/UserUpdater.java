package User;

import java.util.List;
import java.util.Map;

public class UserUpdater {

    public UserUpdater() {}

    public void updateUser(List<User> registeredUsers, String id, Map<String, String> newDetails) {
        for (int i = 0; i < registeredUsers.size(); i++) {
            UserExporter exporter = new UserExporter();
            registeredUsers.get(i).export(exporter);
            Map<String, String> exportedUser = exporter.exportData();

            if (exportedUser.get("id").equals(id)) {
                registeredUsers.remove(i);
                UserImporter importer = new UserImporter(
                        newDetails.get("id"),
                        newDetails.get("name"),
                        newDetails.get("password"),
                        newDetails.get("bookIdList"));
                User updatedUser = new User(importer);
                registeredUsers.add(updatedUser);
                break;
            }
        }
    }
}
