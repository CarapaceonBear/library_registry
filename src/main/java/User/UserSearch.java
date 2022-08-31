package User;

import java.util.Map;
import java.util.Optional;

public interface UserSearch {
    Optional<Map<String, String>> searchUsers(Map<String, String> user);
}
