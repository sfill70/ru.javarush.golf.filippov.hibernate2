package ru.javarush.hibernate2.handler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javarush.hibernate2.dao.*;
import ru.javarush.hibernate2.entity.*;
import ru.javarush.hibernate2.factory.MySessionFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CustomerHandler {

    private final SessionFactory sessionFactory;
    private final ActorDAO actorDAO;
    private final AddressDAO addressDAO;
    private final CategoryDAO categoryDAO;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final CustomerDAO customerDAO;
    private final FilmDAO filmDAO;
    private final FilmTextDAO filmTextDAO;
    private final InventoryDAO inventoryDAO;
    private final LanguageDAO languageDAO;
    private final PaymentDAO paymentDAO;
    private final RentalDAO rentalDAO;
    private final StaffDAO staffDAO;
    private final StoreDAO storeDAO;

    public CustomerHandler() {
        sessionFactory = MySessionFactory.getSessionFactory();
        actorDAO = new ActorDAO(sessionFactory);
        addressDAO = new AddressDAO(sessionFactory);
        categoryDAO = new CategoryDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);
        customerDAO = new CustomerDAO(sessionFactory);
        filmDAO = new FilmDAO(sessionFactory);
        filmTextDAO = new FilmTextDAO(sessionFactory);
        inventoryDAO = new InventoryDAO(sessionFactory);
        languageDAO = new LanguageDAO(sessionFactory);
        paymentDAO = new PaymentDAO(sessionFactory);
        rentalDAO = new RentalDAO(sessionFactory);
        staffDAO = new StaffDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);
    }

    public Customer createCustomer() {
        String firstName = getRandomCustomer().getFirstName();
        String lastName = getRandomCustomer().getLastName();
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Store store = storeDAO.getItems(0, 1).get(0);

            City city = cityDAO.getByName("Aurora");

            Address address = new Address();
            address.setAddress("32 Moon");
            address.setPhone("448477190408");
            address.setCity(city);
            address.setDistrict("Okinawa");
            addressDAO.save(address);

            Customer customer = new Customer();
            customer.setActive(true);
            customer.setEmail("jes.fun@gmail.com");
            customer.setAddress(address);
            customer.setStore(store);
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customerDAO.save(customer);

            session.getTransaction().commit();
            return customer;
        }
    }

    public Customer getRandomCustomer() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = customerDAO.getRandomItem();
            session.getTransaction().commit();
            return customer;
        }
    }

}
