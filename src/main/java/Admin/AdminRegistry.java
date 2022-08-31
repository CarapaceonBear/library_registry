package Admin;

import Utilities.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AdminRegistry implements AdminRegistrySearch, AdminRegistryAddAdmin {

    private final List<Admin> registeredAdmins = new ArrayList<>();
    private final IdGenerator idGenerator;

    public AdminRegistry(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    private void createAdmin(double id, String name, String password) {
        AdminImporter importer = new AdminImporter(id, name, password);
        Admin newAdmin = new Admin(importer);

        registeredAdmins.add(newAdmin);
    }

    public void addAdmin(Map<String, String> admin) {
        createAdmin(idGenerator.generateNewId(), admin.get("name"), admin.get("password"));
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
