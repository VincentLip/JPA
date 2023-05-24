package org.example.services;

import org.example.entities.Picture;
import org.example.interfaces.Repository;

import java.util.List;

public class PictureService extends BaseService implements Repository<Picture> {

    public PictureService(){
        super();
    }
    @Override
    public boolean create(Picture o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Picture o) {
        return false;
    }

    @Override
    public boolean delete(Picture o) {
        return false;
    }

    @Override
    public Picture findById(int id) {
        return null;
    }

    @Override
    public List<Picture> findAll() {
        return null;
    }
}
