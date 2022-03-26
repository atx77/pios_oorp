package hr.tvz.diplomski.pios_oorp.enumeration;

public enum UserType {

    ROLE_CUSTOMER("CUSTOMER"),
    ROLE_ADMIN("ADMIN");

    private String role;

    UserType(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
