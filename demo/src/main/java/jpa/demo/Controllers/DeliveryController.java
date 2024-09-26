package jpa.demo.Controllers;

import jpa.demo.Model.Delivery;
import jpa.demo.Repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class DeliveryController {

    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryController(DeliveryRepository deliveryRepository){
        this.deliveryRepository = deliveryRepository;
    }
    @GetMapping("/delivers")
    public List<Delivery> getDeliveries(){
       return deliveryRepository.getAllDeliveries();

    }
}
