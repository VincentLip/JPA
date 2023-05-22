package org.example;

import org.example.model.People;
import org.example.model.Professor;
import org.example.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    private static EntityManagerFactory emf= Persistence.createEntityManagerFactory("heritage");
    private static EntityManager em = emf.createEntityManager();
    public static void main( String[] args )
    {

        em.getTransaction().begin();

        Student student = new Student("test");
        em.persist(student);
        System.out.println(student);

        Professor professor = new Professor("anglais");
        em.persist(professor);
        System.out.println(professor);

        em.getTransaction().commit();
        em.close();
        emf.close();

    }
}
