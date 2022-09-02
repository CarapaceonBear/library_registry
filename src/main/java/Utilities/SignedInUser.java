package Utilities;

public class SignedInUser {

    private static String id;
    private static String name;
    private static boolean admin = false;

    public static void setId(String value) {
        id = value;
    }

    public static String getId() {
        return id;
    }

    public static void setName(String value) {
        name = value;
    }

    public static String getName() {
        return name;
    }

    public static void setAdminPrivilege(boolean value) {
        admin = value;
    }

    public static boolean checkIfAdmin() {
        return admin;
    }
}
