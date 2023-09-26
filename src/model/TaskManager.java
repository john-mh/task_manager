package src.model;

import src.command.AddItem;
import src.command.Command;
import src.command.DeleteItem;
import src.command.EditItem;
import src.structures.HashTable;
import src.structures.PriorityQueue;
import src.structures.Stack;

import java.time.LocalDateTime;

public class TaskManager {

    private final HashTable<String, TodoItem> table;
    private final PriorityQueue<TodoItem> queue;
    private final Stack<Command> previousCommands;

    public TaskManager(PriorityQueue<TodoItem> queue) {
        this.queue = queue;
        this.table = new HashTable<>();
        this.previousCommands = new Stack<>();
    }

    public TodoItem createTask(String title, String description, LocalDateTime limit) {
        return new Task(title, description, limit);
    }

    public TodoItem createReminder(String title, String description, LocalDateTime limit) {
        return new Reminder(title, description, limit);
    }

    public TodoItem get(String key) {
        return table.get(key);
    }

    public void add(TodoItem item) {
        Command command = new AddItem(table, item);
        command.execute();
        previousCommands.push(command);
    }

    public void edit(TodoItem item, String key) {
        Command command = new EditItem(table, item, key);
        command.execute();
        previousCommands.push(command);
    }

    public void delete(TodoItem item) {
        Command command = new DeleteItem(table, item);
        command.execute();
        previousCommands.push(command);
    }

    public void undo() {
        if (!previousCommands.isEmpty()) {
            Command command = previousCommands.pop();
            command.undo();
        }
    }
}
