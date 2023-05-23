package org.example.services;

import org.example.entities.Produit;
import org.example.interfaces.Repository;
import org.hibernate.query.Query;
import org.hibernate.type.*;

import java.awt.font.FontRenderContext;
import java.util.Date;
import java.util.List;

public class ProduitService extends BaseService implements Repository<Produit> {

    public ProduitService(){
        super();
    }

    @Override
    public boolean create(Produit o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Produit o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Produit o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Produit findById(int id) {
        Produit produit = null;
        session = sessionFactory.openSession();
        produit = (Produit) session.get(Produit.class, id);
        session.close();
        return produit;
    }

    @Override
    public List<Produit> getAllProduct() {
        session =sessionFactory.openSession();
        Query<Produit> produitQuery = session.createQuery("from Produit");
        List<Produit> produitList = produitQuery.list();
        return produitList;
    }

    @Override
    public List<Produit> getProductOverPrice(double d) {
        session =sessionFactory.openSession();
        Query<Produit> produitQuery = session.createQuery("from Produit where prix > ?1");
        produitQuery.setParameter(1,d, DoubleType.INSTANCE);
        List<Produit> produitList = produitQuery.list();
        return produitList;
    }

    @Override
    public List<Produit> getProductBetweenDate(Date d, Date d1) {
        session =sessionFactory.openSession();
        Query<Produit> produitQuery = session.createQuery("from Produit where dateAchat between ?1 and  ?2");
        produitQuery.setParameter(1,d);
        produitQuery.setParameter(2,d1);
        List<Produit> produitList = produitQuery.list();
        return produitList;
    }

    @Override
    public List<Produit> getProductByStock(int i) {
        session =sessionFactory.openSession();
        Query<Produit> produitQuery = session.createQuery("from Produit where stock < ?1");
        produitQuery.setParameter(1,i);
        List<Produit> produitList = produitQuery.list();
        return produitList;
    }

    @Override
    public List<Produit> getStockByBrand(String s) {
        session =sessionFactory.openSession();
        Query<Produit> produitQuery = session.createQuery("from Produit where marque = ?1");
        produitQuery.setParameter(1,s);
        List<Produit> produitList = produitQuery.list();
        return produitList;
    }

    @Override
    public double getAveragePriceProduct() {
        session =sessionFactory.openSession();
        double averagePrice = (double)session.createQuery("select avg(prix) from Produit").uniqueResult();
        return averagePrice;
    }

    @Override
    public List<Produit> getProductByBrand(String s) {
        session =sessionFactory.openSession();
        Query<Produit> produitQuery = session.createQuery("from Produit where marque = ?1");
        produitQuery.setParameter(1,s);
        List<Produit> produitList = produitQuery.list();
        return produitList;
    }

    @Override
    public boolean deleteProductByBrand(List<Produit> lp) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        for (Produit p : lp)
        {
            session.delete(p);
        }
        session.getTransaction().commit();
        return true;
    }


}
