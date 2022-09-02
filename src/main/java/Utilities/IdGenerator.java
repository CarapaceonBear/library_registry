package Utilities;

public class IdGenerator {
//    very simple for now

    private int id = 0;

    public IdGenerator() {}

    public String generateNewId() {
        int result = id;
        id++;
        return Integer.toString(result);
    }
}
