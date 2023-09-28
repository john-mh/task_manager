package src.model;

import java.time.LocalDateTime;

public class TodoItem implements Comparable<TodoItem> {

    protected String title;
    protected String description;
    protected Priority priority;
    protected LocalDateTime limit;

    public TodoItem(String title, String description, LocalDateTime limit) {
        this.title = title;
        this.description = description;
        this.limit = limit;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getLimit() {
        return limit;
    }

    @Override
    public int compareTo(TodoItem other) {
        return Integer.compare(this.priority.getPriority(), other.priority.getPriority());
    }
}
