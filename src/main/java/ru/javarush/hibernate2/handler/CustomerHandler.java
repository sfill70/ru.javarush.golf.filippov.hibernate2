package ru.javarush.hibernate2.handler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javarush.hibernate2.dao.*;
import ru.javarush.hibernate2.entity.*;
import ru.javarush.hibernate2.factory.MySessionFactory;
import ru.javarush.hibernate2.util.Util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class CustomerHandler {

    private final SessionFactory sessionFactory;
    private final AddressDAO addressDAO;
    private final CityDAO cityDAO;
    private final CustomerDAO customerDAO;
    private final StoreDAO storeDAO;

    public CustomerHandler() {
        sessionFactory = MySessionFactory.getSessionFactory();
        addressDAO = new AddressDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);
        customerDAO = new CustomerDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);
    }

    public Customer createCustomer() {
        String firstName = getRandomCustomer().getFirstName();
        String lastName = getRandomCustomer().getLastName();
        String randomAddress = Util.randomAddress();
        String randomPhone = "+01" + ThreadLocalRandom.current().nextInt(100000000,999999999);
        String randomDistrict = Util.randomCity();
        String randomEmail = Util.randomEmail();
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Store store = storeDAO.getItems(0, 2).get(ThreadLocalRandom.current().nextInt(2));
            City city = cityDAO.getRandomItem();

            Address address = new Address();
            address.setAddress(randomAddress);
            address.setPhone(randomPhone);
            address.setCity(city);
            address.setDistrict(randomDistrict);
            addressDAO.save(address);

            Customer customer = new Customer();
            customer.setActive(true);
            customer.setEmail(randomEmail);
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
