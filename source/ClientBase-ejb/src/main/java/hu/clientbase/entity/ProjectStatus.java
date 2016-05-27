package hu.clientbase.entity;

public enum ProjectStatus {
    NOT_STARTED("Not started"),
    PAUSED("Pause"),
    IN_PROGRESS("In Progress"),
    FINISHED("Finished");

    private final String name;

    private ProjectStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
