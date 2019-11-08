package by.jacviah.winery.model;

public enum Rate {
    EMPTY("", 0),
    ONE("*", 1),
    TWO("**", 2),
    THREE("***", 3),
    FOUR("****", 4),
    FIVE("******", 5);

    private String printedText;
    private Integer value;

    private Rate(String str, Integer value){
        this.printedText = str;
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static Rate asRate(Integer value) {
        for (Rate rate : Rate.values()) {
            if (rate.getValue().equals(value))
                return rate;
        }
        return null;
    }

    @Override
    public String toString() {
        return printedText;
    }
}
