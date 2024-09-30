
import edu.eci.arep.models.Delivery;
import edu.eci.arep.repositorys.DeliveryRepository;
import edu.eci.arep.services.DeliveryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for DeliveryService
 * @author Andrés Arias
 */
class DeliveryTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    @InjectMocks
    private DeliveryService deliveryService;

    private Delivery delivery;

    /**
     * Sets up the mocks and the Delivery object
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        delivery = new Delivery("123 Main St", "10.00", "Large", "Food delivery");
    }
    /**
     * Tests the getAllDeliveries method
     */
    @Test
    void testGetAllDeliveries() {
        when(deliveryRepository.findAll()).thenReturn(Arrays.asList(delivery));

        var deliveries = deliveryService.getAllDeliveries();

        assertEquals(1, deliveries.size());
        assertEquals(delivery, deliveries.get(0));
    }

    /**
     * Tests the getDeliveryById method
     */
    @Test
    void testGetDeliveryById() {
        when(deliveryRepository.findById(1L)).thenReturn(Optional.of(delivery));

        Delivery foundDelivery = deliveryService.getDeliveryById(1L);

        assertNotNull(foundDelivery);
        assertEquals(delivery.getAddress(), foundDelivery.getAddress());
    }

    /**
     *  Tests the addDelivery method
     */
    @Test
    void testAddDelivery() {
        deliveryService.addDelivery(delivery);

        verify(deliveryRepository, times(1)).save(delivery);
    }

    /**
     * Tests the updateDelivery method
     */
    @Test
    public void testUpdateDelivery() {

        when(deliveryRepository.findById(1L)).thenReturn(Optional.of(delivery));

        Delivery updatedDelivery = new Delivery("456 Elm St", "15.00", "Medium", "Food delivery");
        deliveryService.updateDelivery(1L, updatedDelivery);
        assertEquals("123 Main St", delivery.getAddress());
        assertEquals("10.00", delivery.getPrice());
        assertEquals("Large", delivery.getSize());
        assertEquals("Food delivery", delivery.getDescription());
    }


    /**
     * Tests the deleteDelivery method
     */
    @Test
    void testDeleteDelivery() {
        deliveryService.deleteDelivery(1L);

        verify(deliveryRepository, times(1)).deleteById(1L);
    }
}

