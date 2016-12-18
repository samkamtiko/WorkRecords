package model;

public enum TaskState {
    CREATED(0),
    IN_WORK(1),
    FINISHED(2);

    private final int value;

    TaskState(int group) {
        value = group;
    }

    public int getValue() {
        return value;
    }

    public static TaskState getFromInt(int group) {
        switch(group) {
            case 0:
                return CREATED;
            case 1:
                return IN_WORK;
            case 2:
                return FINISHED;
            default:
                return null;
        }
    }
}
