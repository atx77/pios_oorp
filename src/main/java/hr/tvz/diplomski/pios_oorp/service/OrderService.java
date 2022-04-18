package hr.tvz.diplomski.pios_oorp.service;

import hr.tvz.diplomski.pios_oorp.domain.Order;
import hr.tvz.diplomski.pios_oorp.domain.User;
import hr.tvz.diplomski.pios_oorp.form.CheckoutForm;

import java.util.List;

public interface OrderService {
    /**
     * Creates new {@link Order} for session {@link User} with details from form
     * @param form Form with payment details
     * @return created {@link Order}
     */
    Order createOrderForCurrentUser(CheckoutForm form);

    /**
     * Fetches {@link Order} by code
     * @param orderCode code of {@link Order} to fetch
     * @return fetched {@link Order}
     */
    Order getByCode(String orderCode);

    /**
     * Fetches liust of all {@link Order} for provided {@link User}
     * @param user {@link User} to fetch list of all {@link Order} for
     * @return list of all {@link Order} of provided {@link User}
     */
    List<Order> getOrdersForCustomer(User user);

    /**
     * @return list of all {@link Order} on site
     */
    List<Order> getAllOrders();
}
