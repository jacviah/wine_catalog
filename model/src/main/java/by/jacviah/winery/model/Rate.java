package by.jacviah.winery.model;

public enum Rate {
    ONE("*", 1),
    TWO("**", 2),
    THREE("***", 3),
    FOUR("****", 4),
    FIVE("******", 5);

    private String printedText;
    private int value;

    private Rate(String str, int value){
        this.printedText = str;
        this.value = value;
    }

    public int getValue() {
        return value;
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
