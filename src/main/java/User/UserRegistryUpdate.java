package User;

import java.util.List;
import java.util.Map;

public interface UserRegistryUpdate {
    void updateUser(String id, Map<String, String> newDetails);
}
