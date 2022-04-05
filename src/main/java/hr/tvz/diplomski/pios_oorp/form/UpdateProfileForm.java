package hr.tvz.diplomski.pios_oorp.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateProfileForm {

    @NotBlank
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
}
