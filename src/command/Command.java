package command;

import model.TodoItem;
import structures.HashTable;

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

    public static TodoItem searchTaskByTitle(HashTable<String, TodoItem> table, String titleToEdit) {
        return null;
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