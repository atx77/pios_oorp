package hr.tvz.diplomski.pios_oorp.service;

import hr.tvz.diplomski.pios_oorp.domain.Cart;
import hr.tvz.diplomski.pios_oorp.domain.Product;

public interface CartService {
    /**
     * @param productId Id of {@link Product} to add to {@link Cart}
     * @param quantity quantity of {@link Product}
     * @return {@link Product} added to {@link Cart}
     */
    Product addProductToCart(Long productId, Integer quantity);

    /**
     * Recalculates total price of {@link Cart}
     */
    void recalculateCartTotalPrice();

    /**
     * Removes {@link Product} from {@link Cart} by id
     * @param productId Id of {@link Product} to remove from {@link Cart}
     * @return removed {@link Product}
     */
    Product removeProductFromCart(Long productId);

    /**
     * Changes quantity of {@link Product} in {@link Cart} of session {@link hr.tvz.diplomski.pios_oorp.domain.User}
     * @param productId Id of {@link Product} to change quantity in {@link Cart}
     * @param quantity quantity of {@link Product}
     * @return {@link Product} whose quantity has been changed
     */
    Product changeProductQuantityInCart(Long productId, Integer quantity);

    /**
     * Removes all {@link Product} from provided {@link Cart}
     * @param cart {@link Cart} to clear
     * @return cart
     */
    Cart clearCart(Cart cart);
}
