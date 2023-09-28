package src.model;

public enum Priority {

    PRIORITARY(0), NON_PRIORITARY(1);

    private final int priority;

    private Priority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

}
