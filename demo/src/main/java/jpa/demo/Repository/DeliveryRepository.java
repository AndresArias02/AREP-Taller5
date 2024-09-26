package jpa.demo.Repository;

import java.util.List;

import jpa.demo.Model.Delivery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
public interface DeliveryRepository extends CrudRepository<Delivery, Long> {
    List<Delivery> getAllDeliveries();
    Delivery findById(long id);
}