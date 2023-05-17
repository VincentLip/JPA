package org.example.impl;

import org.example.dao.UserDAO;
import org.example.model.Task;
import org.example.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private EntityManagerFactory entityManagerFactory;
    public UserDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public boolean addUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
            return true;
        }catch (Exception e){
            if(transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<User> users = entityManager.createQuery("SELECT u FROM User u",User.class).getResultList();
        entityManager.close();
        return users;
    }

    @Override
    public boolean deleteUser(Long userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            User user = entityManager.find(User.class,userId);
            if(user != null){
                entityManager.remove(user);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            if(transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public User getUser(Long userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT u FROM User u where u.id = :id");
        query.setParameter("id",userId);
        User user = (User) query.getSingleResult();
        entityManager.close();
        return  user;
    }
}
