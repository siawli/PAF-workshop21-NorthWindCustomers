package northwind.workshop1.northwind44.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import northwind.workshop1.northwind44.model.Customer;
import northwind.workshop1.northwind44.model.Order;
import northwind.workshop1.northwind44.service.NorthwindService;

@RestController
@RequestMapping("/api")
public class NorthwindRestController {

    @Autowired
    private NorthwindService northwindSvc;

    @GetMapping(path="/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCustomers(@RequestParam(name="offset", defaultValue="0") Integer offset,@RequestParam(name="limit", defaultValue = "5") Integer limit) {

        JsonArray jsonArrCustomers = northwindSvc.getCustomerList(limit, offset);
        return ResponseEntity.ok(jsonArrCustomers.toString());
        
    }

    @GetMapping(path="/customer/{customer_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCustomerById(@PathVariable Integer customer_id) {

        Optional<Customer> customerOpt = northwindSvc.getCustById(customer_id);

        JsonObject message;
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            message = northwindSvc.createJsonObj(customer);
            return ResponseEntity.ok(message.toString());
        } else {
            message = Json.createObjectBuilder()
                                .add("Error", "Customer does not exist in database")
                                .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message.toString());
        }
    }

    @GetMapping(path="customer/{customer_id}/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCustomerOrders(@PathVariable Integer customer_id) {

        Optional<List<Order>> ordersCustOpt = northwindSvc.getOrdersById(customer_id);
        if (ordersCustOpt.isPresent()) {
            List<Order> ordersCust = ordersCustOpt.get();
            JsonArrayBuilder jsonArrBuilder = Json.createArrayBuilder();
            for (Order o : ordersCust) {
                JsonObject orderObj = northwindSvc.createObject(o);
                jsonArrBuilder.add(orderObj);
            }
            JsonArray orders = jsonArrBuilder.build();
            return ResponseEntity.ok(orders.toString());
        } else {
            JsonArray error = Json.createArrayBuilder().build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.toString());
        }
    }
    
}
