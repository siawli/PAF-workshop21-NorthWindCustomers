package northwind.workshop1.northwind44.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import northwind.workshop1.northwind44.model.Customer;
import northwind.workshop1.northwind44.model.Order;
import northwind.workshop1.northwind44.repository.NorthwindRepo;

@Service
public class NorthwindService {

    @Autowired
    private NorthwindRepo northwindRepo;
    
    public JsonArray getCustomerList(Integer limit, Integer offset) {
        List<Customer> listOfCustomers = northwindRepo.getListOfCustomers(offset, limit);

        JsonArrayBuilder jsonArrBuilder = Json.createArrayBuilder();
        for (Customer c : listOfCustomers) { 
            JsonObject jsonObjCus = createJsonObj(c);
            jsonArrBuilder.add(jsonObjCus);
        }
        JsonArray jsonArrListCustomers = jsonArrBuilder.build();

        return jsonArrListCustomers;

    }

    public Optional<Customer> getCustById(Integer id) {
        return northwindRepo.getCustomerViaId(id);
            }   

    public JsonObject createJsonObj(Customer c) {
        JsonObject jsonObjCus = Json.createObjectBuilder()
                                    .add("id", c.getId())
                                    .add("first_name", c.getFirst_name())
                                    .add("last_name", c.getLast_name())
                                    .add("job_title", c.getJob_title())
                                    .add("business phone", c.getBusiness_phone())
                                    .build();
        return jsonObjCus;
    }

    public Optional<List<Order>> getOrdersById(Integer id) {
        return northwindRepo.getOrdersOfCustomer(id);
    }

    public JsonObject createObject(Order o) {
        JsonObject jsonObjOrder = Json.createObjectBuilder()
                                    .add("first_name", o.getFirst_name())
                                    .add("last_name", o.getLast_name())
                                    .add("order_date", o.getOrder_date())
                                    .add("shipping address", o.getShip_address())
                                    .add("shipping_city", o.getShip_city())
                                    .add("shipping_name", o.getShip_name())
                                    .add("shipping_fee", o.getShipping_fees())
                                    .build();
        return jsonObjOrder;
    }
}
