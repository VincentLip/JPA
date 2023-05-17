package org.example.impl;

import org.example.dao.CategoryDAO;
import org.example.model.Category;
import org.example.model.Person;
import org.example.model.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    private EntityManagerFactory entityManagerFactory;

    public CategoryDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    @Override
    public void addCategory(Category category) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(category);
        transaction.commit();
        entityManager.close();

    }

    @Override
    public void deleteCategory(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Category category = entityManager.find(Category.class, id);
        if(category != null){
            entityManager.remove(category);
        }
        transaction.commit();
        entityManager.close();

    }

    @Override
    public List<Category> getAllCategorys() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Category> categories = entityManager.createQuery("SELECT c FROM Category c", Category.class).getResultList();
        entityManager.close();
        return categories;
    }

    public boolean addCategory(Long categoryId, Long taskId){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Task task = entityManager.find(Task.class,taskId);
        Category category = entityManager.find(Category.class,categoryId);
        category.addTask(task);
        task.getCategoryList().add(category);
        entityManager.persist(category);
        transaction.commit();
        entityManager.close();
        return true;
    }
}
