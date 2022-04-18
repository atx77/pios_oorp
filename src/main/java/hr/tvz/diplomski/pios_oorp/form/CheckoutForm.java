package hr.tvz.diplomski.pios_oorp.form;

import hr.tvz.diplomski.pios_oorp.enumeration.DeliveryMode;
import hr.tvz.diplomski.pios_oorp.enumeration.PaymentMethod;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CheckoutForm {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String street;

    @NotNull
    private String city;

    @NotNull
    private String postcode;

    @NotNull
    private DeliveryMode deliveryMode;

    @NotNull
    private PaymentMethod paymentMethod;
}
