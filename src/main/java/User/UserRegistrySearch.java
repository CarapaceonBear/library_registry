package User;

import java.util.Map;
import java.util.Optional;

public interface UserRegistrySearch {
    Optional<Map<String, String>> searchUsers(Map<String, String> user);
    Optional<Map<String, String>> searchById(String id);
}
