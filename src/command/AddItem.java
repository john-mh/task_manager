package command;

import model.TodoItem;
import structures.HashTable;
import java.util.UUID;


/**
 * It is responsible for adding a TodoItem element to a hash table
 */
public class AddItem implements Command {

    private final HashTable<String, TodoItem> table;
    private final TodoItem item;
    private final String key;

    /**
     * This is the constructor method of the AddItem class.
     * @param table
     * @param item
     */
    public AddItem(HashTable<String, TodoItem> table, TodoItem item) {

        key = UUID.randomUUID().toString();
        this.table = table;
        this.item = item;
    }

    /**
     * Adds an TodoItem to the hash table using a randomly generated unique identifier.
     * Ensuring that each item has a unique key.
     */
    @Override
    public void execute() {

        table.add(key, item);
    }


    /**
     * Undoes the operation of adding an element.
     * Removes the previously added item from the hash table using the generated key.
     */
    @Override
    public void undo() {

        table.remove(key);
    }
}