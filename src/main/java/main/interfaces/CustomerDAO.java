package main.interfaces;

import main.models.Customer;

import java.util.List;

public interface CustomerDAO {
     void save(Customer customer);
     List<Customer> getAllCustomers();
     void update(Customer customer);
     void delete(Customer customer);

}
