package Interfaces;

public interface CanSignIn {

    void signIn();
    void signOut();

    public static class Implementation implements CanSignIn {
        public void signIn() {
            System.out.println("sign in");
        }
        public void signOut() {
            System.out.println("sign out");
        }
    }
}
