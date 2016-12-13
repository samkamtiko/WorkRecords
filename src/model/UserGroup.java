package model;

public enum UserGroup {
    ADMINISTRATOR(0),
    MANAGER(1),
    PERFORMER(2);

    private final int value;

    UserGroup(int group) {
        value = group;
    }

    public int getValue() {
        return value;
    }

    public static UserGroup getFromInt(int group) {
        switch(group) {
            case 0:
                return ADMINISTRATOR;
            case 1:
                return MANAGER;
            case 2:
                return PERFORMER;
            default:
                return null;
        }
    }
}
