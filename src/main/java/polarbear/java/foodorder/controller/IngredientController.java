package polarbear.java.foodorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polarbear.java.foodorder.model.IngredientCategory;
import polarbear.java.foodorder.model.IngredientsItem;
import polarbear.java.foodorder.request.IngredientCategoryRequest;
import polarbear.java.foodorder.request.IngredientsItemRequest;
import polarbear.java.foodorder.service.IngredientService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/category/create")
    public ResponseEntity<IngredientCategory> createIngredientCategory(@RequestBody IngredientCategoryRequest req) throws Exception {
        IngredientCategory category = ingredientService.createIngredientCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PostMapping("/item/create")
    public ResponseEntity<IngredientsItem> createIngredientItem(@RequestBody IngredientsItemRequest req) throws Exception {
        IngredientsItem item = ingredientService.createIngredientsItem(req.getRestaurantId(), req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/item/update-stoke/{id}")
    public ResponseEntity<IngredientsItem> updateIngredientItemStock(@PathVariable Long id) throws Exception {
        IngredientsItem item = ingredientService.updateStoke(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/category-by-restaurant/{id}")
    public ResponseEntity<List<IngredientCategory>> findIngredientCategoryByRestaurantId(@PathVariable Long id) throws Exception {
        List<IngredientCategory> categories = ingredientService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/item-by-restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> findIngredientsItemByRestaurantId(@PathVariable Long id) throws Exception {
        List<IngredientsItem> items = ingredientService.findRestaurantIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
