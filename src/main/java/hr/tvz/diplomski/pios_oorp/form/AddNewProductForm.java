package hr.tvz.diplomski.pios_oorp.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class AddNewProductForm {

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
}
