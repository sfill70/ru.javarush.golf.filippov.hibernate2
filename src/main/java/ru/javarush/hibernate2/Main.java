package ru.javarush.hibernate2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javarush.hibernate2.dao.FilmDAO;
import ru.javarush.hibernate2.entity.Customer;
import ru.javarush.hibernate2.entity.Film;
import ru.javarush.hibernate2.factory.MySessionFactory;
import ru.javarush.hibernate2.handler.CustomerHandler;
import ru.javarush.hibernate2.handler.FilmHandler;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Set;

public class Main {
    FilmHandler filmHandler;
    CustomerHandler customerHandler;

    public Main() {
        filmHandler = new FilmHandler();
        customerHandler = new CustomerHandler();
    }



    public static void main(String[] args){

        Main main = new Main();
        main.newFilmWasMade();
        main.createCustomer();

/*        SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        FilmDAO filmDAO = new FilmDAO(sessionFactory);

        Query query = session.createQuery("select concat( lastName, firstName ) from Actor");

        for (Object st: query.getResultList()
             ) {
            System.out.println((String) st);
        }

        Set<Film> filmList = filmDAO.getItemsRandom(0,20);
        for (Film film:filmList
             ) {
            System.out.println(film.getTitle());
        }

        Film film = filmDAO.getById(115);
        System.out.println(film.getTitle());

        Film film1 = filmDAO.getRandomItem();
        System.out.println(film1.getTitle());

        session.getTransaction().commit();*/
        /*List<Actor> list = session.createQuery("from " + Actor.class.getName()).list();

        for (Actor act:list
             ) {
            System.out.println(act.getLastUpdate());
        }*/
//        System.out.println(list);



            /*Actor actor =  session.get(Actor.class, (short)2);
        System.out.println(actor.getFirstName());*/

//        System.out.println(main.getRandomCustomer().getFirstName());

    }

    public void newFilmWasMade(){
        filmHandler.newFilmWasMade();
    }

    public Customer createCustomer(){
        return customerHandler.createCustomer();
    }

    public Customer getRandomCustomer(){
        return customerHandler.getRandomCustomer();
    }
}