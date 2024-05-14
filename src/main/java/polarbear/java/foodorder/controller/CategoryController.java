package polarbear.java.foodorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polarbear.java.foodorder.model.Category;
import polarbear.java.foodorder.model.User;
import polarbear.java.foodorder.service.CategoryService;
import polarbear.java.foodorder.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category,
        @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Category createdCategory = categoryService.createCategory(category.getName(), user.getId());
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant")
    public ResponseEntity<List<Category>> findCategoryByRestaurantId(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Category> categories = categoryService.findCategoryByRestaurantId(user.getId());
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
