package Admin;

public class AdminImporter implements Admin.Importer {

    private final double importingId;
    private final String importingName;
    private final String importingPassword;

    public AdminImporter(double id, String name, String password) {
        this.importingId = id;
        this.importingName = name;
        this.importingPassword = password;
    }

    public double fetchId() { return importingId; }

    public String fetchName() { return importingName; }

    public String fetchPassword() { return importingPassword; }
}
