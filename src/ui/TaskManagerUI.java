package src.ui;

import src.model.TaskManager;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TaskManagerUI {

    private static TaskManager taskManager;
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String [] args){
        taskManager = new TaskManager();
        start();
    }

    public static void start() {
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
            sc.nextLine();

            switch (choice) {
                case 1 -> createTask();
                case 2 -> createReminder();
                case 3 -> editTask();
                case 4 -> deleteTask();
                case 5 -> undo();
                case 6 -> {
                    exit();
                    return;
                }
            }
        }
    }

    private static void createTask() {

        System.out.print("Ingrese el título de la tarea: ");
        String title = sc.nextLine();
        System.out.print("Ingrese la descripción de la tarea: ");
        String description = sc.nextLine();
        System.out.print("Ingrese la fecha límite de la tarea (AAAA-MM-DD HH:mm): ");
        LocalDateTime deadline = LocalDateTime.parse(sc.nextLine());
        System.out.print("¿La tarea tiene prioridad? (S/N): ");
        boolean hasPriority = sc.nextLine().equalsIgnoreCase("S");

        taskManager.add(taskManager.createTask(title, description, deadline, hasPriority));

        System.out.println("Tarea creada exitosamente.");
    }

    private static void createReminder() {

        System.out.print("Ingrese el título del recordatorio: ");
        String title = sc.nextLine();
        System.out.print("Ingrese la descripción del recordatorio: ");
        String description = sc.nextLine();
        System.out.print("Ingrese la fecha límite del recordatorio (AAAA-MM-DD HH:mm): ");
        LocalDateTime deadline = LocalDateTime.parse(sc.nextLine());
        System.out.print("¿El recordatorio tiene prioridad? (S/N): ");
        boolean hasPriority = sc.nextLine().equalsIgnoreCase("S");

        taskManager.add(taskManager.createReminder(title, description, deadline, hasPriority));

        System.out.println("Recordatorio creado exitosamente.");
    }

    private static void editTask() {

        System.out.print("Ingrese el título de la tarea a editar: ");
        String titleToEdit = sc.nextLine();

        String key = taskManager.searchItem(titleToEdit);

        if(!key.equals("")) {
            System.out.print("Ingrese el nuevo título de la tarea: ");
            String newTitle = sc.nextLine();
            System.out.print("Ingrese la nueva descripción de la tarea: ");
            String newDescription = sc.nextLine();
            System.out.print("Ingrese la nueva fecha límite de la tarea (AAAA-MM-DD HH:mm): ");
            LocalDateTime newDeadline = LocalDateTime.parse(sc.nextLine());
            System.out.print("¿La tarea tiene prioridad? (S/N): ");
            boolean newHasPriority = sc.nextLine().equalsIgnoreCase("S");

            taskManager.edit(taskManager.createTask(newTitle, newDescription, newDeadline, newHasPriority));
            System.out.println("Tarea editada exitosamente.");
        } else {
            System.out.println("Tarea no encontrada.");
        }
    }

    private static void deleteTask() {

        System.out.print("Ingrese el título de la tarea a eliminar: ");
        String titleToDelete = sc.nextLine();
        String key = taskManager.searchItem(titleToDelete);

        if (!key.equals("")) {
            taskManager.delete(taskManager.get(key));
            System.out.println("Tarea eliminada exitosamente.");
        } else {
            System.out.println("Tarea no encontrada.");
        }
    }

    private static void undo() {
        taskManager.undo();
        System.out.println("Operación deshecha.");
    }

    private static void exit() {
        System.out.println("Gracias por usar el Administrador de Tareas. ¡Hasta luego!");
    }
}
