package model;

import java.time.LocalDateTime;

/**
 * Class representing a "To do" item.
 */
public class TodoItem implements Comparable<TodoItem> {

    protected String title;
    protected String description;
    protected Priority priority;
    protected LocalDateTime deadline;

    /**
     * Constructs a TodoItem with the given title, description, limit date, and priority.
     *
     * @param title       The title of the Todo item
     * @param description The description of the Todo item
     * @param deadline    The deadline for the Todo item
     * @param priority    The priority of the Todo item
     */
    public TodoItem(String title, String description, LocalDateTime deadline, Priority priority) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority; // Initialize the priority
    }


    /**
     * Returns the title of this TodoItem.
     *
     * @return The title text
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setDeadline(final LocalDateTime deadline) {
        this.deadline = deadline;
    }

    /**
     * Returns the description for this TodoItem.
     *
     * @return The description text
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the deadline for this TodoItem.
     *
     * @return The limit date/time
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Returns the priority for this TodoItem.
     *
     * @return The priority
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Sets the priority for this TodoItem.
     *
     * @param priority The new priority
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }



    /**
     * Compares this TodoItem with another one based on priority.
     *
     * @param other The other TodoItem to compare to
     * @return int representing the comparison result
     */
    @Override
    public int compareTo(TodoItem other) {
        return Integer.compare(this.priority.getPriority(), other.priority.getPriority());
    }

    @Override
    public String toString() {
        return title;
    }

}
