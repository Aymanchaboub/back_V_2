package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Cart;
import com.example.demo.models.CartItem;
import com.example.demo.models.Produit;
import com.example.demo.models.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProduitRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.cartItemRepositry;

@Service
public class CartService {
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private cartItemRepositry cartItemRepositry;
    
    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private UserRepository userRepository;
    public Optional<Cart> getCartById(Long cartId) {
        logger.info("Retrieving cart with ID {}", cartId);
        return cartRepository.findById(cartId);
    }
    
    public List<CartItem> getCartItemsByCart(Cart cart) {
        logger.info("Retrieving cart items for cart with ID {}", cart.getId());
        return cart.getCartItems();
    }
    public Long getCartIdByCartItem(Long cartItemId) {
        Optional<CartItem> optionalCartItem = cartItemRepositry.findById(cartItemId);
        if (optionalCartItem.isPresent()) {
            return optionalCartItem.get().getCart().getId();
        } else {
            throw new IllegalArgumentException("Invalid cart item id: " + cartItemId);
        }
    }


    public Cart getCartByUserId(Long userId) {
        logger.info("Retrieving cart for user with ID {}", userId);
        return cartRepository.findByUserId(userId).orElse(null);
    }
    public Long getCartIdByUserId(Long userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        return cart.map(Cart::getId).orElse(null);
    }


    public void addProductToCart(Long userId, Long productId, int quantity) {
        logger.info("Adding product with ID {} and quantity {} to cart for user with ID {}", productId, quantity, userId);
        Cart cart = getCartByUserId(userId);
        if (cart == null) {
            logger.info("Creating new cart for user with ID {}", userId);
            cart = new Cart();
            User user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                logger.warn("User with ID {} not found", userId);
                return;
            }
            cart.setUser(user);
        }
        Produit product = produitRepository.findById(productId).orElse(null);
        if (product != null) {
            CartItem cartItem = cart.findCartItemByProduitId(productId);
            if (cartItem != null) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
            } else {
                cart.addCartItem(new CartItem(product, quantity));
            }
            cartRepository.save(cart);
            logger.info("Product with ID {} added to cart for user with ID {}", productId, userId);
        } else {
            logger.warn("Product with ID {} not found", productId);
        }
    }

    public void removeProductFromCart(Long userId, Long productId, int quantity) {
        logger.info("Removing product with ID {} and quantity {} from cart for user with ID {}", productId, quantity, userId);
        Cart cart = getCartByUserId(userId);
        if (cart == null) {
            logger.warn("Cart not found for user with ID {}", userId);
            return;
        }
        CartItem cartItem = cart.findCartItemByProduitId(productId);
        if (cartItem != null) {
            if (cartItem.getQuantity() > quantity) {
                cartItem.setQuantity(cartItem.getQuantity() - quantity);
            } else {
                cart.removeCartItem(cartItem);
            }
            cartRepository.save(cart);
            logger.info("Product with ID {} removed from cart for user with ID {}", productId, userId);
        } else {
            logger.warn("Product with ID {} not found in cart for user with ID {}", productId, userId);
        }
    }

    public double getTotal(Long userId) {
        logger.info("Calculating total for cart of user with ID {}", userId);
        Cart cart = getCartByUserId(userId);
        if (cart == null) {
            logger.warn("Cart not found for user with ID {}", userId);
            return 0;
        }
        return cart.getTotal();
    }
    public List<CartItem> getCartItems(Long cartId) {
        return cartItemRepositry.findByCartId(cartId);
    }

    

    
}
