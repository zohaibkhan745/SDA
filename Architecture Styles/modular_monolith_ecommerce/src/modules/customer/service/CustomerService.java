package modules.customer.service;

import modules.customer.model.Customer;
import java.util.*;

public class CustomerService {
    private final Map<String, Customer> customers = new LinkedHashMap<>();

    public CustomerService() {
        addCustomer(new Customer("C001","Alice","alice@example.com"));
        addCustomer(new Customer("C002","Bob","bob@example.com"));
    }

    public Customer findById(String id) { return customers.get(id); }
    public void addCustomer(Customer c) { customers.put(c.getId(), c); }
    public List<Customer> listAll() { return new ArrayList<>(customers.values()); }
}

