package model;

import command.AddItem;
import command.Command;
import command.DeleteItem;
import command.EditItem;
import structures.HashTable;
import structures.PriorityQueue;
import structures.Stack;

import java.time.LocalDateTime;
import java.util.List;


/**
 *
 */
public class TaskManager {

    private final HashTable<String, TodoItem> table;
    private PriorityQueue<TodoItem> queue;
    private final Stack<Command> previousCommands;

    /**
     *
     */
    public TaskManager() {
        this.queue = new PriorityQueue<>();
        this.table = new HashTable<>();
        this.previousCommands = new Stack<>();
    }

    public HashTable<String, TodoItem> getTable() {
        return table;
    }


    /**
     *
     * @param title
     * @param description
     * @param deadline
     * @param hasPriority
     * @return
     */
    public TodoItem createTask(String title, String description, LocalDateTime deadline, boolean hasPriority) {
        Priority priority = hasPriority ? Priority.PRIORITY : Priority.NON_PRIORITY;
        return new Task(title, description, deadline, priority);
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
        queue.add(item);
    }

    /**
     *
     * @param item
     */
    public void edit(TodoItem item) {
        Command command = new EditItem(table, item, table.key(item));
        command.execute();
        updateQueue();
        previousCommands.push(command);
    }

    /**
     *
     * @param item
     */
    public void delete(TodoItem item) {
        Command command = new DeleteItem(table, item, table.key(item));
        command.execute();
        updateQueue();
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
    public String searchItem(String title) {
        List<TodoItem> items = table.values();
        List<TodoItem> matches = Searcher.searchingString(items, title, TodoItem::getTitle);
        String key = "";

        if(!matches.isEmpty()){
            key = table.key(matches.get(0));
        }

        return key;
    }

    private void updateQueue() {
        PriorityQueue<TodoItem> newQueue = new PriorityQueue<>();

        for (TodoItem item : table.values()) {
            if (queue.poll() == item)
                newQueue.add(item);
        }

        queue = newQueue;
    }
}