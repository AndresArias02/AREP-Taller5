package jpa.demo.controllers;

import jpa.demo.models.Delivery;
import jpa.demo.services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for Delivery
 * @author: Andrés Arias
  */
@RestController
@RequestMapping(value = "/api")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService){
        this.deliveryService = deliveryService;
    }

    /**
     * Get all deliveries
     * @return List of deliveries
     */
    @GetMapping("/deliveries")
    public List<Delivery> getDeliveries(){
       return deliveryService.getAllDeliveries();
    }

    /**
     * Get a delivery by id
     * @param id id of the delivery
     * @return Delivery
     */
    @GetMapping("/deliveries/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable long id){
        return new ResponseEntity<>(deliveryService.getDeliveryById(id), HttpStatus.OK);
    }

    /**
     * Add a new delivery
     * @param delivery delivery to add
     */
    @PostMapping("/deliveries")
    public ResponseEntity<?> addDelivery(@RequestBody Delivery delivery){
        deliveryService.addDelivery(delivery);
        return new ResponseEntity<>(delivery, HttpStatus.CREATED);
    }

    /**
     * Update a delivery
     * @param id id of the delivery
     * @param delivery delivery to update
     */
    @PutMapping("/deliveries/{id}")
    public void updateDelivery(@PathVariable Long id, @RequestBody Delivery delivery){
        deliveryService.updateDelivery(id, delivery);
    }

    /**
     * Delete a delivery
     * @param id id of the delivery
     */
    @DeleteMapping("/deliveries/{id}")
    public void deleteDelivery(@PathVariable Long id){
        deliveryService.deleteDelivery(id);
    }

}
