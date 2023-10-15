import model.Priority;
import model.TaskManager;
import model.TodoItem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


public class TaskManagerIT {

    private TaskManager taskManager;

    @BeforeEach
    public void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    public void testCreateTask() {
        TodoItem task = taskManager.createTask("Test Task", "This is a test task.", LocalDateTime.now(), true);
        assertNotNull(task);
        assertEquals("Test Task", task.getTitle());
        assertEquals("This is a test task.", task.getDescription());
        assertNotNull(task.getDeadline());
        assertTrue(task.getPriority() == Priority.PRIORITY);
    }




    @Test
    public void testCreateReminder() {
        TodoItem reminder = taskManager.createReminder("Test Reminder", "This is a test reminder.", LocalDateTime.now(), false);
        assertNotNull(reminder);
        assertEquals("Test Reminder", reminder.getTitle());
        assertEquals("This is a test reminder.", reminder.getDescription());
        assertNotNull(reminder.getDeadline());
        assertEquals(Priority.NON_PRIORITY, reminder.getPriority()); // Use assertEquals to compare enums
    }

    @Test
    public void testAddTask() {
        TodoItem task = taskManager.createTask("Test Task", "This is a test task.", LocalDateTime.now(), true);
        taskManager.add(task);
        assertEquals(1, taskManager.stackSize());
    }

    @Test
    public void testEditTask() {
        TodoItem task = taskManager.createTask("Test Task", "This is a test task.", LocalDateTime.now(), true);
        taskManager.add(task);

        TodoItem newTask = taskManager.createTask("Updated Task", "This is an updated task.", LocalDateTime.now(), false);
        taskManager.edit(task, newTask);

        TodoItem updatedTask = taskManager.get(taskManager.getTable().key(task));  // Obtén la tarea actualizada

        assertEquals("Updated Task", updatedTask.getTitle());
        assertEquals("This is an updated task.", updatedTask.getDescription());
        assertEquals(Priority.NON_PRIORITY, updatedTask.getPriority());
    }




    @Test
    public void testDeleteTask() {
        TodoItem task = taskManager.createTask("Test Task", "This is a test task.", LocalDateTime.now(), true);
        taskManager.add(task);
        taskManager.delete(task);
        assertEquals(0, taskManager.stackSize());
    }

    @Test
    public void testUndo() {
        TodoItem task = taskManager.createTask("Test Task", "This is a test task.", LocalDateTime.now(), true);
        taskManager.add(task);
        taskManager.undo();
        assertEquals(0, taskManager.stackSize());
    }
}
