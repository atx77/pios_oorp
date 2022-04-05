package hr.tvz.diplomski.pios_oorp.service.impl;

import hr.tvz.diplomski.pios_oorp.domain.*;
import hr.tvz.diplomski.pios_oorp.enumeration.CountryEnum;
import hr.tvz.diplomski.pios_oorp.form.CheckoutForm;
import hr.tvz.diplomski.pios_oorp.repository.OrderItemRepository;
import hr.tvz.diplomski.pios_oorp.repository.OrderRepository;
import hr.tvz.diplomski.pios_oorp.service.AddressService;
import hr.tvz.diplomski.pios_oorp.service.CartService;
import hr.tvz.diplomski.pios_oorp.service.OrderService;
import hr.tvz.diplomski.pios_oorp.service.UserService;
import hr.tvz.diplomski.pios_oorp.util.PriceUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    private final PriceUtils priceUtils = new PriceUtils();

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private OrderItemRepository orderItemRepository;

    @Resource
    private UserService userService;

    @Resource
    private AddressService addressService;

    @Resource
    private CartService cartService;

    @Transactional
    @Override
    public Order createOrderForCurrentUser(CheckoutForm form) {
        User user = userService.getLoggedUser();
        Cart cart = user.getCart();
        Assert.notNull(user);
        Assert.notNull(cart);

        Order order = new Order();
        order.setUser(user);
        order.setShippingAddress(addressService.createNewAddressForUser(form.getStreet(), form.getCity(),
                form.getPostcode(), CountryEnum.CROATIA, user));
        order.setCreationDate(new Date());
        order.setDeliveryMode(form.getDeliveryMode());
        order.setPaymentMethod(form.getPaymentMethod());

        order.setItems(new ArrayList<>());
        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setSellingPrice(cartItem.getProduct().getActionPrice() != null ? cartItem.getProduct().getActionPrice() : cartItem.getProduct().getRegularPrice());
            orderItem.setRegularPrice(cartItem.getProduct().getRegularPrice());
            orderItem.setTotalPrice(calculateTotalPriceForProduct(cartItem.getProduct(), cartItem.getQuantity()));
            orderItem.setDiscountPercentage(cartItem.getProduct().getDiscountPercentage());
            orderItemRepository.save(orderItem);
            order.getItems().add(orderItem);
        }
        order.setTotalPrice(order.getItems().stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        orderRepository.save(order);

        order.setCode(StringUtils.leftPad(order.getId().toString(), 6, "0"));
        orderRepository.save(order);

        cartService.clearCart(cart);
        return order;
    }

    @Override
    public Order getByCode(String orderCode) {
        return orderRepository.findByCodeEquals(orderCode)
                .orElseThrow(() -> new IllegalArgumentException("No order with code " + orderCode));
    }

    private BigDecimal calculateTotalPriceForProduct(Product product, Integer quantity) {
        BigDecimal productPrice = product.getActionPrice() != null ? product.getActionPrice() : product.getRegularPrice();
        return productPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
