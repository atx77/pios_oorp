package hr.tvz.diplomski.pios_oorp.service.impl;

import hr.tvz.diplomski.pios_oorp.domain.Cart;
import hr.tvz.diplomski.pios_oorp.domain.CartItem;
import hr.tvz.diplomski.pios_oorp.domain.Product;
import hr.tvz.diplomski.pios_oorp.domain.User;
import hr.tvz.diplomski.pios_oorp.repository.CartItemRepository;
import hr.tvz.diplomski.pios_oorp.repository.CartRepository;
import hr.tvz.diplomski.pios_oorp.service.CartService;
import hr.tvz.diplomski.pios_oorp.service.ProductService;
import hr.tvz.diplomski.pios_oorp.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    private ProductService productService;

    @Resource
    private UserService userService;

    @Resource
    private CartRepository cartRepository;

    @Resource
    private CartItemRepository cartItemRepository;

    @Transactional
    @Override
    public Product addProductToCart(Long productId, Integer quantity) {
        if (quantity == null || quantity < 1) {
            throw new IllegalArgumentException("Quantity must be greater than 1!");
        }
        Product product = productService.getProductForId(productId)
                .orElseThrow(() -> new IllegalArgumentException(MessageFormat.format("Product with id {0} does not exist!", productId)));
        User user = userService.getLoggedUser();
        Cart cart = user.getCart();
        removeInactiveProductsFromCart(cart);

        CartItem cartItem = null;
        Optional<CartItem> cartItemOptional = cart.getItems().stream()
                .filter(ci -> ci.getProduct() != null && productId.equals(ci.getProduct().getId()))
                .findFirst();

        if (cartItemOptional.isPresent()) {
            cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
        }
        cartItemRepository.save(cartItem);

        cart.getItems().add(cartItem);
        cart.setTotalPrice(calculateCartTotalPrice(cart));
        cartRepository.save(cart);
        return product;
    }

    @Override
    public BigDecimal calculateCartTotalPrice(Cart cart) {
        return cart.getItems().stream()
                .map(cartItem -> {
                    Product product = cartItem.getProduct();
                    BigDecimal productPrice = product.getActionPrice() != null ? product.getActionPrice() : product.getRegularPrice();
                    return productPrice.multiply(BigDecimal.valueOf(cartItem.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void recalculateCartTotalPrice() {
        Cart cart = userService.getLoggedUser().getCart();
        removeInactiveProductsFromCart(cart);
        cart.setTotalPrice(calculateCartTotalPrice(cart));
        cartRepository.save(cart);
    }

    @Override
    public Product removeProductFromCart(Long productId) {
        Product product = productService.getProductForId(productId)
                .orElseThrow(() -> new IllegalArgumentException(MessageFormat.format("Product with id {0} does not exist!", productId)));
        for (CartItem cartItem : userService.getLoggedUser().getCart().getItems()) {
            if (product.equals(cartItem.getProduct())) {
                cartItem.setCart(null);
                cartItemRepository.save(cartItem);
            }
        }
        recalculateCartTotalPrice();
        return product;
    }

    @Override
    public Product changeProductQuantityInCart(Long productId, Integer quantity) {
        if (quantity == null || quantity < 1) {
            return removeProductFromCart(productId);
        }

        Product product = productService.getProductForId(productId)
                .orElseThrow(() -> new IllegalArgumentException(MessageFormat.format("Product with id {0} does not exist!", productId)));
        for (CartItem cartItem : userService.getLoggedUser().getCart().getItems()) {
            if (product.equals(cartItem.getProduct())) {
                cartItem.setQuantity(quantity);
                cartItemRepository.save(cartItem);
            }
        }
        recalculateCartTotalPrice();
        return product;
    }

    private void removeInactiveProductsFromCart(Cart cart) {
        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getProduct() != null && !cartItem.getProduct().isActive()) {
                cartItem.setCart(null);
                cartItemRepository.save(cartItem);
            }
        }
    }
}
