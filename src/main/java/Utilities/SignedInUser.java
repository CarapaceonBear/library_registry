package Utilities;

public class SignedInUser {

    private static String name;
    private static boolean admin = false;

    public static void setName(String value) {
        name = value;
    }

    public static String getName() {
        return name;
    }

    public static void setAdminPriviledge(boolean value) {
        admin = value;
    }

    public static boolean getAdminPriviledge() {
        return admin;
    }
}
