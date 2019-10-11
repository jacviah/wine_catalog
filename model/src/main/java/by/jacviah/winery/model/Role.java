package by.jacviah.winery.model;

/**
 * Created by jacviah on 21.09.2019.
 */
public enum Role {
    ADMIN("Administrator"),
    PRO_USER("PRO user"),
    FREE_USER("Free user");

    private String printedText;
    private Role(String str){
        printedText = str;
    }
    public static Role asRole(String str) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(str))
                return role;
        }
        return null;
    }

    @Override
    public String toString() {
        return printedText;
    }
}
