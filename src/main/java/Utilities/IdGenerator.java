package Utilities;

public class IdGenerator {
//    very simple for now

    private double id = 0;

    public IdGenerator() {}

    public double generateNewId() {
        double result = id;
        id++;
        return result;
    }
}
