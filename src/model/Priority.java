package model;

/**
 *
 */
public enum Priority {

    PRIORITY(0), NON_PRIORITY(1);

    private final int priority;

    /**
     * Priority class constructor
     * @param priority
     */
    private Priority(int priority) {

        this.priority = priority;
    }

    /**
     * Returns the numeric priority value associated with each element of the enumeration.
     * @return
     */
    public int getPriority() {

        return priority;
    }

    /**
     * Allows to verify if a Priority item is of high priority.
     * @return
     */
    public boolean isPriority() {

        return this == PRIORITY;
    }

}
