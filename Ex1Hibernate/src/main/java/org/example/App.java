package org.example;

import org.example.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.getTransaction().begin();
        Product product = new Product("marque1","1",100,1,"01/01/2001");
        Product product1 = new Product("marque2","2",200,2,"02/02/2002");
        Product product2 = new Product("marque3","3",300,3,"03/03/2003");
        Product product3 = new Product("marque4","4",400,4,"04/04/2004");
        Product product4 = new Product("marque5","5",500,5,"05/05/2005");

        session.save(product);
        session.save(product1);
        session.save(product2);
        session.save(product3);
        session.save(product4);

        Product product5 = session.load(Product.class,2L);
        System.out.println(product5);

        Product product6 = session.load(Product.class,3L);
        session.delete(product6);

        Product product7 = session.load(Product.class,1L);
        product7.setMarque("marque7");
        product7.setDate("07/07/2007");
        product7.setPrice(700);
        product7.setReference("7");

        session.update(product7);

        session.getTransaction().commit();

        session.close();
        sessionFactory.close();


    }
}
