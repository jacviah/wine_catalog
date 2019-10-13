package by.jacviah.winery.model;

public enum Role {
    ADMIN("Administrator"),
    USER("User"),
    SOMMELIER("Sommelier");
    private String printedText;
    private Role(String str){
        printedText = str;
    }
    public static Role asRole(String str) {
        for (Role role : Role.values()) {
            if (role.toString().equalsIgnoreCase(str)) {
                return role;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return printedText;
    }
}
