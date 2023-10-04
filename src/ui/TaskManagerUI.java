import java.time.LocalDateTime;
import java.util.Scanner;

public class TaskManagerUi {

    private TaskManager taskManager;
    private Scanner sc;

    public TaskManagerUI(TaskManager taskManager) {

        this.taskManager = taskManager;
        this.sc = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Bienvenido al Administrador de Tareas!");

        while (true) {
            System.out.println("\nOpciones:");
            System.out.println("1. Crear tarea");
            System.out.println("2. Crear recordatorio");
            System.out.println("3. Editar tarea");
            System.out.println("4. Eliminar tarea");
            System.out.println("5. Deshacer");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int choice = sc.nextInt();
            scanner.nextLine();

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
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }

    private void createTask() {

        System.out.print("Ingrese el título de la tarea: ");
        String title = sc.nextLine();
        System.out.print("Ingrese la descripción de la tarea: ");
        String description = sc.nextLine();
        System.out.print("Ingrese la fecha límite de la tarea (AAAA-MM-DD HH:mm): ");
        LocalDateTime deadline = LocalDateTime.parse(scanner.nextLine());
        System.out.print("¿La tarea tiene prioridad? (S/N): ");
        boolean hasPriority = sc.nextLine().equalsIgnoreCase("S");


        TodoItem task = taskManager.createTask(title, description, deadline, hasPriority);
        taskManager.addTask(task);

        System.out.println("Tarea creada exitosamente.");
    }

    private void createReminder() {

        System.out.print("Ingrese el título del recordatorio: ");
        String title = sc.nextLine();
        System.out.print("Ingrese la descripción del recordatorio: ");
        String description = sc.nextLine();
        System.out.print("Ingrese la fecha límite del recordatorio (AAAA-MM-DD HH:mm): ");
        LocalDateTime deadline = LocalDateTime.parse(scanner.nextLine());
        System.out.print("¿El recordatorio tiene prioridad? (S/N): ");
        boolean hasPriority = sc.nextLine().equalsIgnoreCase("S");


        TodoItem reminder = taskManager.createReminder(title, description, deadline, hasPriority);
        taskManager.addTask(reminder);

        System.out.println("Recordatorio creado exitosamente.");
    }

    private void editTask() {

        System.out.print("Ingrese el título de la tarea a editar: ");
        String titleToEdit = sc.nextLine();


        TodoItem taskToEdit = EditItem.searchTaskByTitle(taskManager.getTable(), titleToEdit);

        if (taskToEdit != null) {

            System.out.print("Ingrese el nuevo título de la tarea: ");
            String newTitle = scanner.nextLine();
            System.out.print("Ingrese la nueva descripción de la tarea: ");
            String newDescription = scanner.nextLine();
            System.out.print("Ingrese la nueva fecha límite de la tarea (AAAA-MM-DD HH:mm): ");
            LocalDateTime newDeadline = LocalDateTime.parse(scanner.nextLine());
            System.out.print("¿La tarea tiene prioridad? (S/N): ");
            boolean newHasPriority = scanner.nextLine().equalsIgnoreCase("S");


            TodoItem newTask = taskManager.createTask(newTitle, newDescription, newDeadline, newHasPriority);


            EditItem editCommand = new EditItem(taskManager.getTable(), newTask, titleToEdit);
            editCommand.execute();
            System.out.println("Tarea editada exitosamente.");
        } else {
            System.out.println("Tarea no encontrada.");
        }
    }

    private void deleteTask() {

        System.out.print("Ingrese el título de la tarea a eliminar: ");
        String titleToDelete = sc.nextLine();


        TodoItem taskToDelete = DeleteItem.searchTaskByTitle(taskManager.getTable(), titleToDelete);

        if (taskToDelete != null) {

            DeleteItem deleteCommand = new DeleteItem(taskManager.getTable(), taskToDelete);
            deleteCommand.execute();
            System.out.println("Tarea eliminada exitosamente.");
        } else {
            System.out.println("Tarea no encontrada.");
        }
    }

    private void undo() {

        taskManager.undo();
        System.out.println("Operación deshecha.");
    }

    private void exit() {

        System.out.println("Gracias por usar el Administrador de Tareas. ¡Hasta luego!");
    }
}
