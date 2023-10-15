/**
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Priority;
import model.TaskManager;
import model.TodoItem;
import structures.HashTable;
import structures.PriorityQueue;
import structures.Stack;

import java.time.LocalDateTime;


public class TaskManagerIT {

    @Test
    public void testStack() {
        Stack<Integer> stack = new Stack<>();
        Assertions.assertTrue(stack.isEmpty());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Assertions.assertEquals(3, stack.size());
        Assertions.assertFalse(stack.isEmpty());
        Assertions.assertEquals(3, stack.peek());
        Assertions.assertEquals(3, stack.pop());
        Assertions.assertNotEquals(1, (int) stack.pop());
    }

    @Test
    public void testPriorityQueue() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Assertions.assertTrue(queue.isEmpty());
        queue.add(1);
        queue.add(2);
        queue.add(3);
        Assertions.assertEquals(3, queue.size());
        Assertions.assertFalse(queue.isEmpty());
        Assertions.assertEquals(1, queue.peek());
        Assertions.assertEquals(1, queue.poll());
//Assertions.assertNotEquals(1, (int) queue.poll());
        Assertions.assertEquals(2, queue.size());
        Assertions.assertEquals(2, queue.poll());
    }

    @Test
    public void testAddItem() {
        String title = "title";
        String description = "description";
        LocalDateTime limit = LocalDateTime.now();
        TaskManager taskManager = new TaskManager();
        taskManager.add(taskManager.createTask(title, description, limit, true));
        Assertions.assertEquals(1, taskManager.stackSize());
    }


    private TaskManager taskManager;


    @BeforeEach
    public void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    public void testAddAndRetrieveTask() {
        String title = "Test Task";
        String description = "This is a test task.";
        LocalDateTime limit = LocalDateTime.now();
        HashTable<String, TodoItem> table = taskManager.getTable();

// Agregar una tarea a TaskManager
        TodoItem task = taskManager.createTask(title, description, limit, true);
        taskManager.add(task);

// Verificar que la tarea se haya almacenado correctamente en la tabla hash
        TodoItem retrievedTask = table.get(table.key(task));
        Assertions.assertEquals(title, retrievedTask.getTitle());
        Assertions.assertEquals(description, retrievedTask.getDescription());
        Assertions.assertEquals(limit, retrievedTask.getDeadline());
        Assertions.assertEquals(Priority.PRIORITY, retrievedTask.getPriority());
    }
}
*/