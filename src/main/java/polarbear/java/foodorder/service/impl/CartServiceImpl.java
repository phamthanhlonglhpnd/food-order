package polarbear.java.foodorder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import polarbear.java.foodorder.model.Cart;
import polarbear.java.foodorder.model.CartItem;
import polarbear.java.foodorder.model.Food;
import polarbear.java.foodorder.model.User;
import polarbear.java.foodorder.repository.CartItemRepository;
import polarbear.java.foodorder.repository.CartRepository;
import polarbear.java.foodorder.request.AddCartItemRequest;
import polarbear.java.foodorder.service.CartService;
import polarbear.java.foodorder.service.FoodService;
import polarbear.java.foodorder.service.UserService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.findFoodById(req.getFoodId());
        Cart cart = cartRepository.findCardByCustomerId(user.getId());

        for(CartItem cartItem : cart.getItems()) {
            if(cartItem.getFood().equals(food)) {
                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }
        return null;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        return null;
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        return null;
    }

    @Override
    public Long calculateCartTotal(Cart cart) throws Exception {
        return null;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        return null;
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        return null;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        return null;
    }
}
