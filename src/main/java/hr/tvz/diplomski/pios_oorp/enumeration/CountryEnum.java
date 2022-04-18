package hr.tvz.diplomski.pios_oorp.enumeration;

public enum CountryEnum {
    CROATIA("Hrvatska");

    private String description;

    CountryEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
