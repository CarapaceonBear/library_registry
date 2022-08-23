public class Admin implements CanSignIn {

    private double id;
    private String name;
    private String password;

    public Admin() {}

    private final CanSignIn delegate = new CanSignIn.Implementation();
    public void signIn() { delegate.signIn(); }
    public void signOut() { delegate.signOut(); }

    public void getReport() {
        System.out.println("uses interface");
    }
}
