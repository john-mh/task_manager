package command;

import structures.HashTable;
import model.TodoItem;

public class DeleteItem implements Command {

    private final HashTable<String, TodoItem> table;
    private final TodoItem item;
    private final String key;

    public DeleteItem(HashTable<String, TodoItem> table, TodoItem item, String key) {
        this.table = table;
        this.item = item;
        this.key = key;
    }

    public static TodoItem searchTaskByTitle(HashTable<String, TodoItem> table, String titleToDelete) {
        return null;
    }

    @Override
    public void execute() {
        table.remove(key);
    }

    @Override
    public void undo() {
        table.add(key, item);
    }
}