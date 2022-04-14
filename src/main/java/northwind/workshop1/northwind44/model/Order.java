package northwind.workshop1.northwind44.model;

import java.math.BigDecimal;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Order {
    private Integer id;
    private String order_date;
    private String ship_name;
    private String ship_address;
    private String ship_city;
    private String first_name;
    private String last_name;
    private BigDecimal shipping_fees;

    public BigDecimal getShipping_fees() {
        return shipping_fees;
    }
    public void setShipping_fees(BigDecimal shipping_fees) {
        this.shipping_fees = shipping_fees;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getOrder_date() {
        return order_date;
    }
    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }
    public String getShip_name() {
        return ship_name;
    }
    public void setShip_name(String ship_name) {
        this.ship_name = ship_name;
    }
    public String getShip_address() {
        return ship_address;
    }
    public void setShip_address(String ship_address) {
        this.ship_address = ship_address;
    }
    public String getShip_city() {
        return ship_city;
    }
    public void setShip_city(String ship_city) {
        this.ship_city = ship_city;
    }

    public static Order createOrder(SqlRowSet result) {
        Order order = new Order();
        order.setId(result.getInt("id"));
        order.setOrder_date(result.getString("date"));
        order.setShip_address(result.getString("shipAddress"));
        order.setShip_city(result.getString("shipCity"));
        order.setShip_name(result.getString("shipName"));
        order.setFirst_name(result.getString("custFN"));
        order.setLast_name(result.getString("custLN"));
        order.setShipping_fees(result.getBigDecimal("shippingFee"));

        // "select orders.order_date as date, orders.customer_id as id, orders.ship_name as shipName, orders.ship_address as shipAddress, orders.ship_city as shipCity, customers.first_name as custFN, customers.last_name as lastLN, orders.shipping_fee as shippingFee from orders join customers on orders.customer_id = customers.id where customers.id = ?";

        return order;
    }
    
}
