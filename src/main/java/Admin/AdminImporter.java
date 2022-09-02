package Admin;

public class AdminImporter implements Admin.Importer {

    private final String importingId;
    private final String importingName;
    private final String importingPassword;

    public AdminImporter(String id, String name, String password) {
        this.importingId = id;
        this.importingName = name;
        this.importingPassword = password;
    }

    public String fetchId() { return importingId; }

    public String fetchName() { return importingName; }

    public String fetchPassword() { return importingPassword; }
}
