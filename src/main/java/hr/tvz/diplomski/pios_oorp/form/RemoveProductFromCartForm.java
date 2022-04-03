package hr.tvz.diplomski.pios_oorp.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RemoveProductFromCartForm {
    @NotNull
    private Long productId;
}
