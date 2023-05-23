package org.example.interfaces;

import org.example.entities.Produit;

import java.util.Date;
import java.util.List;

public interface Repository<T> {

    boolean create(T o);

    boolean update(T o);

    boolean delete(T o);

    T findById(int id);

    List<T> getAllProduct();

    List<T> getProductOverPrice(double d);

    List<T> getProductBetweenDate(Date d,Date d1);
}