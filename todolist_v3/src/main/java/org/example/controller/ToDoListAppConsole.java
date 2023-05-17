package org.example.controller;

import org.example.impl.TaskDAOImpl;
import org.example.impl.UserDAOImpl;
import org.example.model.Task;
import org.example.model.TaskInfo;
import org.example.model.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoListAppConsole {

    private static EntityManagerFactory entityManagerFactory;
    private static TaskDAOImpl taskDAO;
    private static UserDAOImpl userDAO;

    public static void main() {
        entityManagerFactory = Persistence.createEntityManagerFactory("todolist");
        taskDAO = new TaskDAOImpl(entityManagerFactory);
        userDAO = new UserDAOImpl(entityManagerFactory);

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("#### To Do List ####");
            System.out.println("1. Ajouter une tâche à la liste");
            System.out.println("2. Afficher toutes les tâches de la liste");
            System.out.println("3. Marquer une tâche comme terminée");
            System.out.println("4. Supprimer une tâche de la liste");
            System.out.println("5. Ajouter un utilisateur");
            System.out.println("6. Afficher toutes les tâches d'un utilisateur");
            System.out.println("7. Supprimer un utilisateur et toutes ses tâches");
            System.out.println("0. Quitter l'application");
            System.out.println("Choix : ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consomme la nouvelle ligne

            switch (choice){
                case 1:
                    addTask(scanner);
                    break;
                case 2:
                    displayTasks();
                    break;
                case 3:
                    markTaskAsCompleted(scanner);
                    break;
                case 4:
                    deleteTask(scanner);
                    break;
                case 5 :
                    addUser(scanner);
                    break;
                case 6:
                    displayUsers(scanner);
                    break;
                case 7:
                    deleteUser(scanner);
                    break;
                case 0:
                    System.out.println("Bye");
                    entityManagerFactory.close();
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");

            }

        }while (choice != 0);
    }

    private static void addTask(Scanner scanner){
        System.out.println("Entrer le titre de la tâche : ");
        String title = scanner.nextLine();

        System.out.println("Entrer la description de la tâche : ");
        String description = scanner.nextLine();

        System.out.println("Date limite de la tâche : (dd.MM.yyyy)");
        String dueDateStr = scanner.nextLine();

        LocalDate dueDate = LocalDate.parse(dueDateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        System.out.println("Priorité de la tâche : ");
        int priority = scanner.nextInt();
        scanner.nextLine();

        System.out.println("ID de l'utilisateur : ");
        Long id = scanner.nextLong();
        scanner.nextLine();


        // Creation de la tache
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);

        //Creation de la taskinfo
        TaskInfo taskInfo = new TaskInfo(description,dueDate,priority);

        List<Task> taskList = new ArrayList<>();
        taskList.add(task);



        // Mise en relation
        task.setTaskInfo(taskInfo);
        taskInfo.setTask(task);
        task.setUser(userDAO.getUser(id));

        User user = userDAO.getUser(id);
        user.setTaskList(taskList);


        if(taskDAO.addTask(task)){
            System.out.println("Tâche ajoutée avec succès !");
        }else {
            System.out.println("Erreur");
        }
    }

    private static void displayTasks() {
        List<Task> tasks = taskDAO.getAllTasks();

        if (tasks.isEmpty()) {
            System.out.println("Aucune tâche trouvée.");
        } else {
            System.out.println("=== Liste des tâches ===");
            for (Task task : tasks) {
                System.out.println("###########");
                System.out.println(task.getId() + ". " + task.getTitle() + " (" + (task.isCompleted() ? "Terminée" : "En cours") + ")");
                System.out.println(task.getTaskInfo().toString());
                System.out.println("###########");
            }
        }
    }

    private static void deleteTask(Scanner scanner){
        System.out.println("Entrez l'ID de la tâche à supprimer : ");
        Long taskId  = scanner.nextLong();
        scanner.nextLine();

        if (taskDAO.deleteTask(taskId)){
            System.out.println("Suppression OK");
        }else {
            System.out.println("Erreur");
        }
    }

    private static void markTaskAsCompleted(Scanner scanner){
        System.out.println("Entrez l'ID de la tâche à supprimer : ");
        Long taskId  = scanner.nextLong();
        scanner.nextLine();

        if (taskDAO.markTaskAsCompleted(taskId)){
            System.out.println("Modification OK");
        }else {
            System.out.println("Erreur");
        }
    }

    private static void addUser(Scanner scanner){
        System.out.println("Entrer le nom de l'utilisateur : ");
        String name = scanner.nextLine();

        User user = new User();
        user.setUsername(name);

        if(userDAO.addUser(user)){
            System.out.println("Utilisateur ajouté avec succès !");
        }else {
            System.out.println("Erreur");
        }
    }

    private static void displayUsers(Scanner scanner) {
        System.out.println("ID de l'utilisateur : ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        User user = userDAO.getUser(id);

        if (user == null) {
            System.out.println("Aucune tâche trouvée.");
        } else {
            System.out.println("=== Liste des tâches d'un utilisateur ===");
            System.out.println("###########");
            System.out.println(user.getId() + ". " + user.getUsername() );
            List<Task> taskList = user.getTaskList();
            for (Task t : taskList )
            {
                System.out.println(t);
            }
            System.out.println("###########");

        }
    }

    private static void deleteUser(Scanner scanner){
        System.out.println("Entrez l'ID de l'utilisateur  à supprimer : ");
        Long userId  = scanner.nextLong();
        scanner.nextLine();

        if (userDAO.deleteUser(userId)){
            System.out.println("Suppression OK");
        }else {
            System.out.println("Erreur");
        }
    }

}
