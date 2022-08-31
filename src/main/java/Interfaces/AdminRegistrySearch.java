package Interfaces;

import java.util.Map;
import java.util.Optional;

public interface AdminRegistrySearch {
    Optional<Map<String, String>> searchAdmins(Map<String, String> user);
}
