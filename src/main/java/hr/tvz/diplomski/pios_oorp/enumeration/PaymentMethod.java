package hr.tvz.diplomski.pios_oorp.enumeration;

public enum PaymentMethod {
    CREDIT_CARD("Kreditna kartica"),
    PAYPAL("PayPal"),
    CASH_ON_DELIVERY("Plaćanje pouzećem");

    private String description;

    PaymentMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
