// package northwind.workshop1.northwind44;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import jakarta.json.JsonArray;
// import northwind.workshop1.northwind44.service.NorthwindService;

// @SpringBootTest
// class ServiceTest {

//     @Autowired
//     private NorthwindService northwindSvc;

//     @Test
//     void shouldReturn5Customers() {
//         Integer limit = 5;
//         JsonArray customers = northwindSvc.getCustomerList(5, 0);
//         assertEquals(limit, customers.size());
//     }
    
// }
