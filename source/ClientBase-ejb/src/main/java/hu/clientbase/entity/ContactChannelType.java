package hu.clientbase.entity;

public enum ContactChannelType {
    EMAIL("Email"),
    PHONE("Phone"),
    FAX("Fax"),
    MAILING_ADDRESS("Mailing address");

    private final String name;

    private ContactChannelType(String s) {
        name = s;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
