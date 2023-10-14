package ui;
import model.TaskManager;
import model.TodoItem;
import command.EditItem;
import command.DeleteItem;

import java.time.LocalDateTime;
import java.util.Scanner;


public class TaskManagerUI {



    private TaskManager taskManager;
    private Scanner sc;

    public TaskManagerUI(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.sc = new Scanner(System.in);
    }



    public void start() {
        System.out.println("\n==========================================================================");
        System.out.println("=============== !Bienvenido al Administrador de Tareas! ==================");
        System.out.println("==========================================================================\n");


        while (true) {
            System.out.println("Opciones:");
            System.out.println("1. Crear tarea");
            System.out.println("2. Crear recordatorio");
            System.out.println("3. Editar tarea");
            System.out.println("4. Eliminar tarea");
            System.out.println("5. Deshacer");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    createTask();
                    break;
                case 2:
                    createReminder();
                    break;
                case 3:
                    editTask();
                    break;
                case 4:
                    deleteTask();
                    break;
                case 5:
                    undo();
                    break;
                case 6:
                    exit();
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    private void createTask() {

        System.out.println("\n==========================================================================");
        System.out.println("=========================== Crear Tarea ==================================");
        System.out.println("==========================================================================\n");

        System.out.print("Porfavor ingrese el título de la tarea: ");
        String title = sc.nextLine();
        System.out.print("Porfavor ingrese la descripción de la tarea: ");
        String description = sc.nextLine();
        System.out.print("Porfavor ingrese la fecha límite de la tarea (AAAA-MM-DD HH:mm): ");
        LocalDateTime deadline = LocalDateTime.parse(sc.nextLine());
        System.out.print("¿La tarea tiene prioridad? (S/N): ");
        boolean hasPriority = sc.nextLine().equalsIgnoreCase("S");

        TodoItem task = taskManager.createTask(title, description, deadline, hasPriority);
        taskManager.add(task);

        System.out.println("¡La tarea ha sido creada exitosamente!");
    }

    private void createReminder() {
        System.out.println("\n==========================================================================");
        System.out.println("=========================== Crear Recordatorio ===========================");
        System.out.println("==========================================================================\n");

        System.out.print("Porfavor ingrese el título del recordatorio: ");
        String title = sc.nextLine();
        System.out.print("Porfavor ingrese la descripción del recordatorio: ");
        String description = sc.nextLine();
        System.out.print("Porfavor ingrese la fecha límite del recordatorio (AAAA-MM-DD HH:mm): ");
        LocalDateTime deadline = LocalDateTime.parse(sc.nextLine());
        System.out.print("¿El recordatorio tiene prioridad? (S/N): ");
        boolean hasPriority = sc.nextLine().equalsIgnoreCase("S");

        TodoItem reminder = taskManager.createReminder(title, description, deadline, hasPriority);
        taskManager.add(reminder);

        System.out.println("¡El recordatorio ha sido creado exitosamente!");
    }

    private void editTask() {
        System.out.println("\n==========================================================================");
        System.out.println("=========================== Editar Tarea =================================");
        System.out.println("==========================================================================\n");


        System.out.print("Porfavor ingrese el título de la tarea a editar: ");
        String titleToEdit = sc.nextLine();

        TodoItem taskToEdit = EditItem.searchTaskByTitle(taskManager.getTable(), titleToEdit);

        if (taskToEdit != null) {

            System.out.print("Porfavor ingrese el nuevo título de la tarea: ");
            String newTitle = sc.nextLine();
            System.out.print("Porfavor ingrese la nueva descripción de la tarea: ");
            String newDescription = sc.nextLine();
            System.out.print("Porfavor ingrese la nueva fecha límite de la tarea (AAAA-MM-DD HH:mm): ");
            LocalDateTime newDeadline = LocalDateTime.parse(sc.nextLine());
            System.out.print("¿La tarea tiene prioridad? (S/N): ");
            boolean newHasPriority = sc.nextLine().equalsIgnoreCase("S");

            TodoItem newTask = taskManager.createTask(newTitle, newDescription, newDeadline, newHasPriority);

            EditItem editCommand = new EditItem(taskManager.getTable(), newTask, titleToEdit);
            editCommand.execute();
            System.out.println("¡La tarea fue editada exitosamente!");
        } else {
            System.out.println("Tarea no encontrada.");
        }
    }

    private void deleteTask() {
        System.out.println("\n==========================================================================");
        System.out.println("=========================== Eliminar Tarea ===============================");
        System.out.println("==========================================================================\n");

        System.out.print("Porfavor ingrese el título de la tarea a eliminar: ");
        String titleToDelete = sc.nextLine();

        TodoItem taskToDelete = DeleteItem.searchTaskByTitle(taskManager.getTable(), titleToDelete);


    }

    private void undo() {
        System.out.println("\n==========================================================================");
        System.out.println("============================= Undo =======================================");
        System.out.println("==========================================================================\n");

        taskManager.undo();
        System.out.println("Operación deshecha.");
    }

    private void exit() {

        System.out.println("\n==========================================================================");
        System.out.println("===== Gracias por usar el Administrador de Tareas. ¡Hasta luego! =========");
        System.out.println("==========================================================================\n");
    }

    public static void main (String [] args){

        TaskManager taskManager = new TaskManager(); // Crea una instancia de TaskManager o inicializa como lo haces en tu código
        TaskManagerUI taskManagerUI = new TaskManagerUI(taskManager);
        taskManagerUI.start();

    }
}