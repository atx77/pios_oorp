package hr.tvz.diplomski.pios_oorp.service;

import hr.tvz.diplomski.pios_oorp.domain.Order;
import hr.tvz.diplomski.pios_oorp.domain.User;
import hr.tvz.diplomski.pios_oorp.form.CheckoutForm;

import java.util.List;

public interface OrderService {
    Order createOrderForCurrentUser(CheckoutForm form);
    Order getByCode(String orderCode);
    List<Order> getOrdersForCustomer(User user);
    List<Order> getAllOrders();
}
