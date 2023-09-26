package src.model;

import java.time.LocalDateTime;

public class Reminder extends TodoItem {
    public Reminder(String title, String description, LocalDateTime limit) {
        super(title, description, limit);
    }
}
