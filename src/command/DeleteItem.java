package command;

import structures.HashTable;
import model.TodoItem;

/**
 * Is used to perform and undo the deletion action of an element.
 */
public class DeleteItem implements Command {

    private final HashTable<String, TodoItem> table;
    private final TodoItem item;
    private final String key;

    /**
     * Delete Item class constructor
     * @param table
     * @param item
     * @param key
     */
    public DeleteItem(HashTable<String, TodoItem> table, TodoItem item, String key) {

        this.table = table;
        this.item = item;
        this.key = key;
    }

    /**
     * Function designed to look up an item in a HashTable of objects
     * @param table
     * @param titleToDelete
     * @return
     */
    public static TodoItem searchTaskByTitle(HashTable<String, TodoItem> table, String titleToDelete) {

        return null;
    }

    /**
     * Is implemented to remove the item from the hash table.
     */
    @Override
    public void execute() {

        table.remove(key);
    }

    /**
     * Is implemented to undo the action performed by execute()
     */
    @Override
    public void undo() {

        table.add(key, item);
    }
}