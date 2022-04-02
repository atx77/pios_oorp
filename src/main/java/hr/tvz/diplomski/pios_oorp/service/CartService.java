package hr.tvz.diplomski.pios_oorp.service;

import hr.tvz.diplomski.pios_oorp.domain.Cart;

import java.math.BigDecimal;

public interface CartService {
    void addProductToCart(Long productId, Integer quantity);
    BigDecimal calculateCartTotalPrice(Cart cart);
}
