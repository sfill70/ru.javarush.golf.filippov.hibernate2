package ru.javarush.hibernate2.dao;


import ru.javarush.hibernate2.entity.Language;
import org.hibernate.SessionFactory;

public class LanguageDAO extends GenericDAO<Language>{
    public LanguageDAO(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
