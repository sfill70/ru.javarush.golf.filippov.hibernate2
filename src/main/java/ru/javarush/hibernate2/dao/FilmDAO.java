package ru.javarush.hibernate2.dao;


import ru.javarush.hibernate2.entity.Film;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class FilmDAO extends GenericDAO<Film>{
    public FilmDAO(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }

    public Film getFirstAvailableFilmForRent() {
        Query<Film> query = getCurrentSession().createQuery("select f from Film f " +
                "where f.id not in (select distinct film.id from Inventory)", Film.class);
        if (query.list().isEmpty()) {
            query = getCurrentSession().createQuery("select f from Film f where f.id in " +
                    "(select i.film.id from Inventory i where i.id in " +
                    "(select distinct r.inventory.id from Rental r where r.returnDate is not null))", Film.class);}
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
