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
    public void addProductToCart(Long productId, Integer quantity) {
        Product product = productService.getProductForId(productId);
        if (product == null) {
            throw new IllegalArgumentException(MessageFormat.format("Product with id {0} does not exist!", productId));
        }
        User user = userService.getLoggedUser();
        Cart cart = user.getCart();

        //TODO check if cartItem with same product already exists, increase quantity
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);

        cart.getItems().add(cartItem);
        cart.setTotalPrice(calculateCartTotalPrice(cart));
        cartRepository.save(cart);
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
}
