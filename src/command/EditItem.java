package src.command;

import src.model.TodoItem;
import src.structures.HashTable;

public class EditItem implements Command {

    private final HashTable<String, TodoItem> table;
    private final TodoItem item;
    private final TodoItem oldItem;
    private final String key;


    public EditItem(HashTable<String, TodoItem> table, TodoItem item, String key) {
        this.table = table;
        this.item = item;
        this.key = key;
        this.oldItem = table.get(key);
    }

    @Override
    public void execute() {
        table.remove(key);
        table.add(key, item);
    }

    @Override
    public void undo() {
        table.remove(key);
        table.add(key, oldItem);
    }

}
