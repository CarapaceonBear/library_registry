package User;

import java.util.HashMap;
import java.util.Map;

public class UserExporter implements User.Exporter {

    private double exportingId;
    private String exportingName;
    private String exportingPassword;
    private final Map<String, String> exportingData = new HashMap<>();

    public void storeId(double id) { this.exportingId = id; }

    public void storeName(String name) { this.exportingName = name; }

    public void storePassword(String password) { this.exportingPassword = password; }

    public Map<String, String> exportData() {
        exportingData.put("id", Double.toString(exportingId));
        exportingData.put("name", exportingName);
        exportingData.put("password", exportingPassword);
        return exportingData;
    }
}
