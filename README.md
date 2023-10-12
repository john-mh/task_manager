# Task and Reminder Management System 📅

This project is a task and reminder management system that allows users to add, organize, and manage their pending tasks and reminders. The management system includes key components and functionalities for efficient task and reminder management. Here are the main aspects of the project:

## Features 📋

### Task and Reminder Storage 🗄️

The system uses a hash table to store tasks and reminders. Each entry in the hash table contains the following information:
- Title
- Description
- Due date
- Priority

### User Interface 🖥️

The project includes a user interface that allows users to perform the following actions:
- Add new tasks and reminders.
- Modify existing tasks and reminders.
- Delete tasks and reminders.
- View a list of all tasks and reminders, sorted by due date or priority.
- Utilize the Heapsort algorithm to sort tasks and reminders.

### Priority Management ⭐

The system categorizes tasks into two categories:
- Priority Tasks: Priority queues are used to organize tasks based on their level of importance. Important tasks are handled first.
- Non-Priority Tasks: Tasks with no priority are managed on a first-come, first-served basis (FIFO).

### Undo Functionality ↩️

The project implements an "undo" function that allows users to revert actions performed in the system. The general approach to implementing this function involves:

1. Creating a stack of actions to keep track of user actions.
2. Recording each action performed by the user on the stack.
3. Implementing a method that allows users to undo the last action. This method pops the last recorded action and reverses the corresponding action.

## How to Use the System 🚀

To use this task and reminder management system, follow these steps:

1. Run the application.
2. Add new tasks or reminders, specifying title, description, due date, and priority.
3. Modify existing tasks or reminders if needed.
4. Delete tasks or reminders when they are no longer relevant.
5. View a list of all tasks and reminders and sort them by due date or priority.
6. Use the "Undo" function if you want to revert the last action performed.

## Technologies Used 💻

The system has been developed using technologies such as:

- Programming language: Java
- IDE: IntelliJ IDEA
- User interface: cmd
- Data structures (hash table, heap, priority queue, stack)

## Contributions 🤝

If you wish to contribute to this project, feel free to open issues and pull requests in the project's GitHub repository.
