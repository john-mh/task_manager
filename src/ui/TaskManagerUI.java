package ui;

import model.TaskManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class TaskManagerUI {

    public static TaskManager taskManager = new TaskManager();
    private static Scanner sc = new Scanner(System.in);

    public static void start() {
        System.out.println("\n==========================================================================");
        System.out.println("=============== !Welcome to the Task Manager! ==================");
        System.out.println("==========================================================================\n");

        while (true) {
            System.out.println("Options:");
            System.out.println("1. Create task");
            System.out.println("2. Create reminder");
            System.out.println("3. Edit task or reminder");
            System.out.println("4. Delete task or reminder");
            System.out.println("5. Undo");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            while(choice < 1 || choice > 6) {
                System.out.println("Invalid option.");
                System.out.print("Select an option: ");
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
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public static void createTask() {

        System.out.println("\n==========================================================================");
        System.out.println("=========================== Create Task ==================================");
        System.out.println("==========================================================================\n");

        System.out.print("Please enter the title of the task: ");
        String title = sc.nextLine();
        System.out.print("Please enter the task description: ");
        String description = sc.nextLine();
        System.out.print("Please enter the task due date (YYYYY-MM-DD HH:mm): ");
        LocalDateTime deadline = LocalDateTime.parse(sc.nextLine());
        System.out.print("Does the task have priority (Y/N): ");

        while (!sc.hasNext("[SsNn]")) {
            System.out.println("Invalid option.");
            System.out.print("Does the task have priority (Y/N): ");
            sc.next();
        }

        boolean hasPriority = sc.nextLine().equalsIgnoreCase("S");

        taskManager.add(taskManager.createTask(title, description, deadline, hasPriority));

        System.out.println("The task has been successfully created!");
    }

    private static void createReminder() {
        System.out.println("\n==========================================================================");
        System.out.println("=========================== Create Reminder ===========================");
        System.out.println("==========================================================================\n");

        System.out.print("Please enter the title of the reminder: ");
        String title = sc.nextLine();
        System.out.print("Please enter the description of the reminder: ");
        String description = sc.nextLine();
        System.out.print("Please enter the reminder deadline date (YYYYY-MM-DD HH:mm): ");
        LocalDateTime deadline = LocalDateTime.parse(sc.nextLine());
        System.out.print("Does the reminder have priority (Y/N): ");

        while (!sc.hasNext("[SsNn]")) {
            System.out.println("Invalid option.");
            System.out.print("Does the reminder have priority (Y/N): ");
            sc.next();
        }

        boolean hasPriority = sc.nextLine().equalsIgnoreCase("S");

        taskManager.add(taskManager.createReminder(title, description, deadline, hasPriority));

        System.out.println("The reminder has been successfully created!");
    }

    public static void editTask() {
        System.out.println("\n==========================================================================");
        System.out.println("=========================== Edit Task and Reminder=================================");
        System.out.println("==========================================================================\n");

        System.out.print("Please enter the title of the task to be edited: ");
        String taskTitle = sc.nextLine();

        List<?> tasks = taskManager.searchItem(taskTitle);
        int index = 0;

        if(tasks.isEmpty()) {
            System.out.println("Task not found.");
        } else {
            printResults(tasks);

            System.out.print("Please enter the index of the task to be edited: ");

            index = sc.nextInt();

            while(index < 0 || index >= tasks.size()) {
                System.out.println("Invalid index.");
                System.out.print("Please enter the index of the task to be edited: ");
                index = sc.nextInt();
            }
        }

        System.out.print("Please enter the new task title: ");
        String newTitle = sc.nextLine();
        System.out.print("Please enter new task description: ");
        String newDescription = sc.nextLine();
        System.out.print("Please enter the new task deadline (YYYYY-MM-DD HH:mm): ");
        LocalDateTime newDeadline = LocalDateTime.parse(sc.nextLine());
        System.out.print("Does the task have priority (Y/N): ");
        boolean newHasPriority = sc.nextLine().equalsIgnoreCase("S");

        taskManager.edit(taskManager.getItem(tasks, index), taskManager.createTask(newTitle, newDescription, newDeadline, newHasPriority));
        System.out.println("The task was successfully edited!");

    }

    public static void deleteTask() {
        System.out.println("\n==========================================================================");
        System.out.println("=========================== Delete Task or reminder===============================");
        System.out.println("==========================================================================\n");

        System.out.print("Please enter the title of the task to be deleted: ");
        String taskTitle = sc.nextLine();

        List<?> tasks = taskManager.searchItem(taskTitle);

        if (tasks.isEmpty()) {
            System.out.println("Task not found.");
        } else {
            printResults(tasks);

            System.out.print("Please enter the index of the task to be deleted: ");

            int index = sc.nextInt();

            while (index < 0 || index >= tasks.size()) {
                System.out.println("Invalid index.");
                System.out.print("Please enter the index of the task to be deleted: ");
                index = sc.nextInt();
            }


            if (!taskManager.getTable().isEmpty()) {
                taskManager.delete(taskManager.getItem(tasks, index));
                System.out.println("The task was successfully eliminated!");
            }
        }
    }


    public static void undo() {
        System.out.println("\n==========================================================================");
        System.out.println("============================= Undo =======================================");
        System.out.println("==========================================================================\n");

        taskManager.undo();
        System.out.println("Operation undone.");
    }

    private static void exit() {
        System.out.println("\n==========================================================================");
        System.out.println("===== Thank you for using the Task Manager, see you later! =========");
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