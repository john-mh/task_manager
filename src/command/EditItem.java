package command;

import model.TodoItem;
import structures.HashTable;

/**
 * Allows you to modify a specific TodoItem in the hash table.
 */
public class EditItem implements Command {

    private final HashTable<String, TodoItem> table;
    private final TodoItem item;
    private final TodoItem oldItem;
    private final String key;

    /**
     * This is the constructor of the EditItem class
     * @param table
     * @param item
     * @param key
     */
    public EditItem(HashTable<String, TodoItem> table, TodoItem item, String key) {

        this.table = table;
        this.item = item;
        this.key = key;
        this.oldItem = table.get(key);
    }

    /**
     * Performs the actual editing of the element.
     */
    @Override
    public void execute() {

        table.remove(key);
        table.add(key, item);
    }

    /**
     * Performs the reverse operation to undo editing
     */
    @Override
    public void undo() {

        table.remove(key);
        table.add(key, oldItem);
    }

}
