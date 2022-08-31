package Admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AdminRegistry implements AdminRegistrySearch {

    private final List<Admin> registeredAdmins = new ArrayList<>();

    public AdminRegistry() {}

    public void createAdmin(double id, String name, String password) {
        AdminImporter importer = new AdminImporter(id, name, password);
        Admin newAdmin = new Admin(importer);

        registeredAdmins.add(newAdmin);
    }

    private List<Map<String, String>> listAdmins() {
        List<Map<String, String>> exportedAdmins = new ArrayList<>();
        for (Admin admin : registeredAdmins) {
            AdminExporter exporter = new AdminExporter();
            admin.export(exporter);
            exportedAdmins.add(exporter.exportData());
        }
        return exportedAdmins;
    }

    public Optional<Map<String, String>> searchAdmins(Map<String, String> adminQuery) {
        List<Map<String, String>> adminList = listAdmins();
        return adminList.stream().filter(
                a -> a.get("name").equals(adminQuery.get("name"))
                        && a.get("password").equals(adminQuery.get("password")))
                .findFirst();
    }
}
