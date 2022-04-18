package hr.tvz.diplomski.pios_oorp.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddToCartForm {

    @NotNull
    private Long productId;

    @NotNull
    private Integer quantity;
}
