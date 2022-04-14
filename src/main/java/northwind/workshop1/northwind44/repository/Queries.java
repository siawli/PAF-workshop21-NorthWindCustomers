package northwind.workshop1.northwind44.repository;

public interface Queries {

    public static final String SQL_GET_CUSTOMERS = 
        "select * from customers limit ? offset ?";
    // select * from customers limit 4 offset 2;

    public static final String SQL_GET_CUSTOMER_BY_ID = 
        "select * from customers where id = ?";
    // select * from customers where id = 1;

    public static final String SQL_ORDERS_OF_CUSTOMER = 
        "select orders.order_date as date, orders.customer_id as id, orders.ship_name as shipName, orders.ship_address as shipAddress, orders.ship_city as shipCity, customers.first_name as custFN, customers.last_name as custLN, orders.shipping_fee as shippingFee from orders join customers on orders.customer_id = customers.id where customers.id = ?";
    // select orders.order_date as date, orders.customer_id,
    // orders.ship_name as shipName, orders.ship_address as shipAddress, orders.ship_city as shipCity,
    // customers.first_name as custFN, customers.last_name as lastLN 
    // from orders
    // join customers
    // on orders.customer_id = customers.id
    // where customers.id = 1;
    
}
