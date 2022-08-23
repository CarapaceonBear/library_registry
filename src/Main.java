import Admin.AdminRegistry;
import User.UserRegistry;

// notes
// admin and user importer should autogenerate ids, not take as arguments
// need to implement sign in
// need to implement checking out / returning
// need to implement building reports

public class Main {
    public static void main(String[] args) {
       UserRegistry userRegistry = new UserRegistry();
       AdminRegistry adminRegistry = new AdminRegistry();

       userRegistry.createUser(10000, "Bob", "password");
       userRegistry.createUser(10001, "Rick", "wordpass");
       adminRegistry.createAdmin(20000, "Beth", "admin");
       adminRegistry.createAdmin(20001, "Dave", "admin1");

       userRegistry.listUsers();
       adminRegistry.listAdmins();
    }
}