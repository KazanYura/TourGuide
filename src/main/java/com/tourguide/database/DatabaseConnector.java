package com.tourguide.database;

import java.util.List;
import com.tourguide.containers.general.Place;
import com.tourguide.containers.dao.PlaceDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseConnector {
    private static SessionFactory factory;
    private static DatabaseConnector dataBaseConnector;
    public DatabaseConnector(){
        try {
            factory = new Configuration().
                    configure().
                    addResource("PlaceDAO.hbm.xml").
                    buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static DatabaseConnector getInstance(){
        if (dataBaseConnector == null) {
            dataBaseConnector = new DatabaseConnector();
        }
        return dataBaseConnector;
    }

    /* Method to CREATE an Place in the database */
    public Integer addPlace(PlaceDAO p){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer PlaceID = null;

        try {
            tx = session.beginTransaction();
            PlaceID = (Integer) session.save(p);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return PlaceID;
    }

    /* Method to  READ all the Places */
    public List<PlaceDAO> listPlaces( ) {

        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            List<PlaceDAO> places = session.createQuery("FROM PlaceDAO").getResultList();
            tx.commit();
            return places;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }


    /* Method to DELETE an Place from the records */
    public void deletePlace(Integer PlaceID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Place place = session.get(Place.class, PlaceID);
            session.delete(place);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}