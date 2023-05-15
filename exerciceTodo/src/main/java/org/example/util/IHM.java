package org.example.util;

import org.example.entity.Todo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class IHM {

    Scanner scanner;
    String choice;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_todo");

    public IHM() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        do {

            menu();
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    creationTaskAction();
                    break;
                case "2":
                    getAllTaskAction();
                    break;
                case "3":
                    updateTaskAction();
                    break;
                case "4":
                    deleteTaskAction();
                    break;
                default:
                    emf.close();

            }
        } while (!choice.equals("0"));
    }

    private void menu() {
        System.out.println("1. Ajouter une tâche à la liste");
        System.out.println("2. Afficher toutes les tâches de la liste ");
        System.out.println("3. Marquer une tâche comme terminée");
        System.out.println("4. Supprimer une tâche de la liste");
        System.out.println("0. Exit");
    }

    private void creationTaskAction(){

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        System.out.print("Merci de saisir le titre de la todo : ");
        String title = scanner.nextLine();
        Todo todo = new Todo(title,false);
        em.persist(todo);
        em.getTransaction().commit();
        em.close();

    }

    private void getAllTaskAction(){

        EntityManager em = emf.createEntityManager();
        List<Todo> todoList = null;
        todoList = em.createQuery("select t from Todo t ",Todo.class).getResultList();

        for (Todo t : todoList){
            System.out.println(t);
        }
        em.close();

    }
    private void deleteTaskAction(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        System.out.print("Merci de saisir l'id de la todo à supprimer : ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Todo todo = em.find(Todo.class,id);
        em.remove(todo);
        em.getTransaction().commit();
        em.close();
    }

    private void updateTaskAction(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        System.out.print("Merci de saisir l'id de la todo à modifier : ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Todo todo = em.find(Todo.class,id);
        todo.setStatut(!todo.isStatut());
        em.getTransaction().commit();
        em.close();
    }
}
