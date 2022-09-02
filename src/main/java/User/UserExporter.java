package User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserExporter implements User.Exporter {

    private String exportingId;
    private String exportingName;
    private String exportingPassword;
    private List<String> exportingBookIdList;
    private final Map<String, String> exportingData = new HashMap<>();

    public void storeId(String id) { this.exportingId = id; }

    public void storeName(String name) { this.exportingName = name; }

    public void storePassword(String password) { this.exportingPassword = password; }

    public void storeBookIdList(List<String> bookIdList) { this.exportingBookIdList = bookIdList; }

    public Map<String, String> exportData() {
        exportingData.put("id", exportingId);
        exportingData.put("name", exportingName);
        exportingData.put("password", exportingPassword);
        exportingData.put("bookIdList", String.join(",", exportingBookIdList));
        return exportingData;
    }
}
