package polarbear.java.foodorder.request;

import lombok.Data;
import polarbear.java.foodorder.model.Address;
import polarbear.java.foodorder.model.ContactInformation;

import java.util.List;

@Data
public class CreateRestaurantRequest {
    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;
}
