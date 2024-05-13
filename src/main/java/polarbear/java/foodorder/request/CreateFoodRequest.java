package polarbear.java.foodorder.request;

import lombok.Data;
import polarbear.java.foodorder.model.Category;
import polarbear.java.foodorder.model.IngredientsItem;

import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;
    private Category category;
    private List<String> images;
    private Long restaurantId;
    private boolean vegetarian;
    private boolean isSeasonal;
    private List<IngredientsItem> ingredients;

}
