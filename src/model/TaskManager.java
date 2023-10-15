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
 * Manages tasks and reminders
 */
public class TaskManager {

    private final HashTable<String, TodoItem> table;
    private PriorityQueue<TodoItem> queue;
    private final Stack<Command> previousCommands;

    /**
     * This is the constructor of the TaskManager class
     */
    public TaskManager() {

        this.queue = new PriorityQueue<>();
        this.table = new HashTable<>();
        this.previousCommands = new Stack<>();
    }

    /**
     * Get the table
     * @return
     */
    public HashTable<String, TodoItem> getTable() {

        return table;
    }

    /**
     * Retrieves an item from a list based on its index.
     * @param list
     * @param index
     * @return
     */
    public TodoItem getItem(List<?> list, int index) {

        return (TodoItem) list.get(index);
    }


    /**
     * Creates a new task with the provided title, description, deadline and priority and returns.
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
     * Creates a new reminder with the provided title, description, deadline and priority and returns.
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
     * Adds a task or reminder to the hash table and priority queue.
     * Also logs the command to add the operation to the command stack.
     * @param item
     */
    public void add(TodoItem item) {

        Command command = new AddItem(table, item);
        command.execute();
        previousCommands.push(command);
        queue.add(item);
    }


    /**
     *  Edits an existing task or reminder with new values and updates both the hash table and the priority queue.
     * @param oldItem
     * @param newItem
     */
    public void edit(TodoItem oldItem, TodoItem newItem) {

        Command command = new EditItem(table, newItem, table.key(oldItem));
        command.execute();
        updateQueue();
        previousCommands.push(command);

        oldItem.setTitle(newItem.getTitle());
        oldItem.setDescription(newItem.getDescription());
        oldItem.setPriority(newItem.getPriority());
        oldItem.setDeadline(newItem.getDeadline());
    }


    /**
     * Removes a task or reminder from the hash table and priority queue
     * @param item
     */
    public void delete(TodoItem item) {

        Command command = new DeleteItem(table, item, table.key(item));
        command.execute();
        updateQueue();
        previousCommands.push(command);
    }

    /**
     * Allows you to undo the last operation performed
     * by executing the corresponding command in the command stack.
     */
    public void undo() {

        if (!previousCommands.isEmpty()) {
            Command command = previousCommands.pop();
            command.undo();
        }
    }

    /**
     * Performs a search in the task and reminder list based on title
     * @param title
     * @return
     */
    public List<TodoItem> searchItem(String title) {

        return Searcher.searchingByCondition(table.values(), TodoItem::getTitle, String::contains, title);
    }

    /**
     * Is responsible for updating the priority queue to reflect
     * the current status of the tasks and reminders stored in the hash table.
     */
    private void updateQueue() {

        PriorityQueue<TodoItem> newQueue = new PriorityQueue<>();

        for (TodoItem item : table.values()) {
            if (queue.poll() == item)
                newQueue.add(item);
        }

        queue = newQueue;
    }
}