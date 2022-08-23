package Admin;

import User.UserImporter;

import java.util.ArrayList;
import java.util.List;

public class AdminRegistry {

    private final List<Admin> registeredAdmins = new ArrayList<>();

    public AdminRegistry() {}

    public void createAdmin(double id, String name, String password) {
        AdminImporter importer = new AdminImporter(id, name, password);
        Admin newAdmin = new Admin(importer);

        registeredAdmins.add(newAdmin);
    }

    public void listAdmins() {
        AdminExporter exporter = new AdminExporter();
        for (Admin admin : registeredAdmins) {
            admin.export(exporter);
            System.out.println(exporter.exportData());
        }
    }
}
