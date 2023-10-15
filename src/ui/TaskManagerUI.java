package ui;

import model.TaskManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class TaskManagerUI {

    private static final TaskManager taskManager = new TaskManager();
    private static final Scanner sc = new Scanner(System.in);

    public static void start() {
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

            while(choice < 1 || choice > 6) {
                System.out.println("Opción inválida.");
                System.out.print("Seleccione una opción: ");
                choice = sc.nextInt();
                sc.nextLine();
            }

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
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void createTask() {

        System.out.println("\n==========================================================================");
        System.out.println("=========================== Crear Tarea ==================================");
        System.out.println("==========================================================================\n");

        System.out.print("Por favor ingrese el título de la tarea: ");
        String title = sc.nextLine();
        System.out.print("Por favor ingrese la descripción de la tarea: ");
        String description = sc.nextLine();
        System.out.print("Por favor ingrese la fecha límite de la tarea (AAAA-MM-DD HH:mm): ");
        LocalDateTime deadline = LocalDateTime.parse(sc.nextLine());
        System.out.print("¿La tarea tiene prioridad? (S/N): ");

        while (!sc.hasNext("[SsNn]")) {
            System.out.println("Opción inválida.");
            System.out.print("¿La tarea tiene prioridad? (S/N): ");
            sc.next();
        }

        boolean hasPriority = sc.nextLine().equalsIgnoreCase("S");

        taskManager.add(taskManager.createTask(title, description, deadline, hasPriority));

        System.out.println("¡La tarea ha sido creada exitosamente!");
    }

    private static void createReminder() {
        System.out.println("\n==========================================================================");
        System.out.println("=========================== Crear Recordatorio ===========================");
        System.out.println("==========================================================================\n");

        System.out.print("Por favor ingrese el título del recordatorio: ");
        String title = sc.nextLine();
        System.out.print("Por favor ingrese la descripción del recordatorio: ");
        String description = sc.nextLine();
        System.out.print("Por favor ingrese la fecha límite del recordatorio (AAAA-MM-DD HH:mm): ");
        LocalDateTime deadline = LocalDateTime.parse(sc.nextLine());
        System.out.print("¿El recordatorio tiene prioridad? (S/N): ");

        while (!sc.hasNext("[SsNn]")) {
            System.out.println("Opción inválida.");
            System.out.print("¿El recordatorio tiene prioridad? (S/N): ");
            sc.next();
        }

        boolean hasPriority = sc.nextLine().equalsIgnoreCase("S");

        taskManager.add(taskManager.createReminder(title, description, deadline, hasPriority));

        System.out.println("¡El recordatorio ha sido creado exitosamente!");
    }

    private static void editTask() {
        System.out.println("\n==========================================================================");
        System.out.println("=========================== Editar Tarea =================================");
        System.out.println("==========================================================================\n");

        System.out.print("Por favor ingrese el título de la tarea a editar: ");
        String taskTitle = sc.nextLine();

        List<?> tasks = taskManager.searchItem(taskTitle);
        int index = 0;

        if(tasks.isEmpty()) {
            System.out.println("Tarea no encontrada.");
        } else {
            printResults(tasks);

            System.out.print("Por favor ingrese el índice de la tarea a editar: ");

            index = sc.nextInt();

            while(index < 0 || index >= tasks.size()) {
                System.out.println("Índice inválido.");
                System.out.print("Por favor ingrese el índice de la tarea a editar: ");
                index = sc.nextInt();
            }
        }

        System.out.print("Por favor ingrese el nuevo título de la tarea: ");
        String newTitle = sc.nextLine();
        System.out.print("Por favor ingrese la nueva descripción de la tarea: ");
        String newDescription = sc.nextLine();
        System.out.print("Por favor ingrese la nueva fecha límite de la tarea (AAAA-MM-DD HH:mm): ");
        LocalDateTime newDeadline = LocalDateTime.parse(sc.nextLine());
        System.out.print("¿La tarea tiene prioridad? (S/N): ");
        boolean newHasPriority = sc.nextLine().equalsIgnoreCase("S");

        taskManager.edit(taskManager.getItem(tasks, index), taskManager.createTask(newTitle, newDescription, newDeadline, newHasPriority));
        System.out.println("¡La tarea fue editada exitosamente!");

    }

    private static void deleteTask() {
        System.out.println("\n==========================================================================");
        System.out.println("=========================== Eliminar Tarea ===============================");
        System.out.println("==========================================================================\n");

        System.out.print("Por favor ingrese el título de la tarea a eliminar: ");
        String taskTitle = sc.nextLine();

        List<?> tasks = taskManager.searchItem(taskTitle);

        if(tasks.isEmpty()) {
            System.out.println("Tarea no encontrada.");
        } else {
            printResults(tasks);

            System.out.print("Por favor ingrese el índice de la tarea a eliminar: ");

            int index = sc.nextInt();

            while(index < 0 || index >= tasks.size()) {
                System.out.println("Índice inválido.");
                System.out.print("Por favor ingrese el índice de la tarea a eliminar: ");
                index = sc.nextInt();
            }

            taskManager.delete(taskManager.getItem(tasks, index));
            System.out.println("¡La tarea fue eliminada exitosamente!");
        }
    }

    private static void undo() {
        System.out.println("\n==========================================================================");
        System.out.println("============================= Undo =======================================");
        System.out.println("==========================================================================\n");

        taskManager.undo();
        System.out.println("Operación deshecha.");
    }

    private static void exit() {
        System.out.println("\n==========================================================================");
        System.out.println("===== Gracias por usar el Administrador de Tareas. ¡Hasta luego! =========");
        System.out.println("==========================================================================\n");
    }

    private static void printResults(List<?> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i).toString());
        }
        System.out.println();
    }

    public static void main (String [] args){
        start();
    }
}