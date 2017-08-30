package com.example.ing_richardavid.seccion_07_sqlite.models.DAO;

import com.example.ing_richardavid.seccion_07_sqlite.models.entities.Customer;

import java.util.List;

/**
 * Created by ing_richardavid on 29-08-17.
 */

public interface CustomerDAO {
    boolean insertCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    Customer selectCustomer(Customer customer);
    boolean deleteCustomer(Customer customer);
    List<Customer> getCustomer();
}
