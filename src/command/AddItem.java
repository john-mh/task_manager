package src.command;

import src.model.TodoItem;
import src.structures.HashTable;

public class AddItem implements Command {

    private final HashTable<String, TodoItem> table;
    private final TodoItem item;

    public AddItem(HashTable<String, TodoItem> table, TodoItem item) {

        this.table = table;
        this.item = item;
    }

    @Override
    public void execute() {
        table.add(item);
    }

    @Override
    public void undo() {
        table.remove(item);
    }

    public TodoItem searchTaskTitle(HashTable<String, TodoItem> table, String title) {

        for (TodoItem item : table.values()) {

            if (item.getTitle().equalsIgnoreCase(title)) {

                return item;
            }
        }
        return null;
    }
}
