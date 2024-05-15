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

import java.util.Optional;

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

        CartItem newCartItem = new CartItem();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setIngredients(req.getIngredients());
        newCartItem.setTotalPrice(req.getQuantity() * food.getPrice());

        CartItem savedCartItem = cartItemRepository.save(newCartItem);
        cart.getItems().add(savedCartItem);
        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()) {
            throw new Exception("Cart Item is not found");
        }
        CartItem item = cartItemOptional.get();
        item.setQuantity(quantity);
        item.setTotalPrice(item.getFood().getPrice() * quantity);

        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findCardByCustomerId(user.getId());
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()) {
            throw new Exception("Cart Item is not found");
        }
        CartItem item = cartItemOptional.get();
        cart.getItems().remove(item);
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotal(Cart cart) throws Exception {
        long total = 0L;
        for(CartItem cartItem : cart.getItems()) {
            total += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }

        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> cart = cartRepository.findById(id);
        if(cart.isEmpty()) {
            throw new Exception("Cart is not found");
        }
        return cart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        return cartRepository.findCardByCustomerId(userId);
    }

    @Override
    public Cart clearCart(String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = findCartByUserId(user.getId());
        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}
