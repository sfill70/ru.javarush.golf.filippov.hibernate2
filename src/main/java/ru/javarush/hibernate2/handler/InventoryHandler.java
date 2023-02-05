package ru.javarush.hibernate2.handler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javarush.hibernate2.dao.*;
import ru.javarush.hibernate2.entity.*;
import ru.javarush.hibernate2.factory.MySessionFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class InventoryHandler {
    private final SessionFactory sessionFactory;
    private final FilmDAO filmDAO;
    private final InventoryDAO inventoryDAO;
    private final PaymentDAO paymentDAO;
    private final RentalDAO rentalDAO;
    private final StoreDAO storeDAO;

    public InventoryHandler() {
        sessionFactory = MySessionFactory.getSessionFactory();
        filmDAO = new FilmDAO(sessionFactory);
        inventoryDAO = new InventoryDAO(sessionFactory);
        paymentDAO = new PaymentDAO(sessionFactory);
        rentalDAO = new RentalDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);
    }

    public void customerRentInventory(Customer customer) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Film film = filmDAO.getFirstAvailableFilmForRent();
            Store store = storeDAO.getItems(0, 2).get(ThreadLocalRandom.current().nextInt(2));

            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(store);
            inventoryDAO.save(inventory);

            Staff staff = store.getStaff();

            Rental rental = new Rental();
            rental.setRentalDate(LocalDateTime.now());
            rental.setCustomer(customer);
            rental.setInventory(inventory);
            rental.setStaff(staff);
            rentalDAO.save(rental);

            Payment payment = new Payment();
            payment.setRental(rental);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setCustomer(customer);
            payment.setAmount(BigDecimal.valueOf(44.44));
            payment.setStaff(staff);
            paymentDAO.save(payment);

            session.getTransaction().commit();
        }
    }

    public void returnInventoryToStore() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Rental rental = rentalDAO.getAnyUnreturnedRental();
            rental.setReturnDate(LocalDateTime.now());
            rentalDAO.save(rental);

            session.getTransaction().commit();
        }
    }


}
