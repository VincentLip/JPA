package org.example.impl;

import org.example.dao.AccountDAO;
import org.example.model.Account;
import org.example.model.Agency;
import org.example.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {

    private EntityManagerFactory entityManagerFactory;

    public AccountDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    public boolean addAccount(Account account) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(account);
        transaction.commit();
        entityManager.close();
        return true;

    }

    public boolean addAccount(Account account,Long agencyId,Long customerId) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Agency agency = entityManager.find(Agency.class,agencyId);
        Customer customer = entityManager.find(Customer.class,customerId);
        account.setAgency(agency);
        account.addCustomer(customer);
        customer.getAccounts().add(account);
        entityManager.persist(account);
        transaction.commit();
        entityManager.close();
        return true;
    }


}
