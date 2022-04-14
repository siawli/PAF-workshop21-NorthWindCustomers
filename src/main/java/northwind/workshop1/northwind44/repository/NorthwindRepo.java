package northwind.workshop1.northwind44.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import northwind.workshop1.northwind44.model.Customer;
import northwind.workshop1.northwind44.model.Order;

import static northwind.workshop1.northwind44.repository.Queries.*;

@Repository
public class NorthwindRepo {

    @Autowired
    private JdbcTemplate template;

    public List<Customer> getListOfCustomers(Integer offset, Integer limit) {
        SqlRowSet result = template.queryForRowSet(SQL_GET_CUSTOMERS, limit, offset);

        List<Customer> customerList = new LinkedList<>();
        while (result.next()) {
            Customer customer = Customer.createCustomer(result);
            customerList.add(customer);
        }
        return customerList;
    }

    public Optional<Customer> getCustomerViaId(Integer id) {
        SqlRowSet result = template.queryForRowSet(SQL_GET_CUSTOMER_BY_ID, id);
        
        Customer customer = new Customer();

        if (result.next()) {
            customer = Customer.createCustomer(result);
            return Optional.of(customer);
        }

        return Optional.empty();
    }

    public Optional<List<Order>> getOrdersOfCustomer(Integer id) {
        SqlRowSet result = template.queryForRowSet(SQL_ORDERS_OF_CUSTOMER, id);

        List<Order> ordersOfCustomer = new LinkedList<>();

        if (!result.next()) {
            return Optional.empty();
        }

        while (result.next()) {
            Order order = Order.createOrder(result);
            ordersOfCustomer.add(order);
        }

        return Optional.of(ordersOfCustomer);

    }
    
}
