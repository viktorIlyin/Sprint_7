package api.order;

import java.time.LocalDateTime;

public class Order {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;


    public Order(String[] color) {
        this.address = "ул. Пушкина, дом Колотушкина, 1";
        this.firstName = "Виктор";
        this.lastName = "Ильин";
        this.deliveryDate = LocalDateTime.now().toString();
        this.metroStation = "1";
        this.phone = "+79002281488";
        this.rentTime = 2;
        this.comment = "Кататься улыбаться";
        this.color = color;
    }
}
