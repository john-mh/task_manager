package src.command;

import src.structures.HashTable;
import src.model.TodoItem;

public class DeleteItem implements Command {

    private final HashTable<String, TodoItem> table;
    private final TodoItem item;

    public DeleteItem(HashTable<String, TodoItem> table, TodoItem item) {

        this.table = table;
        this.item = item;
    }

    @Override
    public void execute() {
        table.remove(item);
    }

    @Override
    public void undo() {
        table.add(item);
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
