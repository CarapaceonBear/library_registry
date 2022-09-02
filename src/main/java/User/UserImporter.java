package User;

import java.util.Arrays;
import java.util.List;

public class UserImporter implements User.Importer {

    private final String importingId;
    private final String importingName;
    private final String importingPassword;
    private final List<String> importingBookIdList;

    public UserImporter(String id, String name, String password, String bookIdList) {
        this.importingId = id;
        this.importingName = name;
        this.importingPassword = password;
        this.importingBookIdList = Arrays.asList(bookIdList.split(","));
    }

    public String fetchId() { return importingId; }

    public String fetchName() { return importingName; }

    public String fetchPassword() { return importingPassword; }

    public List<String> fetchBookIdList() { return importingBookIdList; }
}
