package command;

import model.TodoItem;
import structures.HashTable;

import java.util.UUID;

public class AddItem implements Command {

    private final HashTable<String, TodoItem> table;
    private final TodoItem item;
    private final String key;

    public AddItem(HashTable<String, TodoItem> table, TodoItem item) {

        key = UUID.randomUUID().toString();
        this.table = table;
        this.item = item;
    }

    @Override
    public void execute() {
        table.add(key, item);
    }

    @Override
    public void undo() {
        table.remove(key);
    }


}