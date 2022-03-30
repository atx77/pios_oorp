package hr.tvz.diplomski.pios_oorp.form.admin;

import lombok.Data;

@Data
public class AddCategoryAdminForm {
    private Long parentCategoryId;
    private String categoryName;
}
