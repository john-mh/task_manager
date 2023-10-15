package model;

import java.time.LocalDateTime;

public class Reminder extends TodoItem {

    /**
     * Constructs a TodoItem with the given title, description and limit date.
     *
     * @param title       The title of the Todo item
     * @param description The description of the Todo item
     * @param deadline    The deadline for the Todo item
     * @param priority    The priority of the Todo item
     */
    public Reminder(String title, String description, LocalDateTime deadline, Priority priority) {

        super(title, description, deadline, priority);
    }
}
