package model;

public enum Priority {

    PRIORITY(0), NON_PRIORITY(1);

    private final int priority;

    private Priority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
    public boolean isPriority() {
        return this == PRIORITY;
    }

}
