package org.example.services;

import org.example.entities.Comment;
import org.example.interfaces.Repository;

import java.util.List;

public class CommentService extends  BaseService implements Repository<Comment> {

    public CommentService(){
        super();
    }

    @Override
    public boolean create(Comment o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Comment o) {
        return false;
    }

    @Override
    public boolean delete(Comment o) {
        return false;
    }

    @Override
    public Comment findById(int id) {
        return null;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }
}
