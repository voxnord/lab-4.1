package modules;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String email;
    private String phoneNum;
    private List<Order> order;

    public Customer(String name, String email, String phoneNum) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.order = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public String getEmail() {
        return email;
    }

//    public void setEmail(String email) {
//        this.email = email;
//    }

    public String getPhoneNum() {
        return phoneNum;
    }

//    public void setPhoneNum(String phoneNum) {
//        this.phoneNum = phoneNum;
//    }

    public List<Order> getOrder() {
        return order;
    }

    public void addOrder(Order order) {
        if (order != null) {
            this.order.add(order);
        }
    }

    @Override
    public String toString() {
        return "Customer[" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", order=" + order +
                ']';
    }
}
