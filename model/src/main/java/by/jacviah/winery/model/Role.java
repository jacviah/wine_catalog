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

    @Override
    public String toString() {
        return printedText;
    }
}
