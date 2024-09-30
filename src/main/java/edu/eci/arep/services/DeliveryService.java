package edu.eci.arep.services;

import edu.eci.arep.models.Delivery;
import edu.eci.arep.repositorys.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Delivery
 * @author: Andrés Arias
 */
@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    /**
     * Method to get all deliveries
     * @return List of deliveries
     */
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    /**
     * Method to get a delivery by id
     * @param id id of the delivery
     * @return Delivery
     */
    public Delivery getDeliveryById(long id){
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        if(delivery.isPresent()){
            return delivery.get();
        }
        return null;
    }

    /**
     * Method to add a new delivery
     * @param delivery delivery to add
     */
    public void addDelivery(Delivery delivery){
        deliveryRepository.save(delivery);
    }

    /**
     * Method to update a delivery
     * @param delivery delivery to update
     */
    public void updateDelivery(Long id, Delivery delivery){
        Optional<Delivery> deliveryToUpdate = deliveryRepository.findById(id);
        if(deliveryToUpdate.isPresent()){
            Delivery existingDelivery = deliveryToUpdate.get();

            //  Uodate the delivery with the new values if they are not empty
            existingDelivery.setAddress(delivery.getAddress() != null && !delivery.getAddress().isEmpty() ? delivery.getAddress() : existingDelivery.getAddress());
            existingDelivery.setPrice(delivery.getPrice() != null && !delivery.getPrice().isEmpty() ? delivery.getPrice() : existingDelivery.getPrice());
            existingDelivery.setSize(delivery.getSize() != null && !delivery.getSize().isEmpty() ? delivery.getSize() : existingDelivery.getSize());
            existingDelivery.setDescription(delivery.getDescription() != null && !delivery.getDescription().isEmpty() ? delivery.getDescription() : existingDelivery.getDescription());

            // Save the updated delivery
            deliveryRepository.save(existingDelivery);
        }
    }

    /**
     * Method to delete a delivery
     * @param id id of the delivery
     */
    public void deleteDelivery(long id){
        deliveryRepository.deleteById(id);
    }
}
