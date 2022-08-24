package User;

public class UserImporter implements User.Importer {

    private final double importingId;
    private final String importingName;
    private final String importingPassword;

    public UserImporter(double id, String name, String password) {
        this.importingId = id;
        this.importingName = name;
        this.importingPassword = password;
    }

    public double fetchId() { return importingId; }

    public String fetchName() { return importingName; }

    public String fetchPassword() { return importingPassword; }
}
