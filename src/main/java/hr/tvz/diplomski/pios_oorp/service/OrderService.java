package hr.tvz.diplomski.pios_oorp.service;

import hr.tvz.diplomski.pios_oorp.domain.Order;
import hr.tvz.diplomski.pios_oorp.form.CheckoutForm;

public interface OrderService {
    Order createOrderForCurrentUser(CheckoutForm form);
}
