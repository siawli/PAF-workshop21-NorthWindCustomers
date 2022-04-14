package northwind.workshop1.northwind44.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Customer {

    private Integer id;
    private String first_name;
    private String last_name;
    private String job_title;
    private String business_phone;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public String getJob_title() {
        return job_title;
    }
    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }
    public String getBusiness_phone() {
        return business_phone;
    }
    public void setBusiness_phone(String business_phone) {
        this.business_phone = business_phone;
    }

    public static Customer createCustomer(SqlRowSet result) {
        Customer customer = new Customer();
        customer.setFirst_name(result.getString("first_name"));
        customer.setLast_name(result.getString("last_name"));
        customer.setBusiness_phone(result.getString("business_phone"));
        customer.setJob_title(result.getString("job_title"));
        customer.setId(result.getInt("id"));

        return customer;
    }

}
