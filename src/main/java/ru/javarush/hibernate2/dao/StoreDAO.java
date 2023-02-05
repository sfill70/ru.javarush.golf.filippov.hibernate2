package ru.javarush.hibernate2.dao;


import ru.javarush.hibernate2.entity.Store;
import org.hibernate.SessionFactory;

public class StoreDAO extends GenericDAO<Store>{
    public StoreDAO(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}
