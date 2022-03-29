package hr.tvz.diplomski.pios_oorp.enumeration;

public enum UserType {

    CUSTOMER, ADMIN;

    UserType() {

    }

    public String getRole() {
        return "ROLE_" + name();
    }
}
