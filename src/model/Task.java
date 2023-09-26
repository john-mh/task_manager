package src.model;

import java.time.LocalDateTime;

public class Task extends TodoItem {
    public Task(String title, String description, LocalDateTime limit) {
        super(title, description, limit);
    }
}
