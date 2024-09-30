package edu.eci.arep.repositorys;

import java.util.List;
import java.util.Optional;

import edu.eci.arep.models.Delivery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for Delivery
 * @author: Andrés Arias
 */
@Repository
public interface DeliveryRepository extends CrudRepository<Delivery, Long> {

    /**
     * Method to get all deliveries
     * @return List of deliveries
     */
    List<Delivery> findAll();

    /**
     * Method to get a delivery by id
     * @param id id of the delivery
     * @return  Delivery
     */
    Optional<Delivery> findById(long id);

}