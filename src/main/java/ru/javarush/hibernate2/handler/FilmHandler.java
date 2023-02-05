package ru.javarush.hibernate2.handler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javarush.hibernate2.dao.*;
import ru.javarush.hibernate2.entity.*;
import ru.javarush.hibernate2.factory.MySessionFactory;
import ru.javarush.hibernate2.util.Util;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class FilmHandler {
    private final SessionFactory sessionFactory;
    private final ActorDAO actorDAO;
    private final CategoryDAO categoryDAO;
    private final FilmDAO filmDAO;
    private final FilmTextDAO filmTextDAO;
    private final LanguageDAO languageDAO;


    public FilmHandler() {
        sessionFactory = MySessionFactory.getSessionFactory();
        actorDAO = new ActorDAO(sessionFactory);
        categoryDAO = new CategoryDAO(sessionFactory);
        filmDAO = new FilmDAO(sessionFactory);
        filmTextDAO = new FilmTextDAO(sessionFactory);
        languageDAO = new LanguageDAO(sessionFactory);
    }

    public void newFilmWasMade() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Language language = languageDAO.getItemsRandom(0, 20).stream().unordered().findAny().get();
            String title = Util.getFilmName();
            String description = filmDAO.getItemsRandom(0, 10).stream().findAny().get().getDescription();

            Film film = new Film();
            film.setActors(actorDAO.getItemsRandom(1, 15));
            film.setRating(Rating.NC17);
            film.setSpecialFeatures(Set.of(Feature.TRAILERS, Feature.COMMENTARIES));
            film.setLength((short) 123);
            film.setReplacementCost(BigDecimal.TEN);
            System.out.println(language.getName());
            film.setLanguage(language);
            film.setDescription(description);
            film.setTitle(title);
            film.setOriginalLanguage(language);
            film.setCategories(categoryDAO.getItemsRandom(0, 5));
            film.setYear(Year.now());
            film.setRentalDuration((byte) ((ThreadLocalRandom.current().nextInt(6)) + 1));
            film.setRentalRate(BigDecimal.ZERO);
            filmDAO.save(film);

            FilmText filmText = new FilmText();
            filmText.setFilm(film);
            filmText.setId(film.getId());
            filmText.setDescription(description);
            filmText.setTitle(title);
            filmTextDAO.save(filmText);

            session.getTransaction().commit();
        }
    }

}
