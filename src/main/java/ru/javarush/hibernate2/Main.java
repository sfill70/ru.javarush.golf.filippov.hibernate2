package ru.javarush.hibernate2;

import ru.javarush.hibernate2.entity.Customer;
import ru.javarush.hibernate2.handler.CustomerHandler;
import ru.javarush.hibernate2.handler.FilmHandler;
import ru.javarush.hibernate2.handler.InventoryHandler;

public class Main {
    private FilmHandler filmHandler;
    private CustomerHandler customerHandler;
    private InventoryHandler inventoryHandler;

    public Main() {
        filmHandler = new FilmHandler();
        customerHandler = new CustomerHandler();
        inventoryHandler = new InventoryHandler();
    }


    public static void main(String[] args) {
        Main main = new Main();
        for (int i = 0; i < 100; i++) {
            main.newFilmWasMade();
            Customer customer = main.createCustomer();
            main.customerRentInventory(main.getRandomCustomer());
            main.returnInventoryToStore();
        }
    }

    private void newFilmWasMade() {
        filmHandler.newFilmWasMade();
    }

    private Customer createCustomer() {
        return customerHandler.createCustomer();
    }

    private Customer getRandomCustomer() {
        return customerHandler.getRandomCustomer();
    }

    private void customerRentInventory(Customer customer) {
        inventoryHandler.customerRentInventory(customer);
    }

    private void returnInventoryToStore() {
        inventoryHandler.returnInventoryToStore();
    }
}