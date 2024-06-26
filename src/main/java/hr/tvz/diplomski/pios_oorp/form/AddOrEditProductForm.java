package hr.tvz.diplomski.pios_oorp.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class AddOrEditProductForm {

    @NotNull
    private Long categoryId;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String summary;

    @NotNull
    private BigDecimal regularPrice;

    @NotNull
    private BigDecimal actionPrice;

    @NotNull
    private String imageUrl;

    @NotNull
    private String brand;

    private boolean active;

    private Long productId;
}
