public class Main {
    public static void main(String[] args) {
        UserRegistry userRegistry = new UserRegistry();

       userRegistry.createUser(10000, "Bob", "password");
       userRegistry.createUser(10001, "Rick", "wordpass");

       userRegistry.listUsers();
    }
}