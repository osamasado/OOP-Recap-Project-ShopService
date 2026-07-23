package Utils;

public enum OrderStatus {
    IN_PROCESSING("In Processing"),
    IN_DELIVERY("In Delivery"),
    COMPLETED("Completed"),
    CANCELED("Canceled");

    private final String displayText;
    OrderStatus(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return  displayText;
    }
}
