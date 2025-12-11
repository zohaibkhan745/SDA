package modules.customer.controller;

import modules.customer.model.Customer;
import modules.customer.service.CustomerService;
import java.util.List;

public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    public Customer getCustomer(String id) { return service.findById(id); }
    public List<Customer> listCustomers() { return service.listAll(); }
}



