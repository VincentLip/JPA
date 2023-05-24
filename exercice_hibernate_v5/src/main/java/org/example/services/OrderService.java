package org.example.services;

import org.example.entities.Order;
import org.example.interfaces.Repository;

import java.util.List;

public class OrderService extends BaseService implements Repository<Order> {

    public OrderService(){
        super();
    }
    @Override
    public boolean create(Order o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Order o) {
        return false;
    }

    @Override
    public boolean delete(Order o) {
        return false;
    }

    @Override
    public Order findById(int id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }
}
