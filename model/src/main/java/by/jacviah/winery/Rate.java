package by.jacviah.winery;

public enum Rate {
    ONE("*"),
    TWO("**"),
    THREE("***"),
    FOUR("****"),
    FIVE("******");

    private String printedText;
    private Rate(String str){
        printedText = str;
    }
    public static Rate asRate(String str) {
        for (Rate rate : Rate.values()) {
            if (rate.name().equalsIgnoreCase(str))
                return rate;
        }
        return null;
    }

    @Override
    public String toString() {
        return printedText;
    }
}
