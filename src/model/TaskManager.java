package src.model;

import src.command.AddItem;
import src.command.Command;
import src.command.DeleteItem;
import src.command.EditItem;
import src.structures.HashTable;
import src.structures.PriorityQueue;
import src.structures.Stack;
import java.time.LocalDateTime;

/**
 *
 */
public class TaskManager {

    private final HashTable<String, TodoItem> table;
    private final PriorityQueue<TodoItem> queue;
    private final Stack<Command> previousCommands;

    /**
     *
     */
    public TaskManager() {

        this.queue = new PriorityQueue<>();
        this.table = new HashTable<>();
        this.previousCommands = new Stack<>();
    }

    /**
     *
     * @param title
     * @param description
     * @param limit
     * @return
     */
    public TodoItem createTask(String title, String description, LocalDateTime limit, boolean hasPriority) {

        Priority priority = hasPriority ? Priority.PRIORITY : Priority.NON_PRIORITY;
        return new Task(title, description, limit, priority);
    }

    /**
     *
     * @param title
     * @param description
     * @param limit
     * @return
     */
    public TodoItem createReminder(String title, String description, LocalDateTime limit, boolean hasPriority) {

        Priority priority = hasPriority ? Priority.PRIORITY : Priority.NON_PRIORITY;
        return new Reminder(title, description, limit, priority);
    }

    /**
     *
     *
     * @param key
     * @return
     */
    public TodoItem get(String key) {

        return table.get(key);
    }


    /**
     *
     * @return
     */
    public int stackSize() {

        return previousCommands.size();
    }

    /**
     *
     * @param item
     */
    public void add(TodoItem item) {

        Command command = new AddItem(table, item);
        command.execute();
        previousCommands.push(command);
    }

    /**
     *
     * @param item
     * @param key
     */
    public void edit(TodoItem item, String key) {
        Command command = new EditItem(table, item, key);
        command.execute();
        previousCommands.push(command);
    }

    /**
     *
     * @param item
     */
    public void delete(TodoItem item) {

        Command command = new DeleteItem(table, item);
        command.execute();
        previousCommands.push(command);
    }

    /**
     *
     */
    public void undo() {

        if (!previousCommands.isEmpty()) {

            Command command = previousCommands.pop();
            command.undo();
        }
    }

    /**
     *
     * @param title
     * @return
     */
    public String findTaskKeyTitle(String title) {

        List<String> keys = new ArrayList<>();

        for (TodoItem item : table.values()) {

            if (item.getTitle().equalsIgnoreCase(title)) {

                keys.add(item.getTitle());
            }
        }

        if (!keys.isEmpty()) {

            return keys.get(0);
        } else {

            return null;
        }
    }

    public void addTask(TodoItem item) {

        String key = UUID.randomUUID().toString();
        table.add(key, item);
    }

    public void editTask(TodoItem item, String key) {
        if (table.get(key) != null) {
            table.add(key, item);
        }
    }

    public void deleteTask(String key) {
        table.remove(key);
    }

}