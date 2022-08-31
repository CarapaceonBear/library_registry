package Book;

import org.json.JSONObject;

import java.util.List;
import java.util.Optional;

public interface BookRegistrySearch {
    List<JSONObject> searchBooks(String searchTerm);
    Optional<JSONObject> findBookById(String id);
}
