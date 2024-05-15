package polarbear.java.foodorder.request;

import lombok.Data;
import polarbear.java.foodorder.model.Address;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Address deliveryAddress;
}
