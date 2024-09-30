package jpa.demo.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


/**
 * Delivery class
 * @author: Andrés Arias
 */
@Entity
@Getter @Setter
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String address;
    private String price;
    private String size;
    private String description;

    /**
     * Default constructor
     */
    public Delivery() {
    }

    /**
     * Constructor with parameters
     * @param address address of the delivery
     * @param price price of the delivery
     * @param size size of the delivery
     * @param description description of the delivery
     */
    public Delivery(String address, String price, String size, String description) {
        this.address = address;
        this.price = price;
        this.size = size;
        this.description = description;
    }



}