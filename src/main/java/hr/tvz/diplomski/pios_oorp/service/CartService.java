package hr.tvz.diplomski.pios_oorp.service;

import hr.tvz.diplomski.pios_oorp.domain.Cart;
import hr.tvz.diplomski.pios_oorp.domain.Product;

public interface CartService {
    Product addProductToCart(Long productId, Integer quantity);
    void recalculateCartTotalPrice();
    Product removeProductFromCart(Long productId);
    Product changeProductQuantityInCart(Long productId, Integer quantity);
    Cart clearCart(Cart cart);
}
